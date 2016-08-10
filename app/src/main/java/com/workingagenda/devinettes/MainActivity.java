package com.workingagenda.devinettes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<Riddle> riddles;
    private final static String Preferences = "MyPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        riddles = GetRiddles();

        mListView = (ListView) findViewById(android.R.id.list);
        RiddleAdapter adapter = new RiddleAdapter(this, R.layout.row_riddle, riddles);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent = new Intent(getBaseContext(), RiddlePanel.class);
                intent.putExtra("riddle", riddles.get(pos));
                startActivityForResult(intent, 0);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public ArrayList<Riddle> GetRiddles() {
        ArrayList<Riddle> riddles = new ArrayList<>(20);
        Riddle r = new Riddle("Name this pest", "See if you can name this pest:\n" +
                "A trickster of the ancient lore,\n" +
                "reminds me of a writing desk,\n"+
                "once it told me: never more",
                "mobia &amp; ludos 2015",
                "0efec51fd7cf517793321ec68fd852811537b69c");

        riddles.add(r);

        return riddles;
    }
}
