package sttnf.app.nfkita.core.main.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.adapter.CategoryAdapter;
import sttnf.app.nfkita.adapter.NewsAdapter;
import sttnf.app.nfkita.base.BaseFragment;
import sttnf.app.nfkita.core.filterarticle.FilterArticleActivity;
import sttnf.app.nfkita.core.readarticle.ReadArticleActivity;
import sttnf.app.nfkita.models.CategoryModel;
import sttnf.app.nfkita.models.NewsModel;
import sttnf.app.nfkita.utils.GridColumn;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public class HomeFragment extends BaseFragment<HomeFragmentPresenter> implements HomeFragmentView {

    @BindView(R.id.lst_categories) RecyclerView lstCategories;
    @BindView(R.id.loading_request) RelativeLayout layoutLoading;
    @BindView(R.id.pagesContainer) LinearLayout sliderContainer;
    @BindView(R.id.slider_main) SliderLayout sliderMain;
    @BindView(R.id.pg_loading) ProgressBar pgLoading;
    @BindView(R.id.txt_message) TextView txtMessage;
    @BindView(R.id.btn_refresh) Button btnRefresh;
    @BindView(R.id.lst_news) RecyclerView lstNews;
    @BindView(R.id.txt_title) TextView txtTitle;

    HashMap<String, String> slidingImages = new HashMap<>();
    GridLayoutManager gridLayoutManager;

    @Override protected HomeFragmentPresenter createPresenter() {
        return new HomeFragmentPresenter(this);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        binding(view);
        setHasOptionsMenu(true);
        lstNews.setNestedScrollingEnabled(false);
        lstCategories.setNestedScrollingEnabled(false);
        presenter.loadAllNews();
    }

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_refresh:
                onRefresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_refresh)
    public void onRefresh() {
        pgLoading.setVisibility(View.VISIBLE);
        txtMessage.setVisibility(View.GONE);
        btnRefresh.setVisibility(View.GONE);
        txtTitle.setVisibility(View.GONE);
        presenter.loadAllNews();
    }

    @Override public void onCategorySuccess(CategoryModel model) {
        CategoryAdapter adapter = new CategoryAdapter(model.getCategories());
        adapter.setOnClickCallback(this::onClickFilterArticle);
        lstCategories.setLayoutManager(new GridLayoutManager(getContext(), GridColumn.calculateNoOfColumns(getContext())));
        lstCategories.setAdapter(adapter);
    }

    public void onClickFilterArticle(ArrayList<CategoryModel.Categories> result, int position) {
        Intent readArticle = new Intent(getContext(), FilterArticleActivity.class);
        readArticle.putExtra("id", String.valueOf(result.get(position).getId()));
        readArticle.putExtra("slug", result.get(position).getSlug());
        readArticle.putExtra("title", result.get(position).getTitle());
        readArticle.putExtra("type", "slug_article");
        readArticle.putExtra("", "");
        startActivity(readArticle);
    }

    @OnClick(R.id.btn_read_more)
    public void onClickReadMore() {
        Intent readArticle = new Intent(getContext(), FilterArticleActivity.class);
        readArticle.putExtra("title", "Berita NFKita");
        readArticle.putExtra("type", "all_article");
        readArticle.putExtra("slug", "");
        readArticle.putExtra("id", "");
        readArticle.putExtra("", "");
        startActivity(readArticle);
    }

    @Override public void onNewsSuccess(NewsModel result) {
        layoutLoading.setVisibility(View.GONE);
        NewsAdapter adapter = new NewsAdapter(getContext(), result.getPosts());
        adapter.setOnClickCallback(this::onClickReadMore);
        lstNews.setLayoutManager(new LinearLayoutManager(getContext()));
        lstNews.setAdapter(adapter);
        imageSlider(result);

    }

    private void imageSlider(NewsModel result) {
        int sliderCount = (result.getPosts().size()%2 == 0) ?
                result.getPosts().size() / 2:
                result.getPosts().size() / 3;
        for (int i = 0; i < sliderCount; i++) {
            slidingImages.put(result.getPosts().get(i).getTitle(), result.getPosts().get(i).getThumbnail());
        }
        for(String name : slidingImages.keySet()){
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .description(name)
                    .image(slidingImages.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            sliderMain.addSlider(textSliderView);
        }
        sliderMain.setPresetTransformer(SliderLayout.Transformer.Default);
        sliderMain.setPresetIndicator(SliderLayout.PresetIndicators.Center_Top);
        sliderMain.setCustomAnimation(new DescriptionAnimation());
        sliderMain.setDuration(4000);
    }

    @Override public void onError(String message) {
        pgLoading.setVisibility(View.GONE);
        txtTitle.setVisibility(View.VISIBLE);
        txtMessage.setVisibility(View.VISIBLE);
        btnRefresh.setVisibility(View.VISIBLE);
        layoutLoading.setVisibility(View.VISIBLE);
        txtMessage.setText(message);

        Toast.makeText(getContext(), "Something problem!", Toast.LENGTH_LONG).show();
    }

    @Override public void onFinish() {

    }

    public void onClickReadMore(ArrayList<NewsModel.Post> result, int position) {
        Intent readArticle = new Intent(getContext(), ReadArticleActivity.class);
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

    @Override public void onLoading() {
        layoutLoading.setVisibility(View.VISIBLE);
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        presenter.dettachView();
        presenter.onStop();
    }
}
