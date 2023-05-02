package com.caja.ideal.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_db")
public class UserModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @NaturalId(mutable = true)
    private String email;
    @Column(unique = true, length =  50)
    private String username;
    @NotBlank
    private String password;
    private String roles;
}
