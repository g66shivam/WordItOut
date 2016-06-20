package shivamgupta.myfirstapp.com.worditout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.*;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;
import java.util.Random;

/**
 * Created by the master mind Mr.Shivam Gupta on 2/6/2016.
 */
public class SecondActivity extends ActionBarActivity implements View.OnClickListener{

    //TextView A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;
    SharedPreferences someData;
    TextView ans,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,RR,S,T,U,V,W,X,Y,Z,pp1,pp2,ques,theme,score_view;
    String output="";
    ImageView iw;
    String arr[];
    String names_description[];
    int flag,ctr=0,i=0,score=0,increment=0;
    private MobileServiceClient mClient;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maingame_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        try {
            mClient = new MobileServiceClient("https://kappekon.azure-mobile.net/","CqwhsKBBcMLhIqHkogdFYBJfQCjGqe30",this);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        someData = getSharedPreferences(FrontActivity.filename,0);

        A = (TextView) findViewById(R.id.A);
        A.setOnClickListener(this);
        B = (TextView) findViewById(R.id.B);
        B.setOnClickListener(this);
        C = (TextView) findViewById(R.id.C);
        C.setOnClickListener(this);
        D = (TextView) findViewById(R.id.D);
        D.setOnClickListener(this);
        E = (TextView) findViewById(R.id.E);
        E.setOnClickListener(this);
        F = (TextView) findViewById(R.id.F);
        F.setOnClickListener(this);
        G = (TextView) findViewById(R.id.G);
        G.setOnClickListener(this);
        H = (TextView) findViewById(R.id.H);
        H.setOnClickListener(this);
        I = (TextView) findViewById(R.id.I);
        I.setOnClickListener(this);
        J = (TextView) findViewById(R.id.J);
        J.setOnClickListener(this);
        K = (TextView) findViewById(R.id.K);
        K.setOnClickListener(this);
        L = (TextView) findViewById(R.id.L);
        L.setOnClickListener(this);
        M = (TextView) findViewById(R.id.M);
        M.setOnClickListener(this);
        N = (TextView) findViewById(R.id.N);
        N.setOnClickListener(this);
        O = (TextView) findViewById(R.id.O);
        O.setOnClickListener(this);
        P = (TextView) findViewById(R.id.P);
        P.setOnClickListener(this);
        Q = (TextView) findViewById(R.id.Q);
        Q.setOnClickListener(this);
        RR = (TextView) findViewById(R.id.R);
        RR.setOnClickListener(this);
        S = (TextView) findViewById(R.id.S);
        S.setOnClickListener(this);
        T = (TextView) findViewById(R.id.T);
        T.setOnClickListener(this);
        U = (TextView) findViewById(R.id.U);
        U.setOnClickListener(this);
        V = (TextView) findViewById(R.id.V);
        V.setOnClickListener(this);
        W = (TextView) findViewById(R.id.W);
        W.setOnClickListener(this);
        X = (TextView) findViewById(R.id.X);
        X.setOnClickListener(this);
        Y = (TextView) findViewById(R.id.Y);
        Y.setOnClickListener(this);
        Z = (TextView) findViewById(R.id.Z);
        Z.setOnClickListener(this);

        iw = (ImageView) findViewById(R.id.logo);
        ans = (TextView)findViewById(R.id.ans);
        ques = (TextView)findViewById(R.id.ques);
        theme = (TextView)findViewById(R.id.theme);
        score_view = (TextView)findViewById(R.id.score);

        String category = someData.getString("SharedCategory","can't");
        String level = someData.getString("SharedLevel","can't");
        String sscore = someData.getString("SharedScore","can't");
        score = Integer.parseInt(sscore);


        get_string(category);
        i = generate_random(arr.length);
        //arr[i] = arr[i].toUpperCase();
        output = initialize(arr[i],level);
        set_Text(output);
        ques.setText(names_description[i]);
        theme.setText(category);
        score_view.setText(sscore);

        SharedPreferences.Editor editor = someData.edit();
        editor.putString("SharedInt",""+i);
        editor.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.A:
                output = check_string(arr[i],'A',output);
                set_Text(output);
                check_ctr(arr[i]);
                A.setBackgroundColor(Color.RED);
                break;
            case R.id.B:
                output = check_string(arr[i],'B',output);
                set_Text(output);
                check_ctr(arr[i]);
                B.setBackgroundColor(Color.RED);
                break;
            case R.id.C:
                output = check_string(arr[i],'C',output);
                set_Text(output);
                check_ctr(arr[i]);
                C.setBackgroundColor(Color.RED);
                break;
            case R.id.D:
                output = check_string(arr[i],'D',output);
                set_Text(output);
                check_ctr(arr[i]);
                D.setBackgroundColor(Color.RED);
                break;
            case R.id.E:
                output = check_string(arr[i],'E',output);
                set_Text(output);
                check_ctr(arr[i]);
                E.setBackgroundColor(Color.RED);
                break;
            case R.id.F:
                output = check_string(arr[i],'F',output);
                set_Text(output);
                check_ctr(arr[i]);
                F.setBackgroundColor(Color.RED);
                break;
            case R.id.G:
                output = check_string(arr[i],'G',output);
                set_Text(output);
                check_ctr(arr[i]);
                G.setBackgroundColor(Color.RED);
                break;
            case R.id.H:
                output = check_string(arr[i],'H',output);
                set_Text(output);
                check_ctr(arr[i]);
                H.setBackgroundColor(Color.RED);
                break;
            case R.id.I:
                output = check_string(arr[i],'I',output);
                set_Text(output);
                check_ctr(arr[i]);
                I.setBackgroundColor(Color.RED);
                break;
            case R.id.J:
                output = check_string(arr[i],'J',output);
                set_Text(output);
                check_ctr(arr[i]);
                J.setBackgroundColor(Color.RED);
                break;
            case R.id.K:
                output = check_string(arr[i],'K',output);
                set_Text(output);
                check_ctr(arr[i]);
                K.setBackgroundColor(Color.RED);
                break;
            case R.id.L:
                output = check_string(arr[i],'L',output);
                set_Text(output);
                check_ctr(arr[i]);
                L.setBackgroundColor(Color.RED);
                break;
            case R.id.M:
                output = check_string(arr[i],'M',output);
                set_Text(output);
                check_ctr(arr[i]);
                M.setBackgroundColor(Color.RED);
                break;
            case R.id.N:
                output = check_string(arr[i],'N',output);
                set_Text(output);
                check_ctr(arr[i]);
                N.setBackgroundColor(Color.RED);
                break;
            case R.id.O:
                output = check_string(arr[i],'O',output);
                set_Text(output);
                check_ctr(arr[i]);
                O.setBackgroundColor(Color.RED);
                break;
            case R.id.P:
                output = check_string(arr[i],'P',output);
                set_Text(output);
                check_ctr(arr[i]);
                P.setBackgroundColor(Color.RED);
                break;
            case R.id.Q:
                output = check_string(arr[i],'Q',output);
                set_Text(output);
                check_ctr(arr[i]);
                Q.setBackgroundColor(Color.RED);
                break;
            case R.id.R:
                output = check_string(arr[i],'R',output);
                set_Text(output);
                check_ctr(arr[i]);
                RR.setBackgroundColor(Color.RED);
                break;
            case R.id.S:
                output = check_string(arr[i],'S',output);
                set_Text(output);
                check_ctr(arr[i]);
                S.setBackgroundColor(Color.RED);
                break;
            case R.id.T:
                output = check_string(arr[i],'T',output);
                set_Text(output);
                check_ctr(arr[i]);
                T.setBackgroundColor(Color.RED);
                break;
            case R.id.U:
                output = check_string(arr[i],'U',output);
                set_Text(output);
                check_ctr(arr[i]);
                U.setBackgroundColor(Color.RED);
                break;
            case R.id.V:
                output = check_string(arr[i],'V',output);
                set_Text(output);
                check_ctr(arr[i]);
                V.setBackgroundColor(Color.RED);
                break;
            case R.id.W:
                output = check_string(arr[i],'W',output);
                set_Text(output);
                check_ctr(arr[i]);
                W.setBackgroundColor(Color.RED);
                break;
            case R.id.X:
                output = check_string(arr[i],'X',output);
                set_Text(output);
                check_ctr(arr[i]);
                X.setBackgroundColor(Color.RED);
                break;
            case R.id.Y:
                output = check_string(arr[i],'Y',output);
                set_Text(output);
                check_ctr(arr[i]);
                Y.setBackgroundColor(Color.RED);
                break;
            case R.id.Z:
                output = check_string(arr[i],'Z',output);
                set_Text(output);
                check_ctr(arr[i]);
                Z.setBackgroundColor(Color.RED);
                break;
        }
    }

    String initialize(String str,String level)
    {
        int len = str.length();
        String out = "";
        if(level.equals("one"))
        {
            increment = 2;
            for(int i=0;i<len;i++)
            {
                if(str.charAt(i)=='A')
                {
                    out = out+"A";
                }
                else if(str.charAt(i)=='E')
                {
                    out = out+"E";
                }
                else if(str.charAt(i)=='I')
                {
                    out = out+"I";
                }
                else if(str.charAt(i)=='O')
                {
                    out = out+"O";
                }
                else if(str.charAt(i)=='U')
                {
                    out = out+"U";
                }
                else
                    out = out+"_";
            }
        }
        else if(level.equals("two"))
        {
            increment = 4;
            out = out+str.charAt(0);
            for(int i=1;i<len-1;i++)
                out = out+"_";

            out = out+str.charAt(len-1);
        }
        else if(level.equals("three"))
        {
            increment = 6;
            out = out+str.charAt(0);
            for(int i=1;i<len;i++)
                out = out+"_";
        }
        else if(level.equals("four"))
        {
            increment = 8;
            for(int i=1;i<len;i++)
                out = out+"_";
        }
        return  out;
    }

    public String check_string(String str,char c,String output)
    {
        flag = 0;
        char[] o = output.toCharArray();
        char[] a = str.toCharArray();

        for(int i=0;i<str.length();i++)
        {
            if(str.charAt(i)==c)
            {
                o[i] = c;
                flag++;
            }
        }

        String new_output = new String(o);

        if(flag==0)
        {
            ctr++;
        }
        else if(str.contentEquals(new_output))
        {
                score = score+increment;
                String s = ""+score;
                SharedPreferences.Editor editor = someData.edit();
                editor.putString("SharedScore",s);
                editor.putString("SharedWord",str);
                editor.commit();

                showPopup(SecondActivity.this,"Correct","The Word is "+str);
        }
        return new_output;
    }

    public void check_ctr(String str)
    {
        if(ctr==1)
        {
            iw.setImageResource(R.drawable.microsoftlogo11);
        }
        else if(ctr==2)
        {
            iw.setImageResource(R.drawable.microsoftlogo12);
        }
        else if(ctr==3)
        {
            iw.setImageResource(R.drawable.microsoftlogo13);
        }
        else if(ctr==4)
        {
            iw.setImageResource(R.drawable.microsoftlogo14);
            SharedPreferences.Editor editor = someData.edit();
            editor.putString("SharedWord",str);
            editor.commit();

            showPopup(SecondActivity.this, "InCorrect", "The Word is " + str);
        }
    }

    private void showPopup(final Activity context,String s1,String s2) {
        int popupWidth = 300;
        int popupHeight = 225;

        // Inflate the popup_layout.xml
        LinearLayout viewGroup = (LinearLayout) context.findViewById(R.id.popup);
        LayoutInflater layoutInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = layoutInflater.inflate(R.layout.popup_layout, viewGroup);
        pp1 = (TextView) layout.findViewById(R.id.pp1);
        pp2 = (TextView) layout.findViewById(R.id.pp2);
        pp1.setText(s1);
        pp2.setText(s2);
        // Creating the PopupWindow
        final PopupWindow popup = new PopupWindow(context);
        popup.setContentView(layout);
        popup.setWidth(popupWidth);
        popup.setHeight(popupHeight);
        popup.setFocusable(true);

        // Some offset to align the popup a bit to the right, and a bit down, relative to button's position.
        int OFFSET_X = 75;
        int OFFSET_Y = 300;

        // Clear the default translucent background
        popup.setBackgroundDrawable(new BitmapDrawable());

        // Displaying the popup at the specified location, + offsets.
        popup.showAtLocation(layout, Gravity.NO_GRAVITY,OFFSET_X,OFFSET_Y);

        // Getting a reference to Close button, and close the popup when clicked.
        Button close = (Button) layout.findViewById(R.id.Cont);
        close.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent J = new Intent("shivamgupta.myfirstapp.com.worditout.SuggestionActivity");
                startActivity(J);
            }
        });
    }

    public void get_string(String c)
    {
        if(c.equals("tech"))
        {
            arr = DataActivity.technology;
            names_description = DataActivity.technology_description;
        }
        else if(c.equals("geo"))
        {
            arr = DataActivity.geography;
            names_description = DataActivity.geography_description;
        }
        else if(c.equals("animals"))
        {
            arr = DataActivity.animals;
            names_description = DataActivity.animals_description;
        }
    }
    //generating random no

    public int generate_random(int len)
    {
        Random r = new Random();
        int i1 = r.nextInt((len-1)-0) + 0;
        return  i1;
    }

    void set_Text(String str)
    {
        String print="";
        for(int i=0;i<str.length();i++)
        {
            print = print+str.charAt(i)+" ";
        }
        ans.setText(print);
    }

}