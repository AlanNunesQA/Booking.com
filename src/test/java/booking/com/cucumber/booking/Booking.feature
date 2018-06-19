Feature: Search Hotels
  As a site user
    I want to be able to search hotels

  Background: 
    Given I am on the results screen

  Scenario Outline: Make inquiry
    When I select "<filter>"
    Then the name of the hotel "<hotelName>" will appear
    And I will have the result "<isListed>" on the screen

    Examples: 
      | filter  | hotelName             | isListed |
      | Sauna   | Limerick Strand Hotel | true     |
      | Sauna   | George Limerick Hotel | false    |
      | 5 stars | The Savoy Hotel       | true     |
      | 5 stars | George Limerick Hotel | false    |
