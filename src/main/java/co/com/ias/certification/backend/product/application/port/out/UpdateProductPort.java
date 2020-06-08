package co.com.ias.certification.backend.product.application.port.out;

import co.com.ias.certification.backend.product.application.domain.Product;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import co.com.ias.certification.backend.product.application.domain.ProductNotCreated;
import io.vavr.control.Try;

public interface UpdateProductPort {
    Try<Product> updateProduct(ProductId id, ProductNotCreated prodduct);
}