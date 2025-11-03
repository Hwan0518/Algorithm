import java.io.*;
import java.util.*;



public class Main {

	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };


	public static void main(String[] args) throws IOException {

		// 정보 입력
		InputReader inputReader = new InputReader();
		int n = inputReader.readN();
		Data data = inputReader.readData(n);

		// 교실 및 학생 생성
		ClassRoom classRoom = new ClassRoom(n);
		classRoom.createStudents(data);

		// 자리 배정
		classRoom.assignSeats();

		// 만족도 조사
		int result = classRoom.findTotalSatisfaction();

		// 결과
		System.out.print(result);
	}





	static class ClassRoom {

		private int studentCount, roomSize;
		private Student[][] room;
		private Student[] students;

		ClassRoom(int n) {
			this.roomSize = n;
			this.studentCount = n*n;
			this.room = new Student[n+1][n+1];
			this.students = new Student[n*n+1];
		}


		void createStudents(Data data) {

			Set<Integer>[] favorites = data.favorites;
			int[] assignOrder = data.assignOrder;

			for (int idx=1; idx<=studentCount; idx++) {

				int number = assignOrder[idx];

				Student student = new Student(number, favorites[number]);
				students[idx] = student;
			}
		}


		void assignSeats() {

			for (int num=1; num<=studentCount; num++) {

				Student student = this.students[num];

				Seat seat = this.search(student);
				room[seat.r][seat.c] = student;
			}
		}


		private Seat search(Student student) {

			Seat bestSeat = new Seat(0, 0);
			int maxLike = 0;
			int maxVoid = 0;
			int minRow = roomSize+1;
			int minCol = roomSize+1;

			for (int r=1; r<= roomSize; r++) {
				for (int c=1; c<= roomSize; c++) {

					if (room[r][c] != null) continue;

					int curLike = 0;
					int curVoid = 0;

					for (int i=0; i<4; i++) {

						int nr = r + dr[i];
						int nc = c + dc[i];

						if (nr<1 || nr>roomSize || nc<1 || nc>roomSize) continue;

						Student neighbor = room[nr][nc];
						if (neighbor == null) curVoid ++;
						else if (student.isLike(neighbor)) curLike ++;
					}

					if (curLike > maxLike) {
						maxLike = curLike;
						maxVoid = curVoid;
						minRow = r;
						minCol = c;
						bestSeat = new Seat(r, c);
					}

					else if (curLike == maxLike
						&& curVoid > maxVoid
					) {
						maxVoid = curVoid;
						minRow = r;
						minCol = c;
						bestSeat = new Seat(r, c);
					}

					else if (curLike == maxLike
						&& curVoid == maxVoid
						&& r < minRow
					) {
						minRow = r;
						minCol = c;
						bestSeat = new Seat(r, c);
					}

					else if (curLike == maxLike
						&& curVoid == maxVoid
						&& r == minRow
						&& c < minCol
					) {
						minCol = c;
						bestSeat = new Seat(r, c);
					}
				}
			}

			// return best seat
			return bestSeat;
		}


		public int findTotalSatisfaction() {

			int total = 0;

			for (int r=1; r<= roomSize; r++) {
				for (int c = 1; c <= roomSize; c++) {

					Student student = room[r][c];

					int favoriteCnt = 0;
					for (int i = 0; i < 4; i++) {

						int nr = r + dr[i];
						int nc = c + dc[i];

						if (nr < 1 || nr > roomSize || nc < 1 || nc > roomSize) continue;

						Student neighbor = room[nr][nc];
						if (student.isLike(neighbor)) favoriteCnt ++;
					}

					if (favoriteCnt == 1) total+= 1;
					else if (favoriteCnt == 2) total+= 10;
					else if (favoriteCnt == 3) total+= 100;
					else if (favoriteCnt == 4) total+= 1000;
				}
			}

			return total;
		}

	}





	static class Seat {

		private int r, c;

		Seat(int r, int c) {
			this.r = r;
			this.c = c;
		}

	}





	static class Student {

		private int number, satisfaction;
		private Set<Integer> favorite;

		Student(int number, Set<Integer> favorite) {
			this.number = number;
			this.satisfaction = 0;
			this.favorite = favorite;
		}


		boolean isLike(Student neighbor) {
			return this.favorite.contains(neighbor.number);
		}

	}





	static class InputReader {

		private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


		int readN() throws IOException {
			return Integer.parseInt(br.readLine());
		}


		Data readData(int n) throws IOException {

			Set<Integer>[] favorites = new Set[n*n+1];
			int[] assignOrder = new int[n*n+1];

			for (int i=0; i<n*n; i++) {

				StringTokenizer st = new StringTokenizer(br.readLine());
				int student = Integer.parseInt(st.nextToken());

				favorites[student] = new HashSet<>();
				assignOrder[i+1] = student;

				for (int j = 0; j < 4; j++) favorites[student].add(Integer.parseInt(st.nextToken()));
			}

			return new Data(favorites, assignOrder);
		}

	}




	static class Data {

		Set<Integer>[] favorites;
		int[] assignOrder;

		Data(Set<Integer>[] favorites, int[] assignOrder) {
			this.favorites = favorites;
			this.assignOrder = assignOrder;
		}

	}



}
