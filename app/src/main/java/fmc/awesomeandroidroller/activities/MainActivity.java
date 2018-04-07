package fmc.awesomeandroidroller.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import fmc.awesomeandroidroller.Adapters.MenuItemListViewAdapter;
import fmc.awesomeandroidroller.R;
import fmc.awesomeandroidroller.interfaces.IApplicationCallHandler;
import fmc.awesomeandroidroller.interfaces.IApplicationRemoteCallHandler;
import fmc.awesomeandroidroller.models.Application;
import fmc.awesomeandroidroller.models.MenuItemDefinition;

public class MainActivity extends Activity {

    private ProgressDialog _progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        _progressDialog = new ProgressDialog(this);

        ListView lvMenuList = (ListView) findViewById(R.id.lvMenuList);

        MenuItemListViewAdapter lvMenuListAdapter = new MenuItemListViewAdapter(this, R.layout.item_menu_main, Application.Instance().getMenuItems());

        lvMenuList.setAdapter(lvMenuListAdapter);

        lvMenuList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, RollerActivity.class);

                 MenuItemDefinition menuItem = (MenuItemDefinition) adapterView.getItemAtPosition(i);

                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {

        super.onStart();

        _progressDialog.show();

        Application.Instance().getDndDesign(new IApplicationCallHandler() {
            @Override
            public void onResult(boolean success) {
                _progressDialog.dismiss();
            }
        });
    }
}
