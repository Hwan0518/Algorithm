import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m, k, cnt;
	static int[][] nutrients;
	static List<Integer>[][] ages;
	static int[][] A;
	static List<Node> nutList = new ArrayList<>();
	static int[] dr = { -1, 1, 0, 0, -1, -1, 1, 1 };
	static int[] dc = { 0, 0, -1, 1, -1, 1, -1, 1 };
	static List<Node> treeLoc;
	static boolean[][] visited;


	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		cnt = m;

		nutrients = new int[n][n];
		ages = new ArrayList[n][n];
		A = new int[n][n];

		for (int i=0; i<n; i++) {

			st = new StringTokenizer(br.readLine());

			for (int j=0; j<n; j++) {
				// nutrient
				nutrients[i][j] = 5;

				// age
				ages[i][j] = new ArrayList<>();

				// A
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		treeLoc = new ArrayList<>();
		visited = new boolean[n][n];
		for (int i=0; i<m; i++) {

			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken())-1;
			int age = Integer.parseInt(st.nextToken());

			ages[r][c].add(age);
			treeLoc.add(new Node(r, c, 0));
			visited[r][c] = true;
		}

		// after k-year investment
		investment();

		// result
		System.out.print(cnt);
	}


	static void investment() {

		for (int year=1; year<=k; year++) {

//			System.out.println("winter("+(year-1)+")");
//			for (int i=0; i<n; i++) System.out.println(Arrays.toString(treeMap[i]));

			// 1. 봄
			spring();

			// 2. 여름
			summer();

			// 3. 가을
			fall();

			// 4. 겨울
			winter();
		}
	}


	static void spring() {

		List<Node> aliveList = new ArrayList<>();
		for (Node loc : treeLoc) {

			int i = loc.r;
			int j = loc.c;
			int nut = 0;

			List<Integer> temp = new ArrayList<>();

			Collections.sort(ages[i][j]);
			int size = ages[i][j].size();
			for (int t=0; t<size; t++) {

				int age = ages[i][j].get(t);

				// grow
				if (age <= nutrients[i][j]) {
					nutrients[i][j] -= age;
					temp.add(age+1);
				}

				// dead
				else {
					nut += age/2;
					cnt --;
				}
			}

			// 영양분으로 변신
			nutList.add(new Node(i, j, nut));

			// 살아있는 나무들을 treeMap에 추가
			ages[i][j] = new ArrayList<>(temp);

			// 나무 위치 업데이트
			if (!temp.isEmpty()) aliveList.add(loc);
			else visited[loc.r][loc.c] = false;
		}

		treeLoc = aliveList;
	}


	static void summer() {
		for (Node dead : nutList) nutrients[dead.r][dead.c] += dead.nut;
		nutList.clear();
	}


	static void fall() {

		List<Node> newTrees = new ArrayList<>();
		for (Node loc : treeLoc) {

			int i = loc.r;
			int j = loc.c;

			for (int age : ages[i][j]) {

				// validate
				if (age % 5 != 0) continue;

				// breeding
				for (int idx = 0; idx < 8; idx++) {

					int nr = i + dr[idx];
					int nc = j + dc[idx];

					if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
					ages[nr][nc].add(1);
					cnt ++;

					if (!visited[nr][nc]) {
						newTrees.add(new Node(nr, nc, 0));
						visited[nr][nc] = true;
					}
				}
			}
		}

		treeLoc.addAll(newTrees);
	}


	static void winter() {

		// nutrient
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) nutrients[i][j] += A[i][j];
		}
	}


	static class Node {

		int r,c,nut;

		Node(int r, int c, int nut) {
			this.r = r;
			this.c = c;
			this.nut = nut;
		}
	}


}