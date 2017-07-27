package sttnf.app.nfkita.core.feature.laporkampus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.OnClick;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.base.BaseActivity;
import sttnf.app.nfkita.utils.RuntimePermissions;

/**
 * Created by isfaaghyth on 7/27/17.
 * github: @isfaaghyth
 */
public class CampustReportActivity extends BaseActivity<CampustReportPresenter> implements CampustReportView {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    @BindView(R.id.txt_title) TextView txtTitle;
    @BindView(R.id.toolbar_nfkita) Toolbar toolbarNfkita;
    @BindView(R.id.img_result) ImageView imgResult;
    @BindView(R.id.btn_take_picture) RelativeLayout btnTakePicture;
    @BindView(R.id.btn_send) Button btnSend;

    @Override protected CampustReportPresenter createPresenter() {
        return new CampustReportPresenter(this);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_campus_report);
        RuntimePermissions.request(this);
        setToolbar(toolbarNfkita, true);
        txtTitle.setText("Lapor Kampus");
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btn_take_picture)
    public void onBtnTakePictureClicked() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgResult.setImageBitmap(imageBitmap);
        }
    }

    @OnClick(R.id.btn_send)
    public void onBtnSendClicked() {
        Toast.makeText(this, "Laporan terkirim!\nKami akan tindaklanjuti.", Toast.LENGTH_LONG).show();
        onBackPressed();
        finish();
    }
}