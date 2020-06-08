package co.com.ias.certification.backend.product.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.product.application.domain.ProductId;
import co.com.ias.certification.backend.product.application.port.in.FindProductUseCase;
import co.com.ias.certification.backend.product.application.port.out.FindProductPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

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
}
