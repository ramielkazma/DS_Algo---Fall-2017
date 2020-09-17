import java.util.Arrays;

public class RightMagneticRecursive {

	public static void main(String[] args) {

		//given
		int solution[] = {8,16,10,4,6,10,2,12,8,0};
		solve(solution);

		int noSolution[] = {10,16,4,6,2,10,0};
		solve(noSolution);

		for (int i=1;i<=20;i++) //20 random tries
		{
			int integers[] = new int[(int)(Math.random() * 9) + 2]; //randomizes array size from 2 to 10

			//fills up array from first element till element before last
			//final element has to be 0
			for(int j=0;j<integers.length-1;j++)
				integers[j] = (int)(Math.random() * 14) + 1;
			integers[integers.length-1] = 0;

			solve(integers);
		}

		System.out.println("--------------THE END--------------");

	}

	static void printNumbers(int integers[]) {

		for (int i=0;i<integers.length;i++)
			System.out.print(integers[i]+"  ");
		System.out.println("\n");
	}

	//We will check the current element at the index in the parameter and this method returns true if 
	//we can reach the end by accessing current element. We will recursively call this method, and 
	//while making the next call, we will make the current element equal to -1, so that we know we have 
	//accessed this element earlier and doesn't get stuck in infinite loop.
	static boolean solveBoolean(int integers[], int index) {

		//we have reached the index of "0" which ends the game successfully
		if(index == integers.length-1)
			return true;	

		int path = integers[index];

		//if this element has been accessed then there is no solution 
		//as it will repeat the same path infinitely
		if(path == -1)
			return false;

		//if path is even
		if(path % 2 == 0)
			path /= 2;

		//if path is odd
		else
			path = 1 + path/2;

		//create a copy of the array so that modifications are not done permanently on the input array
		//set value of "-1" at index of a number that is access in the copy array to identify it as an
		//accessed number
		int integersCopy[] = Arrays.copyOf(integers, integers.length);
		integersCopy[index] = -1;

		//if there are enough to indices to move right
		if((index+path) <= (integers.length-1))
			if(solveBoolean(integersCopy, index+path))
				return true;

		//else if there are enough indices to move left
		if((index-path) >= 0)
			if(solveBoolean(integersCopy, index-path)) 
				return true;

		//else there are no more moves and the game has no solution
		return false;
	}

	static void solve(int integers[]) {

		//starts the game at index 0
		if(!solveBoolean(integers, 0))
			System.out.println("No solution is possible for: ");

		else System.out.println("Solution is possible for: ");

		printNumbers(integers);
	}

}
