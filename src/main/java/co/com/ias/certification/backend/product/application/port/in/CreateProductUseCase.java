package co.com.ias.certification.backend.product.application.port.in;

import co.com.ias.certification.backend.product.application.domain.*;
import io.vavr.control.Try;
import lombok.Value;

public interface CreateProductUseCase {

    Try<Product> createProduct(CreateProductCommand command);

    @Value(staticConstructor = "of")
    class CreateProductCommand{
        ProductNotCreated product;
    }

}
