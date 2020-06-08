package co.com.ias.certification.backend.order.application.service;

import co.com.ias.certification.backend.common.UseCase;
import co.com.ias.certification.backend.order.application.port.in.DeleteOrderUseCase;
import co.com.ias.certification.backend.order.application.port.out.DeleteOrderPort;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class DeleteOrderService implements DeleteOrderUseCase {

    private final DeleteOrderPort deleteOrderPort;

    @Override
    public Try<List<Object>> deleteOrder(DeleteOrderCommand command) {
        return deleteOrderPort.deleteOrder(command.getId()) ;
    }
}
