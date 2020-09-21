package own.pattern.observer.controllerCallDemo.observer;

import own.pattern.observer.controllerCallDemo.PromotionService;

public class RegPromotionObserver implements RegObserver {

    private PromotionService promotionService; // 依赖注入

    @Override
    public void handleRegSuccess(long userId) {
//        promotionService.issueNewUserExperienceCash(userId);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                promotionService.issueNewUserExperienceCash(userId);
            }
        });
        thread.start();
    }
}
