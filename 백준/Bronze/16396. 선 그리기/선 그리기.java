import java.io.*;
import java.util.*;

/**
 * 구현
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		boolean line[] = new boolean[10001];
		// projection
		for (int i=0; i<n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int stt = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			for (int j=stt; j<end; j++) {
				line[j] = true;
			}
		}
		// searching
		int len = 0;
		for (int i=1; i<10001; i++) {
			if (line[i]) len++;
		}
		// ans
		System.out.print(len);
	}
}