package page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CardWidget {

    SelenideElement cardNumberInput = $x("//input[@placeholder='0000 0000 0000 0000']");
    SelenideElement creditHeader = $x("//h3[contains(text(), 'Кредит по данным')]");
    SelenideElement cashHeader = $x("//h3[contains(text(), 'Оплата по карте')]");
    SelenideElement monthInput = $("input[placeholder='08']");
    SelenideElement yearInput = $("input[placeholder='22']");
    SelenideElement ownerInput = $x("//span[contains(@class, 'input') and .//span[@class='input__top' and text()='Владелец']]//input");
    SelenideElement cvcInput = $x("//input[@placeholder='999']");
    SelenideElement continueButton = $x("//span[@class='button__text' and text()='Продолжить']");
    SelenideElement successNotification = $x("//div[contains(@class, 'notification__title') and contains(text(), 'Успешно')]");

    SelenideElement failNotification = $x("//div[contains(@class, 'notification__title') and contains(text(), 'Ошибка')]");
    SelenideElement sendingRequest = $x("//button[contains(text(), 'Отправляем запрос в Банк...')]");

    SelenideElement wrongFormatMsg = $x("//span[contains(text(), 'Неверный формат')]");
    SelenideElement invalidExpireDateMsg = $x("//span[contains(text(), 'Неверно указан срок действия карты')]");

    SelenideElement cardExpiredMsg = $x("//span[contains(text(), 'Истёк срок действия карты')]");
    SelenideElement fieldMustBeFilledMsg = $x("//span[contains(text(), 'Поле обязательно для заполнения')]");

    public void checkWrongFormatErrorPresent() {
        wrongFormatMsg.shouldBe(visible);
    }

    public void checkFieldMustBeFilledErrorPresent() {
        fieldMustBeFilledMsg.shouldBe(visible);
    }

    public void checkInvalidExpireDateErrorPresent() {
        invalidExpireDateMsg.shouldBe(visible);
    }

    public void checkCardExpiredErrorPresent() {
        cardExpiredMsg.shouldBe(visible);
    }

    public void checkIfBuyWithCardHeaderVisible() {
        cashHeader.shouldBe(visible);
    }

    public void checkIfBuyWithCreditHeaderVisible() {
        creditHeader.shouldBe(visible);
    }


    public void enterCardNumber(String cardNumber) {
        cardNumberInput.shouldBe(visible);
        cardNumberInput.click();
        System.out.println("enter card number " + cardNumber);
        cardNumberInput.setValue(cardNumber);
    }

    public void enterMonth(String month) {
        monthInput.shouldBe(visible).setValue(month);
    }

    public void enterYear(String year) {
        yearInput.shouldBe(visible).setValue(year);
    }

    public void enterCvc(String cvc) {
        cvcInput.shouldBe(visible).setValue(cvc);
    }

    public void enterCardOwnerName(String name) {
        ownerInput.shouldBe(visible).setValue(name);
    }

    public void clickContinueButton() {
        continueButton.shouldBe(visible).click();
    }

    public void checkSuccessfulNotification() {
        successNotification.shouldBe(visible, Duration.ofSeconds(10));
    }

    public void checkFailNotification() {
        failNotification.shouldBe(visible, Duration.ofSeconds(10));
    }

}
