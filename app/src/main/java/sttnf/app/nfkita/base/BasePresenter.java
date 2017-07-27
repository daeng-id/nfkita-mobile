package sttnf.app.nfkita.base;

import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import sttnf.app.nfkita.network.NFKitaClient;
import sttnf.app.nfkita.network.NFKitaRouteService;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public class BasePresenter<V> {
    public V view;
    protected NFKitaRouteService service;
    private CompositeSubscription compositeSubscription;
    private Subscriber subscriber;

    public void attachView(V view) {
        this.view = view;
        service = NFKitaClient.getClient().create(NFKitaRouteService.class);
    }

    public void dettachView() {
        this.view = null;
        if (compositeSubscription != null && compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe();
            Log.e("Base Presenter", "onUnsubscribe: ");
        }
    }

    protected void addSubscribe(Observable observable, Subscriber subscriber) {
        this.subscriber = subscriber;

        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }

    protected void stop() {
        if (subscriber != null) {
            subscriber.unsubscribe();
        }
    }
}
