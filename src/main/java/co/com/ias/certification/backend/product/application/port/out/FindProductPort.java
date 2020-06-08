package co.com.ias.certification.backend.product.application.port.out;

import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;

import java.util.List;

public interface FindProductPort {
    Try<List<Object>> findProduct(ProductId id);
}
