package data;

import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Getter
public class DataHelper {
    private final String approvedCardNumber = "4444 4444 4444 4441";
    private final String declinedCardNumber = "4444 4444 4444 4442";
    private final String invalidCard = "4353 4534 5345 3453";
    private final String validName = "Test Name";
    private final String correctCvcFormat = "111";
    private final String appUrl = "http://localhost:8080";

    private final String approved = "APPROVED";
    private final String declined = "DECLINED";

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
