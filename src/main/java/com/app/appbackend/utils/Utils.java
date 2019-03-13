package com.app.appbackend.utils;

import java.time.LocalDate;
import java.time.Period;

public class Utils {


    public static int getUserAge(LocalDate birthDate, LocalDate currentDate) {

        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println(getUserAge(LocalDate.of(1997, 10, 23), LocalDate.now()));
    }


}
