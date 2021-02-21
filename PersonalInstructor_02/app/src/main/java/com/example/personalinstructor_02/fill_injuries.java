package com.example.personalinstructor_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class fill_injuries extends AppCompatActivity {

    private static final String[] shoulder_injuries = new String[]{

            "Pain from overhead pressing","Too much barbell work","Obsessed with training lats","Your traps are weak","You don’t vary exercises"


    };

    private static final String[] chest_injuries = new String[]{

            "Pain from overhead pressing","Too much barbell work","Obsessed with training lats","Your traps are weak","You don’t vary exercises"
    };
    private static final String[] legs_injuries = new String[]{

            "Pain from overhead pressing","Too much barbell work","Obsessed with training lats","Your traps are weak","You don’t vary exercises"
    };
    private static final String[] Arms_injuries = new String[]{

            "Pain from overhead pressing","Too much barbell work","Obsessed with training lats","Your traps are weak","You don’t vary exercises"

    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill_injuries);

        AutoCompleteTextView editText = findViewById(R.id.I1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>( this,android.R.layout.simple_list_item_1, shoulder_injuries );
        editText.setAdapter(adapter);

        AutoCompleteTextView editText2 = findViewById(R.id.I2);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>( this,android.R.layout.simple_list_item_1, chest_injuries);
        editText2.setAdapter(adapter2);

        AutoCompleteTextView editText3 = findViewById(R.id.I3);
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>( this,android.R.layout.simple_list_item_1, legs_injuries );
        editText3.setAdapter(adapter3);

        AutoCompleteTextView editText4 = findViewById(R.id.I3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>( this,android.R.layout.simple_list_item_1, Arms_injuries );
        editText4.setAdapter(adapter4);

    }
}
