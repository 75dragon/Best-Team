import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

//wrote by Xiaoya Li except the readInputFile() method
//bug fixed by Austin Cheng 
public class Driver
{
	public static Scanner userScanner = new Scanner(System.in);

	public static void main(String[] args)
	{
		char exit;
		boolean repeat = false;
		Scanner inputReader;
		Scanner scanner = new Scanner(System.in);
		do
		{
			MapColoringGraph<States> stateGraph = new MapColoringGraph<>();
			inputReader = openInputFile();
			if (inputReader == null)
				return;

			States lastElem = readInputFile(inputReader, stateGraph);
			exit = 'n';
			while (exit != 'y')
			{

				displayMainMenu();

				char userChoice = scanner.next().charAt(0);
				scanner.nextLine();

				switch (userChoice)
				{
					case 'a':
						System.out.println();
						displayGraph(scanner, stateGraph, lastElem);
						break;

					case 'b':
						addAnEdge(stateGraph, scanner);
						break;

					case 'c':
						removeAnEdge(stateGraph, scanner);
						break;

					case 'd':
						undoRemove(stateGraph);
						break;

					case 'e':
						colorGraph(stateGraph, scanner);
						break;

					case 'f':
						saveGraph(stateGraph);
						break;

					case 'g':
						exit = exitOrRepeat(scanner);
						break;
					default:
						System.out.println("Invalid input. Please enter again.");
				}
			}
			System.out.println("Do you want to start an new graph? ('y' for yes)");
			repeat = Character.toLowerCase(scanner.next().charAt(0)) == 'y' ? true : false;
		} while (repeat);
		inputReader.close();
		scanner.close();
	}

	/**
	 * attempts to open an file the user enters
	 * 
	 * @return the scanner if successful
	 */
	public static Scanner openInputFile()
	{
		String filename;
		Scanner scanner = null;

		System.out.print("Enter the input filename: ");
		filename = userScanner.nextLine();
		File file = new File(filename);

		try
		{
			scanner = new Scanner(file);
		} // end try
		catch (FileNotFoundException fe)
		{
			System.out.println("Can't open input file\n");
			return null; // array of 0 elements
		} // end catch
		return scanner;
	}

	/**
	 * attempts to open a file to write on
	 * 
	 * @return a printwriter, if found
	 */
	public static PrintWriter openOutputFile()
	{

		String filename;
		PrintWriter writer = null;

		System.out.print("Enter the output filename: ");
		filename = userScanner.nextLine();
		File file = new File(filename);
		try
		{
			writer = new PrintWriter(file);
		} // end try
		catch (FileNotFoundException fe)
		{
			System.out.println("Can't open output file\n");
			return null; // array of 0 elements
		} // end catch
		return writer;
	}

