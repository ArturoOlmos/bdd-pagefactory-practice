package pageObjects;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import org.junit.Assert;

public class confirmationPage {
	public confirmationPage(WebDriver driver) {
	     PageFactory.initElements(driver, this);
	     wait = new WebDriverWait(driver,30);
	 }
	
	WebDriverWait wait;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@value,'Complete the Adoption')]") 
	 private WebElement completeAdoption;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@value,'Adopt Another Puppy')]")
	 private WebElement adoptAnother;
	
	@FindBy(how = How.XPATH, using = "//input[contains(@value,'Change your mind')]") 
	 private WebElement changeYourMind;
	
	@FindBy(how = How.XPATH, using = "//td[@class='total_cell']/h2")
	 private WebElement totalAmount;
	
	@FindAll(@FindBy(how = How.XPATH, using = "//input[@type='checkbox']"))
	 private List<WebElement> extraItemsList;

	@FindAll(@FindBy(how = How.XPATH, using = "//div[contains(@class,'amount') and text()]"))
	 private List<WebElement> extraItemsAmmounts;
	
	public void completeAdoption() {
		wait.until(ExpectedConditions.elementToBeClickable(completeAdoption));
		completeAdoption.click();
	}
	
	public void adoptAnother() {
		wait.until(ExpectedConditions.elementToBeClickable(adoptAnother));
		adoptAnother.click();
	}
	
	public double getTotal() {
		String totalAmountString =totalAmount.getText();
		String priceString = totalAmountString.substring(totalAmountString.lastIndexOf("$")+ 1,totalAmountString.length());
		return Double.parseDouble(priceString);
	}
	
	public void validateCartTotal(double ammount) {
		double cartTotal = getTotal();
		Assert.assertEquals(ammount, cartTotal,0);
		System.out.println("Cart total displayed(" + cartTotal+") is equal to calculated cart ("+ammount+")");				
	}
	
	public void selectAllExtraItems() {
		wait.until(ExpectedConditions.visibilityOfAllElements(extraItemsList));
		for(WebElement e : extraItemsList){
			   e.click();
			}
	}
	
	public double getExtraItemsAmmount() {
		wait.until(ExpectedConditions.visibilityOfAllElements(extraItemsAmmounts));
		double extraItemsAmmount = 0;
		for(WebElement e : extraItemsAmmounts){
			   extraItemsAmmount+= Double.parseDouble(e.getText().substring(e.getText().lastIndexOf("$")+ 1,e.getText().length()));
			}
		return extraItemsAmmount;
	}
	

}