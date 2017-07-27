package sttnf.app.nfkita.network;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import sttnf.app.nfkita.models.ArticleModel;
import sttnf.app.nfkita.models.CategoryModel;
import sttnf.app.nfkita.models.CommentModel;
import sttnf.app.nfkita.models.NewsModel;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public interface NFKitaRouteService {

    @GET("/api/get_recent_posts/?count=10")
    Observable<NewsModel> getPopularNews();

    @GET("/api/get_recent_posts/")
    Observable<NewsModel> getNews();

    @GET("/api/get_category_index/")
    Observable<CategoryModel> getCategory();

    @GET("/api/get_post/")
    Observable<ArticleModel> readArticle(
            @Query("post_id") String postId
    );

    @GET("/api/get_category_posts/")
    Observable<NewsModel> getArticleBySlug(
            @Query("slug") String slug
    );

    @GET("/api/get_search_results/")
    Observable<NewsModel> getSearch(
            @Query("search") String search
    );

    @GET("/api/respond/submit_comment/")
    Observable<CommentModel> postComment(
            @Query("post_id") String postId,
            @Query("name") String name,
            @Query("email") String email,
            @Query("content") String content
    );

}
