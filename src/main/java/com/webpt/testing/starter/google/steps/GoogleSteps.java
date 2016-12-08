package com.webpt.testing.starter.google.steps;

import com.webpt.testing.starter.google.pages.GoogleSearchPage;
import net.thucydides.core.annotations.Step;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This is the detail step implementation.
 *
 * We may be able to implement the screen play pattern here, but not sure yet.
 */
public class GoogleSteps {

    // Serenity will automatically provide an instance of this class.
    private GoogleSearchPage searchPage;

    @Step
    public void opensGoogleSearchPage(){
        searchPage.open();
        searchPage.waitForReady();
    }

    @Step
    public void entersSearchTerm(String searchTerm){
        searchPage.enterSearchTerm(searchTerm);
    }

    @Step
    public void clicksSearch(){
        searchPage.clickSearchButton();
    }

    @Step
    public void seesSearchResultsContain(String term){
        assertThat(searchPage.containsText(term))
                .describedAs("Search results should have contained value ["+term+"]")
                .isTrue();
    }

}
