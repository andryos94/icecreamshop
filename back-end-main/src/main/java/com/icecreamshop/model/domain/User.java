package com.icecreamshop.model.domain;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private Long id;

    @Email
    @NotBlank
    @Length(max = 128)
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Length(max = 64)
    @Column(nullable = false)
    private String password;

    @NotBlank
    @Length(max = 16)
    @Column(nullable = false, unique = true)
    private String username;
}
