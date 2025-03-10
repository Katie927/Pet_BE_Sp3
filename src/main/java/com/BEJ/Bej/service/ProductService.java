package com.BEJ.Bej.service;

import com.BEJ.Bej.entity.Product;
import com.BEJ.Bej.repository.ProductRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper objectMapper; // Jackson ObjectMapper để đọc JSON

//    @PostConstruct
//    public void importJsonData() {
//        try {
//            // Đọc file JSON
//            File file = new File("D:/Python3/New folder/hoanghamobile_selenium.json");
//
//            // Chuyển đổi JSON thành List<Product>
//            List<Product> products = objectMapper.readValue(file, new TypeReference<List<Product>>() {});
//
//            // Lưu vào database
//            productRepository.saveAll(products);
//
//            System.out.println("Import dữ liệu thành công! Số lượng sản phẩm: " + products.size());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

}
