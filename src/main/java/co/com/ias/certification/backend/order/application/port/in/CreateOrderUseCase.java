package co.com.ias.certification.backend.order.application.port.in;


import co.com.ias.certification.backend.order.application.domain.*;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;
import lombok.Value;

import java.util.Collection;
import java.util.List;

public interface CreateOrderUseCase {
    Try<List<Object>> createOrder(CreateOrderCommand command, Customer customer);

    Try<Boolean> userHasPermission(Collection authorities);

    @Value(staticConstructor = "of")
    class CreateOrderCommand{
        List<ProductId> products;
        IncompleteOrder order;
    }
}
