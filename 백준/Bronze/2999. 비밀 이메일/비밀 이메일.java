
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		// calc r,c
		int n = input.length();
		int limit = (int) Math.sqrt(n);
		int r = 1;
		int c = n;
		for (int temp = limit; temp>0; temp--) {
			if (n%temp ==0) {
				r = temp;
				c = n/temp;
				break;
			}
		}
		
		// get result
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				sb.append(input.charAt(i + r*j));
			}
		}
		System.out.println(sb);
		
    }
	
}