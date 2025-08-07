
import java.io.*;
import java.util.*;


public class Main {

	static int n;
	static boolean[] visit;
	static int[] arr;
	static int[] curArr;
	static int max;
	
	
	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// dfs
		visit = new boolean[n];
		curArr = new int[n];
		max = 0;
		dfs(0);
		
		// result
		System.out.print(max);
	}
	
	static void dfs(int size) {
		if (size == n) {
			max = Math.max(max, calc());
			return;
		}
		for (int i=0; i<n; i++) {
			if (visit[i]) continue;
			visit[i] = true;
			curArr[size] = arr[i];
			dfs(size+1);
			visit[i] = false;
		}
	}
	
	static int calc() {
		int curV = 0;
		for (int i=0; i<n-1; i++) {
			curV += Math.abs(curArr[i] - curArr[i+1]);
		}
		return curV;
	}
		
}