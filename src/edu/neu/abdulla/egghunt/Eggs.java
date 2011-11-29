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

import android.util.Log;

public class Eggs {

	private String[] BSSD;
	private boolean isFound;
	private String nickname;
	private int low;
	private int high;
	private boolean exclude;
	
	public Eggs(String[] BSSD, String nickname, int lowDbm, int highDbm) {
		this.BSSD = BSSD;
		this.isFound = false;
		this.low = lowDbm;
		this.high = highDbm;
		this.nickname = nickname;
		this.exclude = false;
	}

	public String[] getBSSDs() {
		return BSSD;
	}
	
	public boolean isMatch(String BSSD) {
		Log.d(Launch.TAG, "ismatch: " + BSSD);
		for (int i=0; i<this.BSSD.length; i++) {
			if (this.BSSD[i].equals(BSSD))
				return true;
		}
		return false;
	}

	public void setBSSD(String[] bSSD) {
		BSSD = bSSD;
	}

	public boolean isFound() {
		return isFound;
	}

	public void setFound(boolean isFound) {
		this.isFound = isFound;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public int getLow() {
		return low;
	}

	public void setLow(int low) {
		this.low = low;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}
	public boolean isExclude() {
		return exclude;
	}
	public void setExclude(boolean exclude) {
		this.exclude = exclude;
	}


	
	
	
}
