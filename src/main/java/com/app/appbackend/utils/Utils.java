package com.app.appbackend.utils;

import java.net.InetAddress;
import java.time.LocalDate;
import java.time.Period;

public class Utils {

    public static final String SERVER_ADD = "http://" + InetAddress.getLoopbackAddress().getHostName();
    public static final String DEFAULT_PIC = "/anonym.png";
    public static String UPLOAD_ROOT = "images/";
    public static String ALLOWED_ADDRESS = "http://localhost:8080";
    public static final int BAD_REQUEST = 400;


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
