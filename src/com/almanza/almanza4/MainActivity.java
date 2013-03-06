/* MainActivity.java
 * Due: 3/6/13
 * Author: Kevin Almanza
 * Course: CSCI 3033
 * Description: This file is the controller for the main activity and only layout file 
 * in the project.  It validates input, creates, formats and displays output for a bmi
 * calculator.
 */

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
	static final String WEIGHT = "weight"; //Key for instance bundle
	static final String HEIGHT = "height"; //key for instance bundle
	static final String BMI = "bmi"; //key for instance bundle
	Button btn_calculate; //views for use in the activity
	EditText txt_bmi;
	EditText txt_height;
	EditText txt_weight;
	//called as the activity is created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //inflate main layout
        setContentView(R.layout.activity_main);
        //find the views needed for manipulation
    	btn_calculate = (Button) findViewById(R.id.btn_calculate);
    	txt_bmi = (EditText) findViewById(R.id.txt_bmi);
    	txt_height = (EditText) findViewById(R.id.txt_height);
    	txt_weight = (EditText) findViewById(R.id.txt_weight);
    	//create a listener for interaction with the calculate button
        OnClickListener listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				calculateBMI();
			}
		};
		//set the buttons onclick listener to the one just created
		btn_calculate.setOnClickListener(listener);
		//check to see if there is a savedInstanceState if so, populate the 
		//views below with the saved data
		if(savedInstanceState != null){
    		if(savedInstanceState.containsKey(BMI))
    			txt_bmi.setText(savedInstanceState.getCharSequence(BMI));
    		if(savedInstanceState.containsKey(HEIGHT))
    			txt_height.setText(savedInstanceState.getCharSequence(HEIGHT));
			if (savedInstanceState.containsKey(WEIGHT))
				txt_weight.setText(savedInstanceState.getCharSequence(WEIGHT));
    	}
    }
    //ignored, will bring up a useless settings menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    //whenever the app calls to save the instance make sure to save all the values
    //in the textEdit boxes. 
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
    	savedInstanceState.putCharSequence(HEIGHT, txt_height.getText());
    	savedInstanceState.putCharSequence(WEIGHT, txt_weight.getText());
    	savedInstanceState.putCharSequence(BMI, txt_bmi.getText());
    	super.onSaveInstanceState(savedInstanceState);
    }
    
    //whenever the app checks to restore any instance data it will 
    //make sure to place in the views below
    //the saved data
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
    
    //function to validate input and calculate the BMI
    //from the input and display it in the last text box
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
