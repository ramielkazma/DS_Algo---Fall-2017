import java.util.Arrays;
import java.util.Stack;

public class RightMagneticStack {

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

	static boolean solveBoolean(int[] integers, int index)
	{
		Stack stack = new Stack();

		stack.push(index);
		int path = integers[index];

		while (!stack.isEmpty())
		{	
			index=(int)stack.pop();

			//if the index was a value that cannot be moved by (ie. we cannot move 3 places to the left 
			//if we are at the 1st index) the index has to be changed
			while (index > (integers.length-1) || index < 0)
			{
				index = (int)stack.pop();
				if(stack.isEmpty())
					return false;
			}

			path = integers[index];

			//if we have reached the element "0"
			if (path == 0)
				return true;

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

			//mark that we have accessed the element
			integers[index] = -1;
			
			//push both possible ways of movement
			stack.push(index-path); //left						
			stack.push(index+path); //right
		}

		return false;
	}

	static void solve(int integers[]) {

		//copies the array so that the input array is not changed
		int integersCopy[] = Arrays.copyOf(integers, integers.length);

		//starts the game at index 0
		if(!solveBoolean(integersCopy, 0))
			System.out.println("No solution is possible for: ");

		else System.out.println("Solution is possible for: ");

		printNumbers(integers);
	}

}
