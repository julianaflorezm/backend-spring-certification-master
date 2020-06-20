package co.com.ias.certification.backend.order.adapters.in.web;

import co.com.ias.certification.backend.common.WebAdapter;
import co.com.ias.certification.backend.exceptions.UserUnauthorized;
import co.com.ias.certification.backend.order.application.domain.Customer;
import co.com.ias.certification.backend.order.application.port.in.*;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.keycloak.KeycloakPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

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
    public ResponseEntity createOrder(@RequestBody CreateOrderUseCase.CreateOrderCommand command, Authentication authentication){

        Collection authorities = authentication.getAuthorities();
        KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
        String customerName = principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
        Customer customer = Customer.of(customerName);
        Try<Boolean> hasPermission = createOrderUseCase.userHasPermission(authorities);
        if(hasPermission.get()){
            return ResponseEntity.ok(createOrderUseCase.createOrder(command, customerName));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error:" + customer.getValue() + "unauthorized to do this action");
    }

    @DeleteMapping
    public  ResponseEntity deleteOrder(@RequestBody DeleteOrderUseCase.DeleteOrderCommand command, Authentication authentication){
        Collection authorities = authentication.getAuthorities();
        KeycloakPrincipal principal = (KeycloakPrincipal) authentication.getPrincipal();
        String customerName = principal.getKeycloakSecurityContext().getToken().getPreferredUsername();
        Customer customer = Customer.of(customerName);
        Try<Boolean> hasPermission = deleteOrderUseCase.userHasPermission(authorities);
        if(hasPermission.get()){
            return ResponseEntity.ok(deleteOrderUseCase.deleteOrder(command));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Try.failure(UserUnauthorized.of(customer)));
    }

    @GetMapping
    public ResponseEntity findOrder(@RequestBody FindOrderUseCase.FindOrderQuery query, Authentication authentication){
        Collection authorities = authentication.getAuthorities();
        Try<Boolean> hasPermission = findOrderUseCase.userHasPermission(authorities);
        if(hasPermission.get()){
            return ResponseEntity.ok(findOrderUseCase.findOrder(query));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: ser unauthorized to do this action");
    }

    @PutMapping
    public ResponseEntity updateOrder(@RequestBody UpdateOrderUseCase.UpdateOrderCommand command, Authentication authentication){
        Collection authorities = authentication.getAuthorities();
        Try<Boolean> hasPermission = updateOrderUseCase.userHasPermission(authorities);
        if(hasPermission.get()){
            return ResponseEntity.ok(updateOrderUseCase.updateOrder(command));
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: user unauthorized to do this action");
    }

    @GetMapping("all")
    public ResponseEntity findAllOrders(Authentication authentication){
        Collection authorities = authentication.getAuthorities();
        Try<Boolean> hasPermission = findAllOrdersUseCase.userHasPermission(authorities);
        if(hasPermission.get()){
            return ResponseEntity.ok(findAllOrdersUseCase.findAllOrders());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Error: user unauthorized to do this action");
    }
}
