package ra.demoapi.service;

import ra.demoapi.exception.NotFoundElementException;
import ra.demoapi.model.dto.req.ProductCreateDto;
import ra.demoapi.model.dto.res.ResponseDto;
import ra.demoapi.model.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAll();
    List<Product> getAllByStatusIsTrue();
    ResponseDto<Product> createProduct(ProductCreateDto request);
    ResponseDto<Product> getById(Long id) throws NotFoundElementException;
}
