package calvin.sjsu.cs175.memorygame;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;



import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity {

    ArrayList<Integer> imageIds = new ArrayList<Integer>(Arrays.asList(
            R.drawable.bulbasaur,
            R.drawable.charmander,
            R.drawable.cyndaquil,
            R.drawable.mudkip,
            R.drawable.mew,
            R.drawable.pikachu,
            R.drawable.lugia,
            R.drawable.squirtle,
            R.drawable.togepi,
            R.drawable.totodile,
            R.drawable.bulbasaur,
            R.drawable.charmander,
            R.drawable.cyndaquil,
            R.drawable.mudkip,
            R.drawable.mew,
            R.drawable.pikachu,
            R.drawable.lugia,
            R.drawable.squirtle,
            R.drawable.togepi,
            R.drawable.totodile
    ));

    ArrayList<Integer> currentList = new ArrayList<Integer>();

    ArrayList<ImageButton> btnList = new ArrayList<ImageButton>();

    ArrayList<Integer> visibilities = new ArrayList<Integer>();

    int points = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        ButterKnife.bind(this);

        btnList.add((ImageButton)findViewById(R.id.btn1));
        btnList.add((ImageButton)findViewById(R.id.btn2));
        btnList.add((ImageButton)findViewById(R.id.btn3));
        btnList.add((ImageButton)findViewById(R.id.btn4));
        btnList.add((ImageButton)findViewById(R.id.btn5));
        btnList.add((ImageButton)findViewById(R.id.btn6));
        btnList.add((ImageButton)findViewById(R.id.btn7));
        btnList.add((ImageButton)findViewById(R.id.btn8));
        btnList.add((ImageButton)findViewById(R.id.btn9));
        btnList.add((ImageButton)findViewById(R.id.btn10));
        btnList.add((ImageButton)findViewById(R.id.btn11));
        btnList.add((ImageButton)findViewById(R.id.btn12));
        btnList.add((ImageButton)findViewById(R.id.btn13));
        btnList.add((ImageButton)findViewById(R.id.btn14));
        btnList.add((ImageButton)findViewById(R.id.btn15));
        btnList.add((ImageButton)findViewById(R.id.btn16));
        btnList.add((ImageButton)findViewById(R.id.btn17));
        btnList.add((ImageButton)findViewById(R.id.btn18));
        btnList.add((ImageButton)findViewById(R.id.btn19));
        btnList.add((ImageButton)findViewById(R.id.btn20));

        Button restartBtn = (Button) findViewById(R.id.restartBtn);
        restartBtn.setVisibility(View.INVISIBLE);


        SharedPreferences prefs = getPreferences(MODE_PRIVATE);


            for (int i = 0; i < btnList.size(); i++) {
                currentList.add(prefs.getInt("btnImg" + i, imageIds.get(i)));
            }

            for (int i = 0; i < btnList.size(); i++) {
                int visTemp = prefs.getInt(""+i, 1);
                if (visTemp == 0) {
                    visibilities.add(0);
                }
                else {
                    visibilities.add(1);
                }
            }

            for (int i = 0; i < visibilities.size(); i++) {
                if (visibilities.get(i) == 0) {
                    btnList.get(i).setVisibility(View.INVISIBLE);
                }
                else {
                    btnList.get(i).setVisibility(View.VISIBLE);
                }
            }

            points = prefs.getInt("pts", 0);
            TextView updatePts = (TextView) findViewById(R.id.ptsCounter);
            updatePts.setText(""+points);

        setup();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.new_game:
                restart();
                return true;
            case R.id.rules:
                openRules();
                return true;
            case R.id.shuffle:
                shuffle();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void shuffle() {
        ArrayList<Integer> shuffledList = new ArrayList<Integer>();
        for (int i = 0; i < btnList.size(); i++) {
            if (btnList.get(i).getVisibility() == View.VISIBLE) {
                shuffledList.add(currentList.get(i));
            }
        }
        Collections.shuffle(shuffledList);
        for (int i = 0; i < btnList.size(); i++) {
            if (i < shuffledList.size()) {
                currentList.set(i, shuffledList.get(i));
                btnList.get(i).setVisibility(View.VISIBLE);
                btnList.get(i).setImageResource(R.drawable.question);
            }
            else {
                btnList.get(i).setVisibility(View.INVISIBLE);
            }

        }

    }

    public void onPause() {
        super.onPause();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        if (points < 10) {
            for (int i = 0; i < btnList.size(); i++) {
                if (btnList.get(i).getVisibility() == View.INVISIBLE) {
                    prefsEditor.putInt(""+i, 0);
                }
                else {
                    prefsEditor.putInt(""+i, 1);
                }
            }

            prefsEditor.putInt("pts", points);

        }
        else {
            prefsEditor.putInt("pts", 0);
            for (int i = 0; i < btnList.size(); i++) {
                prefsEditor.putInt(""+i, 1);
            }
        }
        for (int i = 0; i < btnList.size(); i++) {
            prefsEditor.putInt("btnImg" + i, currentList.get(i));
        }
        prefsEditor.apply();
    }

    public void clearSharedPrefs() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.clear();
        prefsEditor.apply();
    }

    public void restart() {

        Button restartBtn = (Button) findViewById(R.id.restartBtn);
        Collections.shuffle(imageIds);
        //Collections.shuffle(currentList);
        for (int i = 0; i < btnList.size(); i++){
            currentList.set(i, imageIds.get(i));
        }
        points = 0;
        TextView pts = (TextView)findViewById(R.id.ptsCounter);
        pts.setText("" + points);
        for (int i = 0; i < btnList.size(); i++) {
            btnList.get(i).setVisibility(View.VISIBLE);
            btnList.get(i).setImageResource(R.drawable.question);
        }
        restartBtn.setVisibility(View.INVISIBLE);
    }

    private void setup() {

        final ArrayList<ImageButton> selectedItems = new ArrayList<ImageButton>();

        for (int i = 0; i < btnList.size(); i++) {
            final int ii = i;
            btnList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Animation expandIn = AnimationUtils.loadAnimation(GameActivity.this, R.anim.expand_in);
                    btnList.get(ii).startAnimation(expandIn);
                    ImageButton clickedBtn = (ImageButton) v;
                    clickedBtn.setImageResource(currentList.get(ii));
                    if (!selectedItems.contains(clickedBtn)) {
                            selectedItems.add(clickedBtn);
                            if (selectedItems.size() == 2) {
                                if (selectedItems.get(0).getDrawable().getConstantState().equals(selectedItems.get(1).getDrawable().getConstantState())) {
                                    Animation anim = new AlphaAnimation(1.0f, 0.0f);
                                    anim.setDuration(1000);
                                    selectedItems.get(0).startAnimation(anim);
                                    selectedItems.get(1).startAnimation(anim);
                                    selectedItems.get(0).setVisibility(View.INVISIBLE);
                                    selectedItems.get(1).setVisibility(View.INVISIBLE);
                                    selectedItems.clear();
                                    points++;
                                    TextView pts = (TextView)findViewById(R.id.ptsCounter);
                                    pts.setText("" + points);
                                    if (points > 9) {
                                        final Button restartBtn = (Button) findViewById(R.id.restartBtn);
                                        restartBtn.setVisibility(View.VISIBLE);
                                        restartBtn.setOnClickListener(new View.OnClickListener(){

                                            @Override
                                            public void onClick(View v) {
                                                //shuffle
                                                restart();
                                            }
                                        });
                                    }
                                } else {
                                    selectedItems.get(0).setImageResource(R.drawable.question);
                                    selectedItems.get(1).setImageResource(R.drawable.question);
                                    selectedItems.clear();
                                }
                            }
                        }
                }
            });
        }
    }

    public void openRules() {
        // Create new fragment and transaction
        RulesFragment frag = new RulesFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack
        transaction.replace(R.id.rules_fragment, frag);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
        View game = findViewById(R.id.game);
        game.setVisibility(View.INVISIBLE);
    }
}

