package POM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.Date;

public class OrderFormSamokat {

    private WebDriver driver;

    public OrderFormSamokat (WebDriver driver){
        this.driver = driver;
    }

    private By FormHeader = By.xpath("//div[@class='Order_Header__BZXOb']");
    private By NextButton = By.xpath("//button[text()='Далее']");
    private By NameInputField = By.xpath("//input[@placeholder='* Имя']");
    private By SurnameInputField = By.xpath("//input[@placeholder='* Фамилия']");
    private By AddressInputField = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private By MetroSelectField = By.xpath("//input[@placeholder='* Станция метро']");
    private By PhoneNumberInputField = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private By OrderDateSelectField = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private By DurationSelectField = By.xpath("//div[text()='* Срок аренды']");
    private By YesButton = By.xpath("//button[text()='Да']");
    private By EndFormHeader = By.xpath("//div[text()='Заказ оформлен']");
    private By BottomOrderButton = By.xpath("/html/body/div/div/div[2]/div[3]/button[2]");


    public void waitOnFormLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(FormHeader));
    }

    public void inputName(String name){
        driver.findElement(NameInputField).sendKeys(name);
    }

    public void inputSurname(String surname){
        driver.findElement(SurnameInputField).sendKeys(surname);
    }

    public void inputAddress(String address){
        driver.findElement(AddressInputField).sendKeys(address);
    }

    public void selectMetro(String metro){
        driver.findElement(MetroSelectField).click();
        WebElement element = driver.findElement(By.xpath("//div[text()='" + metro + "']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.xpath("//div[text()='" + metro + "']")).click();
    }

    public void inputPhoneNumber(String phonenumber){
        driver.findElement(PhoneNumberInputField).sendKeys(phonenumber);
    }

    public void fillForm1(String name, String surname, String address, String metro, String phonenumber){
        inputName(name);
        inputSurname(surname);
        inputAddress(address);
        selectMetro(metro);
        inputPhoneNumber(phonenumber);
    }

    public void clickNextButton() {
        driver.findElement(NextButton).click();
    }

    //знаю что это надо расписать подробнее с прокликиванием вправо если заказ на будущее до нужного месяца,
    //просто не успеваю реализовать это так что заказываю только на текущий месяц в тесте, чтобы тест постоянно
    //выполнялся в тестовых данных поставил текущую дату +1-2 дня
    public void selectOrderDate(Date orderDate){
        driver.findElement(OrderDateSelectField).click();
        driver.findElement(By.xpath("//div[text()='" + orderDate.getDay() + "']")).click();
    }

    public void selectDuration(String duration){
        driver.findElement(DurationSelectField).click();
        WebElement element = driver.findElement(By.xpath("//div[text()='" + duration + "']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        driver.findElement(By.xpath("//div[text()='" + duration + "']")).click();
    }

    public void fillForm2(Date orderDate, String duration){
        selectOrderDate(orderDate);
        selectDuration(duration);
    }

    public void clickYesButton() {
        driver.findElement(YesButton).click();
    }

    public boolean checkEndForm() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(EndFormHeader));
        return true;
    }

    public void clickBottomOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(BottomOrderButton));
        driver.findElement(BottomOrderButton).click();
    }
}
