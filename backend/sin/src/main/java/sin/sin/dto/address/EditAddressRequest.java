package sin.sin.dto.address;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class EditAddressRequest extends AddressRequest {

    private Long addressId;
    private String recipient;
    private String recipientPhoneNumber;

    @Builder
    public EditAddressRequest(String address, boolean original, Long addressId, String recipient,
        String recipientPhoneNumber) {
        super(address, original);
        this.addressId = addressId;
        this.recipient = recipient;
        this.recipientPhoneNumber = recipientPhoneNumber;
    }
}
