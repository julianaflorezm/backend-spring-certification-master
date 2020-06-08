package co.com.ias.certification.backend.order.application.port.in;


import co.com.ias.certification.backend.order.application.domain.*;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;
import lombok.Value;

import java.util.List;

public interface CreateOrderUseCase {
    Try<List<Object>> createOrder(CreateOrderCommand command);

    @Value(staticConstructor = "of")
    class CreateOrderCommand{
        List<ProductId> products;
        OrderNotCreated order;
    }
}
