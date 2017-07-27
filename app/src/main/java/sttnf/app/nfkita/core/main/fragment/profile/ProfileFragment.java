package sttnf.app.nfkita.core.main.fragment.profile;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.jaychang.slm.SocialLoginManager;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.OnClick;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.base.BaseFragment;
import sttnf.app.nfkita.utils.CacheManager;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public class ProfileFragment extends BaseFragment<ProfileFragmentPresenter> implements ProfileFragmentView {

    private String TAG = ProfileFragment.this.getClass().getName();

    @BindView(R.id.layout_login) RelativeLayout layoutLogin;
    @BindView(R.id.txt_full_name) TextView txtFullName;
    @BindView(R.id.img_avatar) ImageView imgAvatar;
    @BindView(R.id.txt_email) TextView txtEmail;

    @Override protected ProfileFragmentPresenter createPresenter() {
        return new ProfileFragmentPresenter(this);
    }

    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        binding(view);
        facebookInit();
        if (CacheManager.checkExist("login")) {
            if (CacheManager.grabBoolean("login")) {
                layoutLogin.setVisibility(View.GONE);
                loadData();
            } else {
                layoutLogin.setVisibility(View.VISIBLE);
            }
        }
    }

    private void loadData() {
        String avatar = CacheManager.grabString("avatar");
        String name = CacheManager.grabString("name");
        String email = CacheManager.grabString("email");
        Glide.with(getContext())
                .load(avatar)
                .asBitmap().centerCrop()
                .placeholder(R.mipmap.ic_nfkita_round)
                .into(new BitmapImageViewTarget(imgAvatar) {
                      @Override protected void setResource(Bitmap resource) {
                          RoundedBitmapDrawable rounded =
                          RoundedBitmapDrawableFactory.create(getContext().getResources(), resource);
                          rounded.setCircular(true);
                          imgAvatar.setImageDrawable(rounded);
                      }
                });
        txtFullName.setText(name);
        txtEmail.setText(email);
    }

    @OnClick(R.id.btn_login_fb)
    public void onLoginFacebook() {
        SocialLoginManager.getInstance(getContext())
                .facebook()
                .login()
                .subscribe(socialUser -> {
                    String avatar = socialUser.photoUrl;
                    String name = socialUser.profile.name;
                    String email = socialUser.profile.email;
                    CacheManager.save("login", true);
                    CacheManager.save("avatar", avatar);
                    CacheManager.save("name", name);
                    CacheManager.save("email", email);
                    layoutLogin.setVisibility(View.GONE);
                    loadData();
                }, error -> {
                    Log.d(TAG, "error: " + error.getMessage());
                });
    }

    @OnClick(R.id.btn_login_gplus)
    public void onLoginGooglePlus() {
        new AlertDialog.Builder(getContext())
            .setTitle("Google Plus")
            .setMessage("Coming soon, bro!")
            .setNegativeButton("OK", (dialog, which) -> {})
            .show();
    }

    @OnClick(R.id.btn_logout)
    public void onLogout() {
        new AlertDialog.Builder(getContext())
                .setTitle("Logout")
                .setMessage("Are you sure, bro?")
                .setPositiveButton("YES", (dialog, which) -> {
                    CacheManager.save("login", false);
                    layoutLogin.setVisibility(View.VISIBLE);
                })
                .setNegativeButton("CANCEL", (dialog, which) -> {})
                .show();
    }

    private void facebookInit() {

    }
}
