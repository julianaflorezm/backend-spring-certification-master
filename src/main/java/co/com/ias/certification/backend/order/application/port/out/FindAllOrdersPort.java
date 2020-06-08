package co.com.ias.certification.backend.order.application.port.out;

import java.util.List;

public interface FindAllOrdersPort {
    List<List<Object>> findAllOrders();
}
