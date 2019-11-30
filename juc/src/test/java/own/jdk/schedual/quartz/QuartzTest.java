package own.jdk.schedual.quartz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import own.jdk.common.TestCommon;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static own.jdk.executorService.scheduled.TimerExample.now;
import static own.jdk.executorService.scheduled.TimerExample.sleep;

public class QuartzTest extends TestCommon {

    @Test
    public void test() {

        try {
            // Grab the Scheduler instance from the Factory
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(HelloJob.class)
                    .withIdentity("job1", "group1")
                    .build();

            Date startTime = DateBuilder.nextGivenSecondDate(null, 3);

            // Trigger the job to run now, and then repeat every 2 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
//                    .startNow()
                    .startAt(startTime)
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(2)
                            .repeatForever())
                    .build();

            // Tell quartz to schedule the job using our trigger
            scheduler.scheduleJob(job, trigger);

            // and start it off
            scheduler.start();

            sleep(10);

            scheduler.shutdown();

        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    public static class HelloJob implements Job {
        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println(Thread.currentThread().getName() + ": hello world " + now());
        }
    }

}
