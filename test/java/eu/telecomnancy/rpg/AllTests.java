package eu.telecomnancy.rpg;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        WarriorTest.class,
        HealerTest.class,
        WizardTest.class,
        TeamTest.class,
        CharacterTest.class,
        GameConfigurationTest.class,
        CharacterRegistryTest.class,
        TeamRegistryTest.class,
        TeamBuilderTest.class,
        BuffVisitorTest.class,
        DamageVisitorTest.class,
        HealVisitorTest.class,
        CombatStrategyTest.class,
        ObserverTests.class,
        CharacterDecoratorTest.class,
        GameFacadeTest.class,
})
public class AllTests {
}
