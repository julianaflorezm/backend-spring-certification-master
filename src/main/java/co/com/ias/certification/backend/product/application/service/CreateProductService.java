package co.com.ias.certification.backend.product.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.product.application.domain.Product;
import co.com.ias.certification.backend.product.application.domain.ProductNotCreated;
import co.com.ias.certification.backend.product.application.port.in.CreateProductUseCase;
import co.com.ias.certification.backend.product.application.port.out.CreateProductPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {

    private final CreateProductPort createProductPort;

    @Override
    public Try<Product> createProduct(CreateProductCommand command) {
        ProductNotCreated product = command.getProduct();
        return createProductPort.createProduct(product);
    }
}
