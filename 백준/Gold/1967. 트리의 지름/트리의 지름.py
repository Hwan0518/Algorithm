'''
Condition
    - TL: 2s

Goals
    - 트리의 지름을 출력

Approach
    - leaf to leaf?
        - no. leaf가 하나인 경우도 존재
    
    - 우선순위 큐
        1. 우선순위 큐를 사용하여, cost가 높은 것 부터 순서대로 꺼낸다
        2. 꺼낸 node에서 가장 먼저 연결되는 두 leaf node의 경로가 트리의 지름이다
        >> fail, cost가 가장 높더라도 아닌 경우가 존재함 
        
    - hint: https://www.acmicpc.net/board/view/136998
        - 어느 한 점에서 가장 먼 정점을 선택, 그 정점에서 가장 먼 정점을 찾아서 거리를 측정하면 그것이 트리의 지름이 된다!
            - 트리의 지름 안에 모든 다른 점들이 들어가있다
            - 따라서, 어떤 점이든 선택한 후 가장 멀리있는 정점을 고른다면, 그 점은 트리 지름 노드 중 하나이다
            - 그리고 트리 지름 노드에서 가장 먼 정점은, 또 다른 트리 지름 노드가 된다
'''
from heapq import *
from sys import stdin
input = stdin.readline

n = int(input())
graph = [[] for _ in range(n+1)]
for i in range(n-1):
    p, c, cost = map(int,input().split())
    graph[p].append((c, cost))
    graph[c].append((p, cost))

# 예외처리
if n == 1:
    print(0)
    exit()

# 다익스트라로 최대거리 탐색 *2
def dijkstra(stt):
    diameterNode = 1
    distance = [int(1e9)]*(n+1)
    distance[0] = 0
    distance[stt] = 0
    hq = []
    hq.append((0,stt))
    while hq:
        dist, node = heappop(hq)
        if distance[node] < dist:
            continue
        for data in graph[node]:
            neighbor = data[0]
            cost = distance[node] + data[1]
            if cost < distance[neighbor]:
                distance[neighbor] = cost
                heappush(hq, (cost, neighbor))
    maxDist = max(distance)
    diameterNode = distance.index(maxDist)
    return diameterNode, maxDist

diameterNode, maxDist = dijkstra(1)
print(dijkstra(diameterNode)[1])
