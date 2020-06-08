package co.com.ias.certification.backend.order.application.port.in;

import java.util.List;

public interface FindAllOrdersUseCase {
    List<List<Object>> findAllOrders();
}
