
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n,m,k;
	static boolean[] selected;
	static int[][] arr;
	static int max = Integer.MIN_VALUE;
	
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		selected = new boolean[2*n+1];
		
		arr = new int[m][k];
		for (int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<k; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());	
			}
		}
		
		// dfs
		dfs(1, 0);
		
		// result
		System.out.println(max);
		
	}
	
	
	static void dfs(int size, int cnt) {
		
		if (size == 2*n+1 || cnt == n) {
			
			int cur = 0;
			for (int i=0; i<m; i++) {
				
				boolean possible = true;
				
				for (int skill : arr[i]) {
					
					if (!selected[skill]) {
						possible = false;
						break;
					}
				}
				
				if (possible) cur ++; 
			}
			
			max = Math.max(max, cur);
			return;
		}
		
		// 미선택
		dfs(size+1, cnt);
		
		// 선택
		selected[size] = true;
		dfs(size+1, cnt+1);
		selected[size] = false;
	}
	
}