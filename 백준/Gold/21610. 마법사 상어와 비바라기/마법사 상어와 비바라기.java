import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int n, m;
	static int[][] map;
	static int[][] commands;
	static ArrayDeque<Node> curClouds = new ArrayDeque<>();
	static Set<Node> rained;
	static boolean[][] rainedMap;

	public static void main(String[] args) throws IOException {

		// input
		InputReader reader = new InputReader();
		reader.setNM();
		reader.setMap();
		reader.setCommands();

		// create Shark
		MagicianShark shark = new MagicianShark();

		// cast 'RainCaller'
		shark.castRainCaller();

		// move
		for (int cnt=0; cnt<m; cnt++) {

			int curD = commands[cnt][0];
			int curS = commands[cnt][1];

			// move
			shark.move(curD, curS);

			// rain
			shark.rain();

			// clear
			shark.clear();

			// waterCopyBug
			shark.waterCopyBug();

			// create new cloud
			shark.createNewCloud();
		}

		// result
		int result = shark.findAllWater();
		System.out.print(result);
	}





	static class MagicianShark {

		private int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
		private int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };
		private int[] diagonalIdx = { 1, 3, 5, 7 };

		void castRainCaller() {
			curClouds.add(new Node(n,1));
			curClouds.add(new Node(n,2));
			curClouds.add(new Node(n-1,1));
			curClouds.add(new Node(n-1,2));
		}


		void move(int d, int s) {

			int size = curClouds.size();

			for (int i=0; i<size; i++) {

				Node cloud = curClouds.removeFirst();

				int nr = cloud.r + dr[d]*(s%n); // nr, nc는 최대 1-(n-1)에서 n+(n-1)범위
				int nc = cloud.c + dc[d]*(s%n);

				if (nr > n) nr -= n;
				else if (nr < 1) nr += n;

				if (nc > n) nc -= n;
				else if (nc < 1) nc += n;

				curClouds.addLast(new Node(nr, nc));
			}
		}


		void rain() {

			rained = new HashSet<>();
			rainedMap = new boolean[n+1][n+1];

			for (Node cloud : curClouds) {

				map[cloud.r][cloud.c] += 1;
				rained.add(cloud);
				rainedMap[cloud.r][cloud.c] = true;
			}
		}


		void clear() {
			curClouds.clear();
		}


		void waterCopyBug() {

			for (Node cur : rained) {

				int cnt = 0;

				for (int i=0; i<4; i++) {

					int idx = diagonalIdx[i];
					int nr = cur.r + dr[idx];
					int nc = cur.c + dc[idx];

					// validate
					if (nr<1 || nr>n || nc<1 || nc>n) continue;
					if (map[nr][nc] == 0) continue;

					// count
					cnt ++;
				}

				// copy
				map[cur.r][cur.c] += cnt;
			}
		}


		void createNewCloud() {

			for (int i=1; i<=n; i++) {
				for (int j=1; j<=n; j++) {

					if (rainedMap[i][j]) continue;
					if (map[i][j] < 2) continue;

					curClouds.addLast(new Node(i, j));
					map[i][j] -= 2;
				}
			}

		}


		int findAllWater() {

			int result = 0;

			for (int i=1; i<=n; i++) {
				for (int j=1; j<=n; j++) result += map[i][j];
			}

			return result;
		}

	}






	static class Node {

		private int r, c;

		Node(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}






	static class InputReader {

		private BufferedReader br;


		InputReader() {
			this.br = new BufferedReader(new InputStreamReader(System.in));
		}


		void setNM() throws IOException {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
		}


		void setMap() throws IOException {

			map = new int[n+1][n+1];

			for (int i=1; i<=n; i++) {

				st = new StringTokenizer(br.readLine());

				for (int j=1; j<=n; j++) {

					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

		}


		void setCommands() throws IOException {

			commands = new int[m][2];

			for (int i=0; i<m; i++) {

				st = new StringTokenizer(br.readLine());

				commands[i][0] = Integer.parseInt(st.nextToken())-1;
				commands[i][1] = Integer.parseInt(st.nextToken());
			}
		}

	}




}
