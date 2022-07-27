package com.example.newsapp;


public class News {

    String  mTitle;
    String  mAuthor;
    String  mUrl;
    String  mImageUrl;
    String mDescription;
    String mPublishAt;
    public News(String title,String author,String url,String imageUrl,String description,String publishAt ){

        mTitle=title;
        mAuthor=author;
        mUrl=url;
        mImageUrl=imageUrl;
        mDescription=description;
        mPublishAt=publishAt;
    }

    public String getmPublishAt() {

        return "PUBLISH AT -: "+ mPublishAt;
    }

    public String getmDescription() {
        return mDescription;
    }

    public String getmUrl() {
        return mUrl;
    }

    public String getmAuthor() {

        if(mAuthor==null)
            mAuthor="No Info";
        return "AUTHOR-: "+mAuthor;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

}
