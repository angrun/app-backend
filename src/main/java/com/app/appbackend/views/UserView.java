package com.app.appbackend.views;

import com.app.appbackend.models.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserView {

    Long id;

    String name;

    String surname;

    String email;

    String city;

    String country;

    String gender;

    Integer age;

    Integer likes;

    String bio;

    LocalDate registerDate;

    List<Image> image;

}
