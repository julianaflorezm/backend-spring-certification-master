package co.com.ias.certification.backend.product.application.port.in;

import co.com.ias.certification.backend.product.application.domain.Product;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import co.com.ias.certification.backend.product.application.domain.ProductNotCreated;
import io.vavr.control.Try;
import lombok.Value;

import java.util.Collection;

public interface UpdateProductUseCase {
    Try<Product> updateProduct(UpdateProductCommand command);

    Try<Boolean> userHasPermission(Collection authorities);

    @Value(staticConstructor = "of")
    class UpdateProductCommand{
        ProductId id;
        ProductNotCreated product;
    }
}
