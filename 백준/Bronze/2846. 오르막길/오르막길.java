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
		int max = 0;
		int stt = Integer.parseInt(st.nextToken());
		int last = stt;
		for (int i=0; i<n-1; i++) {
			int cur = Integer.parseInt(st.nextToken());
			if (cur > last) {
				last = cur;
			} else {
				max = Math.max(max, last-stt);
				stt = cur;
				last = cur;
			}
		}
		max = Math.max(max, last-stt);
		System.out.print(max);
	}
	
}