package test;

import data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import page.CardWidget;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCreditTrip {
    DataHelper dataHelper = new DataHelper();
    private TransactionRecordCredit transactionRecordCredit;

    @BeforeEach
    void setup() {
        open(dataHelper.getAppUrl());
    }

    @Test
    void shouldSuccessfulCreditWhenProvideCorrectData() {
        String month = DataHelper.generateRandomMonth();
        String year = DataHelper.getFutureYearInYY(1);

        MainPage mainPage = new MainPage();
        mainPage.selectCreditOption();

        CardWidget cardWidget = new CardWidget();

        cardWidget.checkIfBuyWithCreditHeaderVisible();

        cardWidget.enterCardNumber(dataHelper.getApprovedCardNumber());

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(dataHelper.getValidName());

        cardWidget.enterCvc(dataHelper.getCorrectCvcFormat());

        cardWidget.clickContinueButton();

        cardWidget.checkSuccessfulNotification();

        transactionRecordCredit = DataBaseHelper.getLastTransactionCredit();
        assertEquals(dataHelper.getApproved(), transactionRecordCredit.getStatus());

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


        cardWidget.enterCardNumber(dataHelper.getDeclinedCardNumber());

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(dataHelper.getValidName());

        cardWidget.enterCvc(dataHelper.getCorrectCvcFormat());

        cardWidget.clickContinueButton();

        cardWidget.checkFailNotification();

        transactionRecordCredit = DataBaseHelper.getLastTransactionCredit();
        assertEquals(dataHelper.getDeclined(), transactionRecordCredit.getStatus());

        int actualNumberOfOrders = DataBaseHelper.getOrderCountByCreditId(transactionRecordCredit.getBankId());
        assertEquals(0, actualNumberOfOrders);
    }

    @Test
    void shouldDeclineCreditWhenInvalidCardProvided() {
        String month = DataHelper.generateRandomMonth();
        String year = DataHelper.getFutureYearInYY(2);
        MainPage mainPage = new MainPage();
        mainPage.selectCreditOption();

        CardWidget cardWidget = new CardWidget();

        cardWidget.checkIfBuyWithCreditHeaderVisible();

        cardWidget.enterCardNumber(dataHelper.getInvalidCard());

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(dataHelper.getValidName());

        cardWidget.enterCvc(dataHelper.getCorrectCvcFormat());

        cardWidget.clickContinueButton();

        cardWidget.checkFailNotification();

        // check that record in db in payment table
        transactionRecordCredit = DataBaseHelper.getLastTransactionCredit();
        assertEquals(dataHelper.getDeclined(), transactionRecordCredit.getStatus());

        int actualNumberOfOrders = DataBaseHelper.getOrderCountByCreditId(transactionRecordCredit.getBankId());
        assertEquals(0, actualNumberOfOrders);
    }

    @ParameterizedTest
    @ValueSource(strings = {"5", "",})
    void shouldRaiseErrorWhenWrongFormatMonthProvided(String month) {
        String year = DataHelper.getFutureYearInYY(2);
        MainPage mainPage = new MainPage();
        mainPage.selectCreditOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCreditHeaderVisible();

        cardWidget.enterCardNumber(dataHelper.getApprovedCardNumber());

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(dataHelper.getValidName());

        cardWidget.enterCvc(dataHelper.getCorrectCvcFormat());

        cardWidget.clickContinueButton();

        cardWidget.checkWrongFormatErrorPresent();

    }

    @ParameterizedTest
    @ValueSource(strings = {"", "1", "22"})
    void shouldRaiseErrorWhenWrongFormatCvcProvided(String cvc) {
        String year = DataHelper.getFutureYearInYY(2);
        String month = DataHelper.generateRandomMonth();
        MainPage mainPage = new MainPage();
        mainPage.selectCreditOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCreditHeaderVisible();

        cardWidget.enterCardNumber(dataHelper.getApprovedCardNumber());

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(dataHelper.getValidName());

        cardWidget.enterCvc(cvc);

        cardWidget.clickContinueButton();

        cardWidget.checkWrongFormatErrorPresent();

    }

    @Test
    void shouldRaiseErrorWhenEmptyOwnerNameProvided() {
        String year = DataHelper.getFutureYearInYY(2);
        String month = DataHelper.generateRandomMonth();
        MainPage mainPage = new MainPage();
        mainPage.selectCreditOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCreditHeaderVisible();

        cardWidget.enterCardNumber(dataHelper.getApprovedCardNumber());

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName("");

        cardWidget.enterCvc(dataHelper.getCorrectCvcFormat());

        cardWidget.clickContinueButton();

        cardWidget.checkFieldMustBeFilledErrorPresent();

    }

    @Test
    void shouldRaiseErrorWhenNonValidMonthProvided() {
        String year = DataHelper.getFutureYearInYY(2);
        String monthInvalid = "13";
        MainPage mainPage = new MainPage();
        mainPage.selectCreditOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCreditHeaderVisible();

        cardWidget.enterCardNumber(dataHelper.getApprovedCardNumber());

        cardWidget.enterMonth(monthInvalid);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(dataHelper.getValidName());

        cardWidget.enterCvc(dataHelper.getCorrectCvcFormat());

        cardWidget.clickContinueButton();

        cardWidget.checkInvalidExpireDateErrorPresent();


    }

    @Test
    void shouldRaiseErrorWhenEmptyNameProvided() {
        String year = DataHelper.getFutureYearInYY(2);
        String month = DataHelper.generateRandomMonth();
        MainPage mainPage = new MainPage();
        mainPage.selectCreditOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCreditHeaderVisible();

        cardWidget.enterCardNumber(dataHelper.getApprovedCardNumber());

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName("");

        cardWidget.enterCvc(dataHelper.getCorrectCvcFormat());

        cardWidget.clickContinueButton();

        cardWidget.checkFieldMustBeFilledErrorPresent();

    }

    @Test
    void shouldRaiseErrorWhenExpiredCardProvided() {
        String year = DataHelper.getPrevYearInYY(2);
        String month = DataHelper.generateRandomMonth();
        MainPage mainPage = new MainPage();
        mainPage.selectCreditOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCreditHeaderVisible();

        cardWidget.enterCardNumber(dataHelper.getApprovedCardNumber());

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(dataHelper.getValidName());

        cardWidget.enterCvc(dataHelper.getCorrectCvcFormat());

        cardWidget.clickContinueButton();

        cardWidget.checkCardExpiredErrorPresent();

    }

    @Test
    void shouldSucceedWhenValidCardWithExpireDateThisMonthProvided() {
        String year = DataHelper.getFutureYearInYY(0);
        String month = DataHelper.getCurrentMonth();
        MainPage mainPage = new MainPage();
        mainPage.selectCreditOption();
        CardWidget cardWidget = new CardWidget();
        cardWidget.checkIfBuyWithCreditHeaderVisible();

        cardWidget.enterCardNumber(dataHelper.getApprovedCardNumber());

        cardWidget.enterMonth(month);

        cardWidget.enterYear(year);

        cardWidget.enterCardOwnerName(dataHelper.getValidName());

        cardWidget.enterCvc(dataHelper.getCorrectCvcFormat());

        cardWidget.clickContinueButton();

        cardWidget.checkSuccessfulNotification();

    }
}
