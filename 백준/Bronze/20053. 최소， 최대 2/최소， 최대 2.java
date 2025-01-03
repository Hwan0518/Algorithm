import java.io.*;
import java.util.*;

/**
 * 
 */
public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<n; i++) {
			int m = Integer.parseInt(br.readLine());
			int min = Integer.MAX_VALUE; 
			int max = -Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j=0; j<m; j++) {
				int num = Integer.parseInt(st.nextToken());
				min = Math.min(min, num);
				max = Math.max(max, num);
			}
			sb.append(min).append(" ").append(max).append("\n");
		}
		System.out.print(sb);
	}
	
}