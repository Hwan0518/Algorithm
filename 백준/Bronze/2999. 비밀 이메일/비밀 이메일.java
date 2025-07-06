
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String input = br.readLine();
		
		// calc r,c
		int n = input.length();
		int r = 1;
		int c = n;
		int temp = 1;
		while (temp < c) {
			if (n%temp == 0) {
				if (temp <= n/temp) {
					r = temp;
					c = n/temp;
				} else {
					temp --;
					break;
				}
			}
			temp ++;
		}
		
		// get result
		StringBuffer sb = new StringBuffer();
		int idx = 0;
		for (int i=0; i<r; i++) {
			for (int j=0; j<c; j++) {
				sb.append(input.charAt(idx+r*j));
			}
			idx++;
		}
		System.out.println(sb);
		
    }
	
}