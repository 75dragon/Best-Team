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
	
	@Override
	public void showAdjList(){
		//add the region name and show the list in our format
	}
}

public class MapColoringGraph<E> extends Graph<E> {
	
	private LinkedStack<E> removedList;
	private String region;
	
	public MapColoringGraph(String name){
		super();
		region = name;
	}
	
	public MapColoringGraph(){
		super();
	}
	
	@Override 
	public Vertex<E> addToVertexSet(E x){
		// instantiate a new ColorVertex instead
		
	}
	
	@Override
	public void addEdge(E source, E dest, double cost){
		// instantiate a new ColorVertex instead
	}
	
	@Override 
	public void addEdge(E source, E dest, int cost){
		// instantiate a new ColorVertex instead
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
