package co.com.ias.certification.backend.product.application.port.in;

import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;
import lombok.Value;

import java.util.List;

public interface FindProductUseCase {
    Try<List<Object>> findProduct(FindProductQuery query);

    @Value(staticConstructor = "of")
    class FindProductQuery{
        ProductId id;
    }
}
