'''
Condition
    - TL: 1s
    - S_i에 시작해서 T_i에 끝나는 N개의 수업
    - 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 함
    - 1<= N <= 20만, 0<=S_i < T_i <= 10^9
    - 끝나는 시간 == 시작시간이면 수강 가능!

Goals
    - 강의실 개수를 출력

Approach
    - 그리디
    - 끝나는 시간 == 시작 시간을 만족시키면 베스트
    - 정렬해보자(내림차순으로) -> 이후 pop 하면서 가능한 강의실을 찾는걸로
        - pop -> 강의실별로 마지막 값의 [1]인덱스 값(T_i) <= pop한 값의 (S_i) 를 만족한다면 -> 해당 강의실에 추가
                                                                    만족하지 않는다면 -> 새로운 강의실을 추가
        - 정렬(O(NlogN)) + 강의를 pop(O(N)) * 모든 강의실 매번 서치(O(sigma(20만)))
        => TLE
    
    - 우선순위큐를 사용해보자
        - hint: https://hongcoding.tistory.com/79
'''
from heapq import *
from sys import stdin
input = stdin.readline

n = int(input())
lectures = [tuple(map(int,input().split())) for _ in range(n)]
# 시작 시간 순으로 정렬
lectures.sort()
# 제일 빨리 시작하고 빨리 끝나는 회의를 집어넣음
endTime = [lectures[0][1]]
# 강의 하나씩 탐색
for i in range(1,n):
    cur_s = lectures[i][0]
    cur_e = lectures[i][1]
    e = endTime[0]
    # 가장 빨리 끝나는 강의 시간 <= 현재 강의 시작 시간
    if e <= cur_s:
        # 가장 빨리 끝나는 강의 시간을 갱신
        heappop(endTime)
        heappush(endTime, cur_e)
    # 가장 빨리 끝나는 강의 시간 > 현재 강의 시작 시간
    else:
        # 새로운 강의실 생성
        heappush(endTime, cur_e)
    
print(len(endTime))
