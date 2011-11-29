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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

public class WifiResultReceiver extends BroadcastReceiver {

	Launch main = null;

	public WifiResultReceiver(Launch main) {
		this.main = main;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
			Log.d(Launch.TAG, "wifi results are back");
			main.dismissPopup();
			List<ScanResult> results = main.wifi.getScanResults();
			List<Entry> entries = new ArrayList<Entry>();
			for (ScanResult result : results) {
				
				
				for (int i=0; i<Consts.listOfEggs.length; i++) {
					if (Consts.listOfEggs[i].isMatch(result.BSSID)) {
						Entry temp = new Entry(result.SSID,result.BSSID,result.level,result.frequency);
						entries.add(temp);
						Consts.listOfEggs[i].setExclude(true);
						break;
					}
				}
				
				//Entry temp = new Entry(result.SSID,result.BSSID,result.level,result.frequency);
				//entries.add(temp);
				
				//String toPrint = String.format("Network: %s - MAC: %s - Strengh: %d - Freq: %d", result.SSID, result.BSSID, result.level, result.frequency);
				//Log.d(Launch.TAG, toPrint);
			}
			//add all the rest of found eggs
			for (int i=0; i<Consts.listOfEggs.length; i++) {
				if (Consts.listOfEggs[i].isFound() && !Consts.listOfEggs[i].isExclude()) {
					Entry temp = new Entry(Consts.listOfEggs[i].getNickname(),Consts.listOfEggs[i].getBSSDs()[0],-10,1000);
					Consts.listOfEggs[i].setExclude(false);
					entries.add(temp);
				} else if (Consts.listOfEggs[i].isExclude()) Consts.listOfEggs[i].setExclude(false);
			}
			
			
			//sort by level
			try {
				Comparator<Entry> comparator = new Comparator<Entry>() {
					@Override
					public int compare(Entry obj1, Entry obj2) {
						return obj2.getLevel()-obj1.getLevel();
					}
				};
				Collections.sort(entries, comparator);
			} catch (ClassCastException e) {

			}
			
			main.populateListView(entries);
		}
	}



}
