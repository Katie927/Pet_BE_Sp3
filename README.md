# DATN Backend (Bej) - Hệ Thống Quản Lý Cửa hàng Bán & Sửa chữa điện thoại

##  Tổng Quan Hệ Thống

**Bej** là một ứng dụng backend được xây dựng trên **Spring Boot 3.4.3** và **Java 21**, cung cấp các chức năng quản lý sản phẩm, đơn hàng cho một nền tảng bán hàng trực tuyến.

### Công Nghệ Chính
- **Framework**: Spring Boot 3.4.3
- **Database**: MySQL
- **Authentication**: OAuth2 (JWT)
- **ORM**: JPA/Hibernate
- **Mapping**: MapStruct
- **Build**: Maven

---

##  Cấu Trúc Project

```
src/main/java/com/DATN/Bej/
├── config/              # Cấu hình ứng dụng (Security, CORS, JWT, VNPay)
├── controller/          # REST Controllers (API endpoints)
│   ├── identity/        # Authentication & Authorization
│   ├── manage/          # Admin management APIs
│   └── cart/            # Shopping cart operations
├── service/             # Business logic layer
│   ├── identity/        # User, Role, Permission management
│   ├── guest/           # Guest user services
│   └── work/            # Work schedule management
├── entity/              # JPA entities (database models)
│   ├── identity/        # User, Role, Permission, Token
│   ├── product/         # Product, Category, Variant, Attribute
│   ├── cart/            # Orders, OrderItems, CartItems
│   └── work/            # WorkSchedule, Task, Shift
├── repository/          # Data access layer (Spring Data JPA)
├── mapper/              # MapStruct DTOs mapping
├── dto/                 # Data Transfer Objects
│   ├── request/         # Request DTOs
│   └── response/        # Response DTOs
├── exception/           # Custom exceptions & global error handler
├── enums/               # Enumeration types (Role, Status)
└── BejApplication.java  # Entry point
```

---

##  Cấu Trúc Cơ Sở Dữ Liệu

### 1. **Identity Module** (Quản lý danh tính)
| Entity | Mô Tả | Quan Hệ |
|--------|-------|--------|
| **User** | Người dùng hệ thống | 1:N Orders, M:N Roles |
| **Role** | Vai trò (ADMIN, USER) | M:N Users, 1:N Permissions |
| **Permission** | Quyền hạn chi tiết | N:1 Role |
| **InvalidatedToken** | Token đã vô hiệu hóa (logout) | - |

#### Luồng Xác Thực (Authentication Flow)
```
1. POST /auth/log-in (phone + password)
   ↓
2. Spring Security xác thực → tạo JWT token
   ↓
3. JWT token được lưu client
   ↓
4. Các request sau gửi kèm Authorization header
   ↓
5. CustomJwtDecoder xác thực token
   ↓
6. SecurityContext được thiết lập với User và Roles
   ↓
7. POST /auth/logout → thêm token vào InvalidatedToken table
```

### 2. **Product Module** (Quản lý sản phẩm)
| Entity | Mô Tả | Quan Hệ |
|--------|-------|---------|
| **Category** | Danh mục sản phẩm | 1:N Products |
| **Product** | Sản phẩm chính | 1:N Variants, 1:N Images, N:1 Category |
| **ProductVariant** | Phiên bản sản phẩm (khác nhau về thuộc tính) | 1:N Attributes, N:1 Product |
| **ProductAttribute** | Thuộc tính cụ thể (size, màu, SKU, giá) | N:1 Variant, 1:N OrderItems |
| **ProductImage** | Hình ảnh sản phẩm | N:1 Product |
| **Brand** | Thương hiệu | - |
| **Supply** | Cung cấp nguyên liệu | - |

