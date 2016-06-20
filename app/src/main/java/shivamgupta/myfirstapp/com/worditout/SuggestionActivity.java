package shivamgupta.myfirstapp.com.worditout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.microsoft.windowsazure.mobileservices.MobileServiceClient;
import com.microsoft.windowsazure.mobileservices.http.ServiceFilterResponse;
import com.microsoft.windowsazure.mobileservices.table.MobileServiceTable;
import com.microsoft.windowsazure.mobileservices.table.TableOperationCallback;

import java.net.MalformedURLException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import org.ispeech.MarkerHolder;
import org.ispeech.SpeechSynthesis;
import org.ispeech.SpeechSynthesisEvent;
import org.ispeech.VisemeHolder;
import org.ispeech.error.BusyException;
import org.ispeech.error.InvalidApiKeyException;
import org.ispeech.error.NoNetworkException;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by the master mind Mr.Shivam Gupta on 2/6/2016.
 */
public class SuggestionActivity extends ActionBarActivity implements View.OnClickListener {

    String arr[];
    private static final String TAG = "iSpeech SDK Sample";
    TextView word, desc;
    Button bing, neww, cont,voice;
    private MobileServiceClient mClient;
    private MobileServiceTable<Item> mToDoTable;
    SpeechSynthesis synthesis;
    Context _context;
    SharedPreferences someData;
    String s;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggestion_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Description");
        _context = this.getApplicationContext();
        someData = getSharedPreferences(FrontActivity.filename, 0);
        assign();
        word = (TextView) findViewById(R.id.word);
        desc = (TextView) findViewById(R.id.desc);
        bing = (Button) findViewById(R.id.bing);
        neww = (Button) findViewById(R.id.newgame);
        cont = (Button) findViewById(R.id.cont);
        //voice = (Button) findViewById(R.id.voice);
        bing.setOnClickListener(this);
        neww.setOnClickListener(this);
        cont.setOnClickListener(this);
        //voice.setOnClickListener(this);

