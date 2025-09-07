import java.io.*;
	import java.util.*;

public class Main {

	static int n;
	static int[] arr;
	static Map<Integer, Integer> map;
	static int[] answer;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[n];
		for (int i=0; i<n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// coordinate compression
		answer = new int[n];
		map = new HashMap<>();
		cc();

		// result
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<n; i++) sb.append(map.get(arr[i])).append(" ");
		System.out.print(sb);
	}



	static void cc() {

		int[] temp = Arrays.copyOf(arr, arr.length);
		Arrays.sort(temp);

		int idx = 0;
		for (int i=0; i<n; i++) {

			if (map.containsKey(temp[i])) continue;
			map.put(temp[i], idx);
			idx ++;

		}
	}

}