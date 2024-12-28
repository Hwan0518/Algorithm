import java.io.*;

/**
 * condition
 * - 완탐
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] cnt = new int[26];
		for (char a:br.readLine().toCharArray()) {
			if (a>=97) {
				a -=97;
			} else {
				a -=65;
			}
			cnt[a] ++;
		}
		int maxV = 0;
		char max = 0;
		for (int i=0; i<26; i++) {
			int v = cnt[i];
			if (v > maxV) {
				max = (char) (i+65);
				maxV = v;
			} else if (v == maxV) {
				max = '?';
			}
		}
		System.out.println(max);
	}
	
}