package sttnf.app.nfkita.core.readarticle;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import butterknife.BindView;
import butterknife.OnClick;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.adapter.CommentsAdapter;
import sttnf.app.nfkita.base.BaseActivity;
import sttnf.app.nfkita.models.ArticleModel;
import sttnf.app.nfkita.models.CommentModel;
import sttnf.app.nfkita.utils.CacheManager;
import sttnf.app.nfkita.utils.MarqueeToolbar;
import sttnf.app.nfkita.utils.WebViewContent;

public class ReadArticleActivity extends BaseActivity<ReadArticlePresenter> implements ReadArticleView {

    @BindView(R.id.txt_date) TextView txtDate;
    @BindView(R.id.img_cover) ImageView imgCover;
    @BindView(R.id.txt_writer) TextView txtWriter;
    @BindView(R.id.edt_comment) EditText edtComment;
    @BindView(R.id.lst_comment) RecyclerView lstComments;
    @BindView(R.id.webview_content) WebView webViewContent;
    @BindView(R.id.toolbar_article) Toolbar toolbarArticle;

    @Override protected ReadArticlePresenter createPresenter() {
        return new ReadArticlePresenter(this);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        binding(R.layout.activity_read_article);
        setSupportActionBar(toolbarArticle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_back);
        lstComments.setNestedScrollingEnabled(false);
        lstComments.setLayoutManager(new LinearLayoutManager(this));
        presenter.loadComments(getIntent("id"));
        loadData();
    }

    private void loadData() {
        Picasso.with(this).load(getIntent("thumbnail")).into(imgCover);
        txtWriter.setText(getIntent("writer"));
        txtDate.setText(getIntent("date"));
        webViewContent.setScrollContainer(false);
        webViewContent.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webViewContent.loadData(WebViewContent.clean(getContent()), "text/html; charset=utf-8", "utf-8");
    }

    @OnClick(R.id.fab_share)
    public void onClickShare() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "NFKita");
        shareIntent.putExtra(Intent.EXTRA_TEXT, getIntent("url"));
        Intent new_intent = Intent.createChooser(shareIntent, "Share it");
        new_intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(new_intent);
    }

    @OnClick(R.id.btn_send)
    public void onClickComment() {
        if (CacheManager.checkExist("login")) {
            if (CacheManager.grabBoolean("login")) {
                presenter.postComment(getIntent("id"), edtComment.getText().toString());
            } else {
                mustLoginAlert();
            }
        } else {
            mustLoginAlert();
        }
    }

    private void mustLoginAlert() {
        new AlertDialog.Builder(this)
                .setTitle("Oops!")
                .setMessage("You haven't login yet.")
                .setNegativeButton("CLOSE", (dialog, which) -> {})
                .show();
    }

    private String getContent() {
        Document doc = Jsoup.parse(getIntent("content"));
        Element imageElement = doc.select("img").first();
        if (imageElement != null) imageElement.remove();
        return "<div>" + doc.toString() + "</div>";
    }

    private String getIntent(String key) {
        return getIntent().getStringExtra(key);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void onArticleSuccess(ArticleModel result) {
        lstComments.setAdapter(new CommentsAdapter(result.getPost().getComments()));
    }

    @Override public void onError(String message) {

    }

    @Override public void onCommentSuccess(CommentModel model) {
        Log.d("TAG", model.getId()+"\n"+model.getContent()+"\n"+model.getDate()+"\n"+model.getDate());
    }
}
