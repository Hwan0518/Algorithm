import java.io.*;
import java.util.*;

/**
 * arr 사용하자
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String w1 = br.readLine();
		String w2 = br.readLine();
		boolean isSame = true;
		int cnt = 1;
		while (!w1.equals("END") || !w2.equals("END")) {
			sb.append("Case ").append(cnt).append(": ");
			int[] arr = new int[26];
			// diff length
			if (w1.length() != w2.length()) {
				sb.append("different\n");
				isSame = false;
			} 
			// same length
			else {
				for (int i=0; i<w1.length(); i++) {
					arr[w1.charAt(i) - 'a'] ++;
					arr[w2.charAt(i) - 'a'] --;
				}
				for (int v:arr) {
					if (v != 0) {
						sb.append("different\n");
						isSame = false;
						break;
					}
				}
			}
			sb.append(isSame ? "same\n" : "");
			// input again
			w1 = br.readLine();
			w2 = br.readLine();
			isSame = true;
			cnt++;
		}
		System.out.print(sb);
	}
}