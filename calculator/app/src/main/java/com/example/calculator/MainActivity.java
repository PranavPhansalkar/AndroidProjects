package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    String mod_operands="";
    String formula="";
    String temp="";
    boolean left_bracket=true;
    TextView operands;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_text();
    }



    public void init_text(){
        operands=(TextView)findViewById(R.id.operands);
        result=(TextView)findViewById(R.id.result);
        }

    public void update_operand(String value) {
        mod_operands+=value;
        operands.setText(mod_operands);
    }

    public void powerOf(){
        ArrayList<Integer> index =  new ArrayList<>();
        for(int i=0;i<mod_operands.length();i++){
            if(mod_operands.charAt(i) == '^')
                index.add(i);
        }

        formula = mod_operands;
        temp = mod_operands;
        for(Integer index_: index)
        {
            changeFormula(index_);
        }
        formula=temp;
    }

    private void changeFormula(Integer index){
        String numberLeft="";
        String numberRight="";

        for(int i = index+1; i < mod_operands.length();i++){
            if(isNumeric(mod_operands.charAt(i)))
                numberRight+=mod_operands.charAt(i);

            else
                break;
        }

        for(int i = index-1; i >= 0 ;i--){
            if(isNumeric(mod_operands.charAt(i)))
                numberLeft+=mod_operands.charAt(i);
            else
                break;
        }

        StringBuilder leftNumberBuilder = new StringBuilder(numberLeft);
        numberLeft = leftNumberBuilder.reverse().toString();


        String original = numberLeft + "^" + numberRight;
        String mod= "Math.pow("+numberLeft+","+numberRight+")";
        temp= temp.replace(original,mod);
    }

    private boolean isNumeric(char c){
        if(c <= '9' && c >='0' || c == '.' )
            return true;
        else
            return false;
    }

    public void equal(View view)  {

        Double result_ = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        powerOf();
        try {
            result_ = (double) engine.eval(formula);
        }catch(ScriptException e){
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();
        }

        if (result_ != null){
            result.setText(String.valueOf(result_.doubleValue()));
        }

    }

    public void clrscr(View view){

        result.setText("");
        operands.setText("");
        mod_operands="";
        left_bracket=true;
    }

    public void bracket(View view){

        if(left_bracket){
            update_operand("(");
            left_bracket = false;
        }
        else{
            update_operand(")");
            left_bracket = true;
        }
    }

    public void power(View view){
        update_operand("^");
    }

    public void divide(View view){
        update_operand("/");
    }

    public void button_seven(View view){
        update_operand("7");
    }

    public void button_eight(View view){
        update_operand("8");
    }

    public void button_nine(View view){
        update_operand("9");
    }

    public void multiply(View view){
        update_operand("*");
    }

    public void button_four(View view){
        update_operand("4");
    }

    public void button_five(View view){
        update_operand("5");
    }

    public void button_six(View view){
        update_operand("6");
    }

    public void subtract(View view){
        update_operand("-");
    }

    public void button_one(View view){
        update_operand("1");
    }

    public void button_two(View view){
        update_operand("2");
    }

    public void button_three(View view){
        update_operand("3");
    }

    public void add(View view){
        update_operand("+");
    }

    public void decimal(View view){
        update_operand(".");
    }

    public void button_zero(View view){
        update_operand("0");
    }


}