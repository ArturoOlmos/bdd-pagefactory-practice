Feature: Title of your feature
  
  @regression
  Scenario: Adopt a puppy
    Given user is in home page
    When user selects puppy number 1
    And user select adopt
    And user completes adoption
    And user enter payment details selecting "bla" as payment method
    Then thank you message is displayed
    
  @regression2
  Scenario: validate multiple puppies cart
  	Given user is in home page
    When user selects top 100 puppies from page 2 for adoption
    And user selects all extra items
    Then correct ammount should be displayed
    
  @regression2
  Scenario: validate multiple puppies cart
  	Given user is in home page
    When user selects top 3 puppies from page 1 for adoption
    Then correct ammount should be displayed