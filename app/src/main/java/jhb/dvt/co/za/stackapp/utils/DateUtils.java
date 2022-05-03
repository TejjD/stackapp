package jhb.dvt.co.za.stackapp.utils;

import java.util.Date;

public class DateUtils {

    public static Date convertUnixToDate(int unixDate) {
        return new Date(unixDate * 1000L);
    }

    public static String getDifferenceInDates(Date previousDate) {
        String date = "";
        Date currentDate = new Date();

        try {
            long dateDifference = currentDate.getTime() - previousDate.getTime();

            long minutes = dateDifference / (60 * 1000) % 60;
            long hours = dateDifference / (60 * 60 * 1000) % 24;
            long days = dateDifference / (24 * 60 * 60 * 1000);

            boolean exists = false;
            if (days > 0) {
                date = date.concat(days + " days ");
                exists = true;
            }
            if (hours > 0) {
                date = date.concat(hours + " hours ");
                exists = true;
            }
            if (minutes > 0) {
                date = date.concat(minutes + " minutes ");
                exists = true;
            }

            if (exists) {
                date = date.concat("ago.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return date;
    }
}
