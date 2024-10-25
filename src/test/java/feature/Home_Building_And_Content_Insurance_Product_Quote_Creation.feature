Feature: Test Directors and Officers Liability All product
  Description: The purpose of this feature is to test the create policy,Override premium, change broker commission and bind the policy
  of Directors and Officers Liability All product

  Background: Log in to the site
    Given User is on Login Page
    Then Login to the site with "cAdhikari" and "O6Ycku+l"
    Then Home Page was populated
    
 @Home_Building_And_Content_Insurance_Product_Quote_Create
  Scenario: Create an initial Quote for Home Building And Content Insurance Product
    Given User Click Client Search Link
    Then Search Client and create Quote,Get All Data from the excel