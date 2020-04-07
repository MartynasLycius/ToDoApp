package com.murad.todoapp.utility;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author Muradul Mostafa
 * Date    05/04/2020
 */
public class Maneger {


    //Temp
    public static final Integer Quantity = 3;
    //Date Format
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static final DateTimeFormatter formatterRest = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    public static final DateTimeFormatter formatterMonthSelect=DateTimeFormatter.ofPattern("dd/MM/yyyy");
     //response status
    public static final String LOGIN_SUCCESS = "Login Successfully";
    public static final String LOGIN_FAILED = "Invalid Username or Password";
    public static final String SERVER_ERROR = "Server error";
    public static final String METHOD_NOT_ALLOWED = "Method Not Allowed";
    public static final String OK = "OK";


    public static boolean checkExpiration() {
        LocalDate expDate = LocalDate.of(2025, 01, 23);
        LocalDate now = LocalDate.now();
        if (expDate.isAfter(now)) return false;
        return true;
    }


}
