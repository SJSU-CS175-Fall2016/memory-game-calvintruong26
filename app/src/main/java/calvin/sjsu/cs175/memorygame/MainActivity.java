package calvin.sjsu.cs175.memorygame;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.playBtn)
    Button playBtn;

    @BindView(R.id.rulesBtn)
    Button rulesBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick (R.id.playBtn)
    public void startRules() {
        startActivity(new Intent(MainActivity.this, GameActivity.class));
    }

    @OnClick (R.id.rulesBtn)
    public void startGame() {
        startActivity(new Intent(MainActivity.this, RulesActivity.class));

    }
}
