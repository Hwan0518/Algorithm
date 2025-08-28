
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n;
	static int m;
	static boolean[] visited;
	static int[] arr;
	static StringBuilder answer = new StringBuilder();
	static int[] cur;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		visited = new boolean[n];
		
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
		
		
		// dfs
		Arrays.sort(arr);
		cur = new int[m];
		dfs(0, 0);
		
		
		// result
		answer.setLength(answer.length() -1);
		System.out.print(answer);
		
	}
	
	
	
	static void dfs(int size, int idx) {
		
		if (size == m) {
			
			for (int i=0; i<m; i++) answer.append(cur[i]).append(" ");
			answer.append("\n");
			
			return;
			
		}
		
		int dupl = -1;
		for (int i=idx; i<n; i++) {
			
			if (visited[i] || dupl == arr[i]) continue;
			visited[i] = true;
			dupl = arr[i];
			
			cur[size] = arr[i];
			dfs(size+1, i);
			
			visited[i] = false;
			
		}
		
	}
		
	
	
}