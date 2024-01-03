package jisu.side.project.dto;

import lombok.*;
import javax.validation.*;
import javax.validation.constraints.*;


@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String id;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String password;

    private char enabled;

    public User toEntity(Role role) {
        return User.builder().id(id).password(password).enabled(enabled).auth(new Auth(id,role)).build();
    }

    @Override
    public java.lang.String toString() {
        return "" + id + " " + password + " " + enabled + " ";
    }
}
