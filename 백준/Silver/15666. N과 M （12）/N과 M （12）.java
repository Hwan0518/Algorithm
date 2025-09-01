
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int m;
	static int[] arr;
	static int[] select;
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		
		// dfs
		select = new int[m];
		dfs(0, 0);
		
		// result
		System.out.print(sb);
	}
	
	
	
	static void dfs(int size, int cur) {
		
		if (size == m) {
			for (int i=0; i<m; i++) sb.append(select[i]).append(" ");
			sb.append("\n");
			return;
		}
		
		int pre = -1;
		for (int i=cur; i<n; i++) {
			if (arr[i] == pre) continue;
			pre = arr[i];
			select[size] = arr[i];
			dfs(size+1, i);
		}
		
	}
	
	
}