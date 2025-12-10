import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws IOException {

		// input
		int n = Integer.parseInt(br.readLine());

		// compare
		LocalDate youngestBirth = LocalDate.of(1, 1, 1);
		LocalDate oldestBirth = LocalDate.of(9999, 12, 31);
		String youngest = "";
		String oldest = "";

		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());

			String name = st.nextToken();
			int day = Integer.parseInt(st.nextToken());
			int month = Integer.parseInt(st.nextToken());
			int year = Integer.parseInt(st.nextToken());

			LocalDate curBirth = LocalDate.of(year, month, day);

			// youngest
			if (curBirth.isAfter(youngestBirth)) {
				youngestBirth = curBirth;
				youngest = name;
			}

			else if (curBirth.isBefore(oldestBirth)) {
				oldestBirth = curBirth;
				oldest = name;
			}
		}
		
		// result
		System.out.print(youngest + "\n" + oldest);
	}

}
