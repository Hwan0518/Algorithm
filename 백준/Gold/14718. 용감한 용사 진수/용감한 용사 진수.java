
import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws IOException {
        
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[][] arr = new int[n][3];
		int[] arr_s = new int[n];
		int[] arr_d = new int[n];
		int[] arr_i = new int[n];
		for (int idx=0; idx<n; idx++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int i = Integer.parseInt(st.nextToken());
			arr[idx][0] = s;
			arr[idx][1] = d;
			arr[idx][2] = i;
			arr_s[idx] = s;
			arr_d[idx] = d;
			arr_i[idx] = i;
		}
		
		// sort
		Arrays.sort(arr_s);
		Arrays.sort(arr_d);
		Arrays.sort(arr_i);
		
		// fin min stat
		int result = Integer.MAX_VALUE;
		for (int s : arr_s) {
			for (int d : arr_d) {
				for (int i : arr_i) {
					int cnt = 0;
					for (int[] stats : arr) {
						if (s >= stats[0] && d >= stats[1] && i >= stats[2]) {
							cnt ++;
						}
					}
					if (cnt == k) {
						int sum = s+d+i;
						result = Math.min(result, sum);
					}
				}
			}
		}
		
		// result
		System.out.print(result);
		
	}
		
}