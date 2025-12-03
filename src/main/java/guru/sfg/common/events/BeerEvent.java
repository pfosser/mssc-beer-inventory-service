package guru.sfg.common.events;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BeerEvent implements Serializable {

	private static final long serialVersionUID = 5112731068275466658L;

	private BeerDto beerDto;
}
