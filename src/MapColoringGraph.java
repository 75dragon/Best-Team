import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;
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
	public boolean remove(E start, E end) {
		Vertex<E> startVertex = vertexSet.get(start);
		boolean removedOK = false;

		if (startVertex != null) {
			Pair<Vertex<E>, Double> endPair = startVertex.adjList.remove(end);
			removedOK = endPair != null;
		}
		Vertex<E> endVertex = vertexSet.get(end);
		if (endVertex != null) {
			Pair<Vertex<E>, Double> startPair = endVertex.adjList.remove(start);
			removedOK = startPair != null;
		}
		if (removedOK) {
			removedList.push(start);
			removedList.push(end);
		}
		return removedOK;
	}
	
	public boolean assignColor(int numOfColor){
		
	}
	
	public void displayColorResult(){
		
	}
	
	public void writeTextResult(PrintWriter writer) {
		Iterator<Entry<E, Vertex<E>>> iter;
		iter = vertexSet.entrySet().iterator();
		Vertex<E> v =iter.next().getValue();
		ColorVertex<E> cv = (ColorVertex<E>)v;
		while (iter.hasNext()) {
			writer.print( iter.next().getValue() + " is: " + cv.getColor());
			//printAdjList(iter.next().getValue(), writer);
		}
		writer.println();
	}
	
	public void writeTextAdjList(PrintWriter writer) {
		Iterator<Entry<E, Vertex<E>>> iter;
		iter = vertexSet.entrySet().iterator();
		while (iter.hasNext()) {
			printAdjList(iter.next().getValue(), writer);
		}
		writer.println();
	}
	
	public void printAdjList( Vertex<E> vertex, PrintWriter writer) {
		Iterator<Entry<E, Pair<Vertex<E>, Double>>> iter;
		Entry<E, Pair<Vertex<E>, Double>> entry;
		Pair<Vertex<E>, Double> pair;
		E data = vertex.getData();
		writer.print("Adj List for " + data + ": ");
		iter = vertex.adjList.entrySet().iterator();
		while (iter.hasNext()) {
			entry = iter.next();
			pair = entry.getValue();
			writer.print(pair.first.data + "(" + String.format("%3.1f", pair.second) + ") ");
		}
		writer.println();
	}
}
