package co.com.ias.certification.backend.order.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.order.application.port.in.FindOrderUseCase;
import co.com.ias.certification.backend.order.application.port.out.FindOrderPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindOrderService implements FindOrderUseCase {
    private final FindOrderPort findOrderPort;
    @Override
    public Try<List<Object>> findOrder(FindOrderQuery query) {
        return findOrderPort.findOrder(query.getId());
    }
}
