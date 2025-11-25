import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;


	public static void main(String[] args) throws IOException {

		br = new BufferedReader(new InputStreamReader(System.in));

		String[] ss = br.readLine().split(" ");
		while (!ss[0].equals("EOI")) {

			boolean isNemoIn = false;
			for (String s : ss) {

				if (s.length() < 4) {
					continue;
				}

				s = s.toUpperCase();
				if (s.startsWith("NEMO")) {
					isNemoIn = true;
					break;
				}
			}

			System.out.println(isNemoIn ? "Found" : "Missing");
			ss = br.readLine().split(" ");
		}
	}

}
