package co.com.ias.certification.backend.product.application.port.in;

import io.vavr.control.Try;

import java.util.Collection;
import java.util.List;

public interface FindAllProductsUseCase {
    List<Object> findAllProducts();

    Try<Boolean> userHasPermission(Collection authorities);
}
