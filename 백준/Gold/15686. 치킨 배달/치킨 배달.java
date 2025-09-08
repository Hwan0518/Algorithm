import java.io.*;
	import java.util.*;

public class Main {

	static int n;
	static int m;
	static List<String> chicken;
	static List<String> home;
	static int chickenCnt = 0;
	static int homeCnt = 0;
	static boolean[] select;
	static int cityDist = Integer.MAX_VALUE;
	static int[] homeDist;



	public static void main(String[] args) throws IOException {

		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		home = new ArrayList<>();
		chicken = new ArrayList<>();

		for (int i=0; i<n; i++) {

			int[] row = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

			for (int j=0; j<n; j++) {

				if (row[j] == 1) {
					homeCnt ++;
					home.add(i+","+j);
				}
				else if (row[j] == 2) {
					chickenCnt ++;
					chicken.add(i+","+j);
				}
			}
		}

		// dfs
		select = new boolean[chickenCnt];
		dfs(0, 0);

		// result
		System.out.print(cityDist);
	}




	static void dfs(int size, int selectCnt) {

		if (size == chickenCnt || selectCnt == m) {
			if (selectCnt > 0) calcCityDist();
			return;
		}

		// non-select
		dfs(size+1, selectCnt);

		// select
		select[size] = true;
		dfs(size+1, selectCnt+1);
		select[size] = false;

	}



	static void calcCityDist() {

		homeDist = new int[homeCnt];
		for (int i=0; i<homeCnt; i++) homeDist[i] = Integer.MAX_VALUE;

		for (int i=0; i<chickenCnt; i++) {

			if (!select[i]) continue;
			String[] locCh = chicken.get(i).split(",");

			for (int j=0; j<homeCnt; j++) {

				String[] locH = home.get(j).split(",");

				int rCh = Integer.parseInt(locCh[0]);
				int cCh = Integer.parseInt(locCh[1]);

				int rH = Integer.parseInt(locH[0]);
				int cH = Integer.parseInt(locH[1]);

				homeDist[j] = Math.min(homeDist[j], Math.abs(rCh-rH) + Math.abs(cCh-cH));
			}
		}

		int curCityDist = Arrays.stream(homeDist).sum();
		cityDist = Math.min(cityDist, curCityDist);
	}


}