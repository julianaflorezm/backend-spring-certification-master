package co.com.ias.certification.backend.product.application.port.out;

import co.com.ias.certification.backend.product.application.domain.Product;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;

public interface DeleteProductPort {
    Try<Product> deleteProduct(ProductId id);
}
