package co.com.ias.certification.backend.order.application.port.out;

import co.com.ias.certification.backend.order.application.domain.IncompleteOrder;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;

import java.util.List;

public interface CreateOrderPort {
    Try<List<Object>> createOrder(List<ProductId> products, IncompleteOrder order, String customerName);
}
