package shivamgupta.myfirstapp.com.worditout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by the master mind Mr.Shivam Gupta on 2/6/2016.
 */
public class CategoryActivity extends ActionBarActivity implements View.OnClickListener {

   Button tech,animals,geo,next;
    SharedPreferences someData;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Choose Category");

        tech = (Button) findViewById(R.id.tech);
        tech.setOnClickListener(this);
        animals = (Button) findViewById(R.id.animals);
        animals.setOnClickListener(this);
        geo = (Button) findViewById(R.id.geo);
        geo.setOnClickListener(this);
        someData = getSharedPreferences(FrontActivity.filename,0);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tech:
                SharedPreferences.Editor editor = someData.edit();
                editor.putString("SharedCategory","tech");
                editor.commit();
                Intent i = new Intent("shivamgupta.myfirstapp.com.worditout.LevelActivity");
                startActivity(i);
                break;
            case R.id.geo:
                SharedPreferences.Editor e = someData.edit();
                e.putString("SharedCategory","geo");
                e.commit();
                Intent ii = new Intent("shivamgupta.myfirstapp.com.worditout.LevelActivity");
                startActivity(ii);
                break;
            case R.id.animals:
                SharedPreferences.Editor ee = someData.edit();
                ee.putString("SharedCategory","animals");
                ee.commit();
                Intent iii = new Intent("shivamgupta.myfirstapp.com.worditout.LevelActivity");
                startActivity(iii);
                break;
        }
    }
}
