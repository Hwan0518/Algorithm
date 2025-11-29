import java.io.*;
import java.util.*;

public class Main {

	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		// create Input Reader
		InputReader inputReader = new InputReader();

		// InitialValue & Map Data read
		InitialValue initVal = inputReader.readInitialValue();
		MapData map = inputReader.readMap(initVal);

		// taxi 생성
		Taxi taxi = new Taxi(initVal.fuel, map, inputReader);

		// passenger 정보 입력
		map.readPassengersLocation(inputReader);

		// start work
		taxi.work();

		// result
		System.out.print(
			taxi.done == map.m && taxi.fuel >= 0
				? taxi.fuel
				: -1
		);
	}



	static class Taxi {

		int r, c, fuel, move, done;
		MapData mapData;
		int[] dr = { -1, 1, 0, 0 };
		int[] dc = { 0, 0, -1, 1 };


		public Taxi(int fuel, MapData mapData, InputReader inputReader) throws IOException {

			int[] pos = inputReader.readInitTaxiValue();
			this.r = pos[0];
			this.c = pos[1];

			this.fuel = fuel;
			this.move = 0;
			this.mapData = mapData;
		}


		public Taxi(int r, int c, int fuel, int move, MapData mapData) {
			this.r = r;
			this.c = c;
			this.move = move;
			this.fuel = fuel;
			this.mapData = mapData;
		}


		// 운행 시작
		public void work() {

			// 승객 수 만큼 운행 시작
			for (int i=0; i<mapData.m; i++) {

				// 현재 위치에서 가장 가까운 승객 탐색
				MapData.Passenger passenger = findNearestPassenger();
				if (passenger == null) break; // 더이상 운행이 불가능한 경우

				// 승객 목적지로 이동
				int totalMoved = goDestination(passenger);
				if (totalMoved == -1) break; // 더이상 운행 불가능

				// 성공 횟수 증가
				done ++;

				// 총 움직인 거리 *2 만큼 연료 충전
				this.fuel += totalMoved*2;
			}
		}


		// 승객 목적지로 이동 -> 총 움직인 거리
		int goDestination(MapData.Passenger passenger) {

			// set-up
			int[][] map = mapData.map;
			int n = mapData.n;
			boolean[][] visited = new boolean[mapData.n][mapData.n];
			ArrayDeque<Taxi> q = new ArrayDeque<>();
			q.addLast(this);

			// 이동
			visited[this.r][this.c] = true;
			Taxi movedTaxi = this;
			boolean isMoved = false;

			while (!q.isEmpty()) {

				Taxi cur = q.removeFirst();

				// 목적지 확인
				if (cur.r == passenger.endR && cur.c == passenger.endC) {
					isMoved = true;
					movedTaxi = cur;
					break;
				}

				// 상하좌우 이동
				for (int i=0; i<4; i++) {

					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];

					if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
					if (map[nr][nc] == 1) continue;
					if (cur.move+1 > cur.fuel) continue;
					if (visited[nr][nc]) continue;

					visited[nr][nc] = true;
					q.addLast(new Taxi(nr, nc, cur.fuel, cur.move+1, cur.mapData));
				}
			}

			// 업데이트
			if (!isMoved) return -1;
			int totalMove = movedTaxi.move;
			moveTaxi(movedTaxi);

			// 총 이동한 거리
			return totalMove;
		}


		// 현재 위치에서 가장 가까운 승객 탐색
		MapData.Passenger findNearestPassenger() {

			// set-up
			int[][] map = mapData.map;
			int n = mapData.n;
			boolean[][] visited = new boolean[mapData.n][mapData.n];
			boolean[][] passengerVisited = mapData.passengerVisited;
			ArrayDeque<Taxi> q = new ArrayDeque<>();
			q.addLast(this);

			// find passenger
			visited[this.r][this.c] = true;
			int nearDist = Integer.MAX_VALUE;
			int nearRow = Integer.MAX_VALUE;
			int nearColumn = Integer.MAX_VALUE;
			Taxi movedTaxi = this;
			boolean isMoved = false;

			while (!q.isEmpty()) {

				Taxi cur = q.removeFirst();

				// 태우지 않았던 승객인 경우
				if (map[cur.r][cur.c] == 2 && !passengerVisited[cur.r][cur.c]) {

					// 최소거리 & 최소컬럼 확인
					if (cur.move < nearDist) {
						movedTaxi = cur;
						nearDist = cur.move;
						nearRow = cur.r;
						nearColumn = cur.c;
						isMoved = true;
					}

					else if (cur.move == nearDist && cur.r < nearRow) {
						movedTaxi = cur;
						nearDist = cur.move;
						nearRow = cur.r;
						nearColumn = cur.c;
						isMoved = true;
					}

					else if (cur.move == nearDist && cur.r == nearRow && cur.c < nearColumn) {
						movedTaxi = cur;
						nearDist = cur.move;
						nearRow = cur.r;
						nearColumn = cur.c;
						isMoved = true;
					}
				}

				// 상하좌우 이동
				for (int i = 0; i < 4; i++) {

					int nr = cur.r + dr[i];
					int nc = cur.c + dc[i];

					if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
					if (map[nr][nc] == 1) continue;
					if (cur.move+1 > nearDist || cur.move+1 > cur.fuel) continue;
					if (visited[nr][nc]) continue;

					visited[nr][nc] = true;
					q.addLast(new Taxi(nr, nc, cur.fuel, cur.move + 1, cur.mapData));
				}
			}

			// move taxi
			if (!isMoved) return null; // 더이상 태울 승객이 없는 경우
			else moveTaxi(movedTaxi);

			// passenger visit
			visitPassenger(movedTaxi);

			// return passenger
			return mapData.passengerMap[r][c];
		}


		void moveTaxi(Taxi movedTaxi) {
			this.r = movedTaxi.r;
			this.c = movedTaxi.c;
			this.fuel -= movedTaxi.move;
		}


		void visitPassenger(Taxi taxi) {
			mapData.passengerVisited[taxi.r][taxi.c] = true;
		}
	}




	// 지도
	static class MapData {

		int n, m;
		int[][] map;
		boolean[][] passengerVisited;
		Passenger[][] passengerMap;


		public MapData(int n, int m, int[][] map) {
			this.n = n;
			this.m = m;
			this.map = map;
			this.passengerVisited = new boolean[n][n];
		}


		// 승객 정보
		void readPassengersLocation(InputReader inputReader) throws IOException {

			passengerMap = new Passenger[n][n];

			for (int i = 0; i < m; i++) {

				// 승객 정보는 InputReader로부터 읽어서 생성
				Passenger p = inputReader.readPassenger();

				// map에 승객 위치 업데이트
				map[p.sttR][p.sttC] = 2;
				passengerMap[p.sttR][p.sttC] = p;
			}
		}



		// 승객
		static class Passenger {

			int sttR, sttC, endR, endC;

			// 생성자가 입력을 직접 읽지 않고, 값만 주입받도록 변경
			public Passenger(int sttR, int sttC, int endR, int endC) {
				this.sttR = sttR;
				this.sttC = sttC;
				this.endR = endR;
				this.endC = endC;
			}
		}
	}



	static class InputReader {

		BufferedReader br;
		StringTokenizer st;

		public InputReader() {
			this.br = new BufferedReader(new InputStreamReader(System.in));
		}


		// 초기값 생성
		InitialValue readInitialValue() throws IOException {

			st = new StringTokenizer(br.readLine());

			int n = Integer.parseInt(st.nextToken());;
			int m = Integer.parseInt(st.nextToken());;
			int fuel = Integer.parseInt(st.nextToken());;

			return new InitialValue(n, m, fuel);
		}


		// 지도 생성
		public MapData readMap(InitialValue initVal) throws IOException {

			int n = initVal.n;
			int m = initVal.m;

			// 지도 정보 read
			int[][] map = new int[n][n];

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) map[i][j] = Integer.parseInt(st.nextToken());
			}

			// 지도 생성
			return new MapData(n, m, map);
		}


		// 택시 시작 정보
		int[] readInitTaxiValue() throws IOException {

			st = new StringTokenizer(br.readLine());

			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			return new int[] {r, c};
		}


		// 승객 시작 정보
		MapData.Passenger readPassenger() throws IOException {

			st = new StringTokenizer(br.readLine());

			int sttR = Integer.parseInt(st.nextToken()) - 1;
			int sttC = Integer.parseInt(st.nextToken()) - 1;
			int endR = Integer.parseInt(st.nextToken()) - 1;
			int endC = Integer.parseInt(st.nextToken()) - 1;
			return new MapData.Passenger(sttR, sttC, endR, endC);
		}
	}



	// 초기값
	static class InitialValue {

		int n, m, fuel;

		private InitialValue(int n, int m, int fuel) {
			this.n = n;
			this.m = m;
			this.fuel = fuel;
		}
	}



}