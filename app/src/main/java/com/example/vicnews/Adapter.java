package com.example.vicnews;


import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.vicnews.model.Articles;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
    private Context mContext;
    private List<Articles> mArticles;

    public Adapter(Context mContext, List<Articles> mArticles) {
        this.mContext = mContext;
        this.mArticles = mArticles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new  ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles article = mArticles.get(position);
        Glide.with(mContext)
                .load(article.getUrlToImage())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.imageView);

        holder.title.setText(article.getTitle());
        holder.description.setText(article.getDescription());
        holder.source.setText(article.getSource().getName());
        holder.author.setText(article.getAuthor());
        holder.date.setText(article.getPublishedAt());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, content.class);
            intent.putExtra("url",  article.getUrl());
            mContext.startActivity(intent);
        });

        // Alternative
        holder.bind(article);
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, description, source, author, date, content;
       // public int currentPosition;
        ProgressBar progressBar;
        LinearLayout parentLayout;
        private Articles article;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.desc);
            source = itemView.findViewById(R.id.source);
            author = itemView.findViewById(R.id.author);
            date = itemView.findViewById(R.id.publishedAt);
            progressBar = itemView.findViewById(R.id.progress_load_photo);

           // content = itemView.findViewById(R.id.content);

           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Articles articles = mArticles.get(currentPosition);


                }
            });*/

           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent = new Intent(mContext, content.class);
                    intent.putExtra("url", article.getUrl());
                    mContext.startActivity(intent);
                }
            });*/


        }

        public void bind(Articles article) {

            this.article = article;
        }
    }


}