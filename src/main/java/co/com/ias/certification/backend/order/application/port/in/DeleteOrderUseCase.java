package co.com.ias.certification.backend.order.application.port.in;

import co.com.ias.certification.backend.order.application.domain.OrderId;
import io.vavr.control.Try;
import lombok.Value;

import java.util.Collection;
import java.util.List;

public interface DeleteOrderUseCase {
    Try<List<Object>> deleteOrder(DeleteOrderCommand command);

    Try<Boolean> userHasPermission(Collection authorities);

    @Value(staticConstructor = "of")
    class DeleteOrderCommand{
        OrderId id;
    }
}
