package shop.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class JwtResponse {
    private String accessToken;
    private String tokenType = "Bearer";


    public JwtResponse(String token) {
        this.accessToken = token;
    }
}
