package co.com.ias.certification.backend.order.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.order.application.port.in.FindAllOrdersUseCase;
import co.com.ias.certification.backend.order.application.port.out.FindAllOrdersPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllOrdersService implements FindAllOrdersUseCase {
    private final FindAllOrdersPort findAllOrdersPort;
    @Override
    public List<List<Object>> findAllOrders() {
        return findAllOrdersPort.findAllOrders();
    }

    @Override
    public Try<Boolean> userHasPermission(Collection authorities) {
        return Try.of(() -> {
            for(Object role : authorities){
                if(role.toString().equals("KeycloakRole{role='ADMIN'}") || role.toString().equals("KeycloakRole{role='EMPLOYEE'}")){
                    return true;
                }
            }
            return false;
        });
    }
}
