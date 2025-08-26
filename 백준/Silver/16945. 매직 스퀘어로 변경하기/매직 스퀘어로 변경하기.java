
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int[][] arr = new int[3][3];
	static int minV = Integer.MAX_VALUE;
	static boolean[] visited = new boolean[10];
	static int[][] cur = new int[3][3];
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i=0; i<3; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}
		
		// back-tracking
		dfs(0);
		
		// result
		System.out.print(minV);
		
	}
	
	
	
	static void dfs(int size) {
		
		if (size == 9) {
			if (magic()) minV = Math.min(minV, calc());
			return;
		}
		
		for (int i=1; i<=9; i++) {
			
			if (visited[i]) continue;
			visited[i] = true;
			cur[size/3][size%3] = i;
			dfs(size+1);
			visited[i] = false;
			
		}
		
	}
	
	
	
	
	static boolean magic() {
		
		int std = cur[0][0] + cur[0][1] + cur[0][2]; 
		
		// row
		for (int i=0; i<3; i++) {
			int nr = cur[i][0] + cur[i][1] + cur[i][2];
			if (std != nr) return false;
		}
		
		// column
		for (int i=0; i<3; i++) {
			int nc = cur[0][i] + cur[1][i] + cur[2][i];
			if (std != nc) return false;
		}
		
		// diagonal
		if (std != cur[0][0] + cur[1][1] + cur[2][2]) return false;
		if (std != cur[0][2] + cur[1][1] + cur[2][0]) return false;
		
		// all same
		return true;
		
	}
	
	
	
	
	static int calc() {
		
		int sum = 0;
		
		for (int i=0; i<3; i++) {
			for (int j=0; j<3; j++) sum += Math.abs(arr[i][j] - cur[i][j]);
		}
		
		return sum;
		
	}
	
}