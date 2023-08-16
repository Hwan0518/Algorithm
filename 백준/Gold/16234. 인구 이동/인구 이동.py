'''
Approach
    - n*n크기의 땅, 각 땅에는 나라가 하나씩 존재
    - r행 c열에는 A[r][c]명이 살고있음
    - 인접한 나라 사이에는 국경선이 존재함
    - 인구이동
        - L<= 국경선에 맞닿은 두 나라 인구차 <= R 이라면, 국경선을 하루동안 연다
        - 모든 국경선을 탐색했다면 인구이동을 시작함
        - 국경선이 열린 두 나라를 연합이라고함
          연합을 이루는 각 칸의 인구수 = (연합의 인구수) / (연합을 이루는 칸의 수), 소수점은 버림
        - 연합을 해체하고, 모든 국경선을 닫음
    - 1<=N<=50, 1<=L<=R<=100, 시작인구<=100, 인구이동 최대는 2000
    >>> 인구이동이 며칠동안 발생하는가?
    
>>> bfs를 통해서 연합을 구해본다
        1-1. 조건을 만족하면 이동 시작, cnt +=1
        1-2. 조건을 만족하지 못한다면, cnt 증가없이 종료
        2. 연합을 모두 구하고, '이동 후 연합 인구수'를 계산함
        3. 연합의 모든 칸을 '이동 후 연합 인구수'로 업데이트함
        4. 다시 1~3을 반복
'''
from sys import stdin
from collections import deque
input = stdin.readline


def inputData():
    n,l,r = map(int,input().split())
    country = [list(map(int,input().split())) for _ in range(n)]
    return n,l,r,country

def findUnion(n,l,r,country):
    global dr,dc
    visited = [[False]*n for _ in range(n)]
    unionList = []
    for row in range(n):
        for col in range(n):
            if visited[row][col]:
                continue
            visited[row][col] = True
            union = []
            allUnionCnt = 0
            dq = deque()
            union.append((row,col))
            dq.append((row,col))
            while dq:
                cr,cc = dq.popleft()
                cur_cnt = country[cr][cc]
                allUnionCnt += cur_cnt
                for i in range(4):
                    nr,nc = cr+dr[i], cc+dc[i]
                    # 조건에 부합하는지 확인
                    if not(0<=nr<n and 0<=nc<n) or visited[nr][nc] or not(l<=abs(cur_cnt - country[nr][nc])<=r):
                        continue
                    visited[nr][nc] = True
                    union.append((nr,nc))
                    dq.append((nr,nc))
            if len(union) > 1:
                union.append(allUnionCnt)
                unionList.append(union)
    return unionList
                    
        

def solution(n,l,r,country):
    global dr,dc
    dr = [-1,1,0,0]
    dc = [0,0,-1,1]
    day = 0    
    
    flag = True
    while flag:
        # 탐색 시작
        unionList = findUnion(n,l,r,country)
        if not unionList: flag = False
        else: day+=1
        # 이동 시작
        while unionList:
            union = unionList.pop()
            unionCnt = union.pop()
            for posit in union:
                row,col = posit
                country[row][col] = unionCnt//len(union)            
    return day


print(solution(*inputData()))
