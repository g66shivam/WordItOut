package shivamgupta.myfirstapp.com.worditout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

/**
 * Created by the master mind Mr.Shivam Gupta on 2/6/2016.
 */
public class LevelActivity extends ActionBarActivity implements View.OnClickListener {
    TextView one, two, three, four;
    SharedPreferences someData;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Select Level");

        one = (TextView) findViewById(R.id.one);
        one.setOnClickListener(this);
        two = (TextView) findViewById(R.id.two);
        two.setOnClickListener(this);
        three = (TextView) findViewById(R.id.three);
        three.setOnClickListener(this);
        four = (TextView) findViewById(R.id.four);
        four.setOnClickListener(this);

        someData = getSharedPreferences(FrontActivity.filename,0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.one:
                SharedPreferences.Editor editor = someData.edit();
                editor.putString("SharedLevel","one");
                editor.commit();
                Intent i = new Intent("shivamgupta.myfirstapp.com.worditout.SecondActivity");
                startActivity(i);
                break;
            case R.id.two:
                SharedPreferences.Editor e = someData.edit();
                e.putString("SharedLevel","two");
                e.commit();
                Intent I = new Intent("shivamgupta.myfirstapp.com.worditout.SecondActivity");
                startActivity(I);
                break;
            case R.id.three:
                SharedPreferences.Editor eee = someData.edit();
                eee.putString("SharedLevel","three");
                eee.commit();
                Intent II = new Intent("shivamgupta.myfirstapp.com.worditout.SecondActivity");
                startActivity(II);
                break;
            case R.id.four:
                SharedPreferences.Editor ee = someData.edit();
                ee.putString("SharedLevel","four");
                ee.commit();
                Intent J = new Intent("shivamgupta.myfirstapp.com.worditout.SecondActivity");
                startActivity(J);
                break;
        }
    }
}
