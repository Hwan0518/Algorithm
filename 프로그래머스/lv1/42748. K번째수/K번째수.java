/*
- i부터 j까지 잘랐을 때, k번째 있는 수를 구한다
    1. i부터 j까지 자른다 = [i-1:j]
    2. 자른 배열을 정렬한다
    3. k번째 수를 구한다 = arr[k-1]  
*/

import java.util.*;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        ArrayList<Integer> answer = new ArrayList<>();
        
        // commands에 따라 수행
        for (int[] com : commands) {
            int i,j,k;
            i = com[0];
            j = com[1];
            k = com[2];
            // i부터 j까지 자름
            ArrayList<Integer> newArr = new ArrayList<>();
            for (int stt=i; stt<=j; stt++){
                newArr.add(array[stt-1]);
            }
            // 정렬
            Collections.sort(newArr);
            // k번째 수를 구해서 answer에 저장
            answer.add(newArr.get(k-1));
        }
        int arrSize = answer.size();
        int[] arr = new int[arrSize];
        for (int idx=0; idx < arrSize; idx++ ){
            arr[idx] = answer.get(idx);
        }

        return arr;
    }
}