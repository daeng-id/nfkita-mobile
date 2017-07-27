package sttnf.app.nfkita.core.main.fragment.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.OnClick;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.adapter.NewsAdapter;
import sttnf.app.nfkita.base.BaseFragment;
import sttnf.app.nfkita.models.NewsModel;
import sttnf.app.nfkita.utils.ProgressLoader;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public class SearchFragment extends BaseFragment<SearchFragmentPresenter> implements SearchFragmentView {

    @BindView(R.id.txt_not_found) TextView txtNotFound;
    @BindView(R.id.lst_search) RecyclerView lstSearch;
    @BindView(R.id.edt_search) EditText edtSearch;

    private ProgressLoader loader;

    @Override protected SearchFragmentPresenter createPresenter() {
        return new SearchFragmentPresenter(this);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        binding(view);
        loader = new ProgressLoader(getContext());
        lstSearch.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @OnClick(R.id.btn_search)
    public void onClickSearch() {
        loader.show();
        presenter.searchArticle(edtSearch.getText().toString());
    }

    @Override public void onSuccess(NewsModel result) {
        loader.hide();
        if (result.getPosts().size() > 0) {
            txtNotFound.setVisibility(View.GONE);
            lstSearch.setAdapter(new NewsAdapter(getContext(), result.getPosts()));
        } else {
            txtNotFound.setVisibility(View.VISIBLE);
        }
    }

    @Override public void onError(String err) {
        loader.hide();
        txtNotFound.setVisibility(View.GONE);
        Toast.makeText(getContext(), err, Toast.LENGTH_LONG).show();
    }
}
