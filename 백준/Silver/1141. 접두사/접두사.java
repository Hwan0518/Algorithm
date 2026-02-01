import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n;
	static String[] arr;

	/*
	접두사x집합 -> 한 단어가 다른 단어의 접두어가 되지 않는 집합

	1. 완탐
		-> nC1 ~ nCn까지 모든 부분집합 경우의수를 구함 (2^n)
		-> 각 부분집합에서 접두어x집합인지 확인 (집합크기k -> k^k)
		-> 따라서 O(2^n * k^k) = 말도안됨. tle

	2. 완탐2
		-> 각 단어를 접두어로 하는게 있다면 다음으로 넘어감
		-> 해당 단어를 접두어로 하는게 없다면 집합에 추가(하나만 추가하므로 어차피 중복제거됨)
		-> n^2?
	 */

	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		arr = new String[n];
		for (int i=0; i<n; i++) arr[i] = br.readLine();

		// search
		Set<String> xSet = new HashSet<>();
		for (int i=0; i<n; i++) {

			String cur = arr[i];
			boolean isPrefix = false;

			loop: for (int j=0; j<n; j++) {

				// 자기 자신 pass
				if (i==j) continue;

				// 비교할 단어
				String compare = arr[j];

				// 완전 동일한 단어면 pass
				if (cur.equals(compare)) continue;

				// 비교할 단어 길이가 더 작다면 pass
				if (cur.length() > compare.length()) continue;

				// 접두어인지 확인
				for (int k=0; k<cur.length(); k++) {

					// 다른 경우 -> 바로종료 (접두어 아님)
					if (cur.charAt(k) != compare.charAt(k)) {
						continue loop;
					}
				}

				// 위에서 통과했다면 접두어란 뜻임
				isPrefix = true;
				break;
			}

			// 접두어가 아니라면 xSet에 추가
			if (!isPrefix) xSet.add(cur);
		}

		// 결과
		System.out.print(xSet.size());
	}

}