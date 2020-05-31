package com.todo.user.entity;

import com.todo.utils.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity(name = "usr")
@Getter @Setter @ToString
/*@Table(uniqueConstraints = { @UniqueConstraint(columnNames = {
        "username", "email"}) })*/
public class User extends AbstractEntity {

    @Id
    @GeneratedValue(generator = "user_id_seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq")
    private Long id;

    @NotNull
    @NotBlank(message = "Please enter username")
    @Column(name = "username", unique = true)
    private String username;

    @NotNull
    @NotBlank(message = "Please enter password")
    @Column(name = "password")
    private String password;

    @Email(message = "{user.email.invalid}")
    @NotNull
    @NotBlank(message = "Please enter email")
    @Column(name = "email", unique = true)
    private String email;

    @Size(min = 4, max = 14, message = "Min 4 and Max 14 digits allowed")
    @Pattern(regexp=("[0-9]+"), message = "Only numbers are allowed")
    @Column(name = "phone")
    private String phone;

    @NotNull
    @NotBlank(message = "Please enter Full Name")
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "address")
    private String address;

    @Override
    public boolean isPersisted() {
        return id != null;
    }
}