	//wrote by Austin Cheng
	/**
	 * reads the input file into the graph. checks for errors along the way
	 * 
	 * @param scanner
	 *            the scanner to read the file
	 * @param stateGraph
	 *            the graph
	 */
	public static States readInputFile(Scanner scanner, MapColoringGraph<States> stateGraph)
	{
		int brokenLines = 0;
		String hold;
		States holdIP1 = null;
		States holdIP2 = null;
		String pattern = "(.+) - (.+)";
		if (scanner == null || scanner.hasNext() == false)
		{
			return null;
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
				holdIP1 = new States(m.group(1));
				holdIP2 = new States(m.group(2));
				stateGraph.addEdge(holdIP1, holdIP2, 0);
			} 
			else
			{
				brokenLines++;
			}
		}
		if (brokenLines != 0)
		{
			System.out.println(
					"This input had " + brokenLines + " invalid input lines. They were not added to the list");
			System.out.println("Remember to use the format");
			System.out.println("String - String");
		}
		return holdIP2;
	}

	/**
	 * displays the menu
	 */
	public static void displayMainMenu()
	{

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
	
	/**
	 * Ask user which way to display the graph and display it.
	 * @param scanner to read input
	 * @param stateGraph -  the graph
	 * @param startElem - the element to start the traversal
	 */

	public static void displayGraph(Scanner scanner, MapColoringGraph<States> stateGraph, States startElem){
		int choice;
		boolean repeat;
		StatesVisitor visitor = new StatesVisitor();
		System.out.println("Which traversal do you want to use? Enter the number of your choice. ");
		System.out.println("1. Depth-First traversal");
		System.out.println("2. Breadth-First traversal");
		System.out.println("3. Show adjacency list of each vertex");
		choice = scanner.nextInt();
		do{
			repeat = false;
			switch(choice){
			case 1:
				stateGraph.depthFirstTraversal(startElem, visitor);
				break;
			case 2: 
				stateGraph.breadthFirstTraversal(startElem, visitor);
				break;
			case 3:
				stateGraph.showAdjTable();
				break;
			default:
				System.out.println("Invalid input. Please enter again.");
				repeat = true;
			}
		}while(repeat);
	}
	/**
	 * adds an edge from 2 states the user inputs
	 * 
	 * @param stateGraph
	 *            the graph
	 * @param scanner
	 *            the scanner to read input
	 */
	public static void addAnEdge(MapColoringGraph<States> stateGraph, Scanner scanner)
	{
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

	/**
	 * removes an edge between 2 states the user inputs
	 * 
	 * @param stateGraph
	 *            the graph
	 * @param scanner
	 *            the scanner to read input
	 */
	public static void removeAnEdge(MapColoringGraph<States> stateGraph, Scanner scanner)
	{
		String s1, s2;
		States state1, state2;
		boolean remove = false;
		System.out.println("Enter two states that you want to disconnect.");
		System.out.println("State1: ");
		s1 = scanner.nextLine();
		System.out.println("State2: ");
		s2 = scanner.nextLine();

		state1 = new States(s1);
		state2 = new States(s2);
		remove = stateGraph.remove(state1, state2);
		if (remove == false)
			System.out.println("Remove fail.");
		else
		{
			System.out.println();
			stateGraph.showAdjTable();
		}

	}

	/**
	 * undo's the last remove(). adds the connection back
	 * 
	 * @param stateGraph
	 *            the graph
	 */
	public static void undoRemove(MapColoringGraph<States> stateGraph)
	{
		boolean remove = stateGraph.undoRemove();
		if (remove)
		{
			System.out.println("\nUndo the previous remove.");
			stateGraph.showAdjTable();
		} else
			System.out.println("\nUndo remove fail.");
	}

	/**
	 * colors the graph. No 2 adjacent objects can be the same color.
	 * @param stateGraph the graph
	 * @param scanner to read input
	 */
	public static void colorGraph(MapColoringGraph<States> stateGraph, Scanner scanner)
	{
		int number;
		boolean success;
		do{
			System.out.println("Enter the number of color you want to use: ");
			number = scanner.nextInt();
		}while(number <= 0);

		String[] colorList = new String[number];
		for (int i = 0; i < number; i++)
		{
			System.out.println("Color" + (i + 1) + " : ");
			colorList[i] = scanner.next();
		}
		success = stateGraph.assignColor(number, colorList);
		if(success){
			System.out.println("Do you want to save the result? (y for yes): ");
			if (Character.toLowerCase(scanner.next().charAt(0)) == 'y')
			{
				PrintWriter writer = openOutputFile();
				stateGraph.writeTextResult(writer, colorList);
			}
		}
	}

	/**
	 * saves the graph to a file
	 * @param stateGraph the graph
	 */
	public static void saveGraph(MapColoringGraph<States> stateGraph)
	{
		PrintWriter writer = openOutputFile();
		stateGraph.writeTextAdjList(writer);
	}

	/**
	 * ask the user exit or not
	 * @param scanner to read input
	 * @return user's choice
	 */
	public static char exitOrRepeat(Scanner scanner)
	{

		System.out.println("Do you want to exit the program? (¡®y¡¯ for exit)");
		char choice = scanner.next().charAt(0);

		return Character.toLowerCase(choice);
	}

}
