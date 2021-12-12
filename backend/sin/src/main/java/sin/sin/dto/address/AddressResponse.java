package sin.sin.dto.address;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@ToString
public class AddressResponse {

    private Long id;
    private String address;
    private String recipient;
    private String recipientPhoneNumber;
    private boolean selected;
    private boolean original;
}