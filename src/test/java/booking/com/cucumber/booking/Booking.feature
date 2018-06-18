@tag
Feature: Search Hotels
  As a site user
    I want to be able to search hotels

  Background: 
    Given I am on the results screen

  @tag1
  Scenario Outline: Efetuar consulta
    When I select "<filter>"
    Then the name of the hotel "<hotelname>" will appear
    And I will have the result "<islisted>" on the screen

    Examples: 
      | filter  | hotelname             | islisted |
      | Sauna   | Limerick Strand Hotel | true     |
      | Sauna   | George Limerick Hotel | false    |
      | 5 stars | The Savoy Hotel       | true     |
      | 5 stars | George Limerick Hotel | false    |
