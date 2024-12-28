import java.io.*;

/**
 * condition
 * - 완탐
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] w = br.readLine().toCharArray();
		int[] cnt = new int[26];
		for (char a:w) {
			if (a>=97) {
				a -=97;
			} else {
				a -=65;
			}
			cnt[a] ++;
		}
		int maxV = 0;
		char max = 0;
		int maxCnt = 0;
		for (int i=0; i<26; i++) {
			int v = cnt[i];
			if (v > maxV) {
				max = (char) (i+65);
				maxV = v;
				maxCnt = 1;
			} else if (v == maxV) {
				maxCnt ++;
			}
		}
		System.out.println(maxCnt==1 ? max : "?");
	}
	
}