package jisu.side.project.dto;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Entity
@Table(name = "authorities")
public class Auth {

    @Id
    @Column(name = "username")
    private String id;

    @Column(name = "authority")
    @Enumerated(EnumType.STRING)
    private Role role;

}

