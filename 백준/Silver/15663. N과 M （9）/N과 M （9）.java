
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
	static Set<String> dupl = new HashSet<>();
	
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
		dfs(0);
		
		
		// result
		answer.setLength(answer.length() -1);
		System.out.print(answer);
		
	}
	
	
	
	static void dfs(int size) {
		
		if (size == m) {
			
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<m; i++) sb.append(cur[i]).append(" ");
			
			if (!dupl.contains(sb.toString())) answer.append(sb).append("\n");

			dupl.add(sb.toString());
			
			return;
			
		}
		
		for (int i=0; i<n; i++) {
			
			if (visited[i]) continue;
			visited[i] = true;
			
			cur[size] = arr[i];
			dfs(size+1);
			
			visited[i] = false;
			
		}
		
	}
		
	
	
}