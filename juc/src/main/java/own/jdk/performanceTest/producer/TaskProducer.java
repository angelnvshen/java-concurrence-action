package own.jdk.performanceTest.producer;

import own.jdk.performanceTest.model.Operation;
import own.jdk.performanceTest.model.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Produces query tasks of different sizes
 * Reads data from a file of order and size of tasks, and then generates a random task
 * 
 * @author florin.bunau
 */
public class TaskProducer {

    private static final int RAND_SEED = 100;
    private static Random randomGenerator = new Random(RAND_SEED);

    /**
     * In this file we define the tasks to be processed and their type
     */
    private static final String TASK_FILENAME = "tasks.txt";

    /**
     * Maximum span of an interval from left margin to right margin
     */
    private static final int MAX_INTERVAL_LENGTH = 10000;
    
    /**
     * Maximum index reached by the right margin of the interval
     */
    private static final int MAX_INTERVAL_INDEX_VALUE = 10000000;
    
    /**
     * Used to go through comma separated tasks in input file
     */
    private final Scanner scanner;
    
    public TaskProducer() throws FileNotFoundException {
        String filePath = getClass().getClassLoader().getResource(TASK_FILENAME).getFile();
        //System.out.println(filePath);
        scanner = new Scanner(new File(filePath));
        scanner.useDelimiter(",");
    }
    
    /**
     * @return Returns the next task according to the input file. Will return null if there are no tasks anymore
     */
    public Task getNext() {
        if (scanner == null) {
            return null;
        }

        if (scanner.hasNext()) {
            String taskAsString = scanner.next();
            return produceQueryTask(Task.TaskType.valueOf(taskAsString));
        }
        return null;
    }

    /**
     * @param taskType Type of task to be produced
     * @return Random task of the specified type
     */
    private Task produceQueryTask(Task.TaskType taskType) {
        List<Operation> operations = new ArrayList<>();

        for (int i = 0; i < taskType.getRange(); ++i) {
            operations.add(getRandomQueryInterval());	
        }
        
        return new Task(operations, taskType);
    }
    
    /**
     * @return A random generated interval
     */
    private Operation.QueryIntervalOperation getRandomQueryInterval() {
        int left =  randomGenerator.nextInt(MAX_INTERVAL_INDEX_VALUE - MAX_INTERVAL_LENGTH);
        int right = left + randomGenerator.nextInt(MAX_INTERVAL_LENGTH);
        return new Operation.QueryIntervalOperation(left, right);
    }
}
