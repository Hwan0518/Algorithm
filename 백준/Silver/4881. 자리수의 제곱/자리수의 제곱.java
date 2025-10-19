import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int a;
	static int b;
	static Map<Integer, Integer> map;
	static int size;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		a = Integer.parseInt(st.nextToken());
		b = Integer.parseInt(st.nextToken());

		// search
		sb = new StringBuilder();

		while (a != 0 && b != 0) {

			solution();

			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
		}

		// result
		System.out.print(sb);
	}


	static void solution() {

		// set-up
		map = new HashMap<>();
		size = Integer.MAX_VALUE;

		// search
		aMapSetting();
		findCommonNumber();

		// result
		sb.append(a).append(" ")
			.append(b).append(" ")
			.append(size == Integer.MAX_VALUE ? 0 : size)
			.append("\n");
	}


	static void findCommonNumber() {

		int cur = b;
		int bSize = 1;

		Set<Integer> bSet = new HashSet<>();

		while (!bSet.contains(cur)) {

			// update b Set
			bSet.add(cur);

			// find common num
			if (map.containsKey(cur)) {
				size = Integer.min(size, map.get(cur) + bSize);
			}

			// update cur, bSize
			cur = getSquareSum(cur);
			bSize ++;
		}

	}


	static void aMapSetting() {

		int cur = a;
		int size = 1;

		while (!map.containsKey(cur)) {

			// update map
			map.put(cur, size);

			// update cur, size
			cur = getSquareSum(cur);
			size ++;
		}
	}


	static int getSquareSum(int square) {

		String strNum = String.valueOf(square);
		int size = strNum.length();
		
		int result = 0;
		for (int i=0; i<size; i++) {
			
			int num = Integer.parseInt(String.valueOf(strNum.charAt(i)));
			result += num * num;
		}

		return result;
	}
	
	/**
	 * 89: 89, 145, 42, 20, 4, 16, 37, 58
	 * 61: 61, 37, 58, 89
	 * 
	 * -> 37을 먼저 탐색하게되면 size가 달라진다. 따라서 min값을 찾도록 변경해야함
	 */
}
