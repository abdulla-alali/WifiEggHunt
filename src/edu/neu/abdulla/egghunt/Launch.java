/**
 ** This file is part of WifiEggHunt.
 **
 ** WifiEggHunt is free software: you can redistribute it and/or modify
 ** it under the terms of the GNU General Public License as published by
 ** the Free Software Foundation; using version 3 of the License.
 **
 ** WifiEggHunt is distributed in the hope that it will be useful,
 ** but WITHOUT ANY WARRANTY; without even the implied warranty of
 ** MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 ** GNU General Public License for more details.
 **
 ** You should have received a copy of the GNU General Public License
 ** along with WifiEggHunt.  If not, see <http://www.gnu.org/licenses/>.
 */

package edu.neu.abdulla.egghunt;

import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class Launch extends Activity implements OnClickListener, OnCheckedChangeListener {

	private ProgressDialog pdialog;
	private Button scanButton;
	private ToggleButton toggleButton;
	public static final String TAG = "WifiEggHunt";
	private WifiResultReceiver receiver = null;
	public WifiManager wifi;
	
	public Launch launch;
	
	private List<Entry> listOfItems;
	
	private static final int CONTEXT_MENU_LOG = 0;
	
	public static boolean isAutoScan = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		wifi = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		setContentView(R.layout.main);
		scanButton = (Button) findViewById(R.id.scan_now);
		scanButton.setOnClickListener(this);
		toggleButton = (ToggleButton) findViewById(R.id.switch_button);
		toggleButton.setOnCheckedChangeListener(this);


	}

	@Override
	public void onStart() {

		launch = this;
		receiver = new WifiResultReceiver(this);
		registerReceiver(receiver, new IntentFilter(
				WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

		ListView lv = (ListView)findViewById(R.id.results_listview);
		lv.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				menu.setHeaderTitle("Log entry");
				menu.add(0, Launch.CONTEXT_MENU_LOG,0, "save this router");
			}

		});
		
		super.onStart();
	}

	@Override
	public void onStop() {
		launch = null;
		if (receiver!=null) unregisterReceiver(receiver);
		super.onStop();
	}

	@Override
	public void onConfigurationChanged(Configuration config) {
		super.onConfigurationChanged(config);
	}

	@Override
	public void onClick(View arg0) {
		if (arg0==scanButton) {
			if (!wifi.isWifiEnabled()) {
				new AlertDialog.Builder(this)
				.setIcon(android.R.drawable.ic_dialog_alert)
				.setTitle("Error")
				.setMessage("Please enable WiFi before proceeding")
				.setPositiveButton(android.R.string.ok, null)
				.show();
			} else {
				pdialog = ProgressDialog.show(this, "Please wait", "Scanning WiFi Access Points");
				wifi.startScan(); 
			}
		}
	}

	public void dismissPopup() {
		if (pdialog!=null) pdialog.dismiss();
	}

	public void populateListView(List<Entry> listEntries) {
		
		//see if scanned results match an egg
		for (int i=0; i<listEntries.size(); i++) {
			for (int x=0; x<Consts.listOfEggs.length; x++) {
				if (Consts.listOfEggs[x].isMatch(listEntries.get(i).getBSSD())) {
					if (!Consts.listOfEggs[x].isFound())
					if (listEntries.get(i).getLevel()<Consts.listOfEggs[x].getHigh() && 
							listEntries.get(i).getLevel()>Consts.listOfEggs[x].getLow()) {
						Consts.listOfEggs[x].setFound(true);
						new AlertDialog.Builder(this)
						.setIcon(android.R.drawable.ic_menu_compass)
						.setTitle("CONGRATULATIONS")
						.setMessage("Congratulations !\nYou have found one of the eggs")
						.setPositiveButton(android.R.string.ok, null)
						.show();
						LogWriter.addEntry(this, Calendar.getInstance().getTime().toLocaleString() + 
								"\t" + Consts.listOfEggs[x].getNickname(), new Handler());
					}
				}
			}
			
		}
		
		ListView list = (ListView)findViewById(R.id.results_listview);
		ItemsAdapter adapter = new ItemsAdapter(this, listEntries);
		list.setAdapter(adapter);
		listOfItems = listEntries;
		
		//Looper
		if (isAutoScan) new Handler().postDelayed(scanWifi, 500);
	}
	
	private Runnable scanWifi = new Runnable() {
		public void run() {
			wifi.startScan();
		}
	};
	
	@Override
	public boolean onContextItemSelected(MenuItem aItem) {
		AdapterContextMenuInfo menuInfo = (AdapterContextMenuInfo) aItem.getMenuInfo();

		switch (aItem.getItemId()) {
		case CONTEXT_MENU_LOG:
			Entry entry = listOfItems.get(menuInfo.position);
			Log.d(TAG, "context menu selected: " + entry.getBSSD());
			LogWriter.addEntry(this, Calendar.getInstance().getTime().toLocaleString() + "\t" + entry.getBSSD() + "\t" +
					entry.getLevel() + "\t" + entry.getSSID() + "\t" + entry.getFreq(), new Handler());
			return true;
		}
		return false;
	}
	
	public void displayToast(final String text) {
		Handler mHandler = new Handler();
		mHandler.post(new Runnable() {
			public void run() {
				Toast.makeText(Launch.this, text, Toast.LENGTH_LONG).show();
			}
		});
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
			scanButton.setEnabled(false);
			isAutoScan=true;
			wifi.startScan();
		} else {
			isAutoScan = false;
			scanButton.setEnabled(true);
		}
	}
}