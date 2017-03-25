package webtest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.opencsv.CSVReader;

public class WebTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://121.193.130.195:8080";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testWeb() throws Exception {
    driver.get(baseUrl + "/");
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("3014218060");
    driver.findElement(By.id("pwd")).clear();
    driver.findElement(By.id("pwd")).sendKeys("218060");
    driver.findElement(By.id("submit")).click();
    String id = driver.findElement(By.xpath(".//*[@id='table-main']/tr[2]/td[2]")).getText();
    String github = driver.findElement(By.xpath(".//*[@id='table-main']/tr[3]/td[2]")).getText();
    String csvGithub = getGithub(id);
    assertEquals(github, csvGithub);
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  
  private String getGithub(String id) throws Exception
  {
	 CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Kirin\\Desktopinputgit.csv"));
	 List<String[]> mylist = reader.readAll();
	 String result = "";
	 for(int i = 0;i<mylist.size();i++)
	 {
		 if(mylist.get(i)[0].equals(id))
			 result = mylist.get(i)[2];
	 }
	 return result;
  }
}
