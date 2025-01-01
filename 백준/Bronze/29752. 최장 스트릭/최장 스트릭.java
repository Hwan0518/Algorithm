import java.io.*;
import java.util.Arrays;

/**
 * 
 */
public class Main {	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] pCnt = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int maxCnt = 0;
		int cur = 0;
		for (int c:pCnt) {
			if (c==0) {
				maxCnt = Math.max(maxCnt, cur);
				cur = 0;
			} else {
				cur ++;
			}
		}
		maxCnt = Math.max(maxCnt, cur);
		System.out.print(maxCnt);
	}
	
}