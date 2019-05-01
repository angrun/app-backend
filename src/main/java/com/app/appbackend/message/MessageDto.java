package com.app.appbackend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class MessageDto {


    Long fromUserId;

    Long toUserId;

    String message;

}
