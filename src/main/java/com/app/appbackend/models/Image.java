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
import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "image", schema = "public")
public class Image {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String name;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "date_created")
    LocalDate dateCreated;

}
