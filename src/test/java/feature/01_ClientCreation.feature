Feature: Create a corporate  Client with address 
  
  Description: The purpose of this feature is to test the client creation with add address

  Background: Log in to the site
    Given User is on Login Page
    Then Login to the site with "cAdhikari" and "O6Ycku+l"
    Then Home Page was populated

  @AddPrivateClient
  Scenario: Add Private Client with all Details
    Given User Click Client Search Link
    Then Click on Add Client Button and Filled up all Private Client information

  @addAddress
  Scenario: Add address  to the Client
    Given User Click Client Search Link
    Then Search the client and add address to the client
