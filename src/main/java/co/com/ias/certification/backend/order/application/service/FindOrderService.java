package co.com.ias.certification.backend.order.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.order.application.port.in.FindOrderUseCase;
import co.com.ias.certification.backend.order.application.port.out.FindOrderPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindOrderService implements FindOrderUseCase {
    private final FindOrderPort findOrderPort;
    @Override
    public Try<List<Object>> findOrder(FindOrderQuery query) {
        return findOrderPort.findOrder(query.getId());
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
