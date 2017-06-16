
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
	
	@Override 
	public boolean equals(Object x){
		if(x instanceof States){
			States para = (States)x;
			return para.getName().equals(name);
		}else
			return false;
	}
	
	@Override
	public int hashCode(){
		return name.hashCode();
	}
	
	
}
