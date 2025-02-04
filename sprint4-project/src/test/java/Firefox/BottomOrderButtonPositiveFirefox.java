package Firefox;

import POM.HomePageSamokat;
import POM.OrderFormSamokat;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Date;

@RunWith(Parameterized.class)
public class BottomOrderButtonPositiveFirefox {

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final Date orderDate;
    private final String duration;

    public BottomOrderButtonPositiveFirefox(String name, String surname, String address, String metro, String phoneNumber,
                                         Date orderDate, String duration) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.orderDate = orderDate;
        this.duration = duration;
    }

    @Parameterized.Parameters
    public static Object[][] getAnswers() {

        Date today = new Date();
        return new Object[][] {
                {"Иван", "Иванов", "ул. Рандомная", "Технопарк", "89260000000",
                        new Date(today.getTime() + (3000 * 60 * 60 * 24)), "двое суток"},
                {"Петр", "Петров", "ул. Рандомская", "Кунцевская", "89260000001",
                        new Date(today.getTime() + (4000 * 60 * 60 * 24)), "семеро суток"},
        };
    }

    WebDriver driver = new FirefoxDriver();

    @Test
    public void test() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HomePageSamokat objHomePage = new HomePageSamokat(driver);
        objHomePage.scrollToOrderButton();
        objHomePage.clickBottomOrderButton();
        OrderFormSamokat objOrderForm = new OrderFormSamokat(driver);
        objOrderForm.waitOnFormLoad();
        objOrderForm.fillForm1(name, surname, address, metro, phoneNumber);
        objOrderForm.clickNextButton();
        objOrderForm.waitOnFormLoad();
        objOrderForm.fillForm2(orderDate, duration);
        objOrderForm.clickBottomOrderButton();
        objOrderForm.clickYesButton();
        assertTrue("Тест не достиг формы подтверждения заказа.", objOrderForm.checkEndForm());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
