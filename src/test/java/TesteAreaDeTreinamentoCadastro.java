import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteAreaDeTreinamentoCadastro {
    private static WebDriver driver;

    @BeforeAll
    public static void abreNavegador() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "\\src\\test\\resources\\campo-treinamento\\componentes.html");
    }

    @AfterAll
    public static void fechaNavegador() {
        driver.close();
        driver.quit();
    }

    @Test
    public void teste20CadastroCompleto(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Matheus");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Santos");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Superior");

        new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("O que eh esporte?");

        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Sugestao 1");

        driver.findElement(By.id("elementosForm:cadastrar")).click();

        assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
        assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Matheus"));
        assertTrue(driver.findElement(By.id("descSobrenome")).getText().endsWith("Santos"));
        assertTrue(driver.findElement(By.id("descComida")).getText().endsWith("Pizza"));
        assertTrue(driver.findElement(By.id("descEscolaridade")).getText().endsWith("superior"));
        assertTrue(driver.findElement(By.id("descEsportes")).getText().endsWith("O que eh esporte?"));
    }
}
