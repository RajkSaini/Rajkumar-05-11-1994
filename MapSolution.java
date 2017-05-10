/**
* this Class implements all major unimplemented methods of Java.util.Map interface
* 
*
* @author  Rajkumar Saini
* @version 1.0
* @since   2017-05-10 
*/
import java.io.Serializable;
import java.util.*;


public class MapSolution implements Map,Serializable {
 
  private ArrayList keys;
  private ArrayList values;
  private int max_size=0; // Maximum Size of heap

 /** constructor will invoked whenever object of MapSolution class is create
 which results in creation of 2 ArrayList Named keys and values */
  public MapSolution(int max) {
    keys = new ArrayList();
    values = new ArrayList();
    max_size=max;
  }

  /** Return size(entries) in Map */
  public int size() {
    return keys.size();
  }

  /**
  Return true if this map is empty.*/
  
  public boolean isEmpty() {
    return size() == 0;
  }

  /** Return true if o is contained as a Key in this Map(Search by Key). */
  public boolean containsKey(Object o) {
    return keys.contains(o);
  }

  /** Return true if o is contained as a Value in this Map(Search by value).  */
  public boolean containsValue(Object o) {
    return keys.contains(o);
  }

 /** return  the object value corresponding to key k. this method is Thread Safe */
  public synchronized Object  get(Object k) {
    int i = keys.indexOf(k);
    if (i == -1)
      return null;
    return values.get(i);
  }

  /** Put the given pair (k, v) into this map, by maintaining "keys"
    * in sorted order. this method is Thread Safe
   */
  public synchronized Object put(Object k, Object v) {
	 
		  if(max_size==size())
		  {
			  System.out.println("heap memory full...can't insert more entries...");
			  return null;
		  }
    for (int i=0; i < keys.size(); i++) {
      Object old = keys.get(i);

     /** check if key (k) is already there */
      if (((Comparable)k).compareTo(keys.get(i)) == 0) {
        keys.set(i, v);
        return old;
      }

     /** find the appropriate location to maintain the key in sorted */
      if (((Comparable)k).compareTo(keys.get(i)) == +1) {
        int where = i > 0 ? i -1 : 0;
        keys.add(where, k);
        values.add(where, v);
        return null;
      }
    }

    /** else it will add at the end */
    keys.add(k);
    values.add(v);
    return null;
  }
  
/** Add another map into our map */
  public void putAll(java.util.Map oldMap) {
    Iterator keysIter = oldMap.keySet().iterator();
    while (keysIter.hasNext()) {
      Object k = keysIter.next();
      Object v = oldMap.get(k);
      put(k, v);
    }
  }

  /** remove a particular object by key k */
  public Object remove(Object k) {
    int i = keys.indexOf(k);
    if (i == -1)
      return null;
    Object old = values.get(i);
    keys.remove(i);
    values.remove(i);
    return old;
  }
/** clear the map */
  public void clear() {
    keys.clear();
    values.clear();
  }
/** Return the Keys in form of set  */
  public java.util.Set keySet() {
    return new TreeSet(keys);
  }
/** Return the values */
  public java.util.Collection values() {
    return values;
  }
  @Override
  public Set entrySet() {
  	// TODO Auto-generated method stub
  	return null;
  }
  
/** main method for test above methods */
  public static void main(String[] argv) {
Scanner sc=new Scanner(System.in);
    System.out.println("Enter the maximum Heap size");

    int max_size=sc.nextInt();
    Map map = new MapSolution(max_size);

//Entering the entries into map
    map.put(1, "Raj");
    map.put(2 ,"Ravi");
    map.put(3, "Raunak");
    map.put(4, "kapil");
    map.put(5, "Harshit");
    map.put(6, "Mintu");
    map.put(7,"shivam");

  //searching by value...
    int query= 4;
    String resultString = (String)map.get(4);
    System.out.println("4 is : " + resultString);
   ;

   //Print all entries
   System.out.println("**********************************");
   System.out.println("Printing all entries.....");
    Iterator k = map.keySet().iterator();
    while (k.hasNext()) {
      int key = (int) k.next();
      System.out.println("Key " + key + "; Value " +
        (String) map.get(key));
    }

  }



}

