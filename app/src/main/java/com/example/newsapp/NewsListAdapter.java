package com.example.newsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/*For Creating the adapter for recyclerView we extend RecyclerView.Adapter paassing ViewHolder class*/
public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsListAdapterViewHolder>{

    private   ArrayList<News> mNewsData=new ArrayList<News>();
    private NewsListAdapterOnClickHandler mClickHandler;
    /*The Adapter needs data for binding the data using onBindViewHolder we
    * will get it through constructor of Adapterclass*/
    public NewsListAdapter(NewsListAdapterOnClickHandler listener){

        mClickHandler=listener;
    }
    public void updateNews(ArrayList<News> news){

        if(mNewsData!=null)
            mNewsData.clear();
        mNewsData.addAll(news);
        notifyDataSetChanged();
    }
    public interface NewsListAdapterOnClickHandler{

        void onItemClicked(News news);
    }
    //This will be called when first list is being made then items will be reused like it is recycleed
    @NonNull
    @Override
    public NewsListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context=parent.getContext(); // getting the context of application or say viewgroup(parent view)
        int layoutIdForListItem = R.layout.item_news; // the list to be converted into view
        LayoutInflater inflater = LayoutInflater.from(context);// making object of layoutinfalter which used to converted xml to view
        boolean shouldAttachToParentImmediately = false; // it should  not attach to root (DON'T KNOW WHAT IT MEANS BUT SEE IN DOCUMENTATION

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        /*We need to return viewHolder object so making and it
          requires view as constructor which is make using inflate method in above statement*/

        NewsListAdapterViewHolder viewHolder = new NewsListAdapterViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickHandler.onItemClicked(mNewsData.get(viewHolder.getAdapterPosition() ));
            }
        });
        return viewHolder;
    }


    //It binds the data in holder
    @Override
    public void onBindViewHolder(@NonNull NewsListAdapterViewHolder holder, int position) {

        News currentItem=mNewsData.get(position);// Gets the items stored in array list and then set the textView using it
        /*Here we can make custom classes like i made ford TourGuide app showing ImageView,2 to 3 TextView or in BOok app
        * where ratingBar was used*/
        holder.mtitleView.setText(currentItem.getmTitle());
        holder.mAuthorView.setText((currentItem.getmAuthor()));
        Glide.with(holder.itemView.getContext()).load(currentItem.mImageUrl).into(holder.mImageView);
        holder.mDescriptionView.setText(currentItem.getmDescription());
        holder.mPubishAtiew.setText(currentItem.getmPublishAt());
    }

    //Tells how many items will be tehere in list
    @Override
    public int getItemCount() {
        return mNewsData.size();
    }

    public class NewsListAdapterViewHolder extends RecyclerView.ViewHolder{

        public final TextView mtitleView;
        public final TextView mAuthorView;
        public final ImageView mImageView;
        public final TextView mDescriptionView;
        public final TextView mPubishAtiew;

        public NewsListAdapterViewHolder( View itemView) {
            super(itemView);

            mtitleView=(TextView)itemView.findViewById((R.id.title));// ViewHolder here we referene the view in item
            mAuthorView=(TextView)itemView.findViewById((R.id.author));// ViewHolder here we referene the view in item
            mImageView=(ImageView)itemView.findViewById((R.id.image));// ViewHolder here we referene the view in item
            mDescriptionView=(TextView)itemView.findViewById((R.id.descriptionn));// ViewHolder here we referene the view in item
            mPubishAtiew=(TextView)itemView.findViewById(R.id.publishAt);
        }
    }
 }
