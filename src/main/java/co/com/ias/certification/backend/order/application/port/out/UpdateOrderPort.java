package co.com.ias.certification.backend.order.application.port.out;

import co.com.ias.certification.backend.order.application.domain.OrderId;
import co.com.ias.certification.backend.order.application.domain.OrderStatus;
import io.vavr.control.Try;

import java.util.List;

public interface UpdateOrderPort {
    Try<List<Object>> updateOrder(OrderId id, OrderStatus status);
}
