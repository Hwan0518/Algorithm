import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;


	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());

			long a = Long.parseLong(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			// 반복되는 마지막 수들을 기억
			List<Long> lastNums = new ArrayList<>();

			long multiplier = a;
			long curLast = a%10;
			while (!lastNums.contains(curLast)) {
				lastNums.add(curLast);
				a *= multiplier;
				curLast = a%10;
			}

			// b/10번째 수를 출력
			int size = lastNums.size();
			int idx = b%size == 0 ? size-1 : b%size-1;
			long ans = lastNums.get(idx) == 0 ? 10 : lastNums.get(idx);
			sb.append(ans).append("\n");
		}

		// result
		System.out.print(sb);
	}

}
