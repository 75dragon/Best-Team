import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

import HW5.HashQP;
import HW5.HashSC;
import HW5.IpAddress;
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
		HashMap<States, ColorVertex<States>> theMap;
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
		Scanner bob = openInputFile();
		String hold;
		States holdIP = null;
		if (bob == null)
		{
			return;
		}
		while (bob.hasNextLine())
		{
			hold = bob.nextLine().trim();
			holdIP = new States(hold);
			System.out.println("Inserted into HashSC: " + holdIP.getDottedDecimal() + ", " + holdIP.getIpValue());
			hold1.insert(holdIP);
			System.out.println("Inserted into HashQP: " + holdIP.getDottedDecimal() + ", " + holdIP.getIpValue());
			hold2.insert(holdIP);
		}
		return holdIP;
	}
	
	public static void main( String args[] )
	{
		new Main();
	}
}
