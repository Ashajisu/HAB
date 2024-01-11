package jisu.side.project.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jisu.side.project.dto.Auth;
import jisu.side.project.dto.Role;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;



@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    private String id;

    @NotEmpty(message = "비밀번호는 필수 입력 값입니다.")
    private String password;


    public Member toEntity(PasswordEncoder encoder) {
        return Member.builder().id(id).password(encoder.encode(password)).enabled('Y').build();
        //.auth(new Auth(id,role)).build();
    }

    public Auth toAuth(Role role){
        return Auth.builder().id(id).role(role).build();
    }

    public Member toQuit(PasswordEncoder encoder){
        return Member.builder().id(id).password(encoder.encode(password)).enabled('N').build();
    }

}
