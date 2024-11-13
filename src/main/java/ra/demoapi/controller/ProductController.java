package ra.demoapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ra.demoapi.exception.NotFoundElementException;
import ra.demoapi.model.dto.req.ProductCreateDto;
import ra.demoapi.model.dto.res.ResponseDto;
import ra.demoapi.model.entity.Product;
import ra.demoapi.service.IProductService;

import java.util.List;

@RestController
@RequestMapping("/api.com/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;
    // lấy tất cả danh sách sp
    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAll();
        return ResponseEntity.ok(products);
    }
    // lấy tất cả danh sách sp có trạng = true
    @GetMapping("/active")
    public ResponseEntity<List<Product>> getProductsByStatusIsTrue(){
        List<Product> products = productService.getAllByStatusIsTrue();
        return ResponseEntity.ok(products);
    }
    // tìm theo id
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<Product>> getById(@PathVariable Long id) throws NotFoundElementException {
        return ResponseEntity.ok(productService.getById(id));
    }
    // thêm mới
    @PostMapping()
    public ResponseEntity<ResponseDto<Product>> createProducts(@Valid @RequestBody ProductCreateDto request){
        return new ResponseEntity<>(productService.createProduct(request), HttpStatus.CREATED);
    }
}
