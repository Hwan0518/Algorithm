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
		while (n != 0) {
			String[] arr = new String[n];
			String[] lowerArr = new String[n];
			for (int i=0; i<n; i++) {
				String s = br.readLine(); 
				arr[i] = s;
				lowerArr[i] = s.toLowerCase();
			}
			Arrays.sort(lowerArr);
			// answer
			for (String s:arr) {
				if (s.toLowerCase().equals(lowerArr[0])) {
					sb.append(s + "\n");
				}
			}
			n = Integer.parseInt(br.readLine());
		}
		System.out.print(sb);
		br.close();
	}
	
}