import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		int[][] arr = new int[n][3];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}

		// search

		int maxS = 0;
		int maxC = 0;
		int maxL = 0;
		int result = 0;

		for (int i=0; i<n; i++) {

			int curS = arr[i][0];
			int curC = arr[i][1];
			int curL = arr[i][2];

			if (curS > maxS) {
				maxS = curS;
				maxC = curC;
				maxL = curL;
				result = i;
			}

			else if (curS == maxS) {

				if (curC < maxC) {
					maxC = curC;
					maxL = curL;
					result = i;
				}

				else if (curC == maxC && curL < maxL) {
					maxL = curL;
					result = i;
				}
			}
		}

		// result
		System.out.print(result+1);
	}


}