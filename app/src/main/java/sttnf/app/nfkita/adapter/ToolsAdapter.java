package sttnf.app.nfkita.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.callback.ItemClickListener;
import sttnf.app.nfkita.models.ToolsModel;

/**
 * Created by isfaaghyth on 7/13/17.
 */

public class ToolsAdapter extends RecyclerView.Adapter<ToolsAdapter.Holder> {

    private Context context;
    private ArrayList<ToolsModel> tools;
    private ItemClickListener listener;

    public ToolsAdapter setListener(ItemClickListener listener) {
        this.listener = listener;
        return this;
    }

    public ToolsAdapter(Context context, ArrayList<ToolsModel> tools) {
        this.context = context;
        this.tools = tools;
    }

    @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tools_item, parent, false));
    }

    @Override public void onBindViewHolder(Holder holder, int position) {
        holder.txtToolsName.setText(tools.get(position).getName());
        Glide.with(context).load(tools.get(position).getResId()).into(holder.imgFeature);

        holder.cardItem.setOnClickListener(v -> listener.onItemClicked(v, position));
    }

    @Override public int getItemCount() {
        return tools.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_tools_name) TextView txtToolsName;
        @BindView(R.id.card_feature) LinearLayout cardItem;
        @BindView(R.id.img_feature) ImageView imgFeature;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
