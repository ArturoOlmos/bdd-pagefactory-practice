package pageObjects;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class paymentPage {
	public paymentPage(WebDriver driver) {
	     PageFactory.initElements(driver, this);
	     wait = new WebDriverWait(driver,30);
	 }
	
	WebDriverWait wait;
	
	@FindBy(how = How.ID, using = "order_name") 
	 private WebElement nameField;
	
	@FindBy(how = How.ID, using = "order_address") 
	 private WebElement addressField;
	
	@FindBy(how = How.ID, using = "order_email") 
	 private WebElement emailField;
	
	@FindBy(how = How.ID, using = "order_pay_type") 
	 private WebElement payTypeField;
	
	@FindBy(how = How.XPATH, using = "//option[@value='Check']") 
	 private WebElement checkPayment;
	
	@FindBy(how = How.XPATH, using = "//option[@value='Credit card']") 
	 private WebElement creditCardPayment;
	
	@FindBy(how = How.XPATH, using = "//option[@value='Purchase order']") 
	 private WebElement purchaseOrderPayment;
	
	@FindBy(how = How.XPATH, using = "//input[@value='Place Order']")
	 private WebElement placeOrderButton;
	
	
	public void enterName(String userName) {
		nameField.sendKeys(userName);
	}
	
	public void enterAddress(String userAddress) {
		addressField.sendKeys(userAddress);
	}
	
	public void enterEmail(String userEmail) {
		emailField.sendKeys(userEmail);
	}
	
	public void enterPayment(String userPayment) {
		wait.until(ExpectedConditions.elementToBeClickable(payTypeField));
		payTypeField.click();
		if (userPayment.equals("check")){
			wait.until(ExpectedConditions.elementToBeClickable(checkPayment));
			checkPayment.click();
			return;
		}
		if (userPayment.equals("credit card")){
			wait.until(ExpectedConditions.elementToBeClickable(creditCardPayment));
			creditCardPayment.click();
			return;
		}
		if (userPayment.equals("purchase order")){
			wait.until(ExpectedConditions.elementToBeClickable(purchaseOrderPayment));
			purchaseOrderPayment.click();
			return;
		}
		System.out.println("Typed payment doesn't exists.....credit card selected by default");
		creditCardPayment.click();
	}
	
	public void placeOrder() {
		wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
		placeOrderButton.click();
	}
	
	

}