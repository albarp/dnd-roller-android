package fmc.awesomeandroidroller.activities;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import fmc.awesomeandroidroller.Adapters.RollerResultGridViewAdapter;
import fmc.awesomeandroidroller.R;
import fmc.awesomeandroidroller.interfaces.IApplicationCallHandler;
import fmc.awesomeandroidroller.interfaces.IApplicationRemoteCallHandler;
import fmc.awesomeandroidroller.models.Application;

public class RollerActivity extends Activity {

    private GridView _grRollerResult;

    private RollerResultGridViewAdapter _rollerResultGridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_roller);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        // Setup Roller Result Grid
        _grRollerResult = (GridView) findViewById(R.id.grRollerResult);

        _rollerResultGridAdapter = new RollerResultGridViewAdapter(this, R.layout.item_grid_roller, Application.Instance().getStats());

        _grRollerResult.setAdapter(_rollerResultGridAdapter);

        // Setup buttons
        Button btRollDndDefault = (Button) findViewById(R.id.btRollDnDefault);

        btRollDndDefault.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {

                Application.Instance().RollDndDefault(new IApplicationCallHandler() {
                    @Override
                    public void onResult(boolean success) {
                        if(success) {
                            _rollerResultGridAdapter.notifyDataSetChanged();
                        }
                        else{
                            Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
    }

}
