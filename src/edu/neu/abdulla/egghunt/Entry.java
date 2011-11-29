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

public class Entry {

	private String SSID;
	private String BSSD;
	private int level;
	private int freq;
	private boolean isFound;

	
	public Entry (String SSID, String BSSD, int level, int freq) {
		this.SSID = SSID;
		this.BSSD = BSSD;
		this.level = level;
		this.freq = freq;
		this.isFound = false;
	}
	
	public String getSSID() {
		return SSID;
	}
	public void setSSID(String sSID) {
		SSID = sSID;
	}
	public String getBSSD() {
		return BSSD;
	}
	public void setBSSD(String bSSD) {
		BSSD = bSSD;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getFreq() {
		return freq;
	}
	public void setFreq(int freq) {
		this.freq = freq;
	}
	public boolean isFound() {
		return isFound;
	}
	public void setIsFound(boolean isFound) {
		this.isFound = isFound;
	}
	
}
