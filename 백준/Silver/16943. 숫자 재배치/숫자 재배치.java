
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static String[] aString;
	static int b;
	static int max;
	static int limit;
	static String[] temp;
	static boolean[] visit;
	
	
	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		aString = st.nextToken().split("");
		b = Integer.parseInt(st.nextToken());
		limit = aString.length;
		
		// dfs
		max = -1;
		temp = new String[limit];
		visit = new boolean[limit];
		dfs(0);
		
		// result
		System.out.print(max);
	
	}
	
	static void dfs(int size) {
		if (size == limit) {
			StringBuilder sb = new StringBuilder();
			for (String t:temp) {
				sb.append(t);
			}
			int tempInt = Integer.parseInt(sb.toString());
			if (tempInt < b && tempInt > max) {
				max = tempInt;
			}
			return;
		}
		for (int i=0; i<limit; i++) {
			if (visit[i]) continue;
			if (size == 0 && aString[i].equals("0")) continue;
			visit[i] = true;
			temp[size] = aString[i];
			dfs(size+1);
			visit[i] = false;
		}
		
	}
	
}