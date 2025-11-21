import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		String s = br.readLine();
		
		// set-up
		Set<Character> DEL = new HashSet<>();
		for (char c: "CAMBRIDGE".toCharArray()) DEL.add(c);
		
		// remove
		sb = new StringBuilder();
		for (char c : s.toCharArray()) {
			if (!DEL.contains(c)) sb.append(c);
		}

		// result
		System.out.print(sb);
	}

}