#### Cấu Trúc Sản Phẩm Hierarchical
```
Product
├── name: "Áo T-shirt"
├── category: "Quần áo"
├── image: "main-image.jpg"
├── variants: [
│   {
│       ProductVariant (Phiên bản 1: Đen)
│       ├── name: "Black Size M"
│       ├── sku: "TSHIRT-BLK-M"
│       ├── attributes: [
│       │   ProductAttribute {price: 200k, quantity: 50}
│       │   ProductAttribute {price: 250k, quantity: 30}
│       │ ]
│   },
│   {
│       ProductVariant (Phiên bản 2: Trắng)
│       ├── name: "White Size M"
│   }
└─ ]
└── introImages: [Ảnh 1, Ảnh 2, ...]
```

### 3. **Cart & Order Module** (Giỏ hàng & đơn hàng)
| Entity | Mô Tả | Quan Hệ |
|--------|-------|---------|
| **CartItem** | Mục trong giỏ hàng | N:1 User, N:1 ProductAttribute |
| **Orders** | Đơn hàng | N:1 User, 1:N OrderItems |
| **OrderItem** | Chi tiết từng sản phẩm trong đơn | N:1 Orders, N:1 ProductAttribute |

#### Luồng Mua Hàng
```
POST /cart/add/{attId}
├─ Thêm ProductAttribute vào CartItem
└─ Lưu vào DB

GET /cart/view
├─ Lấy tất cả CartItem của user hiện tại
└─ Trả về danh sách sản phẩm

POST /cart/place-order (OrderRequest)
├─ Kiểm tra CartItems
├─ Tạo Orders record
├─ Tạo OrderItems từ CartItems
├─ Tính totalPrice
├─ Xóa CartItems
└─ Trả về OrderDetailsResponse

GET /cart/my-order
└─ Lấy tất cả đơn hàng của user

GET /manage/orders/get-all
└─ Admin xem tất cả đơn (admin only)
```

### 4. **Work Schedule Module** (Lịch làm việc)
| Entity | Mô Tả | Quan Hệ |
|--------|-------|---------|
| **WorkSchedule** | Lịch công việc | - |
| **Shift** | Ca làm việc | 1:N Tasks |
| **Task** | Công việc chi tiết | N:1 Shift |

### 5. **Notification Module** (Thông báo)
| Entity | Mô Tả | Quan Hệ |
|--------|-------|---------|
| **Notification** | Thông báo hệ thống | - |

---

##  Các Chức Năng Chính & Luồng Logic

### 1. **Xác Thực & Phân Quyền** (Identity Management)

#### Endpoint: `/auth`
```
POST /auth/log-in
├─ Input: {phoneNumber, password}
├─ Xử lý:
│  ├─ Tìm User theo phoneNumber
│  ├─ Kiểm tra password (passwordEncoder)
│  ├─ Tạo JWT token (CustomJwtDecoder)
│  └─ Load Roles & Permissions
└─ Output: {token, expiresIn}

POST /auth/introspect
├─ Input: {token}
├─ Xử lý: Kiểm tra token có hợp lệ không
└─ Output: {valid: boolean}

POST /auth/logout
├─ Input: JWT token in header
├─ Xử lý: Thêm token vào InvalidatedToken (blacklist)
└─ Output: Success message
```

#### Permission Model
```
User
├─ roles: [ADMIN, USER, ...] (M:N)
└─ Role
   └─ permissions: [READ_PRODUCT, WRITE_PRODUCT, ...] (1:N)
```

#### Security Flow
```
1. Request đến secured endpoint
   ↓
2. JwtAuthenticationFilter kiểm tra Authorization header
   ↓
3. CustomJwtDecoder validate JWT signature & expiration
   ↓
4. Kiểm tra token không trong InvalidatedToken table
   ↓
5. Thiết lập SecurityContext với Authentication object
   ↓
6. @PreAuthorize hoặc SecurityContext.getContext() kiểm tra quyền
   ↓
7. Response trả về
```

---

### 2️. **Quản Lý Sản Phẩm** (Product Management)

