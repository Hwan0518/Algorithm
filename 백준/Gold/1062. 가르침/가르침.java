import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n,k,max;
	static Set<Character> selectedSet, defaultSet;
	static String[] words;

	public static void main(String[] args) throws IOException {

		// input
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		// a,c,i,n,t 는 무조건 포함되므로, 이에 맞처 기본값 셋팅
		k -= 5;
		defaultSet = Set.of( 'a', 'c', 'i', 'n', 't' );

		words = new String[n];
		for (int i=0; i<n; i++) {
			String origin = br.readLine();
			// anta와 tica는 무조건 포함되므로, 중간에만 확인한다
			words[i] = origin.substring(4, origin.length()-3);
		}

		// dfs
		max = 0;
		int idx = 0;
		int select = 0;
		selectedSet = new HashSet<>();

		dfs(idx, select);

		// result
		System.out.print(max);
	}


	static void dfs(int idx, int select) {

		// base-condition
		if (idx == 26 || select == k) {

			if (select == k) {
				int cnt = calcCnt();
				max = Integer.max(max, cnt);
			}

			return;
		}

		// validate
		char alphabet = (char) ('a'+idx);
		if (defaultSet.contains(alphabet)) {
			dfs(idx+1, select);
			return;
		}

		// select
		selectedSet.add(alphabet);
		dfs(idx+1, select+1);
		selectedSet.remove(alphabet);

		// non-select
		dfs(idx+1, select);
	}


	static int calcCnt() {

		int cnt = 0;

		// 모든 단어에 대해서 탐색
		for (String word : words) {

			boolean possible = true;

			// 해당 단어의 알파벳 탐색
			for (int i=0; i<word.length(); i++) {

				// default값은 뛰어넘음
				if (defaultSet.contains(word.charAt(i))) {
					continue;
				}

				// default value가 아닌 알파벳에 대해 탐색
				if (!selectedSet.contains(word.charAt(i))) {
					possible = false;
					break;
				}
			}

			if (possible) cnt ++;
		}

		return cnt;
	}

}