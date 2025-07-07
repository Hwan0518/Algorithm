
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// find (a,b)
		int result = 0;
		for (int b=1; b<=500; b++) {
			int b_sqr = (int) Math.pow(b, 2); 
			int a = (int) Math.sqrt(b_sqr + n);
			if (a>500) {
				break;
			}
			if (Math.pow(a, 2) == b_sqr +n) {
				result ++;
			}
		}
		
		// result
		System.out.print(result);
		
    }
	
}