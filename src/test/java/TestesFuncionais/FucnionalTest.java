package TestesFuncionais;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FucnionalTest {

	public WebDriver acessarAplicacao() {
		System.setProperty("(webdriver.chrome.driver", "tasks-funtionalTest\\Driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		WebDriver driver = acessarAplicacao();

		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Web");
			driver.findElement(By.id("task")).sendKeys("Teste via Web");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			driver.findElement(By.id("saveButton")).click();

			Assert.assertEquals("Sucess!", driver.findElement(By.id("message")).getText());
		} finally {
			driver.quit();
		}

	}

	@Test
	public void naoDeveSalvarTarefaComDataPassada() {
		WebDriver driver = acessarAplicacao();

		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Web");
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
			driver.findElement(By.id("saveButton")).click();

			Assert.assertEquals("Due date must not be in past", driver.findElement(By.id("message")).getText());

		} finally {
			driver.quit();
		}

	}

	@Test
	public void naoDeveSalvarTarefaSemDescricao() {
		WebDriver driver = acessarAplicacao();

		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			driver.findElement(By.id("saveButton")).click();

			Assert.assertEquals("Fill the task description", driver.findElement(By.id("message")).getText());

		} finally {
			driver.quit();
		}

	}

	@Test
	public void naoDeveSalvarTarefaSemData() {
		WebDriver driver = acessarAplicacao();

		try {
			driver.findElement(By.id("addTodo")).click();
			driver.findElement(By.id("task")).sendKeys("Teste via Web");
			driver.findElement(By.id("saveButton")).click();

			Assert.assertEquals("Fill the due date", driver.findElement(By.id("message")).getText());

		} finally {
			driver.quit();
		}

	}

}
