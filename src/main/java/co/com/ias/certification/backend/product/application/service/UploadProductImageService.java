package co.com.ias.certification.backend.product.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import co.com.ias.certification.backend.product.application.port.in.UploadProductImageUseCase;
import co.com.ias.certification.backend.product.application.port.out.UploadProductImagePort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;

@UseCase
@RequiredArgsConstructor
public class UploadProductImageService implements UploadProductImageUseCase {

    private final UploadProductImagePort uploadProductImagePort;

    @Override
    public Try<Object> uploadProductImage(UploadProductImageCommand command) {
        ProductId id = command.getId();
        MultipartFile file = command.getFile();
        return uploadProductImagePort.storeImage(id, file);
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
