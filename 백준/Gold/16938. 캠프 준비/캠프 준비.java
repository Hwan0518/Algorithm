
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static int n;
	static int l;
	static int r;
	static int x;
	static int[] arr;
	static int cnt = 0;
	static boolean[] select;
	
	
	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		x = Integer.parseInt(st.nextToken());
				
		st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i=0; i<n; i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);
		
		// dfs
		select = new boolean[n];
		dfs(0);
		
		// result
		System.out.print(cnt);
	}
	
	
	
	
	static void dfs(int size) {
		
		if (size == n) {
			
			boolean first = true;
			int min = 0;
			int max = 0;
			int sum = 0;
			
			for (int i=0; i<n; i++) {
				
				if (select[i]) {
					
					if (first) {
						min = arr[i];
						first = false;
					}
					max = arr[i];
					sum += arr[i];	
				}
			}
			
			if (l<=sum && sum<=r && max-min>=x) cnt++;
			
			return;
		}
		
		// non select
		dfs(size+1); 
		// select
		select[size] = true; 
		dfs(size+1);
		select[size] = false;
		
	}
	
}