package com.BEJ.Bej.service;

import com.BEJ.Bej.dto.request.productRequest.ProductRequest;
import com.BEJ.Bej.dto.response.productResponse.ProductResponse;
import com.BEJ.Bej.entity.product.Product;
import com.BEJ.Bej.entity.product.ProductAttribute;
import com.BEJ.Bej.entity.product.ProductImage;
import com.BEJ.Bej.exception.AppException;
import com.BEJ.Bej.exception.ErrorCode;
import com.BEJ.Bej.mapper.ProductMapper;
import com.BEJ.Bej.repository.ProductRepository;
import com.BEJ.Bej.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProductService {

    ProductRepository productRepository;
    ProductMapper productMapper;
    private final UserRepository userRepository;

    //    @PreAuthorize((has))
    public List<ProductResponse> getProducts(){
        return productRepository.findByStatusOrderByCreateDateDesc(1).stream().map(productMapper::toProductResponse).toList();
    }




//    admin service
    // admin get
//    public List<ProductResponse> getAllProducts(){
//        return productRepository.findAllByOrderByCreateDateDesc().stream().map(productMapper::toProductResponse).toList();
//    }
    // add new
    public ProductResponse addNewProduct(ProductRequest request) throws IOException {
        if(productRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        Product product = productMapper.toProduct(request);
        product.setCreateDate(LocalDate.now());
        System.out.println(product.getName());

        List<ProductAttribute> attributes = Optional.ofNullable(request.getAttributes())
                .orElse(Collections.emptyList())
                .stream()
                .map(attr -> {
                    ProductAttribute attribute = new ProductAttribute();
                    attribute.setValue(attr);
//                    attribute.setProduct(product);
                    return attribute;
                })
                .toList();

        if (request.getImage() != null) {
            String image = saveFile(request.getImage());
            product.setImage(image);
        }
        if (request.getDetailImages() != null){
            List<ProductImage>  images = request.getDetailImages().stream()
                    .map(file -> {
                        ProductImage image = new ProductImage();
                        try {
                            image.setUrl(saveFile(file));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
//                        image.setProduct(product);
                        return image;
                    }).toList();
//            product.setDetailImages(images);
        }
//        product.setAttributes(attributes);
//        System.out.println(product.getAttributes());

        return productMapper.toProductResponse(productRepository.save(product));
    }

    //delete
    public void delete(String productId){
        productRepository.deleteById(productId);
    }
    //set status
    public void inactive(String productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        int status = product.getStatus();
        if(status == 1) product.setStatus(0);
        else product.setStatus(1);
        productRepository.save(product);
    }




//    save file
//    String saveFile(MultipartFile file) throws IOException {
//        String uploadDir = "D:/Spring/newVuePr/pimg/";
//        Path path = Paths.get(uploadDir + file.getOriginalFilename());
//        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//        return uploadDir + file.getOriginalFilename();
//    }

    private String saveFile(MultipartFile file) throws IOException {
//        String uploadDir = "D:/Spring/newVuePr/BEJ/src/main/resources/static/images";
        String uploadDir = "D:/Spring/newVuePr/pimg/";
        String filename = file.getOriginalFilename();
        Path path = Paths.get(uploadDir + "/" + filename);
        log.info("adu " + String.valueOf(path));
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        return  "http://localhost:8080/bej3/images/" + filename;  // Trả về URL lưu trong DB
    }
}
