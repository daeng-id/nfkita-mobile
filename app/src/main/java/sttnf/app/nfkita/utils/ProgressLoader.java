package sttnf.app.nfkita.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by isfaaghyth on 7/13/17.
 */

public class ProgressLoader {
    private ProgressDialog mProgressDialog;
    private Context context;

    public ProgressLoader(Context context) {
        this.context = context;
        mProgressDialog = new ProgressDialog(context);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Loading...");
    }

    public void show(){
        mProgressDialog.show();
    }

    public void hide(){
        if (mProgressDialog.isShowing())
            mProgressDialog.dismiss();
    }
}
