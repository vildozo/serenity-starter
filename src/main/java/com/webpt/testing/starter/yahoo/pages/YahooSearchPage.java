package com.webpt.testing.starter.yahoo.pages;

import com.webpt.testing.starter.common.pages.Page;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;

/**
 * This is the page object that knows how to interact with the Google search page.
 */
@DefaultUrl("https://www.yahoo.com")
public class YahooSearchPage extends Page {

    @FindBy(id="uh-search-box")
    private WebElementFacade searchField;

    @FindBy(id="uh-search-button")
    private WebElementFacade searchButton;

    public YahooSearchPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitForReady() {
        super.waitForReady();
        waitFor(searchField);
    }

    public void enterSearchTerm(String searchTerm){
        typeInto(searchField, searchTerm);
    }

    public void clickSearchButton(){
        clickOn(searchButton);

        // just waiting for a bit here. You really need to wait for some status to change on the screen.
        // this is just implement this way for simplicity
        waitABit(3000);
    }

}
