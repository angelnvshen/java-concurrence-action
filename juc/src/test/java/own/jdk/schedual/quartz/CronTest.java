package own.jdk.schedual.quartz;

import org.junit.Test;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

public class CronTest {

    /**
     * * : 用来表示任意值
     * ? : 只能用在“Day-of-month”和“Day-of-Week”这两个字段，表示没有特定的值
     * - : 用来表示范围，例如在Hours字段配置“10-12”，解析过来就是小时数为10,11和12都满足。
     * , : 用来表示枚举值，例如在Day-of-Week字段配置“MON,WED,FRI”，解析过来就是星期一，星期三和星期五都满足。
     * / : 用来表示增量逻辑，格式为“初始值/增量值”，例如在Seconds字段配置“5/15”，解析过来就是5,20,35,50都符合。
     * L : last的简写，只能用在“Day-of-month”和“Day-of-Week”这两个字段，
     * W : weekday的简写，只能用在“Day-of-month”字段，表示最靠近指定日期的工作日（星期一到星期五）
     * # : 只能用在Day-of-Week字段，“m#n”表示这个月的第n个星期m。
     *
     * @throws ParseException
     */
    @Test
    public void testCronExpression() throws ParseException {

//        System.out.println(Calendar.getInstance().get(1));

        CronExpression cronExpression = new CronExpression("0/15 5-10 9,18 1,15 * ? 2018-2023");
        System.out.println(cronExpression.getCronExpression());
        System.out.println(cronExpression.getExpressionSummary());
        System.out.println(cronExpression.getNextInvalidTimeAfter(new Date()));
        System.out.println(cronExpression.getNextValidTimeAfter(new Date()));
    }
}
