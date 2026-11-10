package pd7.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@ToString
@Getter
@AllArgsConstructor
public class Trip {
    private final Destination destination;
    private TransportType transport;
    private final BigDecimal price;
    private int duration;
}
