import java.io.*;
import java.util.*;

/**
 * 구현,문자열
 * 
 * approach
 * - 주어진 문자열과 모든 알파벳을 비교해봐야함
 * 	- 틀린 개수를 카운팅해야함
 * - 6단위마다 문자위치 i 증가
 */

public class Main {
	
	static String[] A = new String[] {"0","0","0","0","0","0"};
	static String[] B = new String[] {"0","0","1","1","1","1"};
	static String[] C = new String[] {"0","1","0","0","1","1"};
	static String[] D = new String[] {"0","1","1","1","0","0"};
	static String[] E = new String[] {"1","0","0","1","1","0"};
	static String[] F = new String[] {"1","0","1","0","0","1"};
	static String[] G = new String[] {"1","1","0","1","0","1"};
	static String[] H = new String[] {"1","1","1","0","1","0"};
	static String[][] lst = new String[][] {A,B,C,D,E,F,G,H};
	static String[] lstName = new String[] {"A","B","C","D","E","F","G","H"};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		// input
		int n = Integer.parseInt(br.readLine());
		char[] s = br.readLine().toCharArray();
		// i번째 문자
		for (int i=0; i<n; i++) {
			int[] diff = new int[8];
			// 숫자 비교
			for (int j=0; j<6; j++) {
				// compare
				for (int k=0; k<8; k++) {
					if (!lst[k][j].equals(Character.toString(s[(i*6)+j]))) {
						diff[k] ++;
					}
				}
			}
			// check diff
			boolean dontKnow = true;
			for (int k=0; k<8; k++) {
				if (diff[k] <= 1) {
					sb.append(lstName[k]);
					dontKnow = false;
					break;
				}
			}
			if (dontKnow) {
				System.out.print(i+1);
				return;
			}
		}
		System.out.print(sb);
	}
		
}