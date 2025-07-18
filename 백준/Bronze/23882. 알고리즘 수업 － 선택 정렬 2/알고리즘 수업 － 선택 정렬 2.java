
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
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[n];
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		// do selection sort
		for (int i=n-1; i>=0; i--) {
			int max = 0, max_idx = 0;
			// find max value in current range
			for (int idx=0; idx<=i; idx++) {
				int cur_v = arr[idx];
				if (cur_v > max) {
					max = cur_v;
					max_idx = idx;
				}
			}
			// exchange
			if (max_idx == i) {
				continue;
			}
			else {
				int last = arr[i];
				arr[i] = max;
				arr[max_idx] = last;
				k --;
			}
			// check k
			if (k==0) {
				break;
			}
		}
		
		// result
		StringBuilder sb = new StringBuilder();
		if ( k>0 ) {
			sb.append(-1);
		} else {
			for (int num : arr) {
				sb.append(num);
				sb.append(" ");
			}
		}
		System.out.print(sb);
		
	}
		
}