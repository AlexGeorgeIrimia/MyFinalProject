package iabilet.ro;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIaBilet {

    @Test
    public void register() {
        String url = "https://www.iabilet.ro/account/create";
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        WebElement email = driver.findElement(By.id("AccountCreateForm_email"));
        email.sendKeys("justtesting012024@gmail.com");
        WebElement firstName = driver.findElement(By.id("AccountCreateForm_firstname"));
        firstName.sendKeys("Just");
        WebElement lastName = driver.findElement(By.id("AccountCreateForm_lastname"));
        lastName.sendKeys("Testing");
        WebElement password = driver.findElement(By.id("AccountCreateForm_password"));
        password.sendKeys("Justte$ting2024");
        WebElement confirmPassword = driver.findElement(By.id("AccountCreateForm_password_repeat"));
        confirmPassword.sendKeys("Justte$ting2024");
        driver.findElement(By.xpath("//*[@id=\"AccountCreateForm_accept_tc\"]")).click();
        WebElement button = driver.findElement(By.name("create"));
        button.click();

        WebElement error = driver.findElement(By.className("errorMessage"));
        Assert.assertTrue(error.getText().contains("Această adresă de email este folosită de alt cont."));
        driver.close();
    }

    @Test
    public void login() {
        WebDriver driver = new ChromeDriver();
        String url = "https://www.iabilet.ro/auth";
        driver.get(url);
        driver.manage().window().maximize();
        WebElement usernameInput = driver.findElement(By.id("LoginForm_username_or_email"));
        usernameInput.sendKeys("justtesting012024@gmail.com");
        //3.  click password & enter password: "SuperSecretPassword!"
        WebElement passwordInput = driver.findElement(By.id("LoginForm_password"));
        passwordInput.sendKeys("Justte$ting2024");
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();
        WebElement account = driver.findElement(By.xpath("/html/body/header/div[1]/div/div/div[1]/span/strong"));

        String nickname = "Just";
        Assert.assertTrue(account.getText().contains(nickname));
        driver.close();
    }


    @Test
    public void search() {
        String url = "https://www.iabilet.ro";
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        WebElement search = driver.findElement(By.id("q"));
        search.sendKeys("saga");
        new Actions(driver).sendKeys(Keys.ENTER).perform();

        WebElement results = driver.findElement(By.xpath("/html/body/section/div/h1"));
        Assert.assertTrue(results.getText().contains("saga"));
        driver.close();
    }

    @Test
    public void addToBasket() {
        String url = "https://www.iabilet.ro/cauta/?q=saga";
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
        WebElement addToBasket = driver.findElement(By.xpath("/html/body/section/div/div/div/div/div[4]/div/a"));
        addToBasket.click();

        WebElement iaBilet = driver.findElement(By.className("ticket-categ"));
        Assert.assertTrue(iaBilet.getText().contains("Cumpără bilete"));
        driver.close();
    }

    @Test
    public void subscribeToEvent() {
        WebDriver driver = new ChromeDriver();
        String url = "https://www.iabilet.ro/bilete-thievery-corporation-la-arenele-romane-bestmusic-live-94335/?utm_source=HpTopWeb";
        driver.get(url);
        driver.manage().window().maximize();
        WebElement email = driver.findElement(By.id("Subscriber_email"));
        email.sendKeys("justtesting012024@gmail.com");
        WebElement button = driver.findElement(By.name("submit"));
        button.click();
        sleep(500);
        WebElement alert = driver.findElement(By.xpath("//*[@id=\"subscribe-form-container\"]/div/ul/li"));
        Assert.assertTrue(alert.getText().contains("Acest e-mail este inscris deja."));
        driver.close();
    }



    public void sleep(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}



