package sttnf.app.nfkita.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.interfaces.OnClickCallback;
import sttnf.app.nfkita.models.CategoryModel;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.Holder> {

    private OnClickCallback<ArrayList<CategoryModel.Categories>> onClickCallback;
    private ArrayList<CategoryModel.Categories> categories;

    public CategoryAdapter(ArrayList<CategoryModel.Categories> categories) {
        this.categories = categories;
    }

    public void setOnClickCallback(OnClickCallback<ArrayList<CategoryModel.Categories>> onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_category_item, parent, false));
    }

    @Override public void onBindViewHolder(Holder holder, int position) {
        holder.cardCategories.setOnClickListener(v -> onClickCallback.onClick(categories, position));
        holder.txtCategory.setText(categories.get(position).getTitle());
        holder.txtPostCount.setText("Ada " + categories.get(position).getPost_count() + " artikel.");
    }

    @Override public int getItemCount() {
        return categories.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_categories) CardView cardCategories;
        @BindView(R.id.txt_category) TextView txtCategory;
        @BindView(R.id.txt_post_count) TextView txtPostCount;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
