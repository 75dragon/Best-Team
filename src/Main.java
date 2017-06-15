import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Austin Cheng
 *
 */
public class Main
{
	public Scanner userScanner = new Scanner(System.in);

	public Main()
	{
		HashMap<States, ColorVertex<States>> theMap = new HashMap<States, ColorVertex<States>>();
		fillTable(theMap);
	}

	public Scanner openInputFile()
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

	public void fillTable(HashMap<States, ColorVertex<States>> hold1)
	{
		//TODO make new Graph
		Scanner bob = openInputFile();
		String hold;
		States holdIP = null;
		String pattern = "(.+) - (.+)";
		if (bob == null)
		{
			return;
		}
		String name = bob.nextLine().trim();
		Pattern r = Pattern.compile(pattern);

		while (bob.hasNextLine())
		{
			hold = bob.nextLine().trim();
			// Now create matcher object.
			Matcher m = r.matcher(hold);
			if (m.find())
			{
				System.out.println("Found value: " + m.group(1));
				System.out.println("Found value: " + m.group(2));
			}
			holdIP = new States(m.group(1));
			holdIP = new States(m.group(2));
			//TODO add the state
			//TODO make a connection between the states
		}
	}

	public static void main(String args[])
	{
		new Main();
	}
}
