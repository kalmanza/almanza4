package com.almanza.almanza4;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	static final String WEIGHT = "weight";
	static final String HEIGHT = "height";
	static final String BMI = "bmi";
	Button btn_calculate;
	EditText txt_bmi;
	EditText txt_height;
	EditText txt_weight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    	btn_calculate = (Button) findViewById(R.id.btn_calculate);
    	txt_bmi = (EditText) findViewById(R.id.txt_bmi);
    	txt_height = (EditText) findViewById(R.id.txt_height);
    	txt_weight = (EditText) findViewById(R.id.txt_weight);
        OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				calculateBMI();
			}
		};
		btn_calculate.setOnClickListener(listener);
		if(savedInstanceState != null){
    		if(savedInstanceState.containsKey(BMI))
    			txt_bmi.setText(savedInstanceState.getCharSequence(BMI));
    		if(savedInstanceState.containsKey(HEIGHT))
    			txt_height.setText(savedInstanceState.getCharSequence(HEIGHT));
			if (savedInstanceState.containsKey(WEIGHT))
				txt_weight.setText(savedInstanceState.getCharSequence(WEIGHT));
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
    	savedInstanceState.putCharSequence(HEIGHT, txt_height.getText());
    	savedInstanceState.putCharSequence(WEIGHT, txt_weight.getText());
    	savedInstanceState.putCharSequence(BMI, txt_bmi.getText());
    	super.onSaveInstanceState(savedInstanceState);
    }
    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
    	super.onRestoreInstanceState(savedInstanceState);
    	if(savedInstanceState != null){
    		if(savedInstanceState.containsKey(BMI))
    			txt_bmi.setText(savedInstanceState.getCharSequence(BMI));
    		if(savedInstanceState.containsKey(HEIGHT))
    			txt_height.setText(savedInstanceState.getCharSequence(HEIGHT));
			if (savedInstanceState.containsKey(WEIGHT))
				txt_weight.setText(savedInstanceState.getCharSequence(WEIGHT));
    	}
    }
    
    private void calculateBMI(){
    	double height, weight, bmi;
    	bmi = 0;
    	try{
    		height = Integer.parseInt(txt_height.getText().toString());
    		weight = Integer.parseInt(txt_weight.getText().toString());
    		bmi = weight*703/(height*height);
    		if(height == 0 || weight == 0){
    			Toast.makeText(this, getApplicationContext().getString(R.string.error_zeroed_entries), Toast.LENGTH_LONG).show();
    		}
    		else if(bmi > 0){
        		txt_bmi.setText(String.format("%.1f", bmi));
        	}
    	}
    	catch(Exception e){
    		if(e instanceof NumberFormatException){
    			Toast.makeText(this, getApplicationContext().getString(R.string.error_empty_entries), Toast.LENGTH_LONG).show();
    		}
    		else{
    			Toast.makeText(this, getApplicationContext().getString(R.string.error_other), Toast.LENGTH_LONG).show();
    		}
    	}
    }
}
