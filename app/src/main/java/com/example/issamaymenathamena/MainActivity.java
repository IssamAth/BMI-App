package com.example.issamaymenathamena;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText PoidsEdit, TailleEdit;
    private TextView Result;
    private String ResultOldText;
    private CheckBox Mega_Fonction;
    private RadioGroup M_or_CM;
    double height;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PoidsEdit = findViewById(R.id.poidsEdit);
        TailleEdit = findViewById(R.id.tailleEdit);
        Result = findViewById(R.id.result);
        ResultOldText = Result.getText().toString(); // to keep the old text nd use it when you click RAZ
        Mega_Fonction = findViewById(R.id.megaId);
        M_or_CM = findViewById(R.id.radioGrp);

        M_or_CM.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) { // the goal of this is to make it to centimetre
                height = Double.parseDouble(TailleEdit.getText().toString()); // remove this later
                switch (checkedId){
                    case R.id.centimetre:
                        if (height % 1 != 0 && height != 2){ // if it's Dec . i did this because when i switch from metre to centimetre and height is aready in metre i dont want it to to get divided again, also if its 2m it leaves it
                            break;
                        } else {
                        height = height / 100;
                        Toast.makeText(MainActivity.this, "It's Checked !" + height, Toast.LENGTH_SHORT).show();
                        break;}
                    case R.id.metre:
                        if(height % 1 == 0){ // if not decimal
                            height = height / 100;
                            break;
                        } else {
                            break;
                        }
                    default:
                        break;
                }
            }
        });


        Mega_Fonction.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Result.setText("Have a good day");
                } else {
                    Result.setText(ResultOldText);  // set the Result text back to it's default value
                }
            }
        });



        PoidsEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(PoidsEdit.getText().toString() == String.valueOf(s)){   // if the value of Poids champ isn't changed dont do anything
                    return;
                } else {
                    TailleEdit.setText("");
                    Result.setText(ResultOldText);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



    }



    public void  razFunc (View view){
        PoidsEdit.setText("");
        TailleEdit.setText("");
        Result.setText(ResultOldText); // reset Result textview back to it's default
        height = 1;  // we give it value of 1 cuz we dont want it to show us an error message after using the RAZ button
    }

    public void calculerIMConCicked (View view){

        if (height == 0){
            Toast.makeText(MainActivity.this, "Désolée, mais ce n'est pas possible.", Toast.LENGTH_SHORT).show();
        } else {
            height = Double.parseDouble(TailleEdit.getText().toString());  // i put another height here so i can get the new value after i reseted al champs when ichange poids champ i still need to get their valueswithout triggering the radiogroupe
            if(height % 1 == 0 && height != 2){
                height = height / 100;     // i did this because when resiniliase height it goes back to centimetre
            }
            String weight = PoidsEdit.getText().toString();
            //String height = TailleEdit.getText().toString();
            double IMC =  Double.parseDouble(weight) / Math.pow(height, 2);
            String  Imc = String.valueOf(IMC);
            Result.setText(Imc);
            //Math.pow(i, 2);
        }
    }
}

