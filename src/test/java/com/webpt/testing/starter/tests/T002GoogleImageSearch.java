package com.webpt.testing.starter.tests;

import com.webpt.testing.starter.BaseTest;

/**
 * Runner to execute the client related steps.
 */
public class T002GoogleImageSearch extends BaseTest {

    @Override
    public String[] filterSteps() {
        return new String[] {"GoogleStepDefinitions"};
    }

    @Override
    protected String getStoryPath() {
        return "stories/tests/google/image_search/broad_search.story";
    }

}
