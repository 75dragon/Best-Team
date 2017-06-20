import java.io.PrintWriter;
import java.util.*;
import java.util.Map.Entry;
//designed by Xiaoya Li
//test by collin 
class ColorVertex<E> extends Vertex<E>
{
	private int color;

	public ColorVertex(E x)
	{
		super(x);
		color = -1;
	}

	public ColorVertex()
	{
		super(null);
		color = -1;
	}

	public void setColor(int c)
	{
		color = c;
	}

	public int getColor()
	{
		return color;
	}

	// check whether other vertices in curr's adjList is marked the same color
	// or not
	// wrote by Xiaoya Li
	public boolean isSafeColor(int cr)
	{
		Iterator<Map.Entry<E, Pair<Vertex<E>, Double>>> iter = this.iterator();
		while (iter.hasNext())
		{
			ColorVertex<E> adjV = (ColorVertex<E>) iter.next().getValue().first;
			if (adjV.getColor() == cr)
				return false;
		}
		return true;
	}

	// wrote by Xiaoya Li
	@Override
	public void showAdjList()
	{
		Iterator<Entry<E, Pair<Vertex<E>, Double>>> iter;
		Entry<E, Pair<Vertex<E>, Double>> entry;
		Pair<Vertex<E>, Double> pair;

		System.out.print("Adj List for " + data + ": ");
		iter = adjList.entrySet().iterator();

		while (iter.hasNext())
		{
			entry = iter.next();
			pair = entry.getValue();
			System.out.print(" " + pair.first.data + " | ");
		}
		System.out.println();
	}

}

public class MapColoringGraph<E> extends Graph<E>
{

	private LinkedStack<E> removedList;
	private String region;

	public MapColoringGraph(String name)
	{
		super();
		removedList = new LinkedStack<E>();
		region = name;
	}

	public MapColoringGraph()
	{
		super();
		removedList = new LinkedStack<E>();
		region = "Unknown";
	}

	public void setRegion(String name)
	{
		region = name;
	}

	// wrote by Xiaoya Li
	@Override
	public ColorVertex<E> addToVertexSet(E x)
	{
		// instantiate a new ColorVertex instead
		ColorVertex<E> retVal = null;
		ColorVertex<E> foundVertex;

		// find if Vertex already in the list:
		foundVertex = (ColorVertex<E>) vertexSet.get(x);

		if (foundVertex != null) // found it, so return it
		{
			return foundVertex;
		}

		// the vertex not there, so create one
		retVal = new ColorVertex<E>(x);
		vertexSet.put(x, retVal);

		return retVal; // should never happen
	}

	// wrote by Xiaoya Li
	@Override
	public void addEdge(E source, E dest, double cost)
	{
		// instantiate a new ColorVertex instead
		ColorVertex<E> src, dst;

		// put both source and dest into vertex list(s) if not already there
		src = (ColorVertex<E>) addToVertexSet(source);
		dst = (ColorVertex<E>) addToVertexSet(dest);

		// add dest to source's adjacency list
		src.addToAdjList(dst, cost);
		dst.addToAdjList(src, cost); // ADD THIS IF UNDIRECTED GRAPH

	}

	// wrote by Xiaoya Li
	@Override
	public void addEdge(E source, E dest, int cost)
	{
		// instantiate a new ColorVertex instead
		addEdge(source, dest, (double) cost);
	}

	// wrote by Collin Hurst, fixed by Xiaoya Li
	public boolean undoRemove()
	{
		boolean undo = false;
		E endData = removedList.pop();
		E startData = removedList.pop();
		if(endData != null && startData != null){
			this.addEdge(startData, endData, 0.0);
			undo = true;
		}
		return undo;
	}

	@Override
	public boolean remove(E start, E end)
	{
		Vertex<E> startVertex = vertexSet.get(start);
		boolean removedOK = false;

		if (startVertex != null)
		{
			Pair<Vertex<E>, Double> endPair = startVertex.adjList.remove(end);
			removedOK = endPair != null;
		}
		Vertex<E> endVertex = vertexSet.get(end);
		if (endVertex != null)
		{
			Pair<Vertex<E>, Double> startPair = endVertex.adjList.remove(start);
			removedOK = startPair != null;
		}
		if (removedOK)
		{
			removedList.push(start);
			removedList.push(end);
		}
		return removedOK;
	}

