package test;

import data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import page.CardWidget;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestBuyTrip {

    private final String approvedCardNumber = "4444 4444 4444 4441";
    private final String declinedCardNumber = "4444 4444 4444 4442";
    private final String invalidCard = "4353 4534 5345 3453";
    private final String validName = "Test Name";
    private final String correctCvcFormat = "111";

    private final String APPROVED = "APPROVED";
    private final String DECLINED = "DECLINED";
    private TransactionRecordCredit transactionRecordCredit;
    private TransactionRecordPayment transactionRecordPayment;


    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }

    @Test
    void shouldSuccessfulBuyWhenProvideCorrectData() {
        String month = DataHelper.generateRandomMonth();
        String year = DataHelper.getFutureYearInYY(2);
        MainPage mainPage = new MainPage();
        mainPage.selectBuyOption();

        CardWidget cardWidget = new CardWidget();

        cardWidget.checkIfBuyWithCardHeaderVisible();

        cardWidget.enterCardNumber(approvedCardNumber);

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(validName);

        cardWidget.enterCvc(correctCvcFormat);

        cardWidget.clickContinueButton();

        cardWidget.checkSuccessfulNotification();

        sleep(1000);

        // check that record in db in payment table
        transactionRecordPayment = DataBaseHelper.getLastTransactionPayment();
        assertEquals(APPROVED, transactionRecordPayment.getStatus());

        int actualNumberOfOrders = DataBaseHelper.getOrderCountByPaymentId(transactionRecordPayment.getTransaction_id());
        assertEquals(1, actualNumberOfOrders);

    }

    @Test
    void shouldFailBuyWhenProvideDeclinedCard() {
        String month = DataHelper.generateRandomMonth();
        String year = DataHelper.getFutureYearInYY(2);
        MainPage mainPage = new MainPage();
        mainPage.selectBuyOption();

        CardWidget cardWidget = new CardWidget();

        cardWidget.checkIfBuyWithCardHeaderVisible();

        cardWidget.enterCardNumber(declinedCardNumber);

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(validName);

        cardWidget.enterCvc(correctCvcFormat);

        cardWidget.clickContinueButton();

        cardWidget.checkFailNotification();

        sleep(1000);

        // check that record in db in payment table
        transactionRecordPayment = DataBaseHelper.getLastTransactionPayment();
        assertEquals(DECLINED, transactionRecordPayment.getStatus());

        int actualNumberOfOrders = DataBaseHelper.getOrderCountByPaymentId(transactionRecordPayment.getTransaction_id());
        assertEquals(0, actualNumberOfOrders);

    }

    @Test
    void shouldSuccessfulCreditWhenProvideCorrectData() {
        String month = DataHelper.generateRandomMonth();
        String year = DataHelper.getFutureYearInYY(1);

        MainPage mainPage = new MainPage();
        mainPage.selectCreditOption();

        CardWidget cardWidget = new CardWidget();

        cardWidget.checkIfBuyWithCreditHeaderVisible();

        sleep(1000);

        cardWidget.enterCardNumber(approvedCardNumber);

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(validName);

        cardWidget.enterCvc(correctCvcFormat);

        cardWidget.clickContinueButton();

        cardWidget.checkSuccessfulNotification();

        transactionRecordCredit = DataBaseHelper.getLastTransactionCredit();
        assertEquals(APPROVED, transactionRecordCredit.getStatus());

        int actualNumberOfOrders = DataBaseHelper.getOrderCountByCreditId(transactionRecordCredit.getBankId());
        assertEquals(1, actualNumberOfOrders);

    }

    @Test
    void shouldFailCreditWhenProvideDeclinedCard() {
        String month = DataHelper.generateRandomMonth();
        String year = DataHelper.getFutureYearInYY(1);

        MainPage mainPage = new MainPage();
        mainPage.selectCreditOption();

        CardWidget cardWidget = new CardWidget();

        cardWidget.checkIfBuyWithCreditHeaderVisible();

        sleep(1000);

        cardWidget.enterCardNumber(declinedCardNumber);

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(validName);

        cardWidget.enterCvc(correctCvcFormat);

        cardWidget.clickContinueButton();

        cardWidget.checkFailNotification();

        transactionRecordCredit = DataBaseHelper.getLastTransactionCredit();
        assertEquals(DECLINED, transactionRecordCredit.getStatus());

        int actualNumberOfOrders = DataBaseHelper.getOrderCountByCreditId(transactionRecordCredit.getBankId());
        assertEquals(0, actualNumberOfOrders);
    }

    @Test
    void shouldDeclineWhenInvalidCardProvided() {
        String month = DataHelper.generateRandomMonth();
        String year = DataHelper.getFutureYearInYY(2);
        MainPage mainPage = new MainPage();
        mainPage.selectBuyOption();

        CardWidget cardWidget = new CardWidget();

        cardWidget.checkIfBuyWithCardHeaderVisible();

        sleep(1000);

        cardWidget.enterCardNumber(invalidCard);

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(validName);

        cardWidget.enterCvc(correctCvcFormat);

        cardWidget.clickContinueButton();

        cardWidget.checkFailNotification();

        sleep(1000);

        // check that record in db in payment table
        transactionRecordPayment = DataBaseHelper.getLastTransactionPayment();
        assertEquals(DECLINED, transactionRecordPayment.getStatus());

        int actualNumberOfOrders = DataBaseHelper.getOrderCountByPaymentId(transactionRecordPayment.getTransaction_id());
        assertEquals(0, actualNumberOfOrders);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5", "",})
    void shouldRaiseErrorWhenWrongFormatMonthProvided(String month) {
        String year = DataHelper.getFutureYearInYY(2);
        MainPage mainPage = new MainPage();
        mainPage.selectBuyOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCardHeaderVisible();
        sleep(1000);
        cardWidget.enterCardNumber(invalidCard);

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(validName);

        cardWidget.enterCvc(correctCvcFormat);

        cardWidget.clickContinueButton();

        cardWidget.checkWrongFormatErrorPresent();

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "22"})
    void shouldRaiseErrorWhenWrongFormatCvcProvided(String cvc) {
        String year = DataHelper.getFutureYearInYY(2);
        String month = DataHelper.generateRandomMonth();
        MainPage mainPage = new MainPage();
        mainPage.selectBuyOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCardHeaderVisible();
        sleep(1000);
        cardWidget.enterCardNumber(invalidCard);

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(validName);

        cardWidget.enterCvc(cvc);

        cardWidget.clickContinueButton();

        cardWidget.checkWrongFormatErrorPresent();

    }

    @Test
    void shouldRaiseErrorWhenNonValidMonthProvided() {
        String year = DataHelper.getFutureYearInYY(2);
        String monthInvalid = "13";
        MainPage mainPage = new MainPage();
        mainPage.selectBuyOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCardHeaderVisible();
        sleep(1000);
        cardWidget.enterCardNumber(invalidCard);

        cardWidget.enterMonth(monthInvalid);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(validName);

        cardWidget.enterCvc(correctCvcFormat);

        cardWidget.clickContinueButton();

        cardWidget.checkInvalidExpireDateErrorPresent();

    }

    @Test
    void shouldRaiseErrorWhenEmptyNameProvided() {
        String year = DataHelper.getFutureYearInYY(2);
        String month = DataHelper.generateRandomMonth();
        MainPage mainPage = new MainPage();
        mainPage.selectBuyOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCardHeaderVisible();
        sleep(1000);
        cardWidget.enterCardNumber(invalidCard);

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName("");

        cardWidget.enterCvc(correctCvcFormat);

        cardWidget.clickContinueButton();

        cardWidget.checkFieldMustBeFilledErrorPresent();

    }

    @Test
    void shouldRaiseErrorWhenExpiredCardProvided() {
        String year = DataHelper.getPrevYearInYY(2);
        String month = DataHelper.generateRandomMonth();
        MainPage mainPage = new MainPage();
        mainPage.selectBuyOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCardHeaderVisible();
        sleep(1000);
        cardWidget.enterCardNumber(approvedCardNumber);

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(validName);

        cardWidget.enterCvc(correctCvcFormat);

        cardWidget.clickContinueButton();

        cardWidget.checkCardExpiredErrorPresent();

    }

    @Test
    void shouldSucceedWhenValidCardWithExpireDateThisMonthProvided() {
        String year = DataHelper.getFutureYearInYY(0);
        String month = DataHelper.getCurrentMonth();
        MainPage mainPage = new MainPage();
        mainPage.selectBuyOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCardHeaderVisible();
        sleep(1000);
        cardWidget.enterCardNumber(approvedCardNumber);

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(validName);

        cardWidget.enterCvc(correctCvcFormat);

        cardWidget.clickContinueButton();

        cardWidget.checkSuccessfulNotification();

    }
}
