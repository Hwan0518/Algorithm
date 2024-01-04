'''
요구사항
    - TL : 1s
    - 행 1<=N<=15
    - 열 1<=M<=15
    - O표시는 1,N*M 칸에는 표시x, 0 or 1개 존재
    - 로봇 이동
        - 1번에서 출발해서 N*M번 칸으로 가려고 함
        - 조건1: 한번에 오른쪽 or 아래로만 이동할 수 있음
        - 조건2: O가 존재한다면, 반드시 그 칸을 지나가야함
    - 이동할 수 있는 서로 다른 경로의 수를 찾으시오
    
접근
    - 백트래킹
    - 1에서 O까지 가는 경우의 수 * O에서 N*M까지 가는 경우의 수를 구하면 된다

1. 백트래킹
'''
from sys import stdin
input = stdin.readline

def input_data():
    n,m,k = map(int,input().split())
    graph = [[j for j in range((i-1)*m+1, i*m+1)] for i in range(1,n+1)]
    return n,m,k,graph

def dfs(cr, cc, visited, graph, target, r_target, c_target): 
    global result
    if graph[cr][cc] == target:
        result +=1
        return
    
    for i in range(2):
        nr = cr+dr[i]
        nc = cc+dc[i]
        if nr > r_target or nc > c_target or visited[graph[nr][nc]]:
            continue
        visited[graph[nr][nc]] = True
        dfs(nr, nc, visited, graph, target, r_target, c_target)
        visited[graph[nr][nc]] = False
    

def solution(n,m,k,graph):
    global dr, dc, result
    dr = [1, 0]
    dc = [0, 1]
    result = 0
    visited = [False] * (n*m+1)
    if k >=1:
        dfs(0, 0, visited, graph, k, k//m, k%m-1)
        first = result
        result = 0
        dfs(k//m, k%m-1, visited, graph, n*m, n-1, m-1)
        second = result
        return first * second
    else:
        result = 0
        dfs(0, 0, visited, graph, n*m, n-1, m-1)
        first = result
        return first
    

print(solution(*input_data()))
