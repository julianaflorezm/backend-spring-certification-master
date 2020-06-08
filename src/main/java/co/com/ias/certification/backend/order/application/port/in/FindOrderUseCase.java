package co.com.ias.certification.backend.order.application.port.in;

import co.com.ias.certification.backend.order.application.domain.OrderId;
import io.vavr.control.Try;
import lombok.Value;

import java.util.List;

public interface FindOrderUseCase {
    Try<List<Object>> findOrder(FindOrderQuery query);

    @Value(staticConstructor = "of")
    class FindOrderQuery{
        OrderId id;
    }
}
