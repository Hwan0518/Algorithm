
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int m;
	static int[] arr;
	static int[] cur;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());		
		
		// dfs
		Arrays.sort(arr);
		cur = new int[m+1];
		dfs(0);
		
		// result
		System.out.print(sb);
	}
	
	
	
	static void dfs(int size) {
		
		if (size == m) {
			for (int i=0; i<m; i++) sb.append(cur[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		int pre = -1;
		for (int i=0; i<n; i++) {
			
			if (arr[i] == pre) continue;
			pre = arr[i];
			
			cur[size] = arr[i];
			dfs(size+1);
			
		}
	}
	
	
}