package com.chatman.mad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpScreen extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	setContentView(R.layout.help);

        Button b = (Button)findViewById(R.id.back);
        b.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
		        finish();
			}
		});
    }
}
