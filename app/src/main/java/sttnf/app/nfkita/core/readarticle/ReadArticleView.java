package sttnf.app.nfkita.core.readarticle;

import sttnf.app.nfkita.models.ArticleModel;
import sttnf.app.nfkita.models.CommentModel;

/**
 * Created by isfaaghyth on 7/13/17.
 */

public interface ReadArticleView {
    void onArticleSuccess(ArticleModel result);
    void onError(String message);
    void onCommentSuccess(CommentModel model);
}
