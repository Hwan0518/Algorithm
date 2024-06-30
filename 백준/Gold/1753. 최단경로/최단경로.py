'''
Condition
    - TL: 1s
    - 

Goals
    - 시작점에서 다른 모든 정점으로의 최단 경로의 경로값을 출력

Approach
    - dijkstra
    - TC = O((V+E)logV) = O(약 32만)
'''
from sys import stdin
from heapq import *
input = stdin.readline

def input_data():
    v,e = map(int,input().split())
    s = int(input())
    graph = [[] for _ in range(v+1)]
    for _ in range(e):
        stt,end,cost = map(int,input().split())
        graph[stt].append((end,cost))
    return v,e,s,graph

def dijkstra(s:int, graph:list[list]):
    global distance
    hq = []
    heappush(hq, (0, s))
    while hq:
        dist, node = heappop(hq)
        if distance[node] < dist:
            continue
        # node의 인접 노드를 탐색
        for data in graph[node]:
            neighbor = data[0]
            cost = distance[node] + data[1]
            if cost < distance[neighbor]:
                distance[neighbor] = cost
                heappush(hq, (cost, neighbor))
    return

def main(v:int, e:int, s:int, graph:list[list]):
    global distance
    INF = int(1e9)
    distance = [INF] * (v+1)
    distance[s] = 0
    dijkstra(s, graph)
    # 출력
    for i in range(1,v+1):
        print(distance[i] if distance[i]!=INF else "INF")
    return

main(*input_data())