package com.app.appbackend.match;


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
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matching", schema = "public")
public class Matching {

    @Id
    @GeneratedValue
    Long id;

    @Column(name = "from_user_id")
    Integer fromUserId;

    @Column(name = "to_user_id")
    Integer toUserId;

    @Column(name = "like_value")
    Integer likeValue;

}
