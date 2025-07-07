
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		// find (a,b,c)
		int result = 0;
		for (int c=2; c<n; c+=2) {
			for (int a=3; a<n-c; a++) {
				int b = n-c-a;
				// condition
				if (a+b+c==n && a>0 && b>0 && c>0 && a-b>=2) {
					result ++;
				}
			}
		}
		
		// result
		System.out.print(result);
    }
	
}