package own.pattern.factory.simpleFactory;

import java.util.HashMap;
import java.util.Map;

//为了让类的职责更加单一、代码更加清晰，我们还可以进一步将 createParser() 函数剥离
//到一个独立的类中，让这个类只负责对象的创建。
public class RuleConfigParserFactory {

    /*
    有一组 if 分支判断逻辑，是不是应该用多态或其他设计模式来替代呢？
    实际上，如果 if 分支并不是很多，代码中有if 分支也是完全可以接受的。
    应用多态或设计模式来替代 if 分支判断逻辑，也并不是没有任何缺点的，
    它虽然提高了代码的扩展性，更加符合开闭原则，但也增加了类的个数，牺牲了代码的可读性。
    */

    //如果 parser 可以复用，为了节省内存和对象创建
    //的时间，我们可以将 parser 事先创建好缓存起来。
    private static final Map<String, IRuleConfigParser> cachedParsers = new HashMap<>();

    static {
        cachedParsers.put("json", new JsonRuleConfigParser());
        cachedParsers.put("xml", new XmlRuleConfigParser());
        cachedParsers.put("yaml", new YamlRuleConfigParser());
        cachedParsers.put("properties", new PropertiesRuleConfigParser());
    }

    public static IRuleConfigParser createParser(String configFormat) {

        if (configFormat == null || configFormat.isEmpty()) {
            return null;//返回null还是IllegalArgumentException全凭你自己说了算
        }

        IRuleConfigParser parser = cachedParsers.get(configFormat);

        return parser;
    }
}
