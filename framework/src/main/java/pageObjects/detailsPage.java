package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class detailsPage {
	public detailsPage(WebDriver driver) {
	     PageFactory.initElements(driver, this);
	     wait = new WebDriverWait(driver,30);
	 }
	
	WebDriverWait wait;
	
	@FindBy(how = How.XPATH, using = "//span[contains(@class,'fees')]") 
	 private WebElement fees;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@value,'Adopt Me!')]")
	 private WebElement adoptButton;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Return to List')]") 
	 private WebElement returnLink;
	
	public double getPuppyFees() {
		String feesString =fees.getText();
		String priceString = feesString.substring(feesString.lastIndexOf("$")+ 1,feesString.length());
		return Double.parseDouble(priceString);
	}
	
	public void clickAdopt() {
		wait.until(ExpectedConditions.elementToBeClickable(adoptButton));
		adoptButton.click();
	}
	
	public void clickReturn() {
		wait.until(ExpectedConditions.elementToBeClickable(adoptButton));
		returnLink.click();
	}

}
