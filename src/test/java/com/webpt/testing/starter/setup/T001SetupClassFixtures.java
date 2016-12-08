package com.webpt.testing.starter.setup;

import com.webpt.testing.serenityjbehave.SelectiveSerenityStoryTest;

/**
 * This test runner handles setup of client class fixtures
 */
public class T001SetupClassFixtures extends SelectiveSerenityStoryTest{

    @Override
    public String[] filterSteps() {
        return new String[] {"LoginStepDefinitions", "ClientClassStepDefinitions"};
    }

    @Override
    protected String getStoryPath() {
        return "stories/setup/clients/setup_class_fixtures.story";
    }

}
