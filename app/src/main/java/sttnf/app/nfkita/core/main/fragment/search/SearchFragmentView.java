package sttnf.app.nfkita.core.main.fragment.search;

import sttnf.app.nfkita.models.NewsModel;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public interface SearchFragmentView {
    void onSuccess(NewsModel result);
    void onError(String err);
}
