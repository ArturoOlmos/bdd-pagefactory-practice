package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.mainPage;
import pageObjects.detailsPage;
import pageObjects.confirmationPage;
import pageObjects.paymentPage;

public class steps {
	
	WebDriver driver;
	mainPage mainPage;
	detailsPage detailsPage;
	confirmationPage confirmationPage;
	paymentPage paymentPage;
	double ammount;
	
	@Given("^user is in home page$")
	public void user_is_in_home_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	
	}

	@When("^user selects puppy number (\\d+)$")
	public void user_selects_a_puppy(int puppyNum) throws Throwable {
		mainPage.selectPuppy(puppyNum -1);
	}
	
	@When("^user select adopt$")
	public void user_select_adopt() throws Throwable {
		detailsPage.clickAdopt();
	}

	@When("^user completes adoption$")
	public void user_completes_adoption() throws Throwable {
		confirmationPage.completeAdoption();
	}

	@When("^user enter payment details selecting \"([^\"]*)\" as payment method$")
	public void user_enter_payment_details(String paymentMethod) throws Throwable {
		paymentPage.enterName("Test boy");
		paymentPage.enterAddress("Test Address");
		paymentPage.enterEmail("testboy@test.com");
		paymentPage.enterPayment(paymentMethod);
		paymentPage.placeOrder();
	}

	@Then("^thank you message is displayed$")
	public void thank_you_message_is_displayed() throws Throwable {
	    mainPage.validateThankYou();
	}
	
	@When("^user selects top (\\d+) puppies from page (\\d+) for adoption$")
	public void user_selects_top_puppies_from_page_for_adoption(int puppiesNum, int pageNum) throws Throwable {
		for(int i=1 ; i<=puppiesNum ; i++){
			if(pageNum>1) {
				mainPage.clickNextMultipleTimes(pageNum);
			}
			if(puppiesNum>=mainPage.getPuppiesOnPage() && i==1) {
				puppiesNum=mainPage.getPuppiesOnPage();
				System.out.println("Only "+ puppiesNum + " puppy(es) was/were found in page");
			}
			System.out.println("***Puppy " + i + " of " + puppiesNum + " ***");
			mainPage.selectPuppy(i-1);
			ammount+=detailsPage.getPuppyFees();
			detailsPage.clickAdopt();
			if (i<puppiesNum) {
				confirmationPage.adoptAnother();
			}
		}	
	}
	
	@Then("^correct ammount should be displayed$")
	public void correct_ammount_should_be_displayed() throws Throwable {
	    confirmationPage.validateCartTotal(ammount);
	}
	
	@When("^user selects all extra items$")
	public void user_selects_all_extra_items() throws Throwable {
		confirmationPage.selectAllExtraItems();
		ammount+=confirmationPage.getExtraItemsAmmount();
	}
	
	 @Before
	 public void BeforeSteps() {
		System.setProperty("webdriver.chrome.driver","C:\\eclipse-workspace\\framework\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://puppies.herokuapp.com");
		mainPage = new mainPage(driver);
		detailsPage = new detailsPage(driver);
		confirmationPage = new confirmationPage(driver);
		paymentPage = new paymentPage(driver);
		ammount=0;
	 }	
	
	
	 @After
	 public void AfterSteps() {
		 driver.close();
		 ammount=0;
	 }	
	
}
