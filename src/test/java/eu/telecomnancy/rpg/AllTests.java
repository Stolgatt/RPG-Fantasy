package eu.telecomnancy.rpg;

import org.junit.platform.suite.api.IncludeClassNamePatterns;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        WarriorTest.class,
        HealerTest.class,
        WizardTest.class,
        TeamTest.class,
        CharacterTest.class,
        GameConfigurationTest.class
})
public class AllTests {
}
