package de.mario.torch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

public class TorchActivity extends Activity {

	private static final String TAG = TorchActivity.class.getSimpleName();

	private DroidLED led;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_torch);
		led = new DroidLED();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.torch, menu);
		return true;
	}

	public void onToggleClicked(View view) {
		boolean on = ((ToggleButton) view).isChecked();

		toggleFlashLight(on);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (led.isEnabled()) {
			toggleFlashLight(false);
		}
	}

	private void toggleFlashLight(boolean on) {

		try {

			led.enable(on);
		} catch (Exception e) {
			Log.e(TAG, e.getLocalizedMessage(), e);
			Toast.makeText(getBaseContext(), "Exception toggling flash light",
					Toast.LENGTH_SHORT).show();
		}
	}

}
