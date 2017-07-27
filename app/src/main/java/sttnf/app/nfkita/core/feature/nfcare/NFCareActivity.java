package sttnf.app.nfkita.core.feature.nfcare;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.base.BaseActivity;
import sttnf.app.nfkita.holder.CareChatHolder;
import sttnf.app.nfkita.models.CareChatModel;
import sttnf.app.nfkita.utils.CacheManager;

/**
 * Created by isfaaghyth on 7/26/17.
 * github: @isfaaghyth
 */
public class NFCareActivity extends BaseActivity<NFCarePresenter> implements NFCareView {

    @BindView(R.id.txt_title) TextView txtTitle;
    @BindView(R.id.toolbar_nfkita) Toolbar toolbarNfkita;
    @BindView(R.id.lst_chat) RecyclerView lstChat;
    @BindView(R.id.edt_message) EditText edtMessage;
    @BindView(R.id.btn_send) Button btnSend;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private FirebaseRecyclerAdapter<CareChatModel, CareChatHolder> adapter;

    private String dataShotItem;

    @Override protected NFCarePresenter createPresenter() {
        return new NFCarePresenter(this);
    }

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding(R.layout.activity_nfcare);
        setToolbar(toolbarNfkita, true);
        txtTitle.setText("NFCare!");
        lstChat.setLayoutManager(new LinearLayoutManager(this));
        dataShotItem = CacheManager.grabString("email").split("@")[0];
        initGroupChatFirebase();
    }

    private void initGroupChatFirebase() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference();
        adapter = new FirebaseRecyclerAdapter<CareChatModel, CareChatHolder>(
                CareChatModel.class,
                R.layout.layout_chat_ballon,
                CareChatHolder.class,
                mDatabaseReference.child("chats").child(dataShotItem)) {
            @Override protected void populateViewHolder(CareChatHolder holder, CareChatModel model, int position) {
                if (model.getSender_name().equals(CacheManager.grabString("name"))) {
                    holder.setRightMessage(
                            getApplicationContext(),
                            model.getSender_name(),
                            model.getMessage(),
                            model.getTime(),
                            model.getAvatar()
                    );
                } else {
                    holder.setLeftMessage(
                            model.getSender_name(),
                            model.getMessage(), model.getTime()
                    );
                }
            }
        };
        ChildEventListener childEventListener = new ChildEventListener() {
            @Override public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                lstChat.smoothScrollToPosition(adapter.getItemCount());
            }
            @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                lstChat.smoothScrollToPosition(adapter.getItemCount());
            }
            @Override public void onChildRemoved(DataSnapshot dataSnapshot) {}
            @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {}
            @Override public void onCancelled(DatabaseError databaseError) {}
        };
        mDatabaseReference.addChildEventListener(childEventListener);
        lstChat.setAdapter(adapter);
        lstChat.smoothScrollToPosition(adapter.getItemCount());
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

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        String message = edtMessage.getText().toString();
        if (!message.isEmpty()) {
            Map<String, Object> param = new HashMap<>();
            param.put("sender_name", CacheManager.grabString("name"));
            param.put("avatar", CacheManager.grabString("avatar"));
            param.put("message", message);
            param.put("time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            edtMessage.setText("");
            mDatabaseReference.child("chats").child(dataShotItem)
                    .push()
                    .setValue(param)
                    .addOnCompleteListener(task -> {
                        edtMessage.setText("");
                        if(task.isSuccessful()){

                        }else{
                            Log.d("SendMessage", "failed ");
                        }
                    });
            lstChat.smoothScrollToPosition(adapter.getItemCount());
        }
    }

}