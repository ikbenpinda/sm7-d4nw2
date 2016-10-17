package nl.achan.apps.sbb_spelgids.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import nl.achan.apps.sbb_spelgids.R;
import nl.achan.apps.sbb_spelgids.scanner.BarcodeCaptureActivity;

public class CurrentGameActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_game);
        ButterKnife.bind(this);
        context = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This should open the scanner.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent i = new Intent(context, BarcodeCaptureActivity.class);
                startActivity(i);
            }
        });
    }

}
