package sin.sin.dto.cart;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
@ToString
@Getter
@Setter
public class CartRequest {
    private String productCode;
    private int cnt;
}
