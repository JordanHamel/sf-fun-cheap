package com.sf.funcheap;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class FeedActivity extends Activity {

    public FeedActivity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        new RetrieveFeedTask().execute("http://feeds.feedburner.com/funcheapsf_recent_added_events?format=xml");
    }



    class RetrieveFeedTask extends AsyncTask<String, Void, RssParser.RssFeed> {

        private Exception exception;
        private RssParser.RssFeed feed;
        private List<RssParser.Item> items;
        private ListView listView = (ListView) findViewById(R.id.itemsList);


        protected RssParser.RssFeed doInBackground(String... params) {
            try {
                RssParser rp = new RssParser("http://feeds.feedburner.com/funcheapsf_recent_added_events?format=xml");
                rp.parse();
                feed = rp.getFeed();
                return feed;
            } catch (Exception e) {
                this.exception = e;
                return null;
            }
        }

        @Override
        protected void onPostExecute(RssParser.RssFeed rssFeed) {

            ItemAdapter itemArrayAdapter = new ItemAdapter(activity);
            listView.setAdapter(itemArrayAdapter);

            for (int i = 0; i < feed.items.size(); i++) {
                itemArrayAdapter.addItem(feed.items.get(i));
            }
        }
    }
}


