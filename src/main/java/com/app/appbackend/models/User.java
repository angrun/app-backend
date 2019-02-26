package com.app.appbackend.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


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

    @Column
    String name;

    @Column
    String surname;

    @Column
    String password;

    @Column
    String password2;

    @Column
    String city;

    @Column
    String country;

    @Column
    String gender;

    @Column
    String birth;

    @Column
    Integer likes;

    @Column
    String bio;

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

    //    @Column
//    String hobby [];


}
