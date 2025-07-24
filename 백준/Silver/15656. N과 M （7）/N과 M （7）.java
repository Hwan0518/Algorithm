
import java.io.*;
import java.util.*;


public class Main {
	
	static int n;
	static int m;
	static int[] arr;
	static int[] selected;
	static StringBuilder sb;
	static StringTokenizer st; 
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		
		// Backtracking
		selected = new int[n+1];
		sb = new StringBuilder();
		dfs(0);
		
		// result
		sb.setLength(sb.length()-1);
		System.out.print(sb);
		
	}
	
	
	// backtracking dfs
	public static void dfs(int cnt) {
		if (cnt == m) {
			for (int i=1; i<=m; i++) {
				sb.append(selected[i]).append(" ");
			}
			sb.append("\n");
			return;
		}
		for (int i=0; i<n; i++) {
			int cur = arr[i];
			selected[cnt+1] = cur;
			dfs(cnt+1);
		}
	}
		
}