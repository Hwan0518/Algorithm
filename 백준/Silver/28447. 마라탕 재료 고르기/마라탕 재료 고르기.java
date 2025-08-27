
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n,k;
	static boolean[] visited;
	static int[][] arr;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		visited = new boolean[n];
		arr = new int[n][n];
		
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<n; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// dfs
		dfs(0, 0);

		// result
		System.out.print(max);
		
	}
	
	
	
	static void dfs(int size, int cnt) {
	
		if (size == n || cnt == k) {
			if (cnt == k) max = Math.max(max, calc());
			return;
		}
		
		// 미선택
		dfs(size+1, cnt);
		
		// 선택
		visited[size] = true;
		dfs(size+1, cnt+1);
		visited[size] = false;
		
	}
	
	
	
	static int calc() {
		
		int cur = 0;
		
		for (int i=0; i<n-1; i++) {
			for (int j=i+1; j<n; j++) 
				
				if (visited[i] && visited[j]) cur += arr[i][j];
			
		}
		
		return cur;
	}
		
	
	
}