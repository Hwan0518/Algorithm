
import java.io.*;
import java.util.*;


public class Main {

	static StringTokenizer st;
	static int n;
	static int[][] paints;
	static int[] gom = new int[3];
	static int diff = Integer.MAX_VALUE;
	static int LIMIT;
	static int[] moon = new int[3];
	
	
	public static void main(String[] args) throws IOException {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		paints = new int[n][3];
		for (int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<3; j++) {
				paints[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<3; i++) {
			gom[i] = Integer.parseInt(st.nextToken());
		}
		
		// calc
		for (int limit=2; limit<=7; limit++) {
			LIMIT = limit;
			dfs(0, 0);
		}
		
		// result
		System.out.print(diff);
	}
	
	static void dfs(int size, int cur) {
		if (size == LIMIT) {
			int curDiff = 0;
			for (int i=0; i<3; i++) {
				curDiff += Math.abs(gom[i] - moon[i]/size);
			}
			diff = Math.min(diff, curDiff);	
			return;
		}
		for (int i=cur; i<n; i++) {
			for (int j=0; j<3; j++) {
				moon[j] += paints[i][j];
			}
			dfs(size+1, i+1);
			for (int j=0; j<3; j++) {
				moon[j] -= paints[i][j];
			}
		}
	}
	
	
}