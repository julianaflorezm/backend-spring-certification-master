package co.com.ias.certification.backend.product.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.product.application.domain.Product;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import co.com.ias.certification.backend.product.application.domain.ProductNotCreated;
import co.com.ias.certification.backend.product.application.port.in.UpdateProductUseCase;
import co.com.ias.certification.backend.product.application.port.out.UpdateProductPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.util.Collection;

@UseCase
@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {

    private final UpdateProductPort updateProductPort;

    @Override
    public Try<Product> updateProduct(UpdateProductCommand command) {
        ProductId id = command.getId();
        ProductNotCreated product = command.getProduct();
        return updateProductPort.updateProduct(id, product);
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
