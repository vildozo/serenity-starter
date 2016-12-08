package com.webpt.testing.starter.google.steps;

import com.webpt.testing.starter.google.steps.GoogleSteps;
import net.thucydides.core.annotations.Steps;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

/**
 * Maps google related steps to their implementation.
 *
 * Code at this level should be minimal or else the this file will get out of controller very quickly.
 * Code in each method should only be a few lines that make calls to Steps classes.
 *
 * You can have multiple steps classes, but I'm not sure of the best naming convention for the variables when
 * you encounter this situation.
 *
 * Screen play pattern may be able to be implemented here but I'm not sure yet.
 *
 */
public class GoogleStepDefinitions {

    @Steps
    private GoogleSteps user;

    @Given("I am on the Google search page")
    public void openGoogleSearchPage(){
        user.opensGoogleSearchPage();
    }

    @When("I enter the search term '$searchTerm'")
    public void searchFor(String searchTerm){
        user.entersSearchTerm(searchTerm);
    }

    @When("I click the search icon")
    public void clickSearch(){
        user.clicksSearch();
    }

    @Then("I should see the '$term' in the search results")
    public void searchResultsContain(String term){
        user.seesSearchResultsContain(term);
    }

}
