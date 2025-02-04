package POM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

// описание необходимых для тестирования элементов заглавной страницы
public class HomePageSamokat {

    private WebDriver driver;

    public HomePageSamokat (WebDriver driver){
        this.driver = driver;
    }

    //сначала написал локаторы для кнопок и текста которые нужны
    //private By QuestionsButton1 = By.id("accordion__heading-0");
    //private By QuestionsButton2 = By.id("accordion__heading-1");
    //private By QuestionsButton3 = By.id("accordion__heading-2");
    //private By QuestionsButton4 = By.id("accordion__heading-3");
    //private By QuestionsButton5 = By.id("accordion__heading-4");
    //private By QuestionsButton6 = By.id("accordion__heading-5");
    //private By QuestionsButton7 = By.id("accordion__heading-6");
    //private By QuestionsButton8 = By.id("accordion__heading-7");

    //private By QuestionsAnswer1 = By.id("accordion__panel-0");
    //private By QuestionsAnswer2 = By.id("accordion__panel-1");
    //private By QuestionsAnswer3 = By.id("accordion__panel-2");
    //private By QuestionsAnswer4 = By.id("accordion__panel-3");
    //private By QuestionsAnswer5 = By.id("accordion__panel-4");
    //private By QuestionsAnswer6 = By.id("accordion__panel-5");
    //private By QuestionsAnswer7 = By.id("accordion__panel-6");
    //private By QuestionsAnswer8 = By.id("accordion__panel-7");

    private By TopOrderButton = By.className("Button_Button__ra12g");
    private By BottomOrderButton = By.className("Button_Middle__1CSJM");

    //но когда писал код, понял что для кликов скорее всего будет лучше вот что-то в таком духе сделать, иначе
    //надо делать либо перебор или ещё как-то исхищряться и не уверен как что-то формата QuestionsButton+buttonNumber
    //можно было бы передать в findElement() с транcформацией в вышеперечисленный QuestionsButton#
    public void clickQuestionsButton(int buttonNumber) {
        String buttonId = "accordion__heading-" + buttonNumber;
        By buttonLocator = By.id(buttonId);
        driver.findElement(buttonLocator).click();
    }

    public boolean checkAnswerText(int buttonNumber, String expectedText) {
        String buttonId = "accordion__panel-" + buttonNumber;
        By answerLocator = By.id(buttonId);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return (expectedText.equals(driver.findElement(answerLocator).getText()));
    }

    public void scrollToButton(int buttonNumber) {
        String buttonId = "accordion__heading-" + buttonNumber;
        WebElement element = driver.findElement(By.id(buttonId));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollToOrderButton() {
        WebElement element = driver.findElement(BottomOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickTopOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(TopOrderButton));
        driver.findElement(TopOrderButton).click();
    }

    public void clickBottomOrderButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(BottomOrderButton));
        driver.findElement(BottomOrderButton).click();
    }

}
