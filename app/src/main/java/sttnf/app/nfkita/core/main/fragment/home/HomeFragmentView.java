package sttnf.app.nfkita.core.main.fragment.home;

import sttnf.app.nfkita.models.CategoryModel;
import sttnf.app.nfkita.models.NewsModel;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public interface HomeFragmentView {
    void onLoading();
    void onCategorySuccess(CategoryModel model);
    void onNewsSuccess(NewsModel result);
    void onError(String message);
    void onFinish();
}
