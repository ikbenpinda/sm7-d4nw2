package nl.achan.apps.sbb_spelgids.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import nl.achan.apps.sbb_spelgids.R;
import nl.achan.apps.sbb_spelgids.leaderboard.LeaderboardAdapter;
import nl.achan.apps.sbb_spelgids.models.Team;

public class MainMenuActivity extends AppCompatActivity {

    @BindView(R.id.coordlayout_main)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.btn_start_game)
    Button startButton;

    @BindView(R.id.recview_leaderboard)
    RecyclerView leaderboard;

    public static final String ADMIN_PASSWORD = "/login"; // for hiding the settings menu.

    Context context;

    int passwordCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ButterKnife.bind(this);
        context = getBaseContext();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startNewGame();
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame();
            }
        });

        Team team1 = new Team();
        team1.name = "Smart Mobile";
        team1.score = 12;
        Team team2 = new Team();
        team2.name = "#DEPEEL";
        team2.score = 11;
        Team team3 = new Team();
        team3.name = "groep8";
        team3.score = 10;

        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        teams.add(team3);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        leaderboard.setLayoutManager(manager);
        leaderboard.setAdapter(new LeaderboardAdapter(teams, this));
    }

    private void startNewGame() {
        Log.i("MainMenuActivity", "Starting new game...");
        AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
        dialogbuilder
                .setTitle("Nieuw spel")
                .setMessage("Aantal teams: ")
                .setView(R.layout.dialog_new_game)
                .setPositiveButton("start", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent newGame = new Intent(context, CurrentGameActivity.class);
                        // todo - team names
                        startActivity(newGame);
                    }
                })
                .setNegativeButton("annuleren", null)
                .create()
                .show();
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id){
            case R.id.action_settings: // change at will.
//                if (passwordCount < 5) {
//                    passwordCount++;
                    Snackbar.make(coordinatorLayout, "Niet beschikbaar.", Snackbar.LENGTH_SHORT).show();
//                    break;
//                }
//                final EditText passwordbox = new EditText(this);
//                passwordbox.setPadding(16, 16, 16, 16);
//                passwordbox.setHint("wachtwoord");
//                passwordbox.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
//                AlertDialog passwordDialog = new AlertDialog.Builder(this)
//                        .setTitle("Adminopties")
//                        .setView(passwordbox)
//                        .setNegativeButton("Annuleren", null)
//                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                if (passwordbox.getText().toString().equals("admin")) {
//                                    passwordCount = 0;
//                                    startActivity(new Intent(context, SettingsActivity.class));
//                                }
//                                else
//                                    Snackbar.make(coordinatorLayout, "Nee. Nog steeds niet.", Snackbar.LENGTH_SHORT);
//                            }
//                        })
//                        .create();
//                passwordDialog.show();
                break;
            case R.id.action_feedback:
                final EditText feedbackbox = new EditText(this);
                LinearLayout layout = new LinearLayout(this);
                layout.setOrientation(LinearLayout.VERTICAL);
                int def_padding = getResources().getDimensionPixelSize(R.dimen.padding_default_16);
                layout.setPadding(def_padding, 0, def_padding, 0);
                layout.addView(feedbackbox);
                AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(this);
                dialogbuilder
                        .setTitle(R.string.action_feedback)
                        .setMessage("Iets werkt niet? Laat het ons weten!")
                        .setView(layout)
                        .setNegativeButton("annuleren", null)
                        .setPositiveButton("melden", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (feedbackbox.getText().toString().equals(ADMIN_PASSWORD))
                                    startActivity(new Intent(context, SettingsActivity.class));
                                else
                                    Snackbar.make(coordinatorLayout, "Melding gemaakt. Dankjewel!", Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .create()
                        .show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
