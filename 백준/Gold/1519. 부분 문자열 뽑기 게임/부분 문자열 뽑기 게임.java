import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, min;
	static int[][] dp;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());

		dp = new int[2][n+1];
		for (int i=0; i<=n; i++) {
			dp[0][i] = -1;
			dp[1][i] = -1;
		}

		// dfs
		min = Integer.MAX_VALUE;
		int turn = 0;
		dfs(turn, n);

		// result
		System.out.print(min==Integer.MAX_VALUE ? -1 : min);
	}


	static int dfs(int turn, int num) {

		// base condition
		if (num < 10) return turn;

		// memorize
		if (dp[turn][num] == -1) {

			// 진 부분문자열 숫자 구하기
			List<Integer> substrings = findSubStrings(num);

			// 가능한 경우의 수 탐색
			int nextPlayer = (turn+1) %2;
			int loser = turn;

			// 각 플레이어 턴 -> 플레이어는 무조건 자기가 이기는 경우만 고른다! 지는 경우는 안고름. 이게 바로 최적의 선택.
			for (int i = substrings.size()-1; i>=0; i--) {

				int sub = substrings.get(i);
				int res = dfs(nextPlayer, num-sub);

				// 제일 처음 숫자인 경우, 첫 번째 플레이어가 이기는 경우, min을 갱신
				if (turn == 0 && num == n && res == 1) min = Integer.min(min, sub);

				// 자기가 이기는 경우, loser를 상대방으로 갱신
				if (num != n && res == nextPlayer) {
					loser = nextPlayer;
					break;
				}
			}

			// dp 업데이트
			dp[turn][num] = loser;
		}

		return dp[turn][num];
	}


	static List<Integer> findSubStrings(int num) {

		String sNum = String.valueOf(num);
		int len = sNum.length();

		// 진 부분문자열 (사이즈별로 확인)
		Set<Integer> substringsSet = new HashSet<>();

		for (int size=1; size<len; size++) {

			String cur = "";
			int stt = 0;
			int i = stt;
			while (i < len) {

				cur += String.valueOf(sNum.charAt(i));

				// validate (0으로 시작하면 안됨)
				if (cur.equals("0")) {
					cur = "";
					i++;
					continue;
				}

				// 길이가 size가 되면 i 초기화시키고 재탐색. cur도 초기화
				if (cur.length()==size) {
					substringsSet.add(Integer.parseInt(cur));
					stt ++;
					i = stt;
					cur = "";
				}

				// 길이가 size보다 작으면 계속 탐색
				else i++;
			}
		}

		List<Integer> substrings = new ArrayList<>(substringsSet);
		Collections.sort(substrings);
		return substrings;
	}

}

