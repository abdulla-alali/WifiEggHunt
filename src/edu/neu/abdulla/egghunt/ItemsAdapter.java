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

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemsAdapter extends BaseAdapter {
	private Context context;

	private List<Entry> listEntries;

	public ItemsAdapter(Context context, List<Entry> listEntries) {
		this.context = context;
		this.listEntries = listEntries;
	}

	public int getCount() {
		return listEntries.size();
	}

	public Object getItem(int position) {
		return listEntries.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup viewGroup) {
		Entry entry = listEntries.get(position);
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.wifiresult_item, null);
		}
		TextView tvName = (TextView) convertView.findViewById(R.id.name);
		tvName.setText(entry.getSSID());

		//TextView tvMac = (TextView) convertView.findViewById(R.id.mac);
		//tvMac.setText(entry.getBSSD());

		TextView tvLevel = (TextView) convertView.findViewById(R.id.strength);
		tvLevel.setText(entry.getLevel()+"");

		tvName.setTextColor(Color.WHITE);
		//tvMac.setTextColor(Color.WHITE);
		tvLevel.setTextColor(Color.WHITE);
		tvName.setPaintFlags(257); //i don't know what 257 is, but its the default num


		//is it one of the guys that we know ?
		for (int i=0; i<Consts.listOfEggs.length; i++) {
			if (Consts.listOfEggs[i].isMatch(entry.getBSSD())) {
				Log.d(Launch.TAG, "MATCH");
				if (!Consts.listOfEggs[i].isFound()) {
					tvName.setText(Consts.listOfEggs[i].getNickname());
					tvName.setTextColor(Color.BLUE);
					//tvMac.setTextColor(Color.BLUE);
					tvLevel.setTextColor(Color.BLUE);
					tvName.setPaintFlags(257);
					Log.d(Launch.TAG, "BLUE");
					break;
				} else { //if found
					tvName.setText(Consts.listOfEggs[i].getNickname());
					tvName.setTextColor(Color.GREEN);
					//tvMac.setTextColor(Color.GREEN);
					tvLevel.setTextColor(Color.GREEN);
					tvName.setPaintFlags(tvName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
					Log.d(Launch.TAG, "GREEN");
					break;
				}
			} else { //TODO: need to remove this. if its not a match.
				tvName.setTextColor(Color.WHITE);
				//tvMac.setTextColor(Color.WHITE);
				tvLevel.setTextColor(Color.WHITE);
				tvName.setPaintFlags(257);
				Log.d(Launch.TAG, "WHITE");
			}
		}

		return convertView;
	}

}