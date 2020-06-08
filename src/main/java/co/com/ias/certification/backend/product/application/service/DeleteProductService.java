package co.com.ias.certification.backend.product.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.product.application.domain.Product;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import co.com.ias.certification.backend.product.application.port.in.DeleteProductUseCase;
import co.com.ias.certification.backend.product.application.port.out.DeleteProductPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteProductService implements DeleteProductUseCase {

    private final DeleteProductPort deleteProductPort;

    @Override
    public Try<Product> deleteProduct(DeleteProductCommand command) {
        ProductId id = command.getId();
        return deleteProductPort.deleteProduct(id);
    }
}
