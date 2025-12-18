import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;


	static int n;
	static List<Integer>[] colors;

	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		colors = new List[n];
		for (int i=0; i<n; i++) colors[i] = new ArrayList<>();

		// classify colors
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());
			int loc = Integer.parseInt(st.nextToken());
			int color = Integer.parseInt(st.nextToken()) -1;

			colors[color].add(loc);
		}

		// sort
		for (List<Integer> locs : colors) Collections.sort(locs);

		// find length sum
		int sum = 0;
		for (int i=0; i<n; i++) {

			List<Integer> locs = colors[i];
			int size = locs.size();

			for (int j=0; j<size; j++) {

				if (j==0) sum += locs.get(j+1) - locs.get(j);
				else if (j == size-1) sum += locs.get(j) - locs.get(j-1);
				else sum += Integer.min(locs.get(j)-locs.get(j-1), locs.get(j+1)-locs.get(j));
			}
		}

		// result
		System.out.print(sum);
	}

}