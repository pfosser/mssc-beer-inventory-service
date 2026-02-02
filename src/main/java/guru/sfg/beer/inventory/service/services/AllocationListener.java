package guru.sfg.beer.inventory.service.services;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import guru.sfg.beer.inventory.service.config.JmsConfig;
import guru.sfg.brewery.model.events.AllocateOrderRequest;
import guru.sfg.brewery.model.events.AllocateOrderResult;
import guru.sfg.brewery.model.events.AllocateOrderResult.AllocateOrderResultBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationListener {

	private final AllocationService allocationService;

	private final JmsTemplate jmsTemplate;

	@JmsListener(destination = JmsConfig.ALLOCATE_ORDER_QUEUE)
	public void listen(AllocateOrderRequest request) {
		AllocateOrderResultBuilder builder = AllocateOrderResult.builder();
		builder.beerOrder(request.getBeerOrder()); //

		try {
			Boolean allocationResult = allocationService.allocateOrder(request.getBeerOrder());

			if (allocationResult) {
				builder.allocationError(false);
			} else {
				builder.allocationError(true);
			}
		} catch (Exception ex) {
			log.error("Allocation failed for order {}", request.getBeerOrder().getId());
			builder.allocationError(true);
		}

		jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_ORDER_RESPONSE_QUEUE, builder.build());
	}
}
