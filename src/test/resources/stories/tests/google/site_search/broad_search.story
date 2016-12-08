Meta:

Narrative:
In order to find internet sites that have the information I'm looking for
As an online consumer
I need to be able to use Google to enter broad search terms and get meaningful site results

Scenario: Search Google for Amazon
Given I am on the Google search page
When I enter the search term 'amazon'
And I click the search icon
Then I should see the 'www.amazon.com' in the search results

Scenario: Search Google for Ebay
Given I am on the Google search page
When I enter the search term 'ebay'
And I click the search icon
Then I should see the 'www.ebay.com' in the search results