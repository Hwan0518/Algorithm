import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();


	/*
	백트 + 시뮬
	 */

	static int n, k, max;
	static int[][] map, origin;
	static int[] bullets;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());

		map = new int[n][n];
		origin = new int[n][n];
		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());

			for (int j=0; j<n; j++) {

				int v = Integer.parseInt(st.nextToken());

				map[i][j] = v;
				origin[i][j] = v;
			}
		}

		bullets = new int[k];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<k; i++) bullets[i] = Integer.parseInt(st.nextToken());

		// dfs
		max = 0;
		int bulletIndex = 0;
		int curScore = 0;

		dfs(bulletIndex, curScore);

		// result
		System.out.print(max);
	}


	static void dfs(int idx, int score) {

		// base-condition
		if (idx == k) {
			max = Integer.max(max, score);
			return;
		}

		// select row
		for (int i=0; i<n; i++) {

			// shoot
			for (int j=0; j<n; j++) {

				int targetCurrentHP = map[i][j];
				int targetOriginHP = origin[i][j];
				int attackPower = bullets[idx];

				// blank
				if (targetCurrentHP == 0) continue;

				// can't break (no-bonus target)
				if (targetCurrentHP < 10 && attackPower < targetCurrentHP) {
					// decrease target current hp
					map[i][j] -= attackPower;
					dfs(idx+1, score);
					map[i][j] += attackPower;
				}

				// can break
				else {
					// remove target
					map[i][j] = 0;
					origin[i][j] = 0;

					// bonus target: no create new target
					if (targetCurrentHP >= 10) dfs(idx + 1, score + targetOriginHP);

					// non-bonus target : create new target
					else {
						// create new target
						int newTargetHP = targetOriginHP / 4;
						List<Node> newTargets = new ArrayList<>();
						if (newTargetHP > 0) newTargets = createNewTarget(newTargetHP, i, j);

						// next shoot
						dfs(idx + 1, score + targetOriginHP);

						// roll-back create new target
						for (Node newT : newTargets) {
							origin[newT.r][newT.c] = 0;
							map[newT.r][newT.c] = 0;
						}
					}

					// roll-back remove target
					origin[i][j] = targetOriginHP;
					map[i][j] = targetCurrentHP;
				}

				// if bullet meet target -> can't 전진
				break;
			}
		}

	}


	static List<Node> createNewTarget(int hp, int r, int c) {

		List<Node> newTargets = new ArrayList<>();

		for (int i=0; i<4; i++) {

			int nr = r + dr[i];
			int nc = c + dc[i];

			// validate
			if (nr<0 || nr>=n || nc<0 || nc>=n) continue;
			if (map[nr][nc] != 0) continue;

			// assign new target
			map[nr][nc] = hp;
			origin[nr][nc] = hp;
			newTargets.add(new Node(nr, nc));
		}

		// result
		return newTargets;
	}


	static class Node {

		int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

}