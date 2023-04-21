'''
Condition
    - TL : 2s(4000만)
      ML : 128mb
    - n,m <=1000, x,y<=100만

Question
    - 

Access
    - 예상 분류 : 크루스칼, 프림
      실제 분류 : 최소신장트리

    try_1)
        - prim 알고리즘을 사용해본다
        >>> O(N^2logN) 가능
    
    try_2)
        - 전체 신들을 탐색해야한다. 그리고 각각에서 최소연결값을 찾아야한다.
        
    try_3)
        - 이미 연결된 지점들에서 탐색을 시작한다!
        
    try_4)
        - 다시 프림!
'''
from math import sqrt
from heapq import *
from sys import stdin
input = stdin.readline



#define function
def connect():
    for i in range(1,n+1):
        for j in range(1,n+1):
            if i==j:
                continue
            cx,cy = god[i]
            nx,ny = god[j]
            cost = 0
            # 이미 연결된 지점이라면 cost를 0으로 만든다
            if (i,j) not in before and (j, i) not in before:
                cost = sqrt((cx-nx)**2 + (cy-ny)**2)
            graph[i].append((cost,i,j))
            
    
def solution(stt):
    # 가능한 모든 연결 정보를 그래프에 넣음
    connect()

    # 프림 start
    minCost = 0
    visited[stt] = True
    q = graph[stt]
    heapify(q)
    
    # 방문하지 않은 모든 지점에 연결된 간선들중 최솟값을 찾아서 연결함
    while q:
        cost,stt,end = heappop(q)
        if visited[end]:
            continue
        minCost += cost
        visited[end] = True
        
        for data in graph[end]:
            neighbor = data[2]
            if visited[neighbor]:
                continue
            heappush(q,data)
            
    # 간선을 모두 탐색했다면 minCost 반환
    return minCost




#main
n,m = map(int,input().split())
god = [(0,0)] + [tuple(map(int,input().split())) for _ in range(n)]     # 신들의 위치
before = set([tuple(map(int,input().split())) for _ in range(m)])    # 이미 연결된 정보
graph = [[] for _ in range(n+1)] # 연결 관계 정보
visited = [True] + [False]*n

print(f'{solution(1):.2f}')