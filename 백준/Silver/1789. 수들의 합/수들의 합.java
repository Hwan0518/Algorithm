import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		// input
		long s = Long.parseLong(br.readLine());

		// find cnt
		int cnt = 0;
		long sum = 0;

		long cur = 1;
		while (sum < s) {
			sum += cur;
			cur++;
			cnt ++;
		}

		if (sum > s) cnt --; // (sum-s)에 해당하는 자연수 빼면 됨

		// result
		System.out.print(cnt);
	}

}