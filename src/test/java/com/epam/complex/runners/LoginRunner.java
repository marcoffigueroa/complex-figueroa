package com.epam.complex.runners;

import org.junit.platform.suite.api.*;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@ConfigurationParameter(key = FEATURES_PROPERTY_NAME, value = "classpath:")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.epam.complex.tests")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, html:target/cucumber-report.html, json:target/cucumber.json, summary")
@IncludeTags("LoginTests")
public class LoginRunner {
}