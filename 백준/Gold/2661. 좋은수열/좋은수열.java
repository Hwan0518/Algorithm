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
			if (!validate(size+1)) continue; // 마지막 값을 추가했으므로 size+1을 검사

			dfs(size+1, i);
			if (ans) return;

		}

	}



	static boolean validate(int size) {

		// 비교할 길이(2부터 size/2까지)
		for (int l=2; l<=size/2; l++) {

			boolean isSame = true;

			// 마지막 값이 포함된 블럭만 조회한다
			// 즉, 마지막 추가된 값부터, l까지만 탐색하면 된다. 앞에서부터 할 필요가 없다
			for (int stt=0; stt<l; stt++) {

				if (selected[size - stt -1] != selected[size - stt - l -1]) {
					isSame = false;
					break;
				}

			}

			// 하나라도 동일한게 있다면 false를 return
			if (isSame) return false;
		}

		return true;
	}


}