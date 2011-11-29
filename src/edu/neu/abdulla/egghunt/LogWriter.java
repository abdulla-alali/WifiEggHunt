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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Environment;
import android.os.Handler;
import android.util.Log;

public class LogWriter {

	public static void addEntry(Launch launch, String entry2, final Handler mHandler) {

		final String entry = entry2;
		final Launch launch2 = launch;
		
		Thread thread = new Thread(new Runnable() {
			public void run() {
				String state = Environment.getExternalStorageState();

				if (Environment.MEDIA_MOUNTED.equals(state)) {
					// We can read and write the media
					//check if dir exists
					File logDir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/data/edu.neu.abdulla.egghunt/log");
					//MainService.print("trying to make dir: " + Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/data/com.led.notify/log");
					if (!logDir.exists()) {
						boolean isSuc = logDir.mkdirs();
						Log.d(Launch.TAG, "mkdir is " + isSuc);
					}

					BufferedWriter bufferedWriter = null;

					try {

						//Construct the BufferedWriter object
						bufferedWriter = new BufferedWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Android/data/edu.neu.abdulla.egghunt/log/savedRouters.txt", true));

						bufferedWriter.write(entry);
						bufferedWriter.newLine();

					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					} catch (IOException ex) {
						ex.printStackTrace();
					} finally {
						//Close the BufferedWriter
						try {
							if (bufferedWriter != null) {
								bufferedWriter.flush();
								bufferedWriter.close();
								mHandler.post(new Runnable() {
									public void run() {
										launch2.displayToast("Success");
									}
								});
							}
						} catch (IOException ex) {
							ex.printStackTrace();
						}
					}


				}
			}
		});
		
		thread.start();
		
		
	}
	
}
