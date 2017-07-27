package sttnf.app.nfkita.core.main.fragment.home;

import sttnf.app.nfkita.base.BasePresenter;
import sttnf.app.nfkita.models.CategoryModel;
import sttnf.app.nfkita.models.NewsModel;
import sttnf.app.nfkita.network.RequestResultCallback;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public class HomeFragmentPresenter extends BasePresenter<HomeFragmentView> {

    public HomeFragmentPresenter(HomeFragmentView view) {
        super.attachView(view);
    }

    public void loadCategories() {
        addSubscribe(service.getCategory(), new RequestResultCallback<CategoryModel>() {
            @Override public void onSuccess(CategoryModel model) {
                view.onCategorySuccess(model);
            }
            @Override public void onFailure(String message) {
                view.onError(message);
            }
            @Override public void onFinish() {
                view.onFinish();
            }
        });
    }

    public void loadAllNews() {
        view.onLoading();
        addSubscribe(service.getPopularNews(), new RequestResultCallback<NewsModel>() {
            @Override public void onSuccess(NewsModel result) {
                view.onNewsSuccess(result);
            }
            @Override public void onFailure(String message) {
                view.onError(message);
            }
            @Override public void onFinish() {
                loadCategories();
            }
        });
    }

    public void onStop() {
        stop();
    }
}
