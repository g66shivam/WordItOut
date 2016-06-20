package shivamgupta.myfirstapp.com.worditout;

import android.app.Activity;

import android.app.Activity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;
import com.microsoft.windowsazure.mobileservices.MobileServiceClient;

import java.net.MalformedURLException;
import java.util.List;


public class LeaderBoard extends ActionBarActivity {

    TextView textView, textView2;
    private MobileServiceClient mClient;
    private MobileServiceTable<Item> mToDoTable;
    private ItemAdapter mAdapter;
    SharedPreferences someData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leader_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Leader Board");

        someData = getSharedPreferences(FrontActivity.filename,0);
        String name = someData.getString("SharedName","can't");
        String score = someData.getString("SharedScore","can't");

        textView = (TextView) findViewById(R.id.nname);
//        textView2 = (TextView) findViewById(R.id.nnscore);
        textView.setText(name+" "+score);
  //      textView2.setText(score);
/*
        try {
            mClient = new MobileServiceClient("https://kappekon.azure-mobile.net/", "CqwhsKBBcMLhIqHkogdFYBJfQCjGqe30", this);
            mToDoTable = mClient.getTable(Item.class);
            refreshItemsFromTable();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        */
        // Load the items from the Mobile Service

        //refreshItemsFromTable();
    }

    private void refreshItemsFromTable() {
        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... params) {

                try {
                    final MobileServiceList<Item> result = mToDoTable.execute().get();
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            mAdapter.clear();
                            for (Item item : result) {
                                mAdapter.add(item);
                            }
                        }
                    });
                } catch (final Exception e){
                    //createAndShowDialogFromTask(e, "Error");
                }

                return null;
            }
        };

        //runAsyncTask(task);
    }

}