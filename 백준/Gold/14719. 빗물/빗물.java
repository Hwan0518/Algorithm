import java.io.*;
import java.util.*;



public class Main {

	static BufferedReader br;
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	static int h,w;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		// input
		br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		h = Integer.parseInt(st.nextToken());
		w = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[w];
		for (int i=0; i<w; i++) arr[i] = Integer.parseInt(st.nextToken());

		// find total water
		int highestIdx = findHighestIdx();
		int left = findLeftWater(highestIdx);
		int right = findRightWater(highestIdx);

		// result
		System.out.print(left + right);
	}


	static int findRightWater(int highestIdx) {

		int water = 0;

		int maxV = arr[w-1];

		for (int i=w-2; i>= highestIdx; i--) {

			if (arr[i] > maxV) maxV = arr[i];
			else water += maxV - arr[i];
		}

		return water;
	}


	static int findLeftWater(int highestIdx) {

		int water = 0;

		int maxV = arr[0];
		for (int i=1; i<=highestIdx; i++) {

			if (arr[i] > maxV) maxV = arr[i];
			else water += maxV - arr[i];
		}

		return water;
	}


	static int findHighestIdx() {

		int maxV = arr[0];
		int highestIdx = 0;

		for (int i=1; i<w; i++) {

			if (arr[i] > maxV) {
				maxV = arr[i];
				highestIdx = i;
			}
		}

		return highestIdx;
	}



}