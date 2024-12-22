package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DataHelper {

    public static String getCurrentMonth() {
        LocalDate currentDate = LocalDate.now();
        return currentDate.format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateRandomMonth() {
        Random random = new Random();
        int month = random.nextInt(12) + 1;
        return String.format("%02d", month);
    }

    public static String getFutureYearInYY(int numYears) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
        return LocalDate.now().plusYears(numYears).format(formatter);
    }

    public static String getPrevYearInYY(int numYears) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
        return LocalDate.now().minusYears(numYears).format(formatter);
    }
}
