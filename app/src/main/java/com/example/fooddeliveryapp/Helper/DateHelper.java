package com.example.fooddeliveryapp.Helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    public static String getCurrentDate() {
        // Create a SimpleDateFormat object with the desired format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy, h:mm a");

        // Get the current date and time
        Date currentDate = new Date();

        // Format the date and time using the SimpleDateFormat object
        String formattedDate = dateFormat.format(currentDate);

        // Return the formatted date and time as a string
        return formattedDate;
    }

    public static Date parseDate(String dateString) throws ParseException {
        // Create a SimpleDateFormat object with the expected input format
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy, h:mm a");

        // Parse the date string to obtain a Date object
        Date parsedDate = dateFormat.parse(dateString);

        // Return the parsed Date object
        return parsedDate;
    }
}
