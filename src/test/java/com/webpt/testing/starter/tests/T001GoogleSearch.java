package com.webpt.testing.starter.tests;

import com.webpt.testing.starter.BaseTest;

/**
 * Runner to execute the client related steps.
 */
public class T001GoogleSearch extends BaseTest {

    @Override
    public String[] filterSteps() {
        return new String[] {"GoogleStepDefinitions"};
    }

    @Override
    protected String getStoryPath() {
        return "stories/tests/google/site_search/broad_search.story";
    }

}
