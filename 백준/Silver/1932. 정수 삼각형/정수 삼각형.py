'''
Condition
    - TL : 2s
    - 

Goals
    - 

Approach
    - dp
        - dp[i][j] = i행 j열을 선택했을 때 최대값
        - 가능한 j == (i-1)행의 (j) or (j+1)
        - dp[i][j] = max(dp[i-1][j], dp[i-1][j+1]) + tri[i][j]
'''
from sys import stdin
input = stdin.readline

def dfs(i:int, j:int):
    if i == n-1:
        return triangle[i][j]
    if not dp[i][j]:
        dp[i][j] = max(dfs(i+1,j), dfs(i+1,j+1)) + triangle[i][j]
    return dp[i][j]

def main():
    global n, triangle, dp
    n = int(input())
    triangle = [list(map(int,input().split())) for _ in range(n)]
    if n == 1:
        return triangle[0][0]
    dp = [[0]*i for i in range(1, n)]
    dfs(0, 0)
    return dp[0][0]

print(main())