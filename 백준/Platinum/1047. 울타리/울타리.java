import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static int n, minCnt;
	static Node[] arr;
	static HashSet<Integer> rSet, cSet;

	public static void main(String[] args) throws IOException {

		// input
		n = Integer.parseInt(br.readLine());

		arr = new Node[n];
		rSet = new HashSet<>();
		cSet = new HashSet<>();
		for (int i = 0; i < n; i++) {

			st = new StringTokenizer(br.readLine());
			int curR = Integer.parseInt(st.nextToken());
			int curC = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());

			Node cur = new Node(curR, curC, cnt);
			arr[i] = cur;
			rSet.add(curR);
			cSet.add(curC);
		}

		minCnt = 40;

		// search
		search();

		// result
		System.out.print(minCnt);
	}


	static void search() {

		List<Integer> rList = new ArrayList<>(rSet);
		List<Integer> cList = new ArrayList<>(cSet);
		int[] inside = new int[n];

		// select boundary
		for (int r1=0; r1< rList.size(); r1++) {
			for (int r2=r1; r2< rList.size(); r2++) {
				for (int c1=0; c1< cList.size(); c1++) {
					for (int c2=c1; c2<cList.size(); c2++) {

						int minR = Integer.min(rList.get(r1), rList.get(r2));
						int maxR = Integer.max(rList.get(r1), rList.get(r2));
						int minC = Integer.min(cList.get(c1), cList.get(c2));
						int maxC = Integer.max(cList.get(c1), cList.get(c2));

						// set-up
						int removeCnt = 0;
						int need = (maxR-minR)*2 + (maxC-minC)*2;
						int fence = 0;
						int insideIdx = 0;

						// 바깥 나무
						for (int i=0; i<n; i++) {

							Node tree = arr[i];

							if ((tree.r>=minR && tree.r<=maxR) && (tree.c>=minC && tree.c<=maxC)) {
								inside[insideIdx] = tree.cnt;
								insideIdx ++;
								continue;
							}

							fence += tree.cnt;
							removeCnt ++;
						}

						// fence가 모자라다면 안쪽 나무도 확인
						if (fence < need && insideIdx > 0) {

							// 값이 존재하는 부분만 정렬
							Arrays.sort(inside, 0, insideIdx);

							// 탐색
							for (int i=insideIdx-1; i>=0; i--) {

								if (fence >= need) break;

								fence += inside[i];
								removeCnt ++;
							}
						}
						
						// minCnt 갱신
						if (fence >= need) minCnt = Integer.min(minCnt, removeCnt);
					}
				}
			}
		}

	}


	static class Node {

		int r,c,cnt;

		Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

}