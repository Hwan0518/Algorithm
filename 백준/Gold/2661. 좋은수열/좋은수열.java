import java.io.*;
	import java.util.*;

public class Main {

	static int n;
	static int[] selected;
	static boolean ans = false;
	static StringBuilder sb = new StringBuilder();


	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());


		// dfs
		selected = new int[n];
		dfs(0, 0);

		// result
		System.out.print(sb);
	}




	static void dfs(int size, int pre) {

		if (ans) return;

		if (size == n) {
			for (int i=0; i<n; i++) sb.append(selected[i]);
			ans = true;
			return;
		}

		for (int i=1; i<=3; i++) {

			if (i==pre) continue;

			selected[size] = i;
			if (!validate(size+1)) continue;

			dfs(size+1, i);

		}

	}



	static boolean validate(int size) {

		// 비교할 길이
		for (int l=2; l<=size/2; l++) {

			// 시작점
			for (int stt=0; stt<size-l; stt++) {

				// 동일 여부 체크
				StringBuilder num1 = new StringBuilder();
				StringBuilder num2 = new StringBuilder();

				for (int idx=stt; idx+l < size && idx<stt+l; idx++) {
					num1.append(selected[idx]);
					num2.append(selected[idx+l]);
				}

				// 하나라도 동일한게 있다면 false를 return
				if (num1.length() == l && num1.toString().equals(num2.toString())) return false;
			}
		}

		return true;
	}


}