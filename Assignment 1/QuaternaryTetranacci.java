import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class QuaternaryTetranacci {

	public static void main(String[] args) throws FileNotFoundException {

		PrintWriter pw;
		pw = new PrintWriter(new FileOutputStream("out.txt"));
		
		pw.println("Tetranacci Number:\tTime:");
		
		for(int i=0; i<=35;i+=5)
		{
			//i is input integer for corresponding tetranacci number
		
			long time1 = System.currentTimeMillis(); //returns 
			long tetranacci = tetTetranacci(i);
			long time2 = System.currentTimeMillis();
			
			pw.println("\n"+tetranacci+"\t\t\t"+(time2-time1)+" milliseconds");
		
			System.out.println(tetranacci);
			System.out.println(+(time2-time1)+" milliseconds"+"\n"); //the difference resulting in the time it
																//takes to find the tetranacci number  
		}
		pw.close();
	}
	
	public static long tetTetranacci(long n) {
		
		if(n==0 || n==1 || n==2) //since tetranacci is a series of 4 numbers, the first 3 are 0
			return 0; //since tetranacci is a series of 4 numbers, the first 3 are 0
		else if (n==3)
			return 1; //the fourth element is 1
		else
			return tetTetranacci(n-1) + tetTetranacci(n-2) + tetTetranacci(n-3) + tetTetranacci(n-4);
	}

}