package com.serly.uts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Bundle b = getIntent().getExtras();

        mainGrid = (GridLayout)findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        for (int i=0;i<mainGrid.getChildCount();i++){
            final CardView cardView = (CardView)mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(finalI == 0){
                        Intent myintent = new Intent(AdminActivity.this,UserActivity.class);
                        startActivity(myintent);
                    }
                    else if(finalI == 1){
                        Intent myintent = new Intent(AdminActivity.this,BarangActivity.class);
                        startActivity(myintent);
                    }
//                    if(cardView.getCardBackgroundColor().getDefaultColor() == -1){
//                        cardView.setCardBackgroundColor(Color.parseColor("#FF6F00"));
//                        Toast.makeText(AdminActivity.this,"State : true " + finalI,Toast.LENGTH_SHORT).show();
//                    }
//                    else{
//                        cardView.setCardBackgroundColor(Color.parseColor("#FFFFFF"));
//                        Toast.makeText(AdminActivity.this,"State : false " + finalI,Toast.LENGTH_SHORT).show();
//                    }

                }
            });
        }
    }

    public void button_onClick(View view)
    {
        SharedPreferences setting = getSharedPreferences("key", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = setting.edit();
        editor.remove("username");
        editor.commit();
        finish();
        Intent i = new Intent(this.getApplicationContext(), MainActivity.class);
    }
}
