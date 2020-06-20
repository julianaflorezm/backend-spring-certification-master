package co.com.ias.certification.backend.order.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.order.application.port.in.CreateOrderUseCase;
import co.com.ias.certification.backend.order.application.port.out.CreateOrderPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

    private final CreateOrderPort createOrderPort;

    @Override
    public Try<List<Object>> createOrder(CreateOrderCommand command, String customerName) {
        return createOrderPort.createOrder(command.getProducts(), command.getOrder(), customerName);
    }

    @Override
    public Try<Boolean> userHasPermission(Collection authorities) {
        return Try.of(() -> {
            for(Object role : authorities){
                if(role.toString().equals("KeycloakRole{role='ADMIN'}") || role.toString().equals("KeycloakRole{role='EMPLOYEE'}") || role.toString().equals("KeycloakRole{role='CUSTOMER'}")){
                    return true;
                }
            }
            return false;
        });
    }
}
