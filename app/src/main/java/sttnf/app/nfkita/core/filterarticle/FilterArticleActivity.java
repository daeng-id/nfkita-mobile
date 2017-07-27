package sttnf.app.nfkita.core.filterarticle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.adapter.NewsAdapter;
import sttnf.app.nfkita.base.BaseActivity;
import sttnf.app.nfkita.core.readarticle.ReadArticleActivity;
import sttnf.app.nfkita.models.NewsModel;
import sttnf.app.nfkita.utils.ProgressLoader;

public class FilterArticleActivity extends BaseActivity<FilterArticlePresenter> implements FilterArticleView {

    @BindView(R.id.lst_article) RecyclerView lstArticle;
    @BindView(R.id.toolbar_nfkita) Toolbar toolbarNFKita;
    @BindView(R.id.txt_title) TextView txtTitle;

    private ProgressLoader loader;

    @Override protected FilterArticlePresenter createPresenter() {
        return new FilterArticlePresenter(this);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_filter_article);
        setSupportActionBar(toolbarNFKita);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lstArticle.setLayoutManager(new LinearLayoutManager(this));
        loader = new ProgressLoader(this);
        loader.show();

        if (getIntent().getStringExtra("type").equalsIgnoreCase("slug_article")) {
            txtTitle.setText("#" + getIntent().getStringExtra("slug"));
            presenter.getArticleBySlug(getIntent().getStringExtra("slug"));
        } else if (getIntent().getStringExtra("type").equalsIgnoreCase("all_article")) {
            txtTitle.setText(getIntent().getStringExtra("title"));
            presenter.getAllArticle();
        }
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

    @Override public void onNewsSuccess(NewsModel result) {
        loader.hide();
        NewsAdapter adapter = new NewsAdapter(this, result.getPosts());
        adapter.setOnClickCallback(this::onClickReadMore);
        lstArticle.setAdapter(adapter);
    }

    public void onClickReadMore(ArrayList<NewsModel.Post> result, int position) {
        Intent readArticle = new Intent(this, ReadArticleActivity.class);
        readArticle.putExtra("id", String.valueOf(result.get(position).getId()));
        readArticle.putExtra("slug", result.get(position).getSlug());
        readArticle.putExtra("title", result.get(position).getTitle());
        readArticle.putExtra("thumbnail", result.get(position).getThumbnail());
        readArticle.putExtra("content", result.get(position).getContent());
        readArticle.putExtra("writer", result.get(position).getAuthor().getName());
        readArticle.putExtra("date", result.get(position).getDate());
        readArticle.putExtra("url", result.get(position).getUrl());
        readArticle.putExtra("", "");
        startActivity(readArticle);
    }

    @Override public void onError(String message) {
        loader.hide();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
