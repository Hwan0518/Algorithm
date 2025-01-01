import java.io.*;
import java.util.*;

/**
 * 
 */
public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int maxCnt = 0;
		int cur = 0;
		for (int i=0; i<n; i++) {
			if (Integer.parseInt(st.nextToken()) != 0) {
				cur++;
				maxCnt = Math.max(maxCnt, cur);
			} else {
				cur = 0;
			}
		}	
		System.out.print(maxCnt);
	}
	
}