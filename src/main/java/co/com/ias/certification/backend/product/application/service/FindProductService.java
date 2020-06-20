package co.com.ias.certification.backend.product.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import co.com.ias.certification.backend.product.application.port.in.FindProductUseCase;
import co.com.ias.certification.backend.product.application.port.out.FindProductPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindProductService implements FindProductUseCase {
    private final FindProductPort findProductPort;
    @Override
    public Try<List<Object>> findProduct(FindProductQuery query) {
        ProductId id = query.getId();
        return findProductPort.findProduct(id);
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
