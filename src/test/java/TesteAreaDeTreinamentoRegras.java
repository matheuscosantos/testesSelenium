import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteAreaDeTreinamentoRegras {

    private static WebDriver driver;
    private WebElement searchBox;
    private WebElement searchResults;

    @BeforeAll
    public static void abreNavegador() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "\\src\\test\\resources\\campo-treinamento\\componentes.html");
    }

    @AfterAll
    public static void fechaNavegador() {
        //driver.close();
    }

    @AfterEach
    public void limpaFormulario(){
        driver.findElement(By.id("elementosForm:nome")).clear();
        driver.findElement(By.id("elementosForm:sobrenome")).clear();
    }

    @Test
    public void teste01NomeObrigatorio(){
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Nome eh obrigatorio",alert.getText());
    }

    @Test
    public void teste02SobrenomeObrigatorio(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Sobrenome eh obrigatorio",alert.getText());
    }
}
