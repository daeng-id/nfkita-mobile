package sttnf.app.nfkita.utils;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * Created by isfaaghyth on 7/13/17.
 */

public class MarqueeToolbar extends Toolbar {

    TextView title, subTitle;

    public MarqueeToolbar(Context context) {
        super(context);
    }

    public MarqueeToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MarqueeToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTitle(CharSequence title) {

        reflected = reflectTitle();

        super.setTitle(title);
        selectTitle();
    }

    @Override
    public void setTitle(int resId) {
        if (!reflected) {
            reflected = reflectTitle();
        }
        super.setTitle(resId);
        selectTitle();
    }

    boolean reflected = false;
    private boolean reflectTitle() {
        try {
            Field field = Toolbar.class.getDeclaredField("mTitleTextView");
            field.setAccessible(true);
            title = (TextView) field.get(this);
            title.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            title.setMarqueeRepeatLimit(-1);
            return true;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void selectTitle() {
        if (title != null)
            title.setSelected(true);
    }

// ------------ for Subtitle ----------

    @Override

    public void setSubtitle(CharSequence subTitle) {
        if (!reflectedSub) {
            reflectedSub = reflectSubTitle();
        }
        super.setSubtitle(subTitle);
        selectSubtitle();
    }


    @Override
    public void setSubtitle(int resId) {
        if (!reflected) {
            reflectedSub = reflectSubTitle();
        }
        super.setSubtitle(resId);
        selectSubtitle();
    }

    boolean reflectedSub = false;
    private boolean reflectSubTitle() {
        try {
            Field field = Toolbar.class.getDeclaredField("mSubtitleTextView");
            field.setAccessible(true);
            subTitle = (TextView) field.get(this);
            subTitle.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            subTitle.setMarqueeRepeatLimit(-1);
            return true;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void selectSubtitle() {
        if (subTitle != null)
            subTitle.setSelected(true);
    }

}