package ra.demoapi.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ra.demoapi.exception.DupplicateException;
import ra.demoapi.exception.NotFoundElementException;
import ra.demoapi.model.dto.req.ProductCreateDto;
import ra.demoapi.model.dto.res.ResponseDto;
import ra.demoapi.model.entity.Product;
import ra.demoapi.repository.IProductRepository;
import ra.demoapi.service.IProductService;

import java.util.List;
@Service
@RequiredArgsConstructor // tạo 1 constructor với cá thuộc tính bắt buocj đc đánh dấu là final
public class ProductServiceImpl implements IProductService {
    private final IProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getAllByStatusIsTrue() {
        return productRepository.getListProductsByStatusIsTrue();
    }

    @Override
    public ResponseDto<Product> createProduct(ProductCreateDto request) {
        if (productRepository.existsByName(request.getName())){
            throw  new DupplicateException("Tên sản phẩm đa tôn tại");
        }
        // biên đổi thành entity
        Product product = Product.builder()
                .name(request.getName())
                .stock(request.getStock())
                .image(request.getImage())
                .price(request.getPrice())
                .description(request.getDescription())
                .status(true)
                .build();
        Product newProduct = productRepository.save(product);
       return new ResponseDto<>(201, HttpStatus.CREATED,newProduct);
    }

    @Override
    public  ResponseDto<Product> getById(Long id) throws NotFoundElementException {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundElementException("Product id not found"));
        return new ResponseDto<>(200, HttpStatus.OK,product);
    }
}
