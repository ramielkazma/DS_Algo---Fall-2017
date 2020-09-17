import java.util.Arrays;
import java.util.Collections;

public class SortTester {

	public static void main(String[] args) {
		Tree <Number> tree = new A3BSTree<>();
		Number a [] = new Number[10];

		for (int i = 0; i < a.length; i++) a[i] = i;
		System.out.printf("Initial%n%s%n",Arrays.toString(a));
		
		Collections.shuffle(Arrays.asList(a));
		System.out.printf("Shuffled%n%s%n",Arrays.toString(a));

		TreeSort.sort(tree, a);
		TreeSort.sort(a);
		System.out.printf("Sorted%n%s%n",Arrays.toString(a));
	}

}
