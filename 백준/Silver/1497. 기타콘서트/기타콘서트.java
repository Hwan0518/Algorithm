
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n, m;
	static boolean[][] arr;
	static int answer = Integer.MAX_VALUE;
	static int maxCnt = 0;
	static int[] possible;
	
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new boolean[n][m];
		
		for (int i=0; i<n; i++) {
			String[] input = br.readLine().split(" ")[1].split("");
			
			for (int j=0; j<m; j++) {
				String s = input[j];
				if (s.equals("Y")) arr[i][j] = true;
			}
		}
		
		// dfs
		possible = new int[m];
		dfs(0, 0, 0);
		
		// result
		System.out.print(answer == 0 ? -1 : answer);
		
	}
	
	
	
	static void dfs(int size, int pCnt, int gCnt) {
				
		if (size == n) {
			if (pCnt >= maxCnt) {
				
				if (pCnt == maxCnt) answer = Math.min(answer, gCnt);
				else answer = gCnt;
				
				maxCnt = pCnt;
			}
			
			return;
		}
			
		// 미선택
		dfs(size+1, pCnt, gCnt);
		
		// 선택
		boolean[] select = new boolean[m];
		
		for (int j=0; j<m; j++) {
				
			if (arr[size][j]) { // 연주 가능한 경우
				
				// 첫 번째 연주인 경우
				if (possible[j] == 0) {
					select[j] = true;
					pCnt++; 
				}
				
				possible[j] ++; // 연주 가능하다면 선택했음
			}
		}
		
		dfs(size+1, pCnt, gCnt+1);
		
		// reset
		for (int j=0; j<m; j++) {
			
			if (arr[size][j]) possible[j] --;
			if (select[j]) pCnt--;
		}
		
	}
	
	
}