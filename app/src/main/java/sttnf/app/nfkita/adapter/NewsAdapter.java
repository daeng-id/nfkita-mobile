package sttnf.app.nfkita.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import sttnf.app.nfkita.R;
import sttnf.app.nfkita.core.readarticle.ReadArticleActivity;
import sttnf.app.nfkita.interfaces.OnClickCallback;
import sttnf.app.nfkita.models.NewsModel;

/**
 * Created by isfaaghyth on 7/12/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.Holder> {

    private Context context;
    private OnClickCallback<ArrayList<NewsModel.Post>> onClickCallback;
    private ArrayList<NewsModel.Post> posts;

    public NewsAdapter(Context context, ArrayList<NewsModel.Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    public void setOnClickCallback(OnClickCallback<ArrayList<NewsModel.Post>> onClickCallback) {
        this.onClickCallback = onClickCallback;
    }

    @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_news_item, parent, false));
    }

    @Override public void onBindViewHolder(Holder holder, int position) {
        Picasso.with(context).load(posts.get(position).getThumbnail()).into(holder.imgThumbnail);
        holder.txtTitle.setText(posts.get(position).getTitle());
        holder.txtWriter.setText(posts.get(position).getAuthor().getName());
        holder.txtCommentCount.setText(String.valueOf(posts.get(position).getCommentCount()));
        holder.txtDate.setText(posts.get(position).getDate());

        holder.cardNews.setOnClickListener(v -> onClickCallback.onClick(posts, position));
    }

    @Override public int getItemCount() {
        return posts.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.card_news) CardView cardNews;
        @BindView(R.id.img_cover) ImageView imgThumbnail;
        @BindView(R.id.txt_title) TextView txtTitle;
        @BindView(R.id.txt_writer) TextView txtWriter;
        @BindView(R.id.txt_comment_count) TextView txtCommentCount;
        @BindView(R.id.txt_date) TextView txtDate;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
