package own.spring.core.reveal;

public class FXNewsProvider {

  private IFXNewsListener newsListener;
  private IFXNewsPersister newPersistener;

  public void getAndPersistNews() {
    String[] newsIds = newsListener.getAvailableNewsIds();
    if (newsIds == null || newsIds.length == 0) {
      return;
    }
    for (String newsId : newsIds) {
      FXNewsBean newsBean = newsListener.getNewsByPK(newsId);
      newPersistener.persistNews(newsBean);
      newsListener.postProcessIfNecessary(newsId);
    }
  }

  public FXNewsProvider(IFXNewsListener newsListner, IFXNewsPersister newsPersister) {
    this.newsListener = newsListner;
    this.newPersistener = newsPersister;
  }

  public IFXNewsListener getNewsListener() {
    return newsListener;
  }

  public void setNewsListener(IFXNewsListener newsListener) {
    this.newsListener = newsListener;
  }

  public IFXNewsPersister getNewPersistener() {
    return newPersistener;
  }

  public void setNewPersistener(IFXNewsPersister newPersistener) {
    this.newPersistener = newPersistener;
  }
}
