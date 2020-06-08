package co.com.ias.certification.backend.product.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.product.application.port.in.FindAllProductsUseCase;
import co.com.ias.certification.backend.product.application.port.out.FindAllProductsPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindAllProductsService implements FindAllProductsUseCase {

    private final FindAllProductsPort findAllProductsPort;

    @Override
    public List<Object> findAllProducts() {
        return findAllProductsPort.findAllProducts();
    }
}