#### Controller Endpoints

**Công Khai (Public):**
```
GET /home
└─ Lấy danh sách sản phẩm đang bán (status=1)
   ├─ Mapper: ProductService.getProducts() 
   └─ Response: [ProductListResponse]

GET /home/product/{productId}
└─ Chi tiết sản phẩm
   ├─ Gồm: variants, attributes, images, category
   └─ Response: ProductDetailRes
```

**Admin Only (/manage/product):**
```
POST /manage/product/add
├─ Input: ProductRequest (@ModelAttribute)
│  ├─ name, description, sku
│  ├─ categoryId
│  ├─ image (file)
│  └─ variants: [
│       {name, sku, attributes: [{size, price, quantity}]}
│     ]
├─ Xử lý:
│  ├─ Lưu Product
│  ├─ Lưu ProductImages
│  ├─ Lưu ProductVariants & ProductAttributes
│  └─ Sinh UUID cho mỗi entity
└─ Response: ProductResponse

PUT /manage/product/update/{productId}
├─ Cập nhật Product, Variants, Attributes
└─ Response: ProductResponse

GET /manage/product/list
├─ Danh sách TẤT CẢ sản phẩm (kể cả inactive)
└─ Response: [ProductListResponse]

GET /manage/product/{productId}
└─ Chi tiết sản phẩm (admin view)

DELETE /manage/product/delete/{productId}
├─ Xóa Product (cascade xóa Variants, Attributes, Images)
└─ Response: Void

PUT /manage/product/inactive/{productId}
├─ Đánh dấu sản phẩm ngừng kinh doanh (status = 0)
└─ Response: Void
```

#### Product Service Logic
```
addNewProduct(ProductRequest)
├─ Validate: categoryId tồn tại
├─ Tạo Product entity
├─ Lặp qua request.variants:
│  ├─ Tạo ProductVariant
│  ├─ Lặp qua variant.attributes:
│  │  ├─ Tạo ProductAttribute (chứa SKU, price, quantity)
│  │  └─ Set finalPrice = basePrice * (1 - discount%)
│  └─ Lưu Variant + Attributes
├─ Tạo ProductImages từ request.images
└─ Lưu tất cả (CascadeType.ALL)

getAllProducts() / getProducts()
├─ Nếu admin: ProductRepository.findAllByOrderByCreateDateDesc()
├─ Nếu guest: ProductRepository.findByStatusOrderByCreateDateDesc(1)
├─ Map sang ProductListResponse (chứa variant summary)
└─ Return List

getProductDetails(productId)
├─ Tìm Product theo ID
├─ Eager load: category, variants, attributes, images
└─ Map sang ProductDetailRes (đầy đủ thông tin)
```

---

### 3️. **Giỏ Hàng & Đơn Hàng** (Cart & Orders)

#### Controller Endpoints (/cart)

```
POST /cart/add/{attId}
├─ Input: attId (ProductAttribute ID)
├─ Xử lý:
│  ├─ Lấy current user từ SecurityContext (phoneNumber)
│  ├─ Tìm ProductAttribute theo ID
│  ├─ Kiểm tra CartItem đã tồn tại không
│  ├─ Nếu có: tăng quantity
│  ├─ Nếu không: tạo CartItem mới (quantity=1)
│  └─ Lưu vào CartItem table
└─ Response: CartItemResponse

GET /cart/view
├─ Lấy current user
├─ Query: CartItemRepository.findAllByUserId(userId)
├─ Map sang CartItemResponse (kèm product info)
└─ Response: [CartItemResponse]

POST /cart/place-order (OrderRequest)
├─ Input: {phoneNumber, email, address, description, totalPrice, items}
├─ Xử lý:
│  ├─ Lấy current user
│  ├─ Tạo Orders entity
│  ├─ Lặp request.items:
│  │  ├─ Tìm ProductAttribute
│  │  ├─ Tạo OrderItem
│  │  ├─ Set giá = ProductAttribute.finalPrice
│  │  └─ Thêm vào Orders.orderItems
│  ├─ Tính tổng totalPrice
│  ├─ Set orderAt = now(), updatedAt = now()
│  ├─ Lưu Orders (cascade lưu OrderItems)
│  └─ Xóa CartItems của user
├─ Business Logic:
│  └─ Có thể integrate Payment Gateway (VNPay) tại đây
└─ Response: OrderDetailsResponse

GET /cart/my-order
├─ Lấy current user
├─ Query: OrderRepository.findAllByUserId(userId)
├─ Sắp xếp: thời gian mới nhất trước
└─ Response: [OrderDetailsResponse]
```

