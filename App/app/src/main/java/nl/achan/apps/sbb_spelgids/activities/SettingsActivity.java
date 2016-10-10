package nl.achan.apps.sbb_spelgids.activities;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import nl.achan.apps.sbb_spelgids.D4NW2;
import nl.achan.apps.sbb_spelgids.R;
import nl.achan.apps.sbb_spelgids.location.LocationAdapter;
import nl.achan.apps.sbb_spelgids.location.LocationsRepository;
import nl.achan.apps.sbb_spelgids.models.Level;

public class SettingsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.coordlayout_settings) CoordinatorLayout layout;
    @BindView(R.id.button_manage_locations) Button button;

    AlertDialog locationsDialog;
    AlertDialog waitingForLocationDialog;

    private Context context;
    private LocationsRepository locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        context = getBaseContext();
        locations = ((D4NW2) getApplication()).getLocations();

        // Prepares dialogs.
        final View waitingForLocationsDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_waitingforlocation, null);
        waitingForLocationDialog = new AlertDialog.Builder(this).setView(waitingForLocationsDialogView)
                .setTitle("Wachten op locatie...")
                .setView(waitingForLocationsDialogView)
                .create();

        final View locationsDialogView = LayoutInflater.from(this).inflate(R.layout.dialog_locations, null);
        locationsDialog = new AlertDialog.Builder(this).setView(locationsDialogView)
                .setTitle("Manage locaties")
                .setNeutralButton("terug", null)
                .create();

        RecyclerView registeredLocations = ((RecyclerView) locationsDialogView.findViewById(R.id.lv_registered_locations));
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        LocationAdapter adapter = new LocationAdapter(context, locations.getAllLevels());
        registeredLocations.setLayoutManager(manager);
        registeredLocations.setAdapter(adapter);

        locationsDialogView.findViewById(R.id.button_register_current_location)
                .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("LocationManager", "Registering location...");
                locationsDialog.dismiss();
                waitingForLocationDialog.show();

                SmartLocation
                        .with(context)
                        .location()
                        .oneFix()
                        .start(new OnLocationUpdatedListener() {
                            @Override
                            public void onLocationUpdated(Location location) {
                                Log.i("LocationManager", "Location Registered: " + location.toString());
                                Level level = new Level(location, "level #");
                                level.name += level.hashCode();
                                locations.register(level);
                                waitingForLocationDialog.dismiss();
                                Snackbar.make(
                                        layout,
                                        "Locatie geregistreerd: " + location.toString(),
                                        Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        });
            }
        });
    }

    @OnClick(R.id.button_manage_locations) void manageLocations(){
        locationsDialog.show();
    }

    @OnClick(R.id.button_vibration_test) void vibrate(){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        Log.i("SettingsActivity", "S-senpai! That t-t-tickl~ Fuwaaaaaa~ <3");
        v.vibrate(500);
    }
}
