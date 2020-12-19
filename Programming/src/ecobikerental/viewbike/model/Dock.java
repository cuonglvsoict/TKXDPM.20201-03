package ecobikerental.viewbike.model;

public class Dock {

	int ID;
	String name;
	int numberOfDockingPoint;
	Bike[] ListBike = new Bike[numberOfDockingPoint];
	//mỗi 1 docking point là 1 phần tử của mảng kiểu Bike, trống thì = null
	public Dock(int dockID) {
		ListBike = mergeData();
	}
	
	public void display() {
		
	}
	
	public String getName() {
		return name;
	}
	
	
	
	public Bike[] mergeData(){
		//import du lieu tu csdl vao mang ListBike
		Bike[] ListBike = new Bike[numberOfDockingPoint];
		//gagfkadgfagfjhagfhjkgakdshf
		return ListBike;
	}
	
	
}
