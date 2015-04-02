package edu.msu.brushric.theflockinggame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;


public class GameActivity extends ActionBarActivity {

    /**
     * Game view that draws the pieces
     */
    private GameView gameView;

    /**
     * Game manager to manage the game state
     */
    private GameManager manager;

    /**
     * Game manager to manage the game state
     */
    private Game game;

    /**
     * flag for whos turn it is
     */
    private boolean playerOneTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gameView = (GameView) this.findViewById(R.id.gameView);
        game = gameView.getGame();
        game.setGameActivity(this);

        // get the bundle that was passed
        Bundle b = this.getIntent().getExtras();
        if (b != null)
            // set the manager from the bundle
            manager = b.getParcelable(WelcomeActivity.PARCELABLE);

        // see if it is the first or second players turn
        if (manager.getPlayerOneBird() == 0) {
            game.initializeBirds(manager, new BirdPiece(this, manager.getPlayerTwoBird()));
            playerOneTurn = false;
        } else {
            game.initializeBirds(manager, new BirdPiece(this, manager.getPlayerOneBird()));
        }

        // set the player name text view to the correct player
        TextView playerNameView = (TextView) findViewById(R.id.playerName);
        String playerName;
        if(manager.getPlayerOneBird() == 0) {
            playerName = manager.getPlayerTwoName();
        }
        else {
            playerName = manager.getPlayerOneName();
        }
        playerNameView.setText(this.getString(R.string.placing) + " " + playerName);
    }

    public void onPlace(View view) {
        Bundle b = new Bundle();

        // Check to see if the bird being placed is out of bounds
        if (game.outOfBounds() || game.collision()) {
            if(playerOneTurn) {
                manager.setPlayerWinnerName(manager.getPlayerTwoName());
            }
            else {
                manager.setPlayerWinnerName(manager.getPlayerOneName());
            }

            Intent intent = new Intent(this, WinActivity.class);
            b.putParcelable(WelcomeActivity.PARCELABLE, manager);
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtras(b);
            startActivity(intent);
            return;
        }

        manager.addPiece(game.getDragging());

        // Increment score because bird was placed
        manager.setScore(manager.getScore() + 1);

        if (playerOneTurn) {
            manager.setPlayerOneBird(0);

            // add the parcable to the bundle and return to the game activity
            Intent intent = new Intent(this, GameActivity.class);
            b.putParcelable(WelcomeActivity.PARCELABLE, manager);
            intent.putExtras(b);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, SelectionActivity.class);
            //Intent intent = new Intent(this, WinActivity.class);
            manager.setPlayerTwoBird(0);

            // add the parcable to the bundle and go to the selection activity

            b.putParcelable(WelcomeActivity.PARCELABLE, manager);
            intent.putExtras(b);
            startActivity(intent);
        }
    }
}
