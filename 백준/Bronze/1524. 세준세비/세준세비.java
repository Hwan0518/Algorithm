import java.io.*;
import java.util.*;

/**
 * 문자열 구현
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		for (int i=0; i<t; i++) {
			br.readLine();
			StringTokenizer st1 = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st1.nextToken());
			int m = Integer.parseInt(st1.nextToken());
			int[] s = getSoldier(br);
			int[] b = getSoldier(br);
			Arrays.sort(s);
			Arrays.sort(b);
			sb.append(isSejunWin(s, b) ? "S" : "B").append("\n");
		}
		System.out.print(sb);
	}
	
	
	public static int[] getSoldier(BufferedReader br) throws IOException {
		return Arrays.stream(br.readLine().split(" "))
		.mapToInt(Integer::parseInt)
		.toArray();
	}
	
	
	public static boolean isSejunWin(int[] s, int[] b) {
		int sIdx = 0, bIdx = 0;
		while (sIdx < s.length && bIdx < b.length) {
			if (s[sIdx] < b[bIdx]) {
				sIdx ++;
			} else {
				bIdx ++;
			}
		}
		return bIdx == b.length ? true : false;
	}
	
}