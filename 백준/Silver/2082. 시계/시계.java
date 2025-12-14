/*
 * 모든 숫자는 5 row로 이루어짐
 * 각 숫자에 대해, i번째 row에서 몇번째에 #이 들어있는지를 기록
 * 이후, 0~9까지 완전탐색을 돌려서 입력한 값이 어디에 대응 가능한지를 보자
 * 이때 가장 빠른 시간에 매칭시켜야하니까, 0부터 9까지 순서대로 탐색하면 된다
 */

import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static int[][][] numbers = new int[10][5][3];
	static int[][][] inputs = new int[4][5][3];

	public static void main(String[] args) throws IOException {

		// set-up
		String[] nums = {
			"###  ..#  ###  ###  #.#  ###  ###  ###  ###  ###",
			"#.#  ..#  ..#  ..#  #.#  #..  #..  ..#  #.#  #.#",
			"#.#  ..#  ###  ###  ###  ###  ###  ..#  ###  ###",
			"#.#  ..#  #..  ..#  ..#  ..#  #.#  ..#  #.#  ..#",
			"###  ..#  ###  ###  ..#  ###  ###  ..#  ###  ###"
		};

		// assign
		for (int i=0; i<5; i++) {

			String[] s = nums[i].split("  ");
			for (int j=0; j<=9; j++) {

				String ss = s[j];
				for (int k=0; k<3; k++) {

					int v = ss.charAt(k) == '#' ? 1 : 0;
					numbers[j][i][k] = v;
				}
			}
		}

		// input
		for (int i=0; i<5; i++) {

			String[] s = br.readLine().split(" ");
			for (int j=0; j<4; j++) {

				String ss = s[j];
				for (int k=0; k<3; k++) {

					int v = ss.charAt(k) == '#' ? 1 : 0;
					inputs[j][i][k] = v;
				}
			}
		}

		// compare -> numbers에 없는 '1'이 inputs에 존재하면 불가능
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<4; i++) {

			int[][] broken = inputs[i];
			for (int j=0; j<=9; j++) {

				int[][] base = numbers[j];
				boolean possible = true;
				loop: for (int k=0; k<5; k++) {
					for (int l=0; l<3; l++) {

						int baseV = base[k][l];
						int brokenV = broken[k][l];

						// base가 0인데, broken이 1이라면 불가능 (반대는 가능)
						if (baseV == 0 && brokenV == 1) {
							possible = false;
							break loop;
						}
					}
				}

				// 해당 숫자 가능하다면 바로 입력하고 종료 (가장 빠른 값이므로)
				if (possible) {
					// 숫자 추가
					sb.append(j);
					// : 추가
					if (sb.length() == 2) sb.append(":");
					break;
				}


			}
		}

		// result
		System.out.print(sb);
	}

}