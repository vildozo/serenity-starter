package com.webpt.testing.starter.tests;

import com.webpt.testing.starter.BaseTest;

/**
 * Runner to execute the client related steps.
 */
public class T003YahooSiteSearch extends BaseTest {

    @Override
    public String[] filterSteps() {
        return new String[] {"YahooStepDefinitions"};
    }

    @Override
    protected String getStoryPath() {
        return "stories/tests/yahoo/search/broad_search.story";
    }

}
