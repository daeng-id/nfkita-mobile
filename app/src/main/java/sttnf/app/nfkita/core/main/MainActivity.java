package sttnf.app.nfkita.core.main;

import android.support.annotation.IdRes;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import com.roughike.bottombar.BottomBar;

import butterknife.BindView;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.base.BaseActivity;
import sttnf.app.nfkita.utils.CustomViewPager;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.viewpager_main) CustomViewPager viewPagerMain;
    @BindView(R.id.bottombar_main) BottomBar bottomBarMain;

    @Override protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_main);
        presenter.setupViewPager(viewPagerMain);
        viewPagerMain.setPagingEnabled(false);
        viewPagerMain.setOffscreenPageLimit(4);
        bottomBarMain.setOnTabSelectListener(this::onTabSelected);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        if (viewPagerMain.getCurrentItem() == 0) {
            menu.findItem(R.id.mn_refresh).setVisible(true);
        } else {
            menu.findItem(R.id.mn_refresh).setVisible(false);
        }
        return super.onCreateOptionsMenu(menu);
    }

    public void onTabSelected(@IdRes int tabId) {
        switch (tabId) {
            case R.id.tab_home:viewPagerMain.setCurrentItem(0, true);break;
            case R.id.tab_search:viewPagerMain.setCurrentItem(1, true);break;
            case R.id.tab_tools:viewPagerMain.setCurrentItem(2, true);break;
            case R.id.tab_profile:viewPagerMain.setCurrentItem(3, true);break;
        }
    }

    @Override public FragmentManager getSupportFragment() {
        return getSupportFragmentManager();
    }
}
