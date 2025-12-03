package guru.sfg.common.events;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class NewInventoryEvent extends BeerEvent {

	private static final long serialVersionUID = -4850275030937974097L;

	public NewInventoryEvent(BeerDto beerDto) {
		super(beerDto);
	}
}
