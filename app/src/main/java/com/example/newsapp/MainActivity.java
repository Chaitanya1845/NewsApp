package com.example.newsapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NewsListAdapter.NewsListAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private NewsListAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        /*Creating the layout manager object with constructor having parameter
          Context -:Tells the Current context to application and it say from where to use the resource
          orientation-:Tells whether the layout will be horizontal or vertical
          reverselayout-:if set true layouts from end to start instead of start to end */
        LinearLayoutManager layoutManager;
        layoutManager= new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);


        /*setlayout used for telling which layout used in recycler view */
        mRecyclerView.setLayoutManager(layoutManager);

        mRecyclerView.setHasFixedSize(true);//See In Documentation
        fetchData();
        mAdapter=new NewsListAdapter(this);

        mRecyclerView.setAdapter(mAdapter);
    }
    /*Function Made for demo data for now*/
    private void fetchData(){
        ArrayList<News> newsArrayList=new ArrayList<News>();

        String url="https://saurav.tech/NewsAPI/top-headlines/category/entertainment/in.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            JSONArray newsJsonArray=response.getJSONArray("articles");
                             for(int i=0;i<newsJsonArray.length();i++){

                                 JSONObject newsJsonObject=newsJsonArray.getJSONObject(i);
                                 String author=newsJsonObject.getString("author");
                                 String title=newsJsonObject.getString("title");
                                 String url=newsJsonObject.getString("url");
                                 String urlToImage=newsJsonObject.getString("urlToImage");
                                 String description=newsJsonObject.getString("description");
                                 String publishAt=newsJsonObject.getString("publishedAt");
                                 newsArrayList.add(new News(title,author,url,urlToImage,description,publishAt));
                             }
                             mAdapter.updateNews(newsArrayList);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
}
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                    }
                });
// Add the request to the RequestQueue.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onItemClicked(News news) {

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(this, Uri.parse(news.getmUrl()));    }

}