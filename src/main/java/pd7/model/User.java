package pd7.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class User {
    private final String name;
    private TransportType preferredTransport;
    private BigDecimal budget;
}
