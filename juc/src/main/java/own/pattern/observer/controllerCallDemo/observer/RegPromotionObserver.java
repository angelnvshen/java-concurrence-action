package own.pattern.observer.controllerCallDemo.observer;

import own.pattern.observer.controllerCallDemo.PromotionService;
import own.pattern.observer.eventBus.Subscribe;

public class RegPromotionObserver implements RegObserver {

    private PromotionService promotionService; // 依赖注入

    @Subscribe
    @Override
    public void handleRegSuccess(long userId) {
        promotionService.issueNewUserExperienceCash(userId);
    }
}
