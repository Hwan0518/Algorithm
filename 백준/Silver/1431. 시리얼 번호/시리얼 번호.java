import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, m;


	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		String[] arr = new String[n];
		for (int i=0; i<n; i++) arr[i] = br.readLine();

		// sort
		Arrays.sort(arr, (o1, o2) -> {
			return o1.length() != o2.length()
				? o1.length() - o2.length()
				: sumNums(o1) != sumNums(o2)
					? sumNums(o1) - sumNums(o2)
					: getSort(o1, o2, o1) - getSort(o1, o2, o2);
		});

		// res
		for (String s : arr) sb.append(s).append("\n");
		System.out.print(sb);
	}


	static int getSort(String s1, String s2, String target) {

		String[] ss = { s1, s2 };
		Arrays.sort(ss);

		if (ss[0].equals(target)) return 0;
		else return 1;
	}


	static int sumNums(String s) {

		int sum = 0;

		for (char c : s.toCharArray()) {
			if (c >= '0' && c <= '9') {
				sum += c-'0';
			}
		}

		return sum;
	}

}
