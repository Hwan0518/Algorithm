'''
Condition
    - TL: 0.5s
    - N(V) <= 1000, M(E) <= 10만

Goals
    - 출발 도시에서 도착 도시까지 가는데 드는 최소 비용은?

Approach
    - 다익스트라
        - O((V+E)logV) ~= O(10만)
'''
from heapq import *
from sys import stdin
input = stdin.readline

n = int(input())
m = int(input())
graph = [[] for _ in range(n+1)]
for _ in range(m):
    s,e,c = map(int,input().split())
    graph[s].append((e,c))
a,b = map(int,input().split())
distance = [int(1e9)]*(n+1)

def dijkstra(a:int):
    hq = []
    heappush(hq, (0, a))
    distance[a] = 0
    while hq:
        dist, node = heappop(hq)
        if distance[node] < dist:
            continue
        for data in graph[node]:
            destination = data[0]
            cost = distance[node] + data[1]
            if cost < distance[destination]:
                distance[destination] = cost
                heappush(hq, (cost, destination))

dijkstra(a)
print(distance[b])