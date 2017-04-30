package com.be.uba.hamradio.quiz.activity;
import roboguice.activity.RoboPreferenceActivity;
import android.os.Bundle;

import com.be.uba.hamradio.quiz.R;

public class Settings extends RoboPreferenceActivity {

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings); 
	}
 


}
