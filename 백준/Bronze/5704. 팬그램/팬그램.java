import java.io.*;

/**
 * 
 */
public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] input = br.readLine().toCharArray();
		while (input[0] !='*') {
			int[] cnt = new int[26];
			boolean pangram = true;
			for (char c:input) {
				if (c>=97) cnt[c-97]++;
			}
			for (int count:cnt) {
				if (count == 0) {
					sb.append("N\n");
					pangram = false;
					break;
				}
			}
			if (pangram) sb.append("Y\n");
			input = br.readLine().toCharArray();
		}
		System.out.print(sb);
		br.close();
	}
	
}