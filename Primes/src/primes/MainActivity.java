package primes;

import java.util.Random;

import hdriel.primes.*;


import Primary.DismatlingPrimaryNumbers;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


@SuppressLint("NewApi") public class MainActivity extends ActionBarActivity {

	Button btn;
	TextView tv_show_pirok, tv_amountDiv, tv_Divs,tv_DivsGeneral, tv_meshohlal, tv_sum_div , tv_sum_div_wo_number;
	EditText et_input_number;
	int number;
	DismatlingPrimaryNumbers d;
	LinearLayout layout;
	String[] colors = {"#FFCDD2", "#F8BBD0", "#E1BEE7", "#C5CAE9", "#BBDEFB", "#B3E5FC", "#B2EBF2", "#B2DFDB", "#C8E6C9", "#DCEDC8", "#F0F4C3", "#FFF9C4", "#FFECB3", "#FFE0B2", "#FFCCBC", "#BCAAA4", "#CFD8DC"};
	int sizeColors = 17;
	Random rand = new Random();
	InputMethodManager mgr;
	boolean doubleBackToExitPressedOnce;
    Handler mHandler = new Handler();
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // Set the references to the view in the layout
        btn = (Button)findViewById(R.id.btn_check);
        tv_DivsGeneral = (TextView)findViewById(R.id.tv_divides_general);
        tv_show_pirok = (TextView)findViewById(R.id.tv_show_pirok);
        tv_amountDiv = (TextView)findViewById(R.id.tv_amount_divides);
        tv_Divs = (TextView)findViewById(R.id.tv_divides);
        tv_meshohlal = (TextView)findViewById(R.id.tv_perfect_number);
        tv_sum_div = (TextView)findViewById(R.id.tv_sum_div);
        tv_sum_div_wo_number = (TextView)findViewById(R.id.tv_sum_div_wo_number);        
        et_input_number = (EditText)findViewById(R.id.edit_text_input_number);        
        layout = (LinearLayout)findViewById(R.id.main_layout);        
        mgr = (InputMethodManager) getSystemService(this.INPUT_METHOD_SERVICE);
     
        
      
        // Handle the event on the EditText
        et_input_number.setOnEditorActionListener(new EditText.OnEditorActionListener() {

            public boolean onEditorAction(EditText v, int actionId, KeyEvent event) {
            	 
                if (actionId == EditorInfo.IME_ACTION_DONE) { // when the key Enter press on the keyboard - check the number in the EditText
                	mgr.hideSoftInputFromWindow(v.getWindowToken(), 0);               	
                    return true;
                }
                return false;
            }

			@Override
			public boolean onEditorAction(TextView arg0, int arg1, KeyEvent arg2) {
				btn.performClick();
				return false;
			}
        });
        
        // Change the magnifier icon when the text change
        et_input_number.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

              // you can call or do what you want with your EditText here
            	if (et_input_number.getText().toString().trim().length() > 0){       		
            		btn.setBackground(getResources().getDrawable(R.drawable.magnifier_green));
            		btn.setWidth(50);
            	}
            	else{
            		btn.setBackground(getResources().getDrawable(R.drawable.magnifier_red));
            		btn.setWidth(50);
            	}
            	
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
         });
        
        // update text and display when the button click
        btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// reset all the text and background 
				tv_DivsGeneral.setText("");
				tv_amountDiv.setText("");
				tv_Divs.setText("");
				tv_meshohlal.setText("");
				tv_show_pirok.setText("");
				tv_sum_div.setText("");
				tv_sum_div_wo_number.setText("");			
				d = null;
				layout.setBackgroundColor(Color.argb(255, 255, 255, 255));
				
				
				if(et_input_number.getText().toString().trim().length() > 0){ // if there is some text in the editText 
					
					number = Integer.parseInt(et_input_number.getText().toString());
					
					if(number > 1){
						d = new DismatlingPrimaryNumbers(number);
						tv_DivsGeneral.setText(Html.fromHtml(d.getGeneralSolution(d)));
						tv_amountDiv.setText(Html.fromHtml(d.amountDividesStr()));
						tv_Divs.setText(d.Divides2());
						tv_meshohlal.setText(d.PerfectNumber());
						tv_show_pirok.setText(Html.fromHtml(d.print(d)));
						tv_sum_div.setText("" + d.sumDivides());
						tv_sum_div_wo_number.setText("" + d.sumDivides_wo());
						et_input_number.setText("");	
						btn.setBackground(getResources().getDrawable(R.drawable.magnifier_red));
	            		btn.setWidth(50);
						mgr.hideSoftInputFromWindow(et_input_number.getWindowToken(), 0);
						
						int i = rand.nextInt(sizeColors);
						layout.setBackgroundColor(Color.parseColor(colors[i]));
					}
				}
				else { // the user doesn't enter any number
					Toast.makeText(getApplicationContext(), "הכנס מספר טבעי ושלם כלשהו קודם ! !", Toast.LENGTH_SHORT).show();
				}
			}
		});
    } // end OnCreate method
// ********************************************************************************************************************
   
   
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;                       
        }
    };

    @Override 
    protected void onDestroy() 
    { 
        super.onDestroy();

        if (mHandler != null) { mHandler.removeCallbacks(mRunnable); }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "לחץ על הכפתור BACK שוב כדי לצאת.", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(mRunnable, 2000);
    }
        
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	
        	TextView textview = new TextView(getApplicationContext());
        	textview.setText("יוצר האפליקציה: הדריאל בנג\'ו\nנושא : פירוק למספרים ראשונייים\nתחום: פיתוח חשיבה מתמטית");
        	textview.setTextSize(30);
        	int i = rand.nextInt(sizeColors);
        	textview.setBackground(getResources().getDrawable(R.drawable.border_toast));
        	textview.setBackgroundColor(Color.parseColor(colors[i]));
        	textview.setTextColor(Color.BLACK);
        	textview.setGravity(Gravity.RIGHT);
        	textview.setPadding(10,10,10,10);
        	
        	Toast toast = new Toast(getApplicationContext());
        	toast.setView(textview);
        	toast.setDuration(Toast.LENGTH_LONG);
        	toast.setGravity(Gravity.BOTTOM, 0, 30);
        	toast.show();

        	return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
