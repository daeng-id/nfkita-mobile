package sttnf.app.nfkita.core.main.fragment.tools;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import butterknife.BindView;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.adapter.ToolsAdapter;
import sttnf.app.nfkita.base.BaseFragment;
import sttnf.app.nfkita.callback.ItemClickListener;
import sttnf.app.nfkita.core.feature.laporkampus.CampustReportActivity;
import sttnf.app.nfkita.core.feature.nfcare.NFCareActivity;
import sttnf.app.nfkita.models.ToolsModel;
import sttnf.app.nfkita.utils.CacheManager;
import sttnf.app.nfkita.utils.FragmentAttachUtils;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public class ToolsFragment extends BaseFragment<ToolsFragmentPresenter> implements ToolsFragmentView, ItemClickListener {

    @BindView(R.id.lst_tools) RecyclerView lstTools;

    @Override protected ToolsFragmentPresenter createPresenter() {
        return new ToolsFragmentPresenter(this);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tools, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        binding(view);
        //getActivity().runOnUiThread(() -> FragmentAttachUtils.addMapsFragment(mainMap, this, this, R.id.nurulfikri_map));
        lstTools.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ArrayList<ToolsModel> tools = new ArrayList<>();
        tools.add(new ToolsModel("NF Care!", R.drawable.img_customer_care));
        tools.add(new ToolsModel("Lapor Kampus", R.drawable.img_campus_means));
        tools.add(new ToolsModel("Nurul Fikri\nE-Learning", R.drawable.img_elen_nf));
        tools.add(new ToolsModel("Penerimaan\nMahasiswa\nBaru", R.drawable.img_new_college));
        lstTools.setAdapter(new ToolsAdapter(getContext(), tools).setListener(this));
    }

    @Override public void onItemClicked(View view, int position) {
        if (CacheManager.checkExist("login") && CacheManager.grabBoolean("login")) {
            switch (position) {
                case 0:
                    startActivity(new Intent(getContext(), NFCareActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(getContext(), CampustReportActivity.class));
                    break;
            }
        } else {
            mustLoginAlert();
        }
    }

    private void mustLoginAlert() {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.app_name)
                .setMessage(R.string.must_login)
                .setNegativeButton(R.string.close, (dialog, which) -> {})
                .show();
    }

//    public void onMapReady(GoogleMap googleMap) {
//        LatLng latlong = new LatLng(-6.3529081, 106.8018419);
//        googleMap.addMarker(new MarkerOptions()
//                .position(latlong)
//                .title("STT Nurul Fikri"));
//        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlong, 16.5f));
//    }

}
