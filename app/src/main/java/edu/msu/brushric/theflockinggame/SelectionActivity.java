package edu.msu.brushric.theflockinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;


public class SelectionActivity extends ActionBarActivity {

    /**
     * the game manager for the current game
     */
    private GameManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // get the bundle that was passed
        Bundle b = this.getIntent().getExtras();
        if(b!=null)
            // set the manager from the bundle
            manager = b.getParcelable(WelcomeActivity.PARCELABLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        // set the player name text view to the correct player
        TextView playerName = (TextView) findViewById(R.id.playerNumber);
        String userName;
        if(manager.getPlayerOneBird() == 0) userName = manager.getPlayerOneName();
        else userName = manager.getPlayerTwoName();
        playerName.setText(this.getString(R.string.selecting) + " " + userName);
    }


    /**
     * OnClick function if an ostritch is selected
     * @param view that is selected
     */
    public void onSelectOstritch(View view) {
        ChangeActivity(BirdPiece.OSTRICH);
    }
    /**
     * OnClick function if an parrot is selected
     * @param view that is selected
     */
    public void onSelectParrot(View view) {
        ChangeActivity(BirdPiece.PARROT);
    }
    /**
     * OnClick function if an seagull is selected
     * @param view that is selected
     */
    public void onSelectSeagull(View view) {
        ChangeActivity(BirdPiece.SEAGULL);
    }
    /**
     * OnClick function if an robin is selected
     * @param view that is selected
     */
    public void onSelectRobin(View view) {
        ChangeActivity(BirdPiece.ROBIN);
    }
    /**
     * OnClick function if an bananaquit is selected
     * @param view that is selected
     */
    public void onSelectBananaquit(View view) {
        ChangeActivity(BirdPiece.BANANAQUIT);
    }

    /**
     * function that switches the activity based on what user selection it is
     */
    void ChangeActivity(int bird){

        Bundle b = new Bundle();

        // show a tost to let the user know what bird they selected
        //fix here
//        Toast.makeText(this, bird + " Selected", Toast.LENGTH_SHORT).show();

        if(manager.getPlayerOneBird() == 0){
            Intent intent = new Intent(this, SelectionActivity.class);
            // add the bird player one selected to the manager
            manager.setPlayerOneBird(bird);
            b.putParcelable(WelcomeActivity.PARCELABLE, manager);
            intent.putExtras(b);
            startActivity(intent);
        }
        else{
            Intent intent = new Intent(this, GameActivity.class);
            // add the bird player two selected to the manager
            manager.setPlayerTwoBird(bird);
            b.putParcelable(WelcomeActivity.PARCELABLE, manager);
            intent.putExtras(b);
            startActivity(intent);
        }
    }

}
