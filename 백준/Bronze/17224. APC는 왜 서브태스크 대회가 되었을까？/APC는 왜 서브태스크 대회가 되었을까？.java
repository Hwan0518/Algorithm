import java.io.*;
import java.util.*;

/**
 * 그리디
 * - 어려운 버전부터 점수를 카운팅하자
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] nlk = getIntArr(br.readLine().split(" "));
		int n = nlk[0];
		int l = nlk[1]; // l점수 이하만 풀 수 있음
		int k = nlk[2]; // 최대 k개 풀 수 있음
		int[][] score = new int[n][2];
		for (int i=0; i<n; i++) {
			score[i] = getIntArr(br.readLine().split(" ")); 
		}
		// searching
		boolean difficult = true;
		int i = 0;
		int ans = 0;
		boolean[] visited = new boolean[n];
		while (k>0) {
			if (!visited[i]) {
				if (difficult && score[i][1] <= l) {
					ans += 140;
					k --;
					visited[i] = true;
				} else if(!difficult && score[i][0] <=l) {
					ans += 100;
					k --;
					visited[i] = true;
				}
			}
			i ++;
			if (i==n) {
				if (difficult) {
					difficult = false;
					i = 0;
				}
				else {
					break;
				}
			}
		}
		System.out.print(ans);
	}
	
	
	public static int[] getIntArr(String[] arr) {
		return Arrays.stream(arr)
				.mapToInt(Integer::parseInt)
				.toArray();
	}
		
}