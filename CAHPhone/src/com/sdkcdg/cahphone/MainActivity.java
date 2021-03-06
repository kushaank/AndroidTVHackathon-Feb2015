package com.sdkcdg.cahphone;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cahphone.R;
import com.sdkcdg.bluetooth.BluetoothService;
import com.sdkcdg.proto.CardsProto.CardsMessage;

public class MainActivity extends Activity {

	public static TextView blackCardTV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.phone_activity);
		blackCardTV = (TextView) findViewById(R.id.tv_phone_topic);
		
		CardsMessage c0 = CardsMessage.newBuilder().setFirstCard("Monkeys in Paris").build();
		PlayerUtils.mHand.add(c0);
		
		FragmentManager fm = getFragmentManager();

		CardsFragment cm = (CardsFragment) fm.findFragmentById(R.id.fragmentContainer);
		if (cm == null) 
		{
			cm = new CardsFragment();
			fm.beginTransaction().add(R.id.fragmentContainer, cm).commit();
		}

		Toast.makeText(this, "Initializing Connection", Toast.LENGTH_SHORT)
				.show();
		Intent serviceIntent = new Intent(getApplicationContext(),
				BluetoothService.class);
		startService(serviceIntent);
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
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
