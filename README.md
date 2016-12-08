# Serenity Starter

This project contains example automated tests which simply demonstrate the layout of our framework and helps you
get familiar with how the tests run. 

The projects uses Jbehave to interpret story files written with JBehave story syntax, execute steps and drive a web browser 
through java selenium, and report the results using Serenity BDD reporting.

## System Dependencies

Inorder to execute the tests in this project your system must have the following applications installed:

 1. Java 1.8
 2. Docker
    - If you are on Mac I'd recommend [Docker Toolbox](https://www.docker.com/products/docker-toolbox) over Docker for Mac. Docker for Mac seems to hang and crash a lot. 
 3. Maven 3.x
 4. There is a private WebPT internal maven repository that you must be able to connect to. As long as you are connected 
 to the WebPT VPN you should be able to access it and maven should be able to pull in the projects it needs.
 
## Execution

Basic execution is done with the Maven 'verify' command. So in it's simplest form you would just execute:

    mvn clean verify

Properties and Profiles are used in this project to allow it to execute tests with flexible configuration.
You can request any profile, or override any property on the command line. 

See [Configuration Options](#Configuration Options) for description of all configurable parameters.

### Configuration

You define properties in the pom.xml file. 
All of these properties are also automatically written to the serenity.properties so serenity has access to them.
You can overwrite any properties on your local system by setting up a settings.xml file in your local .m2 directory. 
All config properties can be accessed in the code by using the com.webpt.testing.serenity.configuration.Config class
for example:

    String webDriver = Config.getInstance().getProperty("webdriver.driver");


### Running Tests

When you run the tests you specify the profiles to execute. This could determine the suite you execute, the 
environment you execute against, and if you use the docker selenium grid or not.

The following profiles are available in the project POM and can be called either on command line with the -P option 
or activated in your local settings.xml file.

#### Environments
*Note for this project these will make the tests fail as they will overwrite the base url for the pages we are access. 
I've just included these for examples here because you will use them in real projects.*
- **env-sdev** - runs tests against the sdev environment
- **env-test** - runs tests against the test environment

#### Suites
*These are just examples of different suites you might have in a project that accomplish different things. This project 
can only run the 'tests' suite*
- **setup** - runs the setup suite of tests to prepare and environment with fixtures required by the smokes tests
- **tests** - runs the smoke test suite.
 
#### Docker Selenum Grid
- **dockersg** - runs the tests in a docker selenuim grid.

#### Configuring Execution

By default the project just runs a local chrome web browser in 1 thread. If you want to execute in multiple threads
then you need to use the dockersg profile to run the tests in the docker containers. You can do this by adding the 
-Pdockersg option on the commandline at test execution or by setting up your local settings to run docker by default.

Open or create a file called settings.xml in your ~/.m2 directory. Add the following:

    <settings>
       <activeProfiles>
           <activeProfile>dockersg</activeProfile>
       </activeProfiles>
    </settings>


This will tell maven that you always want the dockersg profile active, by default your project will launch the docker 
containers.

If you want to enable vnc support in the docker containers and lauch a vnc view so you can see what is going on in the 
containers then you would set the dockersg.vncEnabled & dockersg.vncCommand properties.


    <settings>
       <activeProfiles>
           <activeProfile>dockersg</activeProfile>
       </activeProfiles>
       <profiles>
          <profile>
             <id>dockersg</id>
             <properties>
                <dockersg.vncEnabled>true</dockersg.vncEnabled>
                <dockersg.vncCommand>open {vnc-url}</dockersg.vncCommand>
             </properties>
          </profile>
       </profiles>
       
    </settings>


This will basically tell the system that when you run the dockersg profile you want it to overwrite the properties with 
what you specify here. The settings above will enable vnc support in the docker containers & open a vnc viewer for each 
container when run. This is just a convenience thing that allows you to view what the tests are doing while running 
multi threaded against the docker containers.

You can also add and activate a custom profile in this file which modifies any of the available configuration options,
or add any property modification to any existing active profile.

### Example Command lines

The following command line will execute the tests in a single browser

    mvn clean verify

The following command line will execute the tests in the docker grid with 2 threads

    mvn clean verify -Pdockersg
    
The following command line will execute the tests in the docker grid with 3 threads

    mvn clean verify -Pdockersg -Dthreads=3

The following command line would execute the setup suite against sdev in the selenium grid.

    mvn clean verify -Psetup,env-sdev,dockersg

The following command line would execute the tests against the test environment in a local web browser.
 
    mvn clean verify -Ptests,env-test

For this project you should NOT use the environments as they are just provided for examples.

### The Setup Suite

Your project may contain multiple different suites that accomplish different things. You will probably have a setup suite
which would have the intent to be executed against an environment one time. They're purpose is to create any required 
fixtures in the environment that the other tests expect to be present.

To execute setup you just have to activate the *setup* profile.

    mvn clean verify -Psetup

**The setup suite in this project is just included as an example but doesn't do anything, so you don't need to run it.**

### Running Only Specific Tests

You can execute only a subset of the tests by passing the *-Dtests=* flag with the class name or pattern.
Ex: 

    mvn clean verify -Dtests=T002*


### Configuration Options

You can modify any property by adding to your local settings.xml file or setting on the command line with the -D option.

Example of modifying on command line:
 
    mvn clean verify -Dwebpt.user=mrbean -Dthreads=5

- **webdriver.base.url** - The base url of the application you are trying to test. Ex: https://devapp.webpt.com. This 
is automatically overidden to the proper url in the existing environment profiles.
- **webdriver.driver** - chrome or firefox. The browser to use when driving a local browser.
- **webdriver.remote.driver** - chrome or firefox. The browser to use when running against the selenium grid.
- **webdriver.remote.url** - The remote url for selenium to connect to to drive the grid. This should be the selenium grid url. 
*Note that this is not the application url you're trying to test*. For that use the *webdriver.base.url* property. 
- **threads** - *ex: 3* - The number of parallel threads to execute the tests in. Current implemenation will execute all
                          scenarios from a single story in the same thread.
- **dockersg.vncEnabled** - true or false. False by default. This enables vnc server on the selenium grid nodes so you can 
connect and view what's happening in the browser.
- **dockersg.vncCommand** - Optional command line to execute which will launch a vnc viewer for each selenium node. 
This only works if vncEnabled is set to true. You must include the token {vnc-url} where you wish the node
vnc url to be included.
Ex: -DvncCommand=open {vnc-url} . This will execute a command like: *open vnc://127.0.0.1:47385*
- **dockersg.vncCommandDelaySeconds** - The number of seconds to wait before executing provided vncCommand. If you are using a vncCommand
to automatically open vnc windows but keep getting an error saying the vnc client can't connect then try increasing this value.
Your vnc connect may be happening too fast and the node's vnc service may not of had time to start. 

These are the most important properties that you are likely to want to change but there are additional properties that can be set. 
Basically anything in the POM properties can be overwritten.

If you want to add any Serenity specific properties you can do this as well. All project properties can written to 
the serenity.properties file so they are accessible by the Serenity reporting engine.

### Running on a Local Browser
 
If you don't specify the dockersg profile then the tests will try to launch a local browser. You might prefer this
while developing, but keep in mind that running tests in parallel this way can cause unexpected results.

If you want to run the tests this way you should leave the threads property set to 1. Which is it's default value.

Currently the tests work in Chrome locally & in Docker. 

Firefox If you choose to use Firefox locally you will need to use an older version. I'm running 4.6 and it works fine.

The current version of Selenium (2.53.1) we are using does not communicate with the latest Firefox versions. We will 
update to the latest version 3.* when the seleniumhq docker images are ready to support it.

### Running Browsers in Docker

The maven pom is setup to launch a selenium grid running in Docker if you request the profile 'dockersg'.
This uses a custom maven plugin to launch the grid. This project is located here: 
https://gitlab.webpt.com/testing/dockersg-maven-plugin

Example execution:

    mvn clean verify -P dockersg

The purpose of using the docker containers is to provide an isolated environment for each browser while running tests in 
parallel.

 
### Creating A Test

To create a test you'll need to create 4 general types of files:

 1. **The story file**. This is the JBehave story file and it should be placed in the *test/resources/stories/\<suite\>/* 
 directory. This folder has a 2 level structure. Top level is *theme*, next level is *epic*, then the story
 file. So your story file should always be two levels down. Ex: stories/*clients*/*client_list*/search_client_list.story
 2. **The test class**. This is the java test class that will execute your story. This class should be created in the
 *test/java/com/webpt/testing/\<suite\>/* folder. Extend the com.webpt.testing.serenityjbehave.SelectiveSerenityStoryTest
 class and implement the *getStoryPath()* method to provide the path to your story file, and that *filterSteps()*  method
 to provide any array of names where to look for step definitions. This can include class names or package names. 
 3. **The step definitions class**. You will need to create a step definitions class which defines the unique steps
 that your test needs to execute. This class should live in the com.webpt.testing space under an appropriate
 package representing the theme or you are working under. Step definitions should only define unique steps. So if 
 your JBehave story references steps implemented in by other step definition classes don't repeat those steps in your class
 but reuse the existing steps.
 4. **Supporting classes**. This is the reset of the stuff. This should include:
    - Serenity step classes
    - pages objects
    - data models
    - data providers / generators
    - custom page widgets
    - workflows
    - other utility classes
 
Currently we are creating a test class for each story file. This is done because we use the FailSafe plugin to control
multip threading and looks for test classes to multi thread. I am looking at ways to get ride of these classes so we 
don't have that overhead, but for now they should be used.

Currently the test classes are all just dumped into the *com.webpt.testing.\<suite\>* folder/package. This is done 
as a means of controlling test execution order. I have found that in many cases it is helpful to have this ability. 
So each test class should be started with a Txxx where xxx just represents an order number. This is not ideal but it 
will work for now. If we figure out a better way to control test order in the future we will adapt.
 
## Test Output

By default the Serenity test output report is generated in your project folder under the target/serenity folder. Open 
the index.html file in a browser to view.

As the current project is implemented you should see 4 successful scenarios in the output and 1 pending scenario.
 
## Resources

- Serenity: http://www.thucydides.info/docs/serenity/
- Docker: https://docs.docker.com/
- Selenium: http://docs.seleniumhq.org/
- Selenium Docker Containers: https://github.com/SeleniumHQ/docker-selenium