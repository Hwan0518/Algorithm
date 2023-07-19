'''
Approach
    - N x N 표에서 n번째 큰 수를 찾아라
    - 모든 수는 자신의 한칸 위에있는 수보다 크다


>>> 완탐으로 가능하긴 한데..
    : 첫번째줄을 sort해서 내림차순 정렬
      다음줄부터 첫째줄과 비교해서 밀어내기 실행
      >>> 최악의 경우 O(n^3)으로 TLE

>>> 그냥 완탐
    : MLE
    
>>> 첫번째 줄을 기준으로 세로 탐색을 실시
    : 첫번째줄에서 가장 큰 수부터 역으로 탐색을 실시한다
      만약 첫째줄이 (1,2,3,4,5) 라면
      list[4][4] -> list[4][3] -> list[4][2] ... 이런식으로 탐색을 시작
        >>> 최선이라고 볼 수 없음

>>> 우선순위큐를 사용해야한다
    :
'''
from heapq import *
from sys import stdin
input = stdin.readline

def solution():
    n = int(input())
    lst = []
    for _ in range(n):
        for i in list(map(int,input().split())):
            heappush(lst,i)
            if len(lst)>n:
                heappop(lst)
    lst.sort()
    return lst[0]

print(solution())                   