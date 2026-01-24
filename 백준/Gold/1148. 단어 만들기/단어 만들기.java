import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static Map<Character, Integer> map = new HashMap<>();
	static List<String> dict, puzzle;

	public static void main(String[] args) throws IOException {

		// input
		dict = new ArrayList<>();
		String word = br.readLine();
		while (!word.equals("-")) {
			dict.add(word);
			word = br.readLine();
		}

		puzzle = new ArrayList<>();
		String s = br.readLine();
		while (!s.equals("#")) {
			puzzle.add(s);
			s = br.readLine();
		}

		// search
		for (int i=0; i<puzzle.size(); i++) {

			// set-up
			String curP = puzzle.get(i);

			Map<Character, Integer> need = new HashMap<>();
			for (int idx=0; idx<curP.length(); idx++) {
				char c = curP.charAt(idx);
				if (!need.containsKey(c)) need.put(c, 1);
				else need.put(c, need.get(c)+1);
			}

			map = new HashMap<>();
			for (int idx=0; idx<curP.length(); idx++) {
				char c = curP.charAt(idx);
				map.put(c, 0);
			}

			// full scan
			loop: for (String w : dict) {

				Map<Character, Integer> cnt = new HashMap<>();

				for (int idx=0; idx<w.length(); idx++) {

					char c = w.charAt(idx);

					if (!map.containsKey(c)) continue loop;

					if (!cnt.containsKey(c)) cnt.put(c, 1);
					else cnt.put(c, cnt.get(c)+1);
				}

				for (Map.Entry<Character, Integer> entry : cnt.entrySet()) {
					int cntNeed = cnt.get(entry.getKey());
					int puzzleNeed = need.get(entry.getKey());
					if (cntNeed > puzzleNeed) continue loop;
				}

				// put cnt to map
				for (Map.Entry<Character, Integer> entry : cnt.entrySet()) {
					char key = entry.getKey();
					if (entry.getValue() > 0) map.put(key, map.get(key)+1);
				}
			}

			// find max, min
			List<Character> maxs = new ArrayList<>();
			List<Character> mins = new ArrayList<>();
			int maxCnt = Integer.MIN_VALUE;
			int minCnt = Integer.MAX_VALUE;

			for (Map.Entry<Character, Integer> entry : map.entrySet()) {
				char key = entry.getKey();
				int val = entry.getValue();

				if (val > maxCnt) {
					maxCnt = val;
					maxs.clear();
					maxs.add(key);
				}

				else if (val == maxCnt) {
					maxs.add(key);
				}

				if (val < minCnt) {
					minCnt = val;
					mins.clear();
					mins.add(key);
				}

				else if (val == minCnt) {
					mins.add(key);
				}
			}

			Collections.sort(maxs);
			Collections.sort(mins);

			// add res
			StringBuilder resMax = new StringBuilder();
			for (char c : maxs) resMax.append(c);

			StringBuilder resMin = new StringBuilder();
			for (char c : mins) resMin.append(c);

			sb.append(resMin)
				.append(" ")
				.append(minCnt)
				.append(" ")
				.append(resMax)
				.append(" ")
				.append(maxCnt)
				.append("\n");
		}

		// result
		System.out.print(sb);
	}

}
