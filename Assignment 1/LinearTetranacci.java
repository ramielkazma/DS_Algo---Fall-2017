import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class LinearTetranacci {

	private static long counter = 2; //counts the calls of the linTetranacci method
	
	public static void main(String[] args) throws FileNotFoundException {
		
		PrintWriter pw;
		pw = new PrintWriter(new FileOutputStream("out.txt",true));
		pw.println();
		
		for(int i=0; i<=40;i+=5)
		{
			//i is input integer for corresponding tetranacci number
		
			long time1 = System.currentTimeMillis(); //returns 
			long[] tetranacci = linTetranacci(i);
			long time2 = System.currentTimeMillis();
			
			pw.println("\n"+tetranacci[0]+"\t\t\t"+(time2-time1)+" milliseconds");
		
			System.out.println(tetranacci[0]);
			System.out.println((time2-time1)+" milliseconds"+"\n"); //the difference resulting in the time it
																//takes to find the tetranacci number  
		}
		pw.close();
		

	}

	public static long[] linTetranacci(long n) {
		
		counter++; //increments with every method call
		long[] myArray = new long[4]; //series of 4 numbers
		long i=0; long j=0; long k=0; long l=0;
		
		if (n==0 || n==1 || n==2) //since it is a series of 4 numbers, the first 3 elements are 0
		{
			myArray[0]=i; myArray[1]=j; myArray[2]=k; myArray[3]=l;
			return (myArray);
		}
		
		else if (n==3) //4th element is 0
		{
			i=1;
			myArray[0]=i; myArray[1]=j; myArray[2]=k; myArray[3]=l;
			return (myArray);
		}
		
		else
		{
			myArray = linTetranacci(n-1);
			i=myArray[0]; j=myArray[1]; k=myArray[2]; l=myArray[3];
			myArray[3]=myArray[2]; //set the array so that the first element is the highest
			myArray[2]=myArray[1]; //and the last element is the lowest
			myArray[1]=myArray[0];
			myArray[0]=i+j+k+l;
			
			if(counter == n) //print the tetranacci number corresponding 
			{
				System.out.print(myArray[0]+" ");
			}
			return myArray;
		}
		
		
		
	}
	
}
