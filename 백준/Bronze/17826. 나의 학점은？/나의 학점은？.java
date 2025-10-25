import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int[] arr = Arrays.stream(br.readLine().split(" "))
			.mapToInt(Integer::parseInt)
			.toArray();

		int score = Integer.parseInt(br.readLine());

		// search & result
		String result;

		if (score >= arr[4]) result = "A+";
		else if (score >= arr[14]) result = "A0";
		else if (score >= arr[29]) result = "B+";
		else if (score >= arr[34]) result = "B0";
		else if (score >= arr[44]) result = "C+";
		else if (score >= arr[47]) result = "C0";
		else result = "F";

		System.out.print(result);
	}



}