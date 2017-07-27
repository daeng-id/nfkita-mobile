package sttnf.app.nfkita.core.main;

import android.support.v4.view.ViewPager;

import sttnf.app.nfkita.adapter.ViewPagerAdapter;
import sttnf.app.nfkita.base.BasePresenter;
import sttnf.app.nfkita.core.main.fragment.home.HomeFragment;
import sttnf.app.nfkita.core.main.fragment.profile.ProfileFragment;
import sttnf.app.nfkita.core.main.fragment.search.SearchFragment;
import sttnf.app.nfkita.core.main.fragment.tools.ToolsFragment;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public class MainPresenter extends BasePresenter<MainView> {

    public MainPresenter(MainView view) {
        super.attachView(view);
    }

    public void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(view.getSupportFragment(), false);
        adapter.addFragment(new HomeFragment(), "Main");
        adapter.addFragment(new SearchFragment(), "Search");
        adapter.addFragment(new ToolsFragment(), "Tools");
        adapter.addFragment(new ProfileFragment(), "Profile");
        viewPager.setAdapter(adapter);
    }

}
