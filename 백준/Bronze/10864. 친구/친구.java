import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static int[] arr1, arr2;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr1 = new int[m];
		arr2 = new int[m];
		for (int i = 0; i < m; i++) {

			st = new StringTokenizer(br.readLine());
			arr1[i] = Integer.parseInt(st.nextToken());
			arr2[i] = Integer.parseInt(st.nextToken());
		}
		
		// search
		sb = new StringBuilder();
		for (int i=0; i<n; i++) {
			
			int student = i+1;
			int cnt = 0;
			
			for (int idx=0; idx<m; idx++) {
				if (arr1[idx] == student) cnt ++;
				if (arr2[idx] == student) cnt ++;
			}

			sb.append(cnt).append("\n");
		}

		// result
		System.out.print(sb);
	}

}
