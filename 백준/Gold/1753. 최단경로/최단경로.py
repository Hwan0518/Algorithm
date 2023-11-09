'''
dijkstra


'''
from sys import stdin
from heapq import *
input = stdin.readline

def inputData():
    v,e = map(int,input().split())
    stt = int(input())
    graph =[[]*(v+1) for _ in range(v+1)]
    for _ in range(e):
        s,e,w = map(int,input().split())
        graph[s].append((e,w))
    return v,e,stt,graph

def solution(v,e,stt,graph):
    global distance
    Inf = int(1e9)
    distance = [Inf] * (v+1)
    
    def dijkstra(stt, graph):
        q = []
        heappush(q, (0, stt))
        distance[stt] = 0
        while q:
            # 가장 짧은 거리를 뽑아냄
            dist, vertex = heappop(q)
            # 기존값과 뽑아낸 값을 비교 -> 뽑아낸 값보다 기존값이 더 작다면 continue
            if distance[vertex] < dist:
                continue
            # 뽑아낸 vertex와 연결된 인접 노드들을 탐색
            for data in graph[vertex]:
                neighbor = data[0]
                cost = distance[vertex] + data[1]
                # 더 작은 cost로 갱신된다면 q에 추가
                if cost < distance[neighbor]:
                    distance[neighbor] = cost
                    heappush(q, (cost, neighbor))
        return
    dijkstra(stt, graph)
    for c in distance[1:]:
        if c == Inf:
            print("INF")
        else:
            print(c)
    return

solution(*inputData())
    