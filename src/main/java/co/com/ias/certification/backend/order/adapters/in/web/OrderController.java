package co.com.ias.certification.backend.order.adapters.in.web;

import co.com.ias.certification.backend.common.WebAdapter;
import co.com.ias.certification.backend.order.application.port.in.*;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@WebAdapter
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderUseCase createOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;
    private final FindOrderUseCase findOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final FindAllOrdersUseCase findAllOrdersUseCase;

    @PostMapping
    public Try<List<Object>> createOrder(@RequestBody CreateOrderUseCase.CreateOrderCommand command){
        return createOrderUseCase.createOrder(command);
    }

    @DeleteMapping
    public  Try<List<Object>> deleteOrder(@RequestBody DeleteOrderUseCase.DeleteOrderCommand command){
        return deleteOrderUseCase.deleteOrder(command);
    }

    @GetMapping
    public Try<List<Object>> findOrder(@RequestBody FindOrderUseCase.FindOrderQuery query){
        return findOrderUseCase.findOrder(query);
    }

    @PutMapping
    public Try<List<Object>> updateOrder(@RequestBody UpdateOrderUseCase.UpdateOrderCommand command){
        return updateOrderUseCase.updateOrder(command);
    }

    @GetMapping("all")
    public List<List<Object>> findAllOrders(){
        return findAllOrdersUseCase.findAllOrders();
    }
}
