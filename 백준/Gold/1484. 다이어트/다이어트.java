import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int g;
	static List<Integer> list = new ArrayList<>();

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));
		g = Integer.parseInt(br.readLine());

		// twoPinter
		twoPointer();

		// result
		if (!list.isEmpty()) for (Integer num : list) sb.append(num).append("\n");
		else sb.append(-1);

		System.out.print(sb);
	}


	static void twoPointer() {

		int a = 2;
		int b = 1;

		while (a <= 50001) { // a^2-(a-1)^2 = 최대 10만, 따라서 a<=50001

			long v1 = (long) Math.pow(a, 2);
			long v2 = (long) Math.pow(b, 2);

			if (v1 == g + v2) {
				list.add(a);
				b ++;
			}

			else if (v1 > g + v2) b ++; // 더 작아지는 방향 -> b가 커져야함
			else a ++; // 더 커지는 방향 -> a가 커져야함
		}
	}


}