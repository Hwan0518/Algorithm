import java.io.*;
import java.util.*;

/**
 * 선택정
 */
public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		// selectionSort
		selectionSort(n, k, arr);
	}
	
	
	public static void selectionSort(int n, int k, int[] arr) {
		StringBuilder sb = new StringBuilder();
		int cnt = 0;
		int maxI = 0;
		int last = n;
		while (last > 1) {
			// search
			int max = 0;
			maxI = 0;
			for (int i=0; i<last; i++) {
				if (Math.max(max, arr[i]) == arr[i]) {
					max = arr[i];
					maxI = i;
				}
			}
			// change
			if (maxI != last-1) {
				int change = arr[last-1];
				arr[last-1] = max;
				arr[maxI] = change;
				cnt ++;
			}
			// ans
			if (cnt == k) {
				sb.append(arr[maxI]).append(" ").append(arr[last-1]);
				System.out.print(sb);
				return;
			}
			last --;
		}
		System.out.print(-1);
	}
}