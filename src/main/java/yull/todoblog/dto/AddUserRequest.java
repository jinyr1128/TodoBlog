package yull.todoblog.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddUserRequest {
    private String username;
    private String email;
    private String password;

    public boolean isValidUsername() {
        return username != null && username.matches("^[a-z0-9]{4,10}$");
    }

    public boolean isValidPassword() {
        return password != null && password.matches("^[a-zA-Z0-9]{8,15}$");
    }
}
}
