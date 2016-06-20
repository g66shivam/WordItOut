package shivamgupta.myfirstapp.com.worditout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by the master mind Mr.Shivam Gupta on 2/6/2016.
 */
public class FrontActivity extends Activity implements View.OnClickListener {

    Button game,scoreboard;
    EditText name;
    public static String filename = "MyData";
    SharedPreferences someData;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frontpage_layout);

        game = (Button) findViewById(R.id.newgame);
        game.setOnClickListener(this);

        scoreboard = (Button) findViewById(R.id.leader);
        scoreboard.setOnClickListener(this);

        name = (EditText) findViewById(R.id.name);
        someData = getSharedPreferences(filename,0);
    }

        @Override
    public void onClick(View view) {
            switch (view.getId()) {
                case R.id.newgame:
                    String data = name.getText().toString();
                    SharedPreferences.Editor editor = someData.edit();
                    editor.putString("SharedName",data);
                    editor.putString("SharedScore","0");
                    editor.commit();
                    Intent i = new Intent("shivamgupta.myfirstapp.com.worditout.CategoryActivity");
                    startActivity(i);
                    break;
                case R.id.leader:
                    Intent ii = new Intent("shivamgupta.myfirstapp.com.worditout.LeaderBoard");
                    startActivity(ii);
                    break;
            }
    }
}
