'''
Condition
    - TL: 1s
    - 

Goals
    - 

Approach
    - 플로이드워셜 O(N^3) = 100만

'''
from sys import stdin
input = stdin.readline

def input_data():
    n = int(input())
    # 그래프 생성
    graph = []
    for _ in range(n):
        graph.append(list(map(int,input().split())))
    return n, graph

def main(n:int, graph:list[list]):
    # 플로이드워셜 탐색
    for k in range(n):
        for r in range(n):
            for c in range(n):
                if graph[r][c] or (graph[r][k] == 1 and graph[k][c] == 1):
                    graph[r][c] = 1
    # 정답 출력
    for rows in graph:
        print(*rows)

main(*input_data())