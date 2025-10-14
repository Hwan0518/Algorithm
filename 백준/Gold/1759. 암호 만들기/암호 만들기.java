import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static int l;
	static int c;
	static char[] arr;
	static Set<Character> consonants;
	static Map<Character, Boolean> visited;
	static Map<Character, Boolean> visitedConsonant;
	static Map<Character, Boolean> visitedVowel;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = br.readLine().replaceAll(" ","").toCharArray();
		Arrays.sort(arr);

		visited = new HashMap<>();
		for (char c : arr) visited.put(c, false);

		consonants = Set.of('a', 'e', 'i', 'o', 'u');
		visitedConsonant = new HashMap<>();

		visitedVowel = new HashMap<>();

		for (int i=97; i<97+26; i++) {

			char apb = (char) i;

			if (consonants.contains(apb)) visitedConsonant.put(apb, false);
			else visitedVowel.put(apb, false);
		}

		// dfs
		sb = new StringBuilder();
		dfs(0, 0);

		// result
		System.out.print(sb);
	}


	static void dfs(int size, int idx) {

		if (size == l || idx >= c) {

			if (size < l) return;

			int cntConsonant = checkCntConsonant();
			int cntVowel = checkCntVowel();

			if (cntConsonant >= 1 && cntVowel >= 2) {

				for (char apb : arr) {
					if (visited.get(apb)) sb.append(apb);
				}
				sb.append("\n");
			}

			return;
		}

		// 선택
		char cur = arr[idx];
		visited.put(cur, true);

		if (consonants.contains(cur)) {
			visitedConsonant.put(cur, true);
			dfs(size+1, idx+1);
			visitedConsonant.put(cur, false);
		}
		else {
			visitedVowel.put(cur, true);
			dfs(size+1, idx+1);
			visitedVowel.put(cur, false);
		}

		visited.put(cur, false);

		// 비선택
		dfs(size, idx+1);

	}


	static int checkCntConsonant() {

		int cnt = 0;
		for (boolean consonant : visitedConsonant.values()) {
			if (consonant) cnt ++;
		}

		return cnt;
	}


	static int checkCntVowel() {

		int cnt = 0;
		for (boolean vowel : visitedVowel.values()) {
			if (vowel) cnt ++;
		}

		return cnt;
	}


}
