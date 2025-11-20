import java.io.*;
import java.util.*;


public class Main {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		Node[] cnt = new Node[26];
		for (int i=0; i<26; i++) cnt[i] = new Node('z', -1);

		String s = br.readLine();
		while (s != null) {

			// change String arr
			String[] chars = s.split(" ");

			// count alphabet
			for (String ss : chars) {
				for (int i=0; i<ss.length(); i++) {

					// get idx
					char c = ss.charAt(i);
					int idx = c-'a';

					// cnt up
					if (cnt[idx].cnt == -1) cnt[idx] = new Node(c, 1);
					else cnt[idx].cnt ++;
				}
			}

			s = br.readLine();
		}

		Arrays.sort(cnt, (o1, o2) -> {
			return o1.cnt == o2.cnt
				? o2.c - o1.c
				: o2.cnt - o1.cnt;
		});

		// result
		sb = new StringBuilder();
		sb.append(cnt[0].c);

		for (int i=1; i<26; i++) {
			Node node = cnt[i];
			if (node.cnt == cnt[0].cnt) sb.append(node.c);
		}

		System.out.print(sb.reverse());
	}


	static class Node {

		char c;
		int cnt;

		Node(char c, int cnt) {
			this.c = c;
			this.cnt = cnt;
		}
	}

}
