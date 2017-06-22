
import java.util.*;
import java.text.*;


//------------------------------------------------------
/**
 * an extremely simple tester class. 
 * Basically runs a few functions to make a small graph, to test functionality.
 * @author Xiaoya Li
 * @author Austin Cheng
 * @author Collin Hurst
 * Windows 10 Eclipse
 */
public class GraphTester
{
	   // -------  main --------------
	   public static void main(String[] args)
	   {
	      // build graph
		   MapColoringGraph<String> myGraph1 = new MapColoringGraph<String>();
		      myGraph1.addEdge("A", "B", 0);   
		      myGraph1.addEdge("A", "C", 0);  
		      myGraph1.addEdge("A", "D", 0);
		      myGraph1.addEdge("A", "H", 0);
		      myGraph1.addEdge("B", "E", 0);   
		      myGraph1.addEdge("B", "F", 0);
		      myGraph1.addEdge("C", "G", 0);   
		      myGraph1.addEdge("C", "N", 0);
		      myGraph1.addEdge("D", "H", 0);   
		      myGraph1.addEdge("D", "I", 0);
		      myGraph1.addEdge("E", "G", 0);
		      myGraph1.addEdge("F", "J", 0);
		      myGraph1.addEdge("G", "K", 0);   
		      myGraph1.addEdge("G", "L", 0);
		      myGraph1.addEdge("H", "M", 0);   
		      myGraph1.addEdge("H", "N", 0);
		      myGraph1.addEdge("I", "N", 0);

		      String [] str = new  String[] {"red", "yellow", "blue", "white"};
		      myGraph1.showAdjTable();
		      myGraph1.assignColor(4, str );
	   }

}
