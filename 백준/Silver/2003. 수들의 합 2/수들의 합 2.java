import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int m;
	static int[] arr;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		arr = new int[n];
		for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(st.nextToken());

		// tow-pointer
		int result = twoPointer();

		// result
		System.out.print(result);
	}


	static int twoPointer() {

		int cnt = 0;

		int stt = 0;
		int end = 1;
		while (stt <= end) {

			int sum = 0;
			for (int i = stt; i < end; i++) sum += arr[i];

			if (sum == m) cnt ++;

			if (sum <= m) {
				if (end < n) end ++; // end == n까지 가능
				else break; // end를 더이상 증가시킬 수 없으므로 sum이 더 커질 수 없음
			}

			else stt ++; // 포함되는 숫자를 하나 줄이므로 sum이 줄이듦
		}

		return cnt;
	}

}
