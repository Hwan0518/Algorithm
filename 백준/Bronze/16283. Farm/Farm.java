
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int[] input = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int a = input[0];
		int b = input[1];
		int n = input[2];
		int w = input[3];
		
		// find x,y - (1) a==b
		if (a==b) {
			if (n==2 && a*n==w) {
				sb.append(1).append(" ").append(1);
			} else {
				sb.append(-1);
			}
			System.out.print(sb);
			return;
		}
		
		// find x,y - (2) a!=b
		int x = (w-b*n)/(a-b);
		int y = n-x;
		if (x+y!=n || a*x+b*y!=w) {
			sb.append(-1);
			System.out.print(sb);
			return;
		}
		
		// result
		if (x<1 || y<1) {
			sb.append(-1);
		} else {
			sb.append(x).append(" ").append(y);
		}
		System.out.print(sb);
		
    }
	
}