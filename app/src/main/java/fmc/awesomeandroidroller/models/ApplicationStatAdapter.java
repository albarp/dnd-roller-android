package fmc.awesomeandroidroller.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import fmc.awesomeandroidroller.models.ApplicationStat;

/**
 * Created by aarpini on 22/08/2017.
 */

public class ApplicationStatAdapter {

    public static List<ApplicationStat> CreateFromJSONArray(JSONArray response) {

        List<ApplicationStat> stats = new LinkedList<>();

        try {

            for (int i = 0; i < response.length(); i++) {
                JSONObject serverStat = response.getJSONObject(i);

                String serverStatName = serverStat.getString("name");

                stats.add(new ApplicationStat(serverStatName));
            }
        }catch (Exception ex){

        }

        return stats;
    }

    public static void UpdateFromJSONArray(List<ApplicationStat> stats, JSONArray response){

        try {
            for (int i = 0; i < response.length(); i++) {

                JSONObject serverStat = response.getJSONObject(i);

                String serverStatName = serverStat.getString("name");

                int serverStatValue = serverStat.getInt("value");

                int bonus = serverStat.getInt("bonus");

                ApplicationStat applicationStat = getStatByName(stats, serverStatName);

                applicationStat.Value = serverStatValue;

                applicationStat.Bonus = bonus;

            }
        }
        catch (Exception ex){

        }
    }

    private static ApplicationStat getStatByName(List<ApplicationStat> stats, String statName){
        ApplicationStat stat = null;

        for(ApplicationStat currentStat : stats) {
            if(currentStat.Name.equals(statName)){
                stat = currentStat;
                break;
            }
        }
        return stat;
    }
}