#### Admin Orders Management (/manage/orders)
```
GET /manage/orders/get-all
├─ Query tất cả Orders
├─ Sắp xếp: OrderAt descending
└─ Response: [OrdersResponse]

GET /manage/orders/details/{orderId}
├─ Chi tiết đơn hàng
├─ Gồm: user info, items, total price, status
└─ Response: OrderDetailsResponse
```

#### Order Status & Type
```
Orders table fields:
├─ status: 0=pending, 1=processing, 2=shipped, 3=delivered, 4=cancelled
├─ type: 0=online, 1=offline, 2=subscription
└─ totalPrice: tổng tiền (calculated)
```

---

### 4. **Danh Mục Sản Phẩm** (Categories)

#### Category Management (/admin/category)
```
GET /admin/category
├─ Lấy tất cả categories
└─ Response: [CategoryResponse]

POST /admin/category/add
├─ Input: CategoryRequest {name, description, image}
├─ Lưu Category entity
└─ Response: CategoryResponse
```

---

### 5. **Quản Lý Vai Trò & Quyền** (Roles & Permissions)

#### Role Management (/admin/roles)
```
POST /admin/roles
├─ Input: RoleRequest {name, description, permissions}
├─ Tạo Role & assign Permissions
└─ Response: RoleResponse

GET /admin/roles
├─ Lấy tất cả Roles
└─ Response: [RoleResponse]

DELETE /admin/roles/{roleName}
├─ Xóa Role
└─ Response: Success
```

#### Permission Model
```
Permissions (Examples):
├─ PRODUCT_VIEW
├─ PRODUCT_CREATE
├─ PRODUCT_UPDATE
├─ PRODUCT_DELETE
├─ ORDER_VIEW
├─ ORDER_MANAGE
├─ USER_MANAGE
└─ ROLE_MANAGE
```

---

### 4. Run Locally (Chạy local không dùng Docker)

#### Yêu cầu:
- Java 21
- Maven 3.9+
- MySQL 8.x (chạy local trên port 3306)
- RabbitMQ (chạy local)

#### Các bước:
```bash
# 1. Cấu hình database MySQL local
# Tạo database: new_bej_sp3
mysql -u root -p
CREATE DATABASE new_bej_sp3;

# 2. Cập nhật application.yaml với thông tin database local
# (sửa DB_HOST=localhost, DB_PORT=3306)

# 3. Tạo file .env với cấu hình local
DB_HOST=localhost
DB_PORT=3306
# ... (các biến khác)

# 4. Chạy ứng dụng
mvn clean spring-boot:run

# Hoặc build và chạy JAR
mvn clean package
java -jar target/Bej-0.0.1-SNAPSHOT.jar
```

---

---

##  Entity Relationships Diagram

```
User (1) ──── (N) Orders
  │ (M)        
  ├─── (N) CartItem
  └─── (N) Role
        │ (1)
        └─── (N) Permission

Product (1) ──── (N) ProductVariant
  │ (1)               │ (1)
  ├─── (N) Category   └─── (N) ProductAttribute ──── (N) OrderItem
  └─── (N) ProductImage
```

---

##  Security Features

