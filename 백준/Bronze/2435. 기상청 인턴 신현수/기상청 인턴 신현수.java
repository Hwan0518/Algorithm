import java.io.*;
import java.util.*;

/**
 * 슬라이딩윈도우
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
		// get first sum
		int sum = 0;
		for (int i=0; i<k; i++) {
			sum += arr[i];
		}
		// calc max
		int max = sum;
		int end = k;
		for (int i=0; i<n-k; i++) {
			sum -= arr[i];
			sum += arr[end];
			max = Math.max(max, sum);
			end ++;
		}
		System.out.print(max);
	}
}