package own.stu.highConcurrence.cacheconsistence.model;

import lombok.Data;

import java.util.concurrent.Executor;

@Data
public class A {

    private Integer id;

    public static void main(String[] args) {
        Executor executor = new Executor() {
            @Override
            public void execute(Runnable command) {
                command.run();
            }
        };

        executor.execute(() -> {
            System.out.println("ni daye de ");
        });
    }
}
