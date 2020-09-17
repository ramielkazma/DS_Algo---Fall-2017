import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class SmartAR<E> {

	private class CarEntry<String, V> implements Entry<String, V>
	{
		private String license;
		private V value;

		private CarEntry(String license, V value) {
			
			this.license = license;
			this.value = value;	
		}

		public String getKey() { return license; }

		public V getValue()	{ return value; }
		
		public void setKey(String key) { license = key; }
		
		public V setValue(V value) {
			
			this.value = value;
			return value;
		}
		
		public boolean equals(Object o)
		{
			if (o == null || !(o instanceof SmartAR.CarEntry))
				return false;

			CarEntry entry = (CarEntry)o;
			return (this.getKey() == entry.getKey());
		}
	}
	
	static int size;
	ArrayList<CarEntry> licensesArrayList;
	TreeMap licensesTree;
	Hashtable licensesHash;
	

	public SmartAR() {
		size = 0;
	}

	public SmartAR(String input) {

		Scanner sc1 = new Scanner(input);
		size = 0;
		while (sc1.hasNextLine()) {
		  String key = sc1.nextLine();
		  size++;
		}
		sc1.close();
		
		setThreshold(size);
		
		Scanner sc2 = new Scanner(input);
		while (sc2.hasNextLine()) {
		  String key = sc2.nextLine();
		  add(key,null);
		}
		sc2.close();

	}
	
	public void setThreshold(int threshold) {

		if (threshold < 0)
			System.exit(0);

		else 
			if (0 <= threshold && threshold < 1000)
				useSequence();

			else if (1000<= threshold && threshold < 100000)
				useTree();
		
			else useHashTable();

	}

	private void useSequence() {

		licensesArrayList = new ArrayList<>();
		licensesTree = null;
		licensesHash = null;
		
	}

	private void useTree() {
		
		licensesTree = new TreeMap<>();
		licensesArrayList = null;
		licensesHash = null;
	}
	
	private void useHashTable() {
		
		licensesHash = new Hashtable<>();
		licensesArrayList = null;
		licensesTree = null;
	}

	public int setKeyLength(int length) {
		
		
	}

	public void generate(int n) {

		//creates n new keys

	}

	public Object[] allKeys() {

		if (licensesArrayList != null) {
			
			Object[] licensesArray = licensesArrayList.toArray();
			Arrays.sort(licensesArray);
			return licensesArray;
		}
		
		if (licensesTree != null) return (licensesTree.keySet().toArray());
		
		if (licensesHash != null) {
			
			Object[] keys = licensesHash.keySet().toArray();
			Arrays.sort(keys);
			return keys;
		}

	}
	
	public void add(String key, E value) {
	
		CarEntry ce = new CarEntry(key,value);
		
		if (licensesArrayList != null) licensesArrayList.add(ce);
		if (licensesTree != null) licensesTree.put(key,value);
		if (licensesHash != null) licensesHash.put(key, value);
	}

	public void remove(String key) {
	
		if (licensesArrayList != null) licensesArrayList.remove(key);
		if (licensesTree != null) licensesTree.remove(key);
		if (licensesHash != null) licensesHash.remove(key);
	}
	
	public void getValues(String key) {
		
		if (licensesArrayList != null) getValuesAL(key);
		if (licensesTree != null) getValuesTM(key);
		if (licensesHash != null) getValuesHT(key);
	}
	
	private void getValuesAL(String key) {
		
		for (CarEntry ce: licensesArrayList)
		{
			if(ce.getKey() == key)
				System.out.println(ce.getValue());
		}
	}
	
	private void getValuesTM(String key) {

		licensesTree.get(key);
	}

	private void getValuesHT(String key) {

		licensesHash.get(key);
	}

	public String nextKey(String key) {
		
		if (licensesArrayList != null) 
			return nextKeyAL(key);
		if (licensesTree != null) 
			return nextKeyTM(key);
		if (licensesHash != null) 
			return nextKeyHT(key);
		else return null;
		
	}
	
	public String nextKeyAL(String key) {
		
		for(CarEntry ce: licensesArrayList)
		{
			if(ce.getKey().equals(key))
				return (String)(licensesArrayList.get(licensesArrayList.indexOf(ce) + 1).getKey());
		}
		return null;
	}
	
	public String nextKeyTM(String key) {
		
		Set<String> keys = licensesTree.keySet();
		 
	    Iterator<String> itr = keys.iterator();
	 
	    if (itr.hasNext())
	       return (itr.next());	
	    
	    return null;
	}
	
	public String nextKeyHT(String key) {
		
		Set<String> keys = licensesHash.keySet();
		 
	    Iterator<String> itr = keys.iterator();
	 
	    if (itr.hasNext())
	       return (itr.next());	
	    
	    return null;
	}

	public String prevKey(String key) {
		
		if (licensesArrayList != null) 
			return prevKeyAL(key);
		if (licensesTree != null) 
			return prevKeyTM(key);
		if (licensesHash != null) 
			return prevKeyHT(key);
		else return null;
	}
	
	public String prevKeyAL(String key) {
		
		for(CarEntry ce: licensesArrayList)
		{
			if(ce.getKey().equals(key))
				return (String)(licensesArrayList.get(licensesArrayList.indexOf(ce) - 1).getKey());
		}
		return null;
	}
	
	public String prevKeyTM(String key) {
		
	}
	
	public String prevKeyHT(String key) {
		
	}

	public CarEntry[] previousCars(String key) {
		
		for(CarEntry ce: licensesArrayList)
		{
			if(ce.getKey().equals(key))
				
		}
	}
}
