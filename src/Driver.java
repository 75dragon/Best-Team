import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Driver {

	public static Scanner userScanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		char exit;
		boolean repeat = false;
		Scanner inputReader;
		Scanner scanner = new Scanner(System.in);
		do{
			MapColoringGraph<States> stateGraph = new MapColoringGraph<>();
			inputReader = openInputFile();
			if(inputReader == null)
				return;
		
			readInputFile(inputReader, stateGraph);
			exit = 'n';
			while(exit != 'y'){
				
				 displayMainMenu(stateGraph);
				
				 char userChoice= scanner.next().charAt(0);
				 scanner.nextLine();
			
				switch(userChoice){
				case'a':	
					System.out.println();
					stateGraph.showAdjTable();
					break;
					
				case'b':
					addAnEdge(stateGraph, scanner);
					break;
					
				case'c':
					removeAnEdge(stateGraph, scanner);
					break;
					
				case'd':
	//				undoRemove(stateGraph);
					break;
					
				case'e':
					colorGraph(stateGraph, scanner);
					break;
					
				case'f':
					saveGraph(stateGraph);
					break;
					
				case'g':
					exit = exitOrRepeat(scanner);
					break;
				default: System.out.println("Invalid input. Please enter again.");	
				}
			}
			System.out.println("Do you want to start an new graph? ('y' for yes)");
			repeat = Character.toLowerCase(scanner.next().charAt(0)) == 'y' ? true : false;
		}while(repeat);
		inputReader.close();
		scanner.close();
	}
	
	public static Scanner openInputFile()
	{
		String filename;
    	Scanner scanner=null;
    
    	System.out.print("Enter the input filename: ");
    	filename = userScanner.nextLine();
    	File file= new File(filename);

    	try{
    		scanner = new Scanner(file);
    	}// end try
    	catch(FileNotFoundException fe){
    	   System.out.println("Can't open input file\n");
   	    return null; // array of 0 elements
    	} // end catch
    	return scanner;
	}
	
	public static PrintWriter openOutputFile(){
   	
        String filename;
    	PrintWriter writer=null;
            
    	System.out.print("Enter the output filename: ");
    	filename = userScanner.nextLine();
       	File file= new File(filename);
       	try{
       		writer = new PrintWriter(file);
       	}// end try
       	catch(FileNotFoundException fe){
       	   System.out.println("Can't open output file\n");
      	    return null; // array of 0 elements
       	} // end catch
       	return writer;
	}
	
	public static void readInputFile(Scanner scanner, MapColoringGraph<States> stateGraph ){
		//TODO make new Graph
				
				String hold;
				States holdIP1 = null;
				States holdIP2 = null;
				String pattern = "(.+) - (.+)";
				if (scanner == null)
				{
					return;
				}
				String name = scanner.nextLine().trim();
				stateGraph.setRegion(name);
				Pattern r = Pattern.compile(pattern);

				while (scanner.hasNextLine())
				{
					hold = scanner.nextLine().trim();
					// Now create matcher object.
					Matcher m = r.matcher(hold);
					if (m.find())
					{
						System.out.println("Found value: " + m.group(1));
						System.out.println("Found value: " + m.group(2));
					}
					
					holdIP1 = new States(m.group(1));
					holdIP2 = new States(m.group(2));
					
					//TODO make a connection between the states
					stateGraph.addEdge(holdIP1, holdIP2, 0);
				}
	}
	
	public static void displayMainMenu(MapColoringGraph<States> stateGraph){
		System.out.println("\n\n*************************************************");
		System.out.println("a. Display the graph");
		System.out.println("b. Add an edge");
		System.out.println("c. Remove an edge");
		System.out.println("d. Undo previous remove");
		System.out.println("e. Color the graph");
		System.out.println("f. Save the graph");
		System.out.println("g. Exit");
		System.out.println("*************************************************");
		System.out.println("Please enter a letter for your choice: ");
		
		
	}
	
	public static void addAnEdge(MapColoringGraph<States> stateGraph, Scanner scanner){
		String s1, s2;
		States state1, state2;
		
		System.out.println("Enter two states that you want to connect.");
		System.out.println("State1: ");
		s1 = scanner.nextLine();
		System.out.println("State2: ");
		s2 = scanner.nextLine();
		
		state1 = new States(s1);
		state2 = new States(s2);
		stateGraph.addEdge(state1, state2, 0);
		System.out.println();
		stateGraph.showAdjTable();
		
	}

	public static void removeAnEdge(MapColoringGraph<States> stateGraph, Scanner scanner){

		String s1, s2;
		States state1, state2;
		boolean remove = false;
		
		do{
			System.out.println("Enter two states that you want to disconnect.");
			System.out.println("State1: ");
			s1 = scanner.nextLine();
			System.out.println("State2: ");
			s2 = scanner.nextLine();
			
			state1 = new States(s1);
			state2 = new States(s2);
			remove = stateGraph.remove(state1, state2);
			if(remove == false)
				System.out.println("Remove fail. Enter again.");
			else{
				System.out.println();
				stateGraph.showAdjTable();
			}
				
		}while(!remove);

	}
	
	
	public static void undoRemove(MapColoringGraph<States> stateGraph){
		boolean remove = stateGraph.undoRemove();
		if(remove){
			System.out.println("\nUndo the previous remove.");
			stateGraph.showAdjTable();
		}
		else 
			System.out.println("\nUndo remove fail.");
	}
	
	
	public static void colorGraph(MapColoringGraph<States> stateGraph, Scanner scanner){
		int number;

		System.out.println("Enter the number of color you want to use: ");
		number = scanner.nextInt();
		
		String [] colorList = new String[number];
		for(int i = 0; i < number; i++){
			System.out.println("Color" + (i+1) + " : ");
			colorList[i] = scanner.next();
		}
		stateGraph.assignColor(number, colorList);
		System.out.println("Do you want to save the result? (y for yes): ");
		if(Character.toLowerCase(scanner.next().charAt(0)) == 'y' );{
			PrintWriter writer = openOutputFile();
			stateGraph.writeTextResult(writer, colorList);
		}
	}
	
	public static void saveGraph(MapColoringGraph<States> stateGraph){
		PrintWriter writer = openOutputFile();
		stateGraph.writeTextAdjList(writer);
	}
	
	public static char exitOrRepeat(Scanner scanner){
		
		System.out.println("Do you want to exit the program? (¡®y¡¯ for exit)");
		char choice = scanner.next().charAt(0);
		
		return Character.toLowerCase(choice);
	}
	
}
