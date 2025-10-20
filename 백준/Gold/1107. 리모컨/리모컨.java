import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static int m;
	static HashSet<Integer> broken;
	static int min;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		m = Integer.parseInt(br.readLine());

		broken = new HashSet<>();
		if (m > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < m; i++) broken.add(Integer.parseInt(st.nextToken()));
		}

		min = Math.abs(n - 100);

		// search
		search();

		// result
		System.out.print(min);
	}


	static void search() {

		int size = String.valueOf(n).length();

		for (int channel=0; channel <= 1_000_000; channel++) {

			if (!validate(channel)) continue;

			int cnt = String.valueOf(channel).length() + Math.abs(n - channel);

			min = Integer.min(min, cnt);
		}
	}


	static boolean validate(int channel) {

		int[] numList = Arrays.stream(String.valueOf(channel).split(""))
			.mapToInt(Integer::parseInt)
			.toArray();

		for (int num : numList) {
			if (broken.contains(num)) return false;
		}

		return true;
	}

}
