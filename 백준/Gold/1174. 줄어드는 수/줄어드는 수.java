import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static List<Long> decreaseNums = new ArrayList<>();


	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine())-1;

		// find all decrease number -> 어차피 전체 생성되는게 작아서 모두 구해도 된다
		for (int stt=0; stt<=9; stt++) {
			for (int size=1; size<=10; size ++) {
				findDecreaseNum(stt, stt, size);
			}
		}

		Collections.sort(decreaseNums);

		// result
		System.out.print(n<decreaseNums.size() ? decreaseNums.get(n) : -1);
	}


	static void findDecreaseNum(long cur, int curLast, int size) {

		String sNum = String.valueOf(cur);

		// base-condition
		if (sNum.length() == size) {
			decreaseNums.add(cur);
			return;
		}

		// find next Number
		for (int i=curLast-1; i>=0; i--) {
			findDecreaseNum(Long.parseLong(sNum+i), i, size);
		}
	}

}
