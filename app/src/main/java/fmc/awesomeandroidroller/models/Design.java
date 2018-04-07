package fmc.awesomeandroidroller.models;

import java.util.List;

/**
 * Created by aarpini on 24/08/2017.
 */

public class Design {

    private String[] _stats;

    public String[] getStats(){
        return _stats;
    }

    public void setStats(String[] stats){
        _stats = stats;
    }

    private FormulaItem[] _formulas;

    public FormulaItem[] getFormulas() {return _formulas;}

    public void setFormulas(FormulaItem[] _formulas) {this._formulas = _formulas;}

    public Design(){
    }

    public Design(String[] stats, FormulaItem[] formulas){
        _stats = stats;
        _formulas = formulas;
    }

    public FormulaItem getFormulaItemByValue(int value){
        for (FormulaItem currentFormula : _formulas){
            if(currentFormula.getValue() == value)
                return currentFormula;
        }
        return null;
    }

}
