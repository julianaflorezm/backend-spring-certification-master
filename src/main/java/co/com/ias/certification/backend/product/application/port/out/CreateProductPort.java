package co.com.ias.certification.backend.product.application.port.out;

import co.com.ias.certification.backend.product.application.domain.*;
import io.vavr.control.Try;

public interface CreateProductPort {
    Try<Product> createProduct(ProductNotCreated product);
}
