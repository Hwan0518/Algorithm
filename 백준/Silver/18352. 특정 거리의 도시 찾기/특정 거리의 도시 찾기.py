'''
- TL : 2s
- 2<=N,K<= 30만, 1<=M<=100만, 1<=X<=N
- A B는 단방향도로를 의미

>>> 기준도로에서 다른 모든 도로를 조회해야함
>>> N=30만이므로 NlogN까지 가능
>>> 다익스트라를 사용 = (N+E)logN-> O((30만+100만)*18)

1. 다익스트라

'''

from heapq import *
import sys
input = sys.stdin.readline
n,m,k,x = map(int,input().split())

#1. 출발 노드 설정, graph 입력
Inf = int(1e9)
stt=x
graph=[[] for _ in range(n+1)]
for _ in range(m):
    # a->b로 단방향 도로만 존재
    a, b = map(int,input().split())
    graph[a].append((b,1))
    

#2. 최단거리 table, visited table 정의
visited = [False]*(n+1)
distance = [Inf]*(n+1)


#3. 다익스트라 함수 정의
def dijkstra(stt):
    distance[stt] = 0
    visited[stt] = True
    hq = []
    # 힙큐 설정 & 다익스트라 시작
    heappush(hq, (0, stt))
    while hq:
        dist, node = heappop(hq)
        # 큐에서 뽑아낸 최소 거리와, 이미 갱신된 거리를 비교 -> 뽑아낸게 더 크다면 continue
        if distance[node] < dist:
            continue
        # 큐에서 뽑아낸 노드와 연결된 인접 노드 탐색
        for data in graph[node]:
            neighbor = data[0]
            cost = distance[node] + data[1] # 거쳐가는 경로      
            # 거쳐가는 경로가 현재 경로보다 짧다면 갱신
            if cost < distance[neighbor]:
                distance[neighbor] = cost
                heappush(hq, (cost, neighbor))


#4. 거리=k인 node 찾기
dijkstra(stt)
count=0
for i in range(1,n+1):
    if distance[i] == k:
        count +=1
        print(i)
if count==0:
    print(-1)
