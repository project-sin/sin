package sin.sin.dto;

import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
public class EmoneyResponse {

    private Timestamp createdDate;
    private Timestamp expirationDate;
    private String content;
    private int point;
}
