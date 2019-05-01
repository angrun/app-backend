package com.app.appbackend.message;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "messages", schema = "public")
public class Message {


    @Id
    @GeneratedValue
    Long id;

    @Column(name = "from_user_id")
    Long fromUserId;

    @Column(name = "to_user_id")
    Long toUserId;

    @Column
    String message;

    @Column(name = "message_seen")
    Boolean messageSeen;

    @Column(name = "date_sent")
    LocalDateTime dateSent;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", fromUserId=" + fromUserId +
                ", toUserId=" + toUserId +
                ", message='" + message + '\'' +
                ", dateSent=" + dateSent +
                '}';
    }
}