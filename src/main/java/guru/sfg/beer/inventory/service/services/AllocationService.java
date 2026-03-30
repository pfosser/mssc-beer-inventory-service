package guru.sfg.beer.inventory.service.services;

import guru.sfg.brewery.model.BeerOrderDto;

public interface AllocationService {

	/**
	 * @param beerOrder
	 * @return {@code true} if we allocate everything, {@code false} if we are short
	 *         on allocation
	 */
	Boolean allocateOrder(BeerOrderDto beerOrder);

	void deallocateOrder(BeerOrderDto beerOrder);
}