        word.setText(someData.getString("SharedWord", "can't"));
        int i = Integer.parseInt(someData.getString("SharedInt", "can't"));
        desc.setText(arr[i]);
        try {
            mClient = new MobileServiceClient("https://kappekon.azure-mobile.net/", "CqwhsKBBcMLhIqHkogdFYBJfQCjGqe30", this);
            mToDoTable = mClient.getTable(Item.class);
            // Create an adapter to bind the items with the view
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        final Item item = new Item();
        String name = someData.getString("SharedName", "can't");
        String score = someData.getString("SharedScore", "can't");
        int Score = Integer.parseInt(score);
        item.name = name;
        item.score = Score;
        mClient.getTable(Item.class).insert(item, new TableOperationCallback<Item>() {
            public void onCompleted(Item entity, Exception exception, ServiceFilterResponse response) {
                if (exception == null) {
                    Toast.makeText(SuggestionActivity.this, "Data Sent", Toast.LENGTH_LONG).show();
                    // Insert succeeded
                    // textView.setText(item.Text);
                    //textView2.setText("" + item.score);
                } else {
                    //Toast.makeText(MainActivity.this, "not sent", Toast.LENGTH_LONG).show();
                    // Insert failed
                }
            }
        });
        findViewById(R.id.voice).setOnClickListener(new OnSpeakListener());
        prepareTTSEngine();
        synthesis.setStreamType(AudioManager.STREAM_MUSIC);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bing:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.bing.com/search?q=" + someData.getString("SharedWord", "can't")));
                startActivity(intent);
                break;
            case R.id.newgame:
                Intent ii = new Intent("shivamgupta.myfirstapp.com.worditout.FrontActivity");
                startActivity(ii);
                break;
            case R.id.cont:
                Intent iii = new Intent("shivamgupta.myfirstapp.com.worditout.SecondActivity");
                startActivity(iii);
                break;
            /*
            case R.id.voice:
                Intent myIntent = new Intent(_context, TTSActivity.class);
                startActivity(myIntent);
                break;
                */
        }
    }

    public void assign() {
        String category = someData.getString("SharedCategory", "cant");

        if (category.equals("tech")) {
            arr = DataActivity.technology_meaning;
        } else if (category.equals("animals")) {
            arr = DataActivity.animals_meaning;
        } else if (category.equals("geo")) {
            arr = DataActivity.geography_meaning;
        }
    }


    private void prepareTTSEngine() {
        try {
            synthesis = SpeechSynthesis.getInstance(this);
            synthesis.setSpeechSynthesisEvent(new SpeechSynthesisEvent() {

                public void onPlaySuccessful() {
                    Log.i(TAG, "onPlaySuccessful");
                }

                public void onPlayStopped() {
                    Log.i(TAG, "onPlayStopped");
                }

                public void onPlayFailed(Exception e) {
                    Log.e(TAG, "onPlayFailed");


                    AlertDialog.Builder builder = new AlertDialog.Builder(SuggestionActivity.this);
                    builder.setMessage("Error[TTSActivity]: " + e.toString())
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }

                public void onPlayStart() {
                    Log.i(TAG, "onPlayStart");
                }

                @Override
                public void onPlayCanceled() {
                    Log.i(TAG, "onPlayCanceled");
                }


            });


        } catch (InvalidApiKeyException e) {
            Log.e(TAG, "Invalid API key\n" + e.getStackTrace());
            Toast.makeText(_context, "ERROR: Invalid API key", Toast.LENGTH_LONG).show();
        }

    }


    private class OnSpeakListener implements OnClickListener {

        public void onClick(View v) {

//			try {
//				String ttsText = ((EditText) findViewById(R.id.text)).getText().toString();
//				byte [] b = synthesis.downloadByteArray(ttsText);
//
//				if (b!=null){
//					Log.d("DEBUG", "SUCESSSSSSSS!!!!!");
//					MediaPlayer mediaPlayer;
//					mediaPlayer = new MediaPlayer();
//
//					File tempMp3 = File.createTempFile("test", ".mp3", getCacheDir());
//	                FileOutputStream fos = new FileOutputStream(tempMp3);
//	                fos.write(b);
//	                fos.close();
//
//	                mediaPlayer = MediaPlayer.create(getApplicationContext(),Uri.fromFile(tempMp3));
//	                mediaPlayer.start();
//
//
////					mediaPlayer.setDataSource();
//				}else{
//					Log.d("DEBUG", "FAILURE :( ");
//				}
//
//			} catch (BusyException e) {
//				Log.e(TAG, "SDK is busy");
//				e.printStackTrace();
//				Toast.makeText(_context, "ERROR: SDK is busy", Toast.LENGTH_LONG).show();
//			} catch (NoNetworkException e) {
//				Log.e(TAG, "Network is not available\n" + e.getStackTrace());
//				Toast.makeText(_context, "ERROR: Network is not available", Toast.LENGTH_LONG).show();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

            try {
                String ttsText = someData.getString("SharedWord","Can't");

                synthesis.speak(ttsText);

            } catch (BusyException e) {
                Log.e(TAG, "SDK is busy");
                e.printStackTrace();
                Toast.makeText(_context, "ERROR: SDK is busy", Toast.LENGTH_LONG).show();
            } catch (NoNetworkException e) {
                Log.e(TAG, "Network is not available\n" + e.getStackTrace());
                Toast.makeText(_context, "ERROR: Network is not available", Toast.LENGTH_LONG).show();
            }
        }
    }



    public class OnStopListener implements OnClickListener {

        public void onClick(View v) {
            if (synthesis != null) {
                synthesis.stop();
            }

//			VisemeHolder vh;
//			final String TAG = "VISEME INFO";
//
//			try {
//				vh = synthesis.getVisemeInfo("Hello World", "usenglishfemale", "0", "mp3");
//				int frames = vh.getFrames();
//				Log.d("DEBUG", "FRAMES:" + frames);
//				Log.d("DEBUG", "TOTAL LENGTH (in ms):" + vh.getTotalLength());
//				for (int i = 0; i<frames; i++){
//
//					Log.d(TAG, "Start (in ms):" + vh.getStart(i));
//					Log.d(TAG, "End (in ms):" + vh.getEnd(i));
//					Log.d(TAG, "Length (in ms):" + vh.getLength(i));
//					Log.d(TAG, "Frame:" + (i));
//					Log.d(TAG, "Mouth:" + vh.getMouth(i));
//					Log.d(TAG, " ");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}


//			MarkerHolder mh;
//			final String TAG = "MARKER INFO";
//
//			try {
//				mh = synthesis.getMarkerInfo("Hello World", "usenglishfemale", "0", "mp3");
//				int words = mh.getWords();
//				Log.d("DEBUG", "WORDS:" + words);
//				Log.d("DEBUG", "TOTAL LENGTH (in ms):" + mh.getTotalLength());
//				for (int i = 0; i<words; i++){
//
//					Log.d("DEBUG", "Start (in ms):" + mh.getStart(i));
//					Log.d("DEBUG", "End (in ms):" + mh.getEnd(i));
//					Log.d("DEBUG", "Length (in ms):" + mh.getLength(i));
//					Log.d("DEBUG", "Word:" + (i));
//					Log.d("DEBUG", "Text:" + mh.getText(i));
//					Log.d("DEBUG", " ");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
        }
    }



    @Override
    protected void onPause() {
        synthesis.stop();	//Optional to stop the playback when the activity is paused
        super.onPause();
    }

}