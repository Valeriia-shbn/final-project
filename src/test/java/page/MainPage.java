package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    SelenideElement buttonBuyCredit = $x("//button[.//span[@class='button__text' and text()='Купить в кредит']]");
    SelenideElement buttonBuy = $x("//button[@role='button' and .//span[text()='Купить']]");

    public MainPage(){
        buttonBuy.shouldBe(visible);
    }

    public void selectBuyOption(){
        buttonBuy.shouldBe(visible).click();
    }

    public void selectCreditOption(){
        buttonBuyCredit.shouldBe(visible).click();
    }
}
