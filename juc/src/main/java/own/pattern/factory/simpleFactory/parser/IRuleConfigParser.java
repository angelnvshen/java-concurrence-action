package own.pattern.factory.simpleFactory.parser;

import own.pattern.factory.simpleFactory.RuleConfig;

public interface IRuleConfigParser {
    RuleConfig parse(String configText);
}
