import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;


	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		// find nthNum
		int cnt = 0;
		long curLong = 0L;
		String curString = String.valueOf(curLong);

		while (cnt <= n && curLong <= 9876543210L) {

			long res = validate(curString);

			if (res == curLong) {
				cnt ++;
				curLong ++;
			}

			else {
				curLong = res;
			}

			curString = String.valueOf(curLong);
		}

		cnt --;
		curLong --;

		// result
		long nthNum = cnt != n ? -1 : curLong;
		System.out.print(nthNum);
	}


	static long validate(String cur) {

		char base = cur.charAt(0);

		for (int i=1; i<cur.length(); i++) {

			char c = cur.charAt(i);

			if (base <= c) {
				sb = new StringBuilder();

				for (int j=0; j<cur.length(); j++) {
					if (j == i-1) sb.append((long) (base-'0'+1));
					else if (j > i-1) sb.append(0);
					else sb.append(cur.charAt(j));
				}

				return Long.parseLong(sb.toString());
			}

			base = c;
		}

		return Long.parseLong(cur);
	}

}