Meta:

Narrative:
In order to find images of the things I'm looking for
As an online consumer
I need to be able to use Google to enter broad search terms and find meaningful images

Scenario: Search Google for Images
Given I am on the Google search page
When I select the 'Images' option from the More menu
And I enter the search term '<term>'
And I click the search icon
Then I should see the '<result>' image in the search results

Examples:
|term|result|
|amazon|amazon.com|
|yahoo|yahoo.com|