package co.com.ias.certification.backend.product.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.product.application.port.in.FindAllProductsUseCase;
import co.com.ias.certification.backend.product.application.port.out.FindAllProductsPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllProductsService implements FindAllProductsUseCase {

    private final FindAllProductsPort findAllProductsPort;

    @Override
    public List<Object> findAllProducts() {
        return findAllProductsPort.findAllProducts();
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
