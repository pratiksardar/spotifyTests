Feature: Spotify Search
  As a user
  I want to search for an artist on Spotify
  So that I can listen to their songs

  Scenario: Search for Coldplay
    Given I am on the Spotify website
    When I search for "Coldplay"
    Then I should see search results related to "Coldplay"
