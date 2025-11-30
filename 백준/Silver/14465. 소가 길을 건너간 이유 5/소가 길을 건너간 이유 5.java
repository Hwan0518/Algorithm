import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	
	
	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		int[] arr = new int[n];

		for (int i=0; i<b; i++) arr[Integer.parseInt(br.readLine())-1] = -1;

		// find min cnt
		int minCnt = twoPointer(arr, n, k);

		// result
		System.out.print(minCnt);
	}
	
	
	
	
	static int twoPointer(int[] arr, int n, int k) {
		int p1 = 0;
		int p2 = 0;
		boolean[] visited = new boolean[n];

		int minCnt = Integer.MAX_VALUE;
		int curCnt = 0;
		int normal = 0;

		while (p2 < n) {

			if (!visited[p2]) {
				visited[p2] = true;

				if (arr[p2] == -1) curCnt++;
				normal++;
			}

			// k개 이상이면 p1 증가
			if (normal >= k) {

				// update cnt
				minCnt = Integer.min(minCnt, curCnt);

				// decrease cnt
				if (arr[p1] == -1) curCnt --;
				normal --;

				// p1 증가 (p1을 제외시키는 부분임)
				if (p1 < p2) {
					p1 ++;
					visited[p1] = false;
				}
				else p2 ++;
			}

			// k개 미만이면 p2 증가
			else p2 ++;
		}
		
		return minCnt;
	}



}
