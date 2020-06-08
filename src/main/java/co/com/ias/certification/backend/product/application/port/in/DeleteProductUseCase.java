package co.com.ias.certification.backend.product.application.port.in;

import co.com.ias.certification.backend.product.application.domain.Product;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;
import lombok.Value;

public interface DeleteProductUseCase{
    Try<Product> deleteProduct(DeleteProductCommand command);

    @Value(staticConstructor = "of")
    class DeleteProductCommand{
        ProductId id;
    }
}

