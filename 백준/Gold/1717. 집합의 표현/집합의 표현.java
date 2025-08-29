
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n;
	static int m;
	static int[] parent;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		parent = new int[n+1];
		for (int i=0; i<n+1; i++) parent[i] = i;
		
		// command
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<m; i++) {
			
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (command == 0) union(a, b);
			else sb.append(find(a)==find(b) ? "YES" : "NO").append("\n");
			
		}
		
		// result
		System.out.print(sb);
	}
	
	
	
	static void union(int a, int b) {
		
		int pA = find(a);
		int pB = find(b);
		
		if (pA <= pB) parent[pB] = pA;
		else parent[pA] = pB;
		
	}
	
	
	static int find(int node) {
		
		if (parent[node] == node) return parent[node];
		else return parent[node] = find(parent[node]);
		
	}
	
}