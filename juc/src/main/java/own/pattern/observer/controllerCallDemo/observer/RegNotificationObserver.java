package own.pattern.observer.controllerCallDemo.observer;

import own.pattern.observer.controllerCallDemo.NotificationService;
import own.pattern.observer.eventBus.Subscribe;

public class RegNotificationObserver implements RegObserver {

    private NotificationService notificationService;

    @Override
    @Subscribe
    public void handleRegSuccess(long userId) {
        notificationService.sendInboxMessage(userId, "Welcome...");
    }
}
