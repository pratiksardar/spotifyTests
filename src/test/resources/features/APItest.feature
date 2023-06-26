Feature: Spotify API Test

  Scenario: Retrieve Track
    Given I have obtained the access token
    When I retrieve a track with ID "6nek1Nin9q48AVZcWs9e9D"
    Then the track artist should be "Coldplay"

  Scenario: Search Tracks by Artist
    Given I have obtained the access token
    When I retrieve a track with ID "6nek1Nin9q48AVZcWs9e9D"
    Then I search for tracks by the same artist
    And the search results should include tracks by the artist

  Scenario: Create a new playlist
    Given I have a valid access token
    When I create a new playlist with name "My New Playlist"
    Then I should see a new playlist ID

