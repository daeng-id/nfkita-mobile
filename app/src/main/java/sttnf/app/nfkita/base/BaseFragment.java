package sttnf.app.nfkita.base;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    protected P presenter;
    protected abstract P createPresenter();

    protected void binding(View view) {
        ButterKnife.bind(this, view);
        presenter = createPresenter();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        if (presenter != null) {
            presenter.dettachView();
        }
    }
}
