package sttnf.app.nfkita;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.jaychang.slm.SocialLoginManager;

import sttnf.app.nfkita.utils.KeyHash;

/**
 * Created by isfaaghyth on 7/13/17.
 */

public class NFKitaApplication extends MultiDexApplication {

    private static Context context;

    @Override public void onCreate() {
        super.onCreate();
        KeyHash.generateKeyHash(this);
        SocialLoginManager.init(this);
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
