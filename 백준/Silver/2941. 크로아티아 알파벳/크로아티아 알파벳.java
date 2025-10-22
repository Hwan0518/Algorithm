import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();

	static Set<String> set;

	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		char[] arr = br.readLine().toCharArray();

		set = Set.of("c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z=");

		// search
		int cnt = getCount(arr);

		// result
		System.out.print(cnt);
	}


	static int getCount(char[] arr) {

		int cnt = 0;
		int p = 0;

		while (p < arr.length) {

			int max = Integer.min(p+3, arr.length);

			boolean isCA = false;
			String cur = "";

			for (int i=p; i<max; i++) {

				cur += arr[i];

				if (set.contains(cur)) {
					cnt ++;
					p = i+1;
					isCA = true;
					break;
				}
			}

			if (!isCA) {
				cnt++;
				p ++;
			}
		}

		return cnt;
	}
}