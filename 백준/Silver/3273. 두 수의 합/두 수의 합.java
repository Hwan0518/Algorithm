import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int[] arr;
	static int x;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());

		arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());
		Arrays.sort(arr);


		x = Integer.parseInt(br.readLine());

		// tow-pointer
		int result = twoPointer();

		// result
		System.out.print(result);
	}


	static int twoPointer() {

		int cnt = 0;

		int stt = 0;
		int end = n-1;
		while (stt < end) {

			int a1 = arr[stt];
			int a2 = arr[end];
			int sum = a1+a2;

			if (sum == x) {
				cnt++;
				stt ++;
			}

			else if (sum < x) {
				stt ++;
			}

			else {
				end --;
			}
		}

		return cnt;
	}

}
