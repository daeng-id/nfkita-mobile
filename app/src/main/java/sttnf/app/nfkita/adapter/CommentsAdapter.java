package sttnf.app.nfkita.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.models.NewsModel;

/**
 * Created by isfaaghyth on 7/13/17.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.Holder> {

    private ArrayList<NewsModel.Post.Comments> comments;

    public CommentsAdapter(ArrayList<NewsModel.Post.Comments> comments) {
        this.comments = comments;
    }

    @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_comment_item, parent, false));
    }

    @Override public void onBindViewHolder(Holder holder, int position) {
        holder.txtName.setText(comments.get(position).getName());
        Elements content = Jsoup.parse(comments.get(position).getContent()).select("p");
        holder.txtComment.setText(content.text());
    }

    @Override public int getItemCount() {
        return comments.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_name) TextView txtName;
        @BindView(R.id.txt_comment) TextView txtComment;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
