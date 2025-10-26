import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int t;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		t = Integer.parseInt(br.readLine());

		for (int i=0; i<t; i++) {

			int n = Integer.parseInt(br.readLine());
			solution(n);
		}

		// result
		System.out.print(sb);
	}


	static void solution(int n) {

		int max = 1;

		while (n != 1) {

			max = Integer.max(max, n);

			if (n%2 == 0) n = n/2;
			else n = n*3 +1;
		}

		sb.append(max).append("\n");
	}

}