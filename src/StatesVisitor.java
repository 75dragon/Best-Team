/**
 * wrote by Xiaoya Li 
 * Visitor object to help easily print out the map.
 * @author Xiaoya Li
 * @author Austin Cheng
 * @author Collin Hurst
 * Windows 10 Eclipse
 */
public class StatesVisitor implements Visitor<States> {

	@Override
	public void visit(States obj) {
		System.out.println(obj);
		
	}

}
