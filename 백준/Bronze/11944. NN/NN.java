import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		// input
		String[] input = br.readLine().split(" ");
		String n = input[0];

		int intN = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);

		int limit = Integer.min(n.length()*intN, m);

		// create ans
		while (sb.length() < limit) {

			int remain = limit - sb.length();

			if (remain >= n.length()) {
				sb.append(n);
			}
			else {
				for (int i = 0; i < remain; i++) sb.append(n.charAt(i));
			}
		}

		// result
		System.out.print(sb);
	}

}