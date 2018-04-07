package fmc.awesomeandroidroller.models;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import fmc.awesomeandroidroller.interfaces.IApplicationCallHandler;
import fmc.awesomeandroidroller.interfaces.IApplicationRemoteCallHandler;
import fmc.awesomeandroidroller.sync.StatRollerRestClient;

/**
 * Created by aarpini on 15/08/2017.
 */

public class Application {

    private static Application _instance = null;

    private ObjectMapper _mapper = null;

    private List<ApplicationStat> _stats = null;

    public List<ApplicationStat> getStats(){
        return _stats;
    }

    public List<MenuItemDefinition> _menuItems = null;

    public List<MenuItemDefinition> getMenuItems(){
        return _menuItems;
    }

    private Design _design = null;

    public Design getDesign(){
        return _design;
    }

    private Application(){

        _mapper = new ObjectMapper();

        _mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        _stats = new LinkedList<>();

        _menuItems = new LinkedList<>();

        _menuItems.add(new MenuItemDefinition("Basic Roller", "A roller with some nice preset."));
        _menuItems.add(new MenuItemDefinition("Advanced Roller", "Fine tune your hero with fine tuned stats."));
        _menuItems.add(new MenuItemDefinition("Class Roller", "Choose a job, roll your stats."));
        _menuItems.add(new MenuItemDefinition("Star Roller", "Let the stars decide."));
    }


    public void getDndDesign(final IApplicationCallHandler callHandler) {

        StatRollerRestClient.getDndDesign(new IApplicationRemoteCallHandler() {
            @Override
            public void onCallResult(int errCode, String response) {
                try {

                    _design = _mapper.readValue(response, Design.class);

                    for (String s : _design.getStats()) {
                        _stats.add(new ApplicationStat(s));
                    }

                    callHandler.onResult(true);
                }
                catch (Exception ex) {
                    callHandler.onResult(false);
                }
            }
        });
    }

    public void RollDndDefault(final IApplicationCallHandler callHandler){

        StatRollerRestClient.rollDndDefault(new IApplicationRemoteCallHandler() {
            @Override
            public void onCallResult(int errCode, String response) {
                try {

                    //ApplicationStat[] rollStats = _mapper.readValue(response, ApplicationStat[].class);

                    JSONArray rollStats = new JSONArray(response);

                    for(int i = 0; i < rollStats.length(); i++){
                        JSONObject singleRollStat = rollStats.getJSONObject(i);

                        String serverStatName = singleRollStat.getString("name");

                        int serverStatValue = singleRollStat.getInt("value");

                        ApplicationStat applicationStat = getStatByName(_stats, serverStatName);

                        FormulaItem statFormulaItem =  _design.getFormulaItemByValue(serverStatValue);

                        applicationStat.Value = serverStatValue;

                        applicationStat.Bonus = statFormulaItem.getBonus();

                        applicationStat.Rating = statFormulaItem.getRating();

                    }

                    callHandler.onResult(true);
                }
                catch (Exception ex) {
                    callHandler.onResult(false);
                }
            }
        });
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

    public static Application Instance(){
        if(_instance == null){
            _instance = new Application();
        }
        return _instance;
    }
}
