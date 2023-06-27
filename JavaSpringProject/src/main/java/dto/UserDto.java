package dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import entities.User;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String username;
    private String password;
    private String role;

    public static UserDto fromUser(User user) {
        return new UserDto(user.getUsername(), user.getPassword(), user.getRole());
    }
}
