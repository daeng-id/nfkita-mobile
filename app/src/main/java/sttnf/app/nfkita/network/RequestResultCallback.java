package sttnf.app.nfkita.network;

import android.util.Log;

import retrofit2.HttpException;
import rx.Subscriber;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public abstract class RequestResultCallback<M> extends Subscriber<M> {
    private static final String TAG = RequestResultCallback.class.getName();

    public abstract void onSuccess(M model);
    public abstract void onFailure(String message);
    public abstract void onFinish();

    @Override public void onError(Throwable e) {
        e.printStackTrace();
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            int code = httpException.code();
            String message = httpException.getMessage();
            Log.i(TAG, "code : " + code);
            onFailure(message);

        } else {
            onFailure(e.getMessage());
        }
        onFinish();
    }

    @Override public void onNext(M model) {
        onSuccess(model);
    }

    @Override public void onCompleted() {
        onFinish();
    }
}