'''
- y는 x보다 항상 크다
- 따라서 계속 증가시키면서 최솟값을 갱신하면 된다
'''
import sys
sys.setrecursionlimit(10**7)
def solution(x, y, n):
    Inf = int(1e9)
    dp = [Inf] *(y+1)
    dp[x] = 0
    def dfs(i):
        nonlocal x,y,n,dp
        if i == x:
            return 0
        elif i == 0:
            return Inf
        if dp[i] == Inf:
            dp[i] = min(
                dfs(max(0, i-n)),
                dfs(i//2) if not i%2 else dp[0],
                dfs(i//3) if not i%3 else dp[0]
            )+1
        return dp[i]
    dfs(y)
    return dp[y] if dp[y] < Inf else -1 