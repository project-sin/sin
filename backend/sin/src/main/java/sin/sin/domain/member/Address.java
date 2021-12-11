package sin.sin.domain.member;

import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;

@Getter
@Embeddable
public class Address {

    @NotNull
    private String originAddress;

    @ElementCollection
    private List<String> addresses = new ArrayList<>();

    @NotNull
    private String selectedAddress;

    public void addAddress(String address) {

    }

    public void deleteAddress(String address) {

    }

    public Address() {

    }

    @Builder
    public Address(String originAddress, List<String> addresses, String selectedAddress) {
        this.originAddress = originAddress;
        if (Objects.nonNull(addresses)) {
            this.addresses = addresses;
        }
        this.selectedAddress = selectedAddress;
    }
}
