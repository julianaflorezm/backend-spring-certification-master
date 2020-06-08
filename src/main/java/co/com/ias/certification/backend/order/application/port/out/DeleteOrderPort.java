package co.com.ias.certification.backend.order.application.port.out;

import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;

import java.util.List;

public interface DeleteOrderPort {
    Try<List<Object>> deleteOrder(ProductId id);
}
