package sttnf.app.nfkita.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import sttnf.app.nfkita.R;

/**
 * Created by isfaaghyth on 7/26/17.
 * github: @isfaaghyth
 */

public class CareChatHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.layout_left)
    LinearLayout layoutLeft;
    @BindView(R.id.txt_name_left)
    TextView txtNameLeft;
    @BindView(R.id.txt_message_left) TextView txtMessageLeft;
    @BindView(R.id.txt_time_left) TextView txtTimeLeft;

    @BindView(R.id.layout_right) LinearLayout layoutright;
    @BindView(R.id.txt_name_right) TextView txtNameRight;
    @BindView(R.id.txt_message_right) TextView txtMessageRight;
    @BindView(R.id.txt_time_right) TextView txtTimeRight;
    @BindView(R.id.img_avatar) ImageView imgAvatar;

    public CareChatHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public Map<String, Integer> initSize(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Map<String, Integer> values = new HashMap<>();
        values.put("width", displayMetrics.widthPixels / 2);
        values.put("height", displayMetrics.heightPixels / 2);
        return values;
    }

    public void setLeftMessage(String name, String message, String time) {
        layoutLeft.setVisibility(View.VISIBLE);
        layoutright.setVisibility(View.GONE);
        txtNameLeft.setText(name);
        txtMessageLeft.setText(message);
        txtTimeLeft.setText(time);
    }

    public void setRightMessage(Context context, String name, String message, String time, String avatar) {
        layoutright.setVisibility(View.VISIBLE);
        layoutLeft.setVisibility(View.GONE);
        txtNameRight.setVisibility(View.GONE);
        txtNameRight.setText(name);
        txtMessageRight.setText(message);
        txtTimeRight.setText(time);

        Glide.with(context)
                .load(avatar)
                .asBitmap().centerCrop()
                .placeholder(R.mipmap.ic_nfkita_round)
                .into(new BitmapImageViewTarget(imgAvatar) {
                    @Override protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable rounded =
                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                        rounded.setCircular(true);
                        imgAvatar.setImageDrawable(rounded);
                    }
                });
    }

}
