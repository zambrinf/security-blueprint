package br.com.zn43.security_blueprint.custom_user_details;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(schema = "myschema", name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName;
    private String password;
    private boolean enabled;
    private String roles;

    public User(int id, String userName, String password, boolean enabled, String roles) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

}
