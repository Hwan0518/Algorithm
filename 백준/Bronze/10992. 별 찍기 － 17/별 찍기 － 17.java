import java.io.*;
import java.util.*;

/**
 * 
 */
public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		for (int i=1; i<n; i++) {
			sb.append(" ".repeat(n-i))
			.append("*")
			.append(i==1 ? "" : " ".repeat(2*(i-1)-1))
			.append(i==1 ? "\n" : "*\n");
		}
		sb.append("*".repeat(n*2-1));
		System.out.print(sb);
		br.close();
	}
	
}