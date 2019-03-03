package com.app.appbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


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

    @NotNull
    @NotEmpty
    @Column
    String name;

    @Column
    @NotNull
    @NotEmpty
    String surname;

    @Column
    @NotNull
    @NotEmpty
    String email;

    @Column
    @NotNull
    @NotEmpty
    String password;

    @Column
    @NotNull
    @NotEmpty
    String password2;

    @Column
    @NotNull
    @NotEmpty
    String city;

    @Column
    @NotNull
    @NotEmpty
    String country;

    @Column
    @NotNull
    @NotEmpty
    String gender;

    @Column
    LocalDate birth;

    @Column
    Integer likes;

    @Column
    String bio;

    @Column(name = "register_date")
    LocalDate registerDate;

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
