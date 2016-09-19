package calvin.sjsu.cs175.memorygame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

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


    ArrayList<ImageButton> btnList = new ArrayList<ImageButton>();

    ArrayList<Integer> visibilities = new ArrayList<Integer>();

    int points = 0;

    int executionCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Collections.shuffle(imageIds);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

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


        SharedPreferences prefs = getPreferences(MODE_PRIVATE);

        executionCount = prefs.getInt("execCount", 0);
        if (executionCount == 0) {
            clearSharedPrefs();
            Collections.shuffle(imageIds);
        }
        executionCount++;
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

    public void onPause() {
        super.onPause();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        for (int i = 0; i < btnList.size(); i++) {
            if (btnList.get(i).getVisibility() == View.INVISIBLE) {
                prefsEditor.putInt(""+i, 0);
            }
            else {
                prefsEditor.putInt(""+i, 1);
            }
        }
        prefsEditor.putInt("execCount", executionCount);
        prefsEditor.putInt("pts", points);
        prefsEditor.apply();

    }

    public void clearSharedPrefs() {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.clear();
        prefsEditor.apply();
    }


    private void setup() {


        final ArrayList<ImageButton> selectedItems = new ArrayList<ImageButton>();

        for (int i = 0; i < btnList.size(); i++) {
            final int ii = i;
            btnList.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageButton clickedBtn = (ImageButton) v;
                    clickedBtn.setImageResource(imageIds.get(ii));
                    if (!selectedItems.contains(clickedBtn)) {
                            selectedItems.add(clickedBtn);
                            if (selectedItems.size() == 2) {
                                if (selectedItems.get(0).getDrawable().getConstantState().equals(selectedItems.get(1).getDrawable().getConstantState())) {
                                    selectedItems.get(0).setVisibility(View.INVISIBLE);
                                    selectedItems.get(1).setVisibility(View.INVISIBLE);
                                    selectedItems.clear();
                                    points++;
                                    TextView pts = (TextView)findViewById(R.id.ptsCounter);
                                    pts.setText("" + points);
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
}

