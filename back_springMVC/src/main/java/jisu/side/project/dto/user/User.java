package jisu.side.project.dto.user;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "users")
@ToString
public class User {

    @Id
    @Column(name = "username")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "enabled", columnDefinition = "CHARACTER DEFAULT 'Y'" )
    private char enabled;

    @Builder
    public User(String id, String password, char enabled){
        this.id = id;
        this.password = password;
        this.enabled = enabled;
    }
}
