package ecobikerental.viewbike.gui;

import java.util.ArrayList;

import ecobikerental.viewbike.model.Dock;

public class MapScreen {
	
	static ArrayList<Dock> DockList;

	public MapScreen(ArrayList<Dock> DockList) {
		this.DockList=DockList;
	}

	public static void display() {
		for (int i = 0; i < DockList.size(); i++) {
			System.out.println(DockList.get(i).getName());
		}
	}

}
