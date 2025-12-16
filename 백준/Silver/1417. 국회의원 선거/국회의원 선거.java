import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;


	/*
	greedy하게 풀어보자
	다솜이보다 득표수가 많은 사람 중, 가장 큰 값중 하나를 매수하며 계속 비교
	 */

	public static void main(String[] args) throws IOException {

		// input
		int n = Integer.parseInt(br.readLine());

		int[] arr = new int[n];
		for (int i=0; i<n; i++) arr[i] = Integer.parseInt(br.readLine());

		// bribe people
		int maxV = arr[0];
		int maxIdx = 0;
		for (int i=0; i<n; i++) {
			if (arr[i] >= maxV) {
				maxV = arr[i];
				maxIdx = i;
			}
		}

		int cnt = 0;
		while (maxIdx != 0) {

			// bribe
			arr[maxIdx] --;
			arr[0] ++;
			cnt++;
			
			// update
			maxV = 0;
			for (int i=0; i<n; i++) {
				if (arr[i] >= maxV) {
					maxV = arr[i];
					maxIdx = i;
				}
			}
		}

		// result
		System.out.print(cnt);
	}

}