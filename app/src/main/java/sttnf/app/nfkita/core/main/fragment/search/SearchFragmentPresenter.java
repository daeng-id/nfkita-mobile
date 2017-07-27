package sttnf.app.nfkita.core.main.fragment.search;

import sttnf.app.nfkita.base.BasePresenter;
import sttnf.app.nfkita.models.NewsModel;
import sttnf.app.nfkita.network.RequestResultCallback;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public class SearchFragmentPresenter extends BasePresenter<SearchFragmentView> {

    public SearchFragmentPresenter(SearchFragmentView view) {
        super.attachView(view);
    }

    public void searchArticle(String search) {
        addSubscribe(service.getSearch(search), new RequestResultCallback<NewsModel>() {
            @Override public void onSuccess(NewsModel model) {
                view.onSuccess(model);
            }
            @Override public void onFailure(String message) {
                view.onError(message);
            }
            @Override public void onFinish() {}
        });
    }
}
