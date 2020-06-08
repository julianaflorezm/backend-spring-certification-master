package co.com.ias.certification.backend.order.application.port.out;

import co.com.ias.certification.backend.order.application.domain.OrderId;
import io.vavr.control.Try;

import java.util.List;

public interface DeleteOrderPort {
    Try<List<Object>> deleteOrder(OrderId id);
}
