'''
Condition
    - TL: 1s
    - 밀 수 있는 방향: 아래, 오른, 오른아래
    - 회전 방향: 45도 (4번 돌면 그다음이 같아짐)
    - 이동 방법
        - 파이프가 가로일 때: 오른, 오른아래
        - 파이프가 세로일 때: 아래, 오른아래
        - 파이프가 대각선일 때: 오른, 아래, 오른아래
    - 오른아래일 때 4칸이 모두 빈칸이어야함

Goals
    - 파이프 한쪽 끝을 (N,N)으로 이동시키는 방법의 개수는?

Approach
    - 백트래킹으로 해보자 ->x
    - dp로 풀이
        - dp[i][j][k] = (i,j)가 k모양일 때 도달 가능한 경우의 수 (k=0,1,2로 아래,오른,대각 이다)
                    = k=0일때 세로,대각에서 오는 경우의 수 + k=1일때 가로,대각에서 오는 경우의 수 + k=2일때 세로,가로,대각에서 오는 경우의 수
                    = dp[i-1][j][0] + dp[i-1][j-1][2]
                     +dp[i][j-1][1] + dp[i-1][j-1][2]
                     +dp[i-1][j][0] + dp[i][j-1][1] + dp[i-1][j-1][2]
'''
from sys import stdin
input = stdin.readline

n = int(input())
graph = [list(map(int,input().split())) for _ in range(n)]

# 아래, 오른, 오른아래
dr = [1,0,1]
dc = [0,1,1]

cnt = 0
# dp 초기값 설정
dp = [[[0]*3 for _ in range(n)] for _ in range(n)]
dp[0][1][1] = 1
# 0행은 벽을 만나기 전까지 모두 1로 된다
for j in range(2,n):
    if graph[0][j] == 0:
        dp[0][j][1] = dp[0][j-1][1]

for i in range(1,n):
    for j in range(1,n):
        # 대각선 파이프 놓기 -> 회전 가능 여부를 확인
        if graph[i][j] == 0 and graph[i][j-1] == 0 and graph[i-1][j] == 0:
            dp[i][j][2] = sum(dp[i-1][j-1])
        if graph[i][j] == 0:
            # 세로 파이프 놓기
            dp[i][j][0] = dp[i-1][j][0] + dp[i-1][j][2]
            # 가로 파이프 놓기
            dp[i][j][1] = dp[i][j-1][1] + dp[i][j-1][2]

print(sum(dp[n-1][n-1]))