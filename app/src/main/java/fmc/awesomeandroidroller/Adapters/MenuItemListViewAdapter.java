package fmc.awesomeandroidroller.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import fmc.awesomeandroidroller.R;
import fmc.awesomeandroidroller.models.MenuItemDefinition;

/**
 * Created by aarpini on 12/08/2017.
 */

public class MenuItemListViewAdapter extends ArrayAdapter<MenuItemDefinition> {

    private List<MenuItemDefinition> menuItems = null;

    public MenuItemListViewAdapter(Context context, int resource, List<MenuItemDefinition> objects) {
        super(context, resource, objects);

        menuItems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if(v == null){
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.item_menu_main, null);
        }

        MenuItemDefinition currentMenuItemDefinition = menuItems.get(position);

        TextView itemMenuTitle = (TextView) v.findViewById(R.id.itemMenuTitle);
        TextView itemMenuDescription = (TextView) v.findViewById(R.id.itemMenuDescription);

        itemMenuTitle.setText(currentMenuItemDefinition.Title);
        itemMenuDescription.setText(currentMenuItemDefinition.Description);

        return v;
    }
}
