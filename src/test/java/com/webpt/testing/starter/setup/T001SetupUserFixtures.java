package com.webpt.testing.starter.setup;

import com.webpt.testing.serenityjbehave.SelectiveSerenityStoryTest;

/**
 * This test runner handles setup of user fixtures
 */
public class T001SetupUserFixtures extends SelectiveSerenityStoryTest{

    @Override
    public String[] filterSteps() {
        return new String[] {""};
    }

    @Override
    protected String getStoryPath() {
        return "stories/setup/fixtures/user_fixtures.story";
    }

}
