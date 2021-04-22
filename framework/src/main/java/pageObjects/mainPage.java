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

import com.google.common.base.Verify;

public class mainPage {
	
	public mainPage(WebDriver driver) {
	     PageFactory.initElements(driver, this);
	     wait = new WebDriverWait(driver,30);
	 }
	
	WebDriverWait wait;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Adopt a Puppy')]") 
	 private WebElement adoptTab;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Learn')]") 
	 private WebElement learnTab;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Animal Shelters')]") 
	 private WebElement animalSelthersTab;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Classfields')]") 
	 private WebElement classfieldsTab;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Message Boards')]") 
	 private WebElement messageBoardsTab;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Pet News')]") 
	 private WebElement petNewsTab;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Next')]") 
	 private WebElement nextLink;
	
	@FindBy(how = How.XPATH, using = "//a[contains(text(),'Previous')]") 
	 private WebElement previousLink;
	
	@FindAll(@FindBy(how = How.XPATH, using = "//input[contains(@value,'View Details')]"))
	 private List<WebElement> puppyList;
	
	@FindAll(@FindBy(how = How.XPATH, using = "//div[@class='name']"))
	 private List<WebElement> puppyNamesList;
	
	@FindAll(@FindBy(how = How.XPATH, using = "//div[@class='details']/h4[1]"))
	 private List<WebElement> puppyBreedList;
	
	@FindAll(@FindBy(how = How.XPATH, using = "//div[@class='details']/h4[2]"))
	 private List<WebElement> puppySexList;
	
	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Thank you')]") 
	 private WebElement thankYouMessage;
	
	
	public void enterLearnTab() {
		wait.until(ExpectedConditions.visibilityOf(learnTab));
		learnTab.click(); 
	}
	
	public void enterAdoptTab() {
		wait.until(ExpectedConditions.visibilityOf(adoptTab));
		adoptTab.click(); 
	}
	
	public void selectPuppy(int puppy_number) {
		wait.until(ExpectedConditions.visibilityOfAllElements(puppyList));
		System.out.println("Selected puppy details");
		System.out.println("Name: "+puppyNamesList.get(puppy_number).getText());
		System.out.println("Breed: "+puppyBreedList.get(puppy_number).getText());
		System.out.println("Sex: "+puppySexList.get(puppy_number).getText());
		puppyList.get(puppy_number).click(); 
	}
	
	public void validateThankYou() {
		Verify.verify(thankYouMessage.isDisplayed());
	}
	
	public void clickNext() {
		wait.until(ExpectedConditions.visibilityOf(nextLink));
		nextLink.click(); 
	}
	
	public void clickNextMultipleTimes(int clickTimes) {
		for (int i=0;i<clickTimes-1;i++){
			wait.until(ExpectedConditions.visibilityOf(nextLink));
			nextLink.click(); 
		}
	}
	
	public int getPuppiesOnPage() {
		wait.until(ExpectedConditions.visibilityOfAllElements(puppyList));
		return puppyList.size(); 
	}
	
	
	
	
}