	// wrote by Xiaoya Li
	public boolean assignColor(int numOfColor, String[] colorStr)
	{
		int numColored = 0;
		Iterator<Map.Entry<E, Vertex<E>>> iter = vertexSet.entrySet().iterator();
		if (!assignHelper(numOfColor, numColored, iter))
		{
			System.out.println("Solution does not exist");
			return false;
		}
		displayColorResult(colorStr);
		return true;

	}

	// wrote by Xiaoya Li
	private boolean assignHelper(int numOfColor, int numColored, Iterator<Map.Entry<E, Vertex<E>>> iter)
	{
		/* base case: If all vertices are assigned a color then return true */
		if (numColored == vertexSet.size())
			return true;

		/* Consider this vertex and try different colors */
		while (iter.hasNext())
		{
			ColorVertex<E> curr = (ColorVertex<E>) iter.next().getValue();
			for (int c = 0; c < numOfColor; c++)
			{

				/* Check if assignment of color c to this vertex is fine */
				if (curr.isSafeColor(c))
				{
					curr.setColor(c);
					numColored++;

					/* recur to assign colors to rest of the vertices */
					if (assignHelper(numOfColor, numColored, iter))
						return true;

					/*
					 * If assigning color c doesn't lead to a solution then
					 * remove it
					 */
					curr.setColor(-1);
					numColored--;
				}
			}
		}
		/*
		 * If no color can be assigned to this vertex then return false
		 */
		return false;

	}

	// wrote by Xiaoya Li
	public void displayColorResult(String[] colorStr)
	{
		System.out.println("Solution for " + region + " Exists: Following are the assigned colors");
		Iterator<Entry<E, Vertex<E>>> iter;
		iter = vertexSet.entrySet().iterator();

		while (iter.hasNext())
		{
			ColorVertex<E> cv = (ColorVertex<E>) iter.next().getValue();
			System.out.print(cv.getData() + " is: " + colorStr[cv.getColor()]);
			System.out.println();
		}
		System.out.println();
	}

	// wrote by Xiaoya Li
	@Override
	public void showAdjTable()
	{
		Iterator<Entry<E, Vertex<E>>> iter;

		System.out.println("------------------------ ");
		System.out.println(region);
		iter = vertexSet.entrySet().iterator();
		while (iter.hasNext())
		{
			((ColorVertex<E>) (iter.next().getValue())).showAdjList();
		}
		System.out.println();
	}

	// wrote by Collin Hurst
	public void writeTextResult(PrintWriter writer, String[] colorList)
	{
		Iterator<Entry<E, Vertex<E>>> iter;
		iter = vertexSet.entrySet().iterator();

		writer.println(region);
		while (iter.hasNext())
		{
			Vertex<E> v = iter.next().getValue();
			ColorVertex<E> cv = (ColorVertex<E>) v;
			writer.println(cv.getData() + " is: " + colorList[cv.getColor()]);
		}
		writer.println();
		writer.close();
	}

	// wrote by Collin Hurst
	public void writeTextAdjList(PrintWriter writer)
	{
		Iterator<Entry<E, Vertex<E>>> iter;
		iter = vertexSet.entrySet().iterator();
		writer.println(region);
		while (iter.hasNext())
		{
			printAdjList(iter.next().getValue(), writer);
		}
		writer.println();
		writer.close();
	}

	// wrote by Collin Hurst
	public void printAdjList(Vertex<E> vertex, PrintWriter writer)
	{
		Iterator<Entry<E, Pair<Vertex<E>, Double>>> iter;
		Entry<E, Pair<Vertex<E>, Double>> entry;
		Pair<Vertex<E>, Double> pair;
		E data = vertex.getData();
		writer.print("Adj List for " + data + ": ");
		iter = vertex.adjList.entrySet().iterator();
		while (iter.hasNext())
		{
			entry = iter.next();
			pair = entry.getValue();
			writer.print(pair.first.getData() + " | ");
		}
		writer.println();
	}

}
