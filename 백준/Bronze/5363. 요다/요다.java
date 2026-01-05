import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static List<String[]> list;

	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		list = new ArrayList<>();
		for (int i=0; i<n; i++) list.add(br.readLine().split(" "));

		// yoda
		sb = new StringBuilder();
		for (String[] s : list) {
			for (int i=2; i<s.length; i++) sb.append(s[i]).append(" ");
			sb.append(s[0]).append(" ");
			sb.append(s[1]);
			sb.append("\n");
		}

		// result
		System.out.print(sb);
	}

}