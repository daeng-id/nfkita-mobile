package sttnf.app.nfkita.core.readarticle;

import sttnf.app.nfkita.base.BasePresenter;
import sttnf.app.nfkita.models.ArticleModel;
import sttnf.app.nfkita.models.CommentModel;
import sttnf.app.nfkita.network.RequestResultCallback;
import sttnf.app.nfkita.utils.CacheManager;

/**
 * Created by isfaaghyth on 7/13/17.
 */

public class ReadArticlePresenter extends BasePresenter<ReadArticleView> {

    public ReadArticlePresenter(ReadArticleView view) {
        super.attachView(view);
    }

    public void loadComments(String postId) {
        addSubscribe(service.readArticle(postId), new RequestResultCallback<ArticleModel>() {
            @Override public void onSuccess(ArticleModel result) {
                view.onArticleSuccess(result);
            }
            @Override public void onFailure(String message) {
                view.onError(message);
            }
            @Override public void onFinish() {}
        });
    }

    public void postComment(String postId, String content) {
        addSubscribe(service.postComment(
                postId,
                CacheManager.grabString("name"),
                CacheManager.grabString("email"),
                content), new RequestResultCallback<CommentModel>() {
            @Override public void onSuccess(CommentModel model) {
                view.onCommentSuccess(model);
            }
            @Override public void onFailure(String message) {
                view.onError(message);
            }
            @Override public void onFinish() {}
        });
    }

}
