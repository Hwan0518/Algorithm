import java.io.*;

/**
 * 문자열 구
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// n
		int n = Integer.parseInt(br.readLine());
		// input
		for (int i=0; i<n; i++) {
			int[] alphabet = new int[26];
			// s
			for (char c:br.readLine().toCharArray()) {
				if (65<=c && c<=90) {
					alphabet[c-65] ++;
				} else if (97<=c && c<=122) {
					alphabet[c-97] ++;
				}
			}
			// check
			StringBuilder sb = new StringBuilder();
			sb.append("missing ");
			for (int j=0; j<26; j++) {
				if (alphabet[j] == 0) {
					sb.append((char) (j+97));
				}
			}
			// result
			System.out.println(sb.length() == 8 ? "pangram" : sb);
		}
	}
	
}