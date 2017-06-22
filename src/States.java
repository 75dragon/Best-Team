/**
 * wrote by Xiaoya Li
 * a state class - as simple as you can get, really.
 * has a string to represent the name, thats basically it.
 * @author Xiaoya Li
 * @author Austin Cheng
 * @author Collin Hurst
 * Windows 10 Eclipse
 */
public class States {
	private String name;
	
	public States(String n){
		name = n;
	}
	
	public void setName(String n){
		name = n;
	}
	
	public String getName(){
		return name;
	}
	
	@Override
	public String toString(){
		return name;
	}
	
	//compares the name
	@Override 
	public boolean equals(Object x){
		if(x instanceof States){
			States para = (States)x;
			return para.getName().equals(name);
		}else
			return false;
	}
	
	//returns a hashcode for the name
	@Override
	public int hashCode(){
		return name.hashCode();
	}
	
	
}
