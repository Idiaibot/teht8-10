package com.example.admin.teht8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

public class InfoActivity_1 extends AppCompatActivity {

    public Boolean advancedOn = false;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.super_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.advanced_options:
                // check state
                advancedOn = !advancedOn;
                Log.d("fucker","poop"+ advancedOn);

                showHideAdvanced();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showHideAdvanced(){
        final Switch fbf_toggle = findViewById(R.id.fourbyfour_toggle);
        final RadioGroup carType = findViewById(R.id.car_type);
        if(advancedOn){
            carType.setVisibility(View.VISIBLE);
            fbf_toggle.setVisibility(View.VISIBLE);
        }else{
            carType.setVisibility(View.GONE);
            fbf_toggle.setVisibility(View.GONE);
        }
    }

    public void buttonDisableEnable(EditText name, CheckBox box, EditText age, Button button){
        if(name.getText().length() > 0){
            if(box.isChecked()){
                if(age.getText().length() > 0){
                    int value = Integer.parseInt(age.getText().toString());
                    if(value < 11 && value > 0){
                        button.setEnabled(true);
                    }
                    else{
                        button.setEnabled(false);
                    }
                }else{
                    button.setEnabled(false);
                }
            }else{
                button.setEnabled(true);
            }

        }else{
            button.setEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        final Button button = findViewById(R.id.button2);
        final EditText name = findViewById(R.id.editText4);
        final EditText age = findViewById(R.id.editText5);
        final CheckBox box = findViewById(R.id.checkBox3);

        final Switch fbf_toggle = findViewById(R.id.fourbyfour_toggle);
        final RadioGroup carType = findViewById(R.id.car_type);

        if(box.isChecked()){
            age.setVisibility(View.VISIBLE);
        } else {
            age.setVisibility(View.GONE);
        }


        buttonDisableEnable(name, box, age, button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                if(name.getText().length() > 0 )
                {
                    // not null not empty
                    Intent intent = new Intent(getApplicationContext(), SummaryActivity.class);
                    String message = "name: " + name.getText().toString();

                    if(box.isChecked()){
                        message = message.concat("\nage of car: " + age.getText().toString());
                    }

                    if(advancedOn){
                        if(fbf_toggle.isChecked()){
                            message = message.concat("\nit's four by four!");
                        }
                        message = message.concat("\nbody type of car: " + ((RadioButton) findViewById(carType.getCheckedRadioButtonId())).getText().toString());
                    }
                    intent.putExtra("SECRET_MESSAGE", message);
                    startActivity(intent);
                }else {
                    //null or empty
                }


            }
        });



        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                buttonDisableEnable(name, box, age, button);
            }
        });

        age.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                buttonDisableEnable(name, box, age, button);
            }
        });



        box.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if(box.isChecked()){
                    age.setVisibility(View.VISIBLE);
                } else {
                    age.setVisibility(View.GONE);
                }

                buttonDisableEnable(name, box, age, button);

            }
        });


    }


}
