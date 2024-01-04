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
    : 2^n -> TLE, 메모이제이션을 활용해보자
    
2. DP (Bottom-up) : reference = https://hongcoding.tistory.com/116
    dp[i][j] = i, j에서의 갯수
             = dp[i-1][j] + dp[i][j-1]
'''
from sys import stdin
input = stdin.readline

def input_data():
    n,m,k = map(int,input().split())
    return n,m,k

def non_target(n, m):
    for r in range(n):
        for c in range(m):
            if r==0 and c==0:
                dp[r][c] = 1
                continue
            dp[r][c] = dp[r-1][c] + dp[r][c-1]

def target(stt_r, stt_c, target_r, target_c):
    for r in range(stt_r, target_r+1):
        for c in range(stt_c, target_c+1):
            if r==0 and c==0:
                dp[r][c] = 1
                continue
            dp[r][c] = dp[r-1][c] + dp[r][c-1]    
    

def solution(n,m,k):
    global dp
    dp = [[0]*m for _ in range(n)]
    if k:
        target_r = (k-1)//m
        target_c = (k-1)%m
        target(0, 0, target_r, target_c)
        target(target_r, target_c, n-1, m-1)
        return dp[n-1][m-1]
    else:
        non_target(n, m)
        return dp[n-1][m-1]
        
print(solution(*input_data()))
