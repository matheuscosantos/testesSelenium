import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TesteAreaDeTreinamento {

    private static WebDriver driver;

    @BeforeAll
    public static void abreNavegador() {
        System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void fechaNavegador() {
        driver.quit();
    }

    @Test
    public void teste00AbreAreaDeTreinamento(){
        driver.get("file:///" + System.getProperty("user.dir") + "\\src\\test\\resources\\campo-treinamento\\componentes.html");
    }

    @Test
    public void teste01TextFieldNome(){
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste do nome");
        String valorAtual = driver.findElement(By.id("elementosForm:nome")).getAttribute("value");
        assertEquals("Teste do nome",valorAtual);
    }

    @Test
    public void teste02TextFieldSobrenome(){
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Teste do sobrenome");
        String valorAtual = driver.findElement(By.id("elementosForm:sobrenome")).getAttribute("value");
        assertEquals("Teste do sobrenome",valorAtual);
    }

    @Test
    public void teste03TextAreaSugestoes(){
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste das sugest\u00f5es\nSugest\u00e3o 01\nSugest\u00e3o 02");
        String valorAtual = driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value");
        assertEquals("Teste das sugest\u00f5es\nSugest\u00e3o 01\nSugest\u00e3o 02",valorAtual);
    }

    @Test
    public void teste04RadioButtonMasculino(){
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        assertEquals(driver.findElement(By.id("elementosForm:sexo:0")).isSelected(),true);
    }

    @Test
    public void teste05RadioButtonFeminino(){
        driver.findElement(By.id("elementosForm:sexo:1")).click();
        assertEquals(driver.findElement(By.id("elementosForm:sexo:1")).isSelected(),true);
    }

    @Test
    public void teste06CheckBoxComidaFavoritaTodos(){
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:1")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();

        assertEquals(driver.findElement(By.id("elementosForm:comidaFavorita:0")).isSelected(),true);
        assertEquals(driver.findElement(By.id("elementosForm:comidaFavorita:1")).isSelected(),true);
        assertEquals(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected(),true);
        assertEquals(driver.findElement(By.id("elementosForm:comidaFavorita:3")).isSelected(),true);
    }

    @Test
    public void teste07ComboBoxEscolaridade(){
        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
        combo.selectByVisibleText("Especializacao");
        assertEquals("Especializacao",combo.getFirstSelectedOption().getText());
    }

    @Test
    public void teste08ComboBoxEscolaridadeValores(){
        WebElement elemento = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(elemento);
        List<WebElement> opcoes = combo.getOptions();

        boolean encontrou = false;
        for(WebElement opcao : opcoes){
            if(opcao.getText().equals("Mestrado")){
                encontrou = true;
            }
        }
        assertEquals(true,encontrou);
    }

    @Test
    public void teste09ComboBoxEsportesMultiplasSelecoes(){
        WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(elemento);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");
        List<WebElement> todosElementosSelecionados = combo.getAllSelectedOptions();
        assertEquals(3,todosElementosSelecionados.size());
    }

    @Test
    public void teste10ComboBoxEsportesDesselecionar(){
        WebElement elemento = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(elemento);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");
        combo.deselectByVisibleText("Natacao");
        List<WebElement> todosElementosSelecionados = combo.getAllSelectedOptions();
        assertEquals(2,todosElementosSelecionados.size());
    }

    @Test
    public void teste11ButtonCliqueSimples(){
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();
        assertEquals("Obrigado!",botao.getAttribute("value"));
    }

    @Test
    public void teste12LinkClick(){
        WebElement botao = driver.findElement(By.linkText("Voltar"));
        botao.click();
        String texto = driver.findElement(By.id("resultado")).getText();
        assertEquals("Voltou!",texto);
    }

    @Test
    @Disabled
    public void teste13Ignore(){
        WebElement botao = driver.findElement(By.linkText("Voltar"));
        botao.click();
    }

    @Test
    public void teste14BuscaTextoNaPagina(){
        WebElement corpoDaPagina = driver.findElement(By.tagName("body"));
        assertTrue(corpoDaPagina.getText().contains("Campo de Treinamento"));
    }

    @Test
    public void teste15BuscaTextoPorTag(){
        String titulo = driver.findElement(By.tagName("h3")).getText();
        assertEquals("Campo de Treinamento", titulo);
    }

    @Test
    public void teste16LocalizarTextoPorClasse(){
        String titulo = driver.findElement(By.className("facilAchar")).getText();
        assertEquals("Cuidado onde clica, muitas armadilhas...",titulo);
    }

    @Test
    public void teste17AlertSimples(){
        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Alert Simples",alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();
    }

    @Test
    public void teste18AlertConfirm(){
        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Confirm Simples",alert.getText());
        alert.accept();
        assertEquals("Confirmado",alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();
    }

    @Test
    public void teste19Prompt(){
        driver.findElement(By.id("prompt")).click();
        Alert alert = driver.switchTo().alert();
        assertEquals("Digite um numero", alert.getText());
        alert.sendKeys("Matheus");
        alert.accept();
        assertEquals("Era Matheus?", alert.getText());
        alert.accept();
        assertEquals(":D",alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();
    }

    @Test
    public void teste20Iframe(){
        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();
        Alert alert = driver.switchTo().alert();
        String mensagemAlert = alert.getText();
        assertEquals("Frame OK!",alert.getText());
        alert.accept();
        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(mensagemAlert);
    }

    @Test
    public void teste21Janela(){
        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Testa janela");
        driver.close();
        driver.switchTo().window("");
    }

    @Test
    public void teste22WindowHandler(){
        driver.findElement(By.id("buttonPopUpHard")).click();
        driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
        driver.findElement(By.tagName("textarea")).sendKeys("Teste");
        driver.close();
        driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
    }
}
