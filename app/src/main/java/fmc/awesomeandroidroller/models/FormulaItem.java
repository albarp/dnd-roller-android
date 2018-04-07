package fmc.awesomeandroidroller.models;

/**
 * Created by aarpini on 28/08/2017.
 */

public class FormulaItem {

    private int _value;

    public int getValue() {
        return _value;
    }

    public void setvalue(int value) {
        this._value = value;
    }

    private int _bonus;

    public int getBonus() {
        return _bonus;
    }

    public void setBonus(int bonus) {
        this._bonus = bonus;
    }

    private int _rating;

    public int getRating() {
        return _rating;
    }

    public void setRating(int rating) {
        this._rating = rating;
    }

    public  FormulaItem(){
        this(0,0,0);
    }

    public FormulaItem(int value, int bonus, int rating){
        this._value = value;
        this._bonus = bonus;
        this._rating = rating;
    }
}
