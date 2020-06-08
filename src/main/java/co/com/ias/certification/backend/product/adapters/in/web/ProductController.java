package co.com.ias.certification.backend.product.adapters.in.web;

import co.com.ias.certification.backend.common.WebAdapter;
import co.com.ias.certification.backend.product.application.domain.Product;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import co.com.ias.certification.backend.product.application.port.in.*;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@WebAdapter
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final FindProductUseCase findProductUseCase;
    private final FindAllProductsUseCase findAllProductsUseCase;
    private final UploadProductImageUseCase uploadProductImageUseCase;

    @PostMapping
    public Try<Product> createProduct(@RequestBody CreateProductUseCase.CreateProductCommand command ) {
        return createProductUseCase.createProduct(command);
    }

    @PutMapping
    public Try<Product> updateProduct(@RequestBody UpdateProductUseCase.UpdateProductCommand command){
        return updateProductUseCase.updateProduct(command);
    }

    @DeleteMapping
    public Try<Product> deleteProduct(@RequestBody DeleteProductUseCase.DeleteProductCommand command){
        return deleteProductUseCase.deleteProduct(command);
    }

    @GetMapping()
    public Try<List<Object>> findProduct(@RequestBody FindProductUseCase.FindProductQuery query){
        return findProductUseCase.findProduct(query);
    }

    @GetMapping("/all")
    public List<Object> findAllProducts(){
        return findAllProductsUseCase.findAllProducts();
    }

    @PostMapping("/images")
    Try<Object> uploadProductImage(@RequestParam("file") MultipartFile file, @RequestParam("id") Long productId) throws IOException {
        ProductId id = ProductId.of(productId);
        UploadProductImageUseCase.UploadProductImageCommand command = UploadProductImageUseCase.UploadProductImageCommand.of(
                id,
                file
        );
        return uploadProductImageUseCase.uploadProductImage(command);
    }


}
