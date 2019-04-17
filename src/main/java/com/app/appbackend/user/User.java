package com.app.appbackend.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user", schema = "public")
public class User {

    @Id
    @GeneratedValue
    Long id;

    @NotNull(message = "Name is compulsory")
    @Column
    @NotEmpty(message = "Name is compulsory")
    String name;

    @Column
    @NotNull(message = "Surname is compulsory")
    @NotEmpty(message = "Surname is compulsory")
    String surname;

    @Column
    @NotNull(message = "Email is compulsory")
    @NotEmpty(message = "Email is compulsory")
    @Email(message = "Email format is invalid")
    String email;

    @Column
    @NotNull(message = "Password is compulsory")
    @Length(min = 5, message = "Password should be at least 5 characters")
    String password;

    @Column
    String password2;

    @Column
    @NotNull(message = "City is compulsory")
    @NotEmpty(message = "City is compulsory")
    String city;

    @Column
    @NotNull(message = "Country is compulsory")
    @NotEmpty(message = "Country is compulsory")
    String country;

    @Column
    @NotNull(message = "Gender is compulsory")
    @NotEmpty(message = "Gender is compulsory")
    String gender;

    @Column
    @NotNull(message = "Birthday is compulsory")
    LocalDate birth;

    @Column
    Integer likes;

    @Column
    String bio;

    @Column(name = "register_date")
    LocalDate registerDate;

    @Column
    Boolean seen;

    Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", password2='" + password2 + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", gender='" + gender + '\'' +
                ", birth='" + birth + '\'' +
                ", likes=" + likes +
                ", bio='" + bio + '\'' +
                '}';
    }
}
