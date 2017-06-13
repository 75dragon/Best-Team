import java.io.PrintWriter;
import java.util.*;
class ColorVertex<E> extends Vertex<E>{
	private int color;
	
	public ColorVertex(E x){
		super(x);
	}
	
	public ColorVertex(){
		super(null);
	}
	
	public void setColor(int c){
		color = c;
	}
	
	public int getColor(){
		return color;
	}
}

public class MapColoringGraph<E> extends Graph<E> {
	private HashMap<E, ColorVertex<E>> vertexSet;
	private LinkedStack<E> removedList;
	private String countryName;
	
	public MapColoringGraph(String name){
		vertexSet = new HashMap<E, ColorVertex<E>>();
		countryName = name;
	}
	
	public MapColoringGraph(){
		vertexSet = new HashMap<E, ColorVertex<E>>();
	}
	
	public void setCountryName(String name){
		countryName = name;
	}
	
	public String getCountryName(){
		return countryName;
	}
	
	@Override
	public boolean remove(E start, E end){
		
	}
	
	public boolean assignColor(int numOfColor){
		
	}
	
	public void displayColorResult(){
		
	}
	
	public void writeTextResult(PrintWriter writer){
		
	}
	
	public void writeTextAdjList(PrintWriter writer){
		
	}
	
}