1. **JWT Token Authentication**
   - CustomJwtDecoder: Token validation & signature verification
   - Expiration handling
   - Refresh token support

2. **Authorization**
   - Role-based access control (RBAC)
   - Permission-based access control (PBAC)
   - @PreAuthorize annotations

3. **Token Blacklist**
   - InvalidatedToken table tracks logged-out tokens
   - Prevent token reuse after logout

4. **CORS Configuration**
   - Allowed origins: http://localhost:5173
   - Allowed methods: GET, POST, PUT, DELETE, OPTIONS
   - Credentials: true

---

##  API Response Format

### Success Response
```json
{
  "result": { /* actual data */ },
  "code": 1000,
  "message": "Success"
}
```

### Error Response
```json
{
  "code": 1001,
  "message": "Error description",
  "result": null
}
```

### Error Codes
| Code | Meaning |
|------|---------|
| 1000 | Success |
| 1001 | Error |
| 1002 | Unauthorized |
| 1003 | Forbidden |
| 1004 | Not Found |

---

##  Testing Entities with CommandLineRunner

Run the application to see detected entities and their record counts:

```bash
mvn spring-boot:run
```

Check logs for:
```
Detected 17 JPA entities:
 - User (class com.DATN.Bej.entity.identity.User) -> count = 0
 - Product (class com.DATN.Bej.entity.product.Product) -> count = 0
 - Category (class com.DATN.Bej.entity.product.Category) -> count = 0
 - Orders (class com.DATN.Bej.entity.cart.Orders) -> count = 0
 ... (and more)
```

---

##  Key Business Flows

### Flow 1: Product Purchase
```
1. Customer logs in → JWT token
2. Browse products → GET /home
3. Add to cart → POST /cart/add/{attId}
4. View cart → GET /cart/view
5. Place order → POST /cart/place-order
6. Payment → VNPay integration
7. Order confirmation → Send notification
```

### Flow 2: Admin Product Management
```
1. Admin logs in with ADMIN role
2. Create category → POST /admin/category/add
3. Add product → POST /manage/product/add
4. Update product → PUT /manage/product/update/{id}
5. View all orders → GET /manage/orders/get-all
6. Send notifications → POST /api/notifications/broadcast
```

### Flow 3: User Authentication
```
1. POST /auth/log-in (phone, password)
   ↓
2. Verify credentials + load roles
   ↓
3. Generate JWT token
   ↓
4. Return token to client
   ↓
5. Client uses token in Authorization header
   ↓
6. Server validates token on each request
   ↓
7. POST /auth/logout → blacklist token
```

---

##  Technology Stack Summary

| Layer | Technology |
|-------|-----------|
| **Framework** | Spring Boot 3.4.3 |
| **Language** | Java 21 |
| **Database** | MySQL 8.x |
| **ORM** | JPA/Hibernate |
| **Security** | OAuth2 + JWT |
| **Build Tool** | Maven |
| **Mapping** | MapStruct 1.5.5 |
| **Logging** | SLF4J + Logback |
| **Message Queue** | RabbitMQ |
| **Containerization** | Docker |

---

##  Key Contact Points

- **Admin API Base**: `http://localhost:8080/bej3`
- **Database**: `localhost:3306/new_bej_sp3`
- **Frontend**: `http://localhost:5173`

---

##  Future Enhancements

1. **Inventory Management**: Track stock levels per ProductAttribute
2. **User Reviews & Ratings**: Add reviews system for products
3. **Discount & Coupon System**: Support promotional codes
4. **Order Status Tracking**: Real-time order status updates
5. **Analytics Dashboard**: Sales metrics and reports
6. **Email Notifications**: Confirmation emails for orders
7. **Wishlist Feature**: Save favorite products
8. **Recommendation Engine**: Suggest products based on purchase history

---

**Last Updated**: November 2025  
**Version**: 1.0.0  
**Status**: Active Development 

