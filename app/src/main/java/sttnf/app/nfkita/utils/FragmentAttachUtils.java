package sttnf.app.nfkita.utils;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by isfaaghyth on 7/13/17.
 */

public class FragmentAttachUtils {

    public static void addMapsFragment(@NonNull SupportMapFragment mainMap,
                                       OnMapReadyCallback callback,
                                       @NonNull Fragment fragment,
                                       int frameId) {
        mainMap = (SupportMapFragment) fragment.getFragmentManager().findFragmentById(frameId);
        FragmentManager fragmentManager = fragment.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        mainMap = SupportMapFragment.newInstance();
        fragmentTransaction.replace(frameId, mainMap).commit();
        mainMap.getMapAsync(callback);
    }

}

