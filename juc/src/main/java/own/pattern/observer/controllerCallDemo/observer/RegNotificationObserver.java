package own.pattern.observer.controllerCallDemo.observer;

import own.pattern.observer.controllerCallDemo.NotificationService;

public class RegNotificationObserver implements RegObserver {

    private NotificationService notificationService;
    @Override
    public void handleRegSuccess(long userId) {
//        notificationService.sendInboxMessage(userId, "Welcome...");

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                notificationService.sendInboxMessage(userId, "Welcome...");
            }
        });
        thread.start();
    }
}
