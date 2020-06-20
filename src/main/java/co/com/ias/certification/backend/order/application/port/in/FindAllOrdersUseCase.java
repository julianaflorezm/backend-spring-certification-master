package co.com.ias.certification.backend.order.application.port.in;

import io.vavr.control.Try;

import java.util.Collection;
import java.util.List;

public interface FindAllOrdersUseCase {
    List<List<Object>> findAllOrders();

    Try<Boolean> userHasPermission(Collection authorities);
}
