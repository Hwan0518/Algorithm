
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] input = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int A = input[0];
		int B = input[1];

		// find x1,x2
		int temp = (int) Math.sqrt(Math.pow(A, 2) - B);
		int x_1 = (-1*A + temp);
		int x_2 = (-1*A - temp);
		
		// result
		if (x_1 == x_2) {
			System.out.print(x_1);
		} else {
			System.out.print(x_2 + " " + x_1);
		}
		
    }
	
}