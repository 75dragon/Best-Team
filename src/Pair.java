/**
 * a simple pair data structure, (first, second), that uses first as a key
 * @author Xiaoya Li
 * @author Austin Cheng
 * @author Collin Hurst
 * Windows 10 Eclipse
 */
public class Pair<E, F>
{
   public E first;
   public F second;
   
   public Pair(E x, F y) { first = x; second = y; }
   
   public boolean equals(Object rhs)
   {
      Pair<E, F> other;
      other = (Pair<E, F>)rhs;
      return first.equals(other.first);
   }
   
   public int hashCode()
   {
      return first.hashCode();
   }
}