package co.com.ias.certification.backend.order.application.port.in;

import co.com.ias.certification.backend.product.application.domain.ProductId;
import io.vavr.control.Try;
import lombok.Value;

import java.util.List;

public interface DeleteOrderUseCase {
    Try<List<Object>> deleteOrder(DeleteOrderCommand command);

    @Value(staticConstructor = "of")
    class DeleteOrderCommand{
        ProductId id;
    }
}
