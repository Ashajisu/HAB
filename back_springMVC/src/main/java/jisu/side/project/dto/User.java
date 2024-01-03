package jisu.side.project.dto;

import jakarta.persistence.*;
import lombok.*;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "username")
    private String id;

    @Column(name = "password")
    private String password;

//    @Column(name = "enabled", columnDefinition = "VARCHAR(255) DEFAULT 'Y'" )
    private char enabled;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "username")
    private Auth auth;

    @Builder
    public User(String id, String password, char enabled, Auth auth) {
        this.id = id;
    }

    @Override
    public java.lang.String toString() {
        return "" + id + " " + password + " " + enabled + " " + auth.getRole().toString();
    }
}
