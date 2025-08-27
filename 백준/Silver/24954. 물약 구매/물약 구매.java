
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n;
	static int[] cost;
	static int[][] discount;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		n = Integer.parseInt(br.readLine());
		
		cost = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<n+1; i++) cost[i] = Integer.parseInt(st.nextToken());
		
		discount = new int[n+1][n+1];
		for (int i=1; i<n+1; i++) {
			
			int cnt = Integer.parseInt(br.readLine());
			
			for (int j=0; j<cnt; j++) {
				st = new StringTokenizer(br.readLine());
				int potion = Integer.parseInt(st.nextToken());
				int amount = Integer.parseInt(st.nextToken());
				discount[i][potion] = amount;
			}
		}
		
		// dfs
		visited = new boolean[n+1];
		dfs(1, 0);
		
		// result
		System.out.print(min);
	
	}
	
	
	
	static void dfs(int size, int cnt) {
		
		if (size == n+1) {

			min = Math.min(min, cnt);			
			return;
			
		}
		
		for (int i=1; i<n+1; i++) {
			
			if (visited[i]) continue;
			visited[i] = true;
			
			// discount
			int[] temp = new int[n+1];
			for (int j=1; j<n+1; j++) {
				
				int d = discount[i][j];
				if (d != 0) {
					temp[j] = cost[j];
					cost[j] = Math.max(cost[j]-d, 1);
				}
			}
			
			dfs(size+1, cnt+cost[i]);
			
			// rollback
			for (int j=1; j<n+1; j++) {
				if (temp[j] > 0) cost[j] = temp[j];
			}
			
			visited[i] = false;
		}
		
	}
		
	
	
}