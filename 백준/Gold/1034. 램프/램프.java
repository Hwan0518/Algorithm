import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n,m,k;

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 모든 행에 대해, 동일한 need(켜야만 하는 열 번호) 개수를 확인한다
		Map<String, Integer> needs = new HashMap<>();
		for (int i=0; i<n; i++) {
			String inputString = br.readLine();
			if (!needs.containsKey(inputString)) needs.put(inputString, 1);
			else needs.put(inputString, needs.get(inputString)+1);
		}

		// need에 대해 차례대로 필요한 k수를 확인하고, 남은 k%2==0이면 end, k%2==1이면 다음 need로 넘어간다
		k = Integer.parseInt(br.readLine());

		int max = 0;
		for (Map.Entry<String, Integer> value : needs.entrySet()) {

			int need = 0;
			
			for (char c : value.getKey().toCharArray()) {
				if (c == '0') need ++;
			}
			
			int remain = k - need;
			
			if (remain < 0) continue;
			if (remain % 2 == 0) max = Integer.max(max, value.getValue());
		}

		// result
		System.out.print(max);
	}

}