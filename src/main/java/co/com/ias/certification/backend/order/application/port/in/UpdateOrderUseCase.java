package co.com.ias.certification.backend.order.application.port.in;

import co.com.ias.certification.backend.order.application.domain.OrderId;
import co.com.ias.certification.backend.order.application.domain.OrderStatus;
import io.vavr.control.Try;
import lombok.Value;

import java.util.List;

public interface UpdateOrderUseCase {
    Try<List<Object>> updateOrder(UpdateOrderCommand command);

    @Value(staticConstructor = "of")
    class UpdateOrderCommand{
        OrderId id;
        OrderStatus status;
    }
}
