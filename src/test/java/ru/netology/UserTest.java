package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static java.time.Duration.ofSeconds;

public class UserTest {

    SelenideElement success = $x("//div[@data-test-id='success-notification']");
    SelenideElement reschedule = $x("//div[@data-test-id='reschedule-notification']");
    SelenideElement error = $x("//div[@data-test-id='error-notification']");

    @BeforeEach
    public void setUp() {
        open("http://localhost:9999/");
    }

    @Test
    public void happyPath() {

        UserData user = UserGenerator.generateUser(4);

        $("[data-test-id='city'] input").val(user.getCity());
        $x(".//span[@data-test-id='date']//input[@class='input__control']").val(user.getDate());
        $("[data-test-id='name'] input").val(user.getName());
        $("[data-test-id='phone'] input").val(user.getPhone());
        $x(".//label[@data-test-id='agreement']").click();
        $x(".//button//ancector::span[contains(text(),'Запланировать')]").click();

        success.should(visible, ofSeconds(15));
        success.$x(".//div[@class='notification__content']").should(text("Встреча успешно забронирована на " +
                user.getDate()));
        success.$x(".//button").click();
        success.should(hidden);

        $x(".//span[@data-test-id='date']//input[@class='input__control']").
                val(UserGenerator.generateDate(2));
        $x(".//button//ancector::span[contains(text(),'Запланировать')]").click();

        reschedule.should(visible, ofSeconds(15));
        reschedule.$x(".//span[contains(text(),'Перепланировать')]//ancector::button").click();
        success.should(hidden);

        success.should(visible, ofSeconds(15));
        success.$x(".//div[@class='notification__content']").should(text("Встреча успешно забронирована на " +
                UserGenerator.generateDate(2)));
        success.$x(".//button").click();
        success.should(hidden);
    }
}
