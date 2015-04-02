package edu.msu.brushric.theflockinggame;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class WinActivity extends ActionBarActivity {

    /**
     * the game manager for the current game
     */
    private GameManager manager;
    private int score;

    TextView scoreBox, player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        scoreBox = (TextView) findViewById(R.id.points);
        player = (TextView) findViewById(R.id.winner);

        // get the bundle that was passed
        Bundle b = this.getIntent().getExtras();
        if(b!=null) {
            // set the manager from the bundle
            manager = b.getParcelable(WelcomeActivity.PARCELABLE);
            score =  manager.getScore();
            scoreBox.setText("" + score);
            player.setText(manager.getWinnerName());
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_win, menu);
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

    public void onReset(View view) {
        Intent intent = new Intent(this, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
