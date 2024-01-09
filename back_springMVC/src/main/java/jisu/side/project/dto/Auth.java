package jisu.side.project.dto;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@ToString
@Table(name = "authorities")
public class Auth {

    @Id
    @Column(name = "username")
    private String id;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Auth(String id, Role role) {
        this.id = id;
        this.role = role;
    }
}

