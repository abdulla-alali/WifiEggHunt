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

public class Consts {

	public static boolean debug = false;
	
	public static final Eggs[] listOfEggs = {
		new Eggs(new String[] { "00:16:01:12:d9:be" }, "My own very home", -55, -50), //home gg-al (discard, testing only)
		new Eggs(new String[] { "00:24:6c:c8:04:21" }, "Shilman", -76, -50), //shilman hall
		new Eggs(new String[] { "c0:3f:0e:19:54:88" }, "Wollaston", -72, -50), //new Max Wifi
		new Eggs(new String[] { "00:1d:7e:c0:15:a8", "d8:5d:4c:ea:03:46", "00:23:69:8c:ea:ad" }, "L Willis hall", -75, -50), //after wollaston on the left
		new Eggs(new String[] { "66:2a:2f:53:7c:99" } , "Meserve 35", -79, -50), //new
		new Eggs(new String[] { "a0:21:b7:ac:73:27" } , "Tree-Hydrant", -72, -50), //opposite of hydrnant Willis hall
		new Eggs(new String[] { "00:0f:24:6c:57:31", "00:24:6c:c8:18:a1" }, "CCIS", -79, -50), //CCIS
		new Eggs(new String[] { "00:18:f8:4d:be:fb" } , "Bike stand", -80, -50), //new bike stand drop BASS

		new Eggs(new String[] { "2e:22:64:d6:ee:73" } , "Snell Trash", -75, -50), //trash infront of egan's door
		new Eggs(new String[] { "3a:b6:9b:65:24:d1" } , "Tree Snell", -70, -50), //corner of churchill hall by hayden
		new Eggs(new String[] { "00:40:10:10:00:03" } , "Tree Hyden", -80, -50), //tree left of snell's main entrance
		new Eggs(new String[] { "00:24:6c:c8:03:00", "00:24:6c:c8:03:01" }, "Library", -70, -50), //by library
		new Eggs(new String[] { "00:24:6c:c8:02:c1" }, "Courtesy plz", -69, -50), //Opposite courtesy please board and snell library
		new Eggs(new String[] { "00:24:6c:c9:2c:e0" }, "Starbucks bridge", -83, -50), //Outside starbucks near the bridge and the road
		new Eggs(new String[] { "00:0c:41:14:a0:ec" }, "Walkway jnct", -88, -50), //On the walk near the junction
		new Eggs(new String[] { "02:23:03:11:51:0c" }, "Ell Hall", -75, -50) //to the right of el hall
		
	};
	
	
}
