package sttnf.app.nfkita.core.filterarticle;

import sttnf.app.nfkita.models.NewsModel;

/**
 * Created by isfaaghyth on 7/13/17.
 */

public interface FilterArticleView {
    void onNewsSuccess(NewsModel result);
    void onError(String message);
}
