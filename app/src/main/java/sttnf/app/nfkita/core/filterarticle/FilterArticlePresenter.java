package sttnf.app.nfkita.core.filterarticle;

import sttnf.app.nfkita.base.BasePresenter;
import sttnf.app.nfkita.models.NewsModel;
import sttnf.app.nfkita.network.RequestResultCallback;

/**
 * Created by isfaaghyth on 7/13/17.
 */

public class FilterArticlePresenter extends BasePresenter<FilterArticleView> {

    public FilterArticlePresenter(FilterArticleView view) {
        super.attachView(view);
    }

    public void getArticleBySlug(String slug) {
        addSubscribe(service.getArticleBySlug(slug), new RequestResultCallback<NewsModel>() {
            @Override public void onSuccess(NewsModel model) {
                view.onNewsSuccess(model);
            }
            @Override public void onFailure(String message) {
                view.onError(message);
            }
            @Override public void onFinish() {}
        });
    }

    public void getAllArticle() {
        addSubscribe(service.getNews(), new RequestResultCallback<NewsModel>() {
            @Override public void onSuccess(NewsModel model) {
                view.onNewsSuccess(model);
            }
            @Override public void onFailure(String message) {
                view.onError(message);
            }
            @Override public void onFinish() {}
        });
    }

}
