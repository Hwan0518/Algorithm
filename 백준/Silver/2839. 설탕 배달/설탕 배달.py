'''
Approach
    - 설탕을 n kg 정확하게 배달
    - 3kg, 5kg존재
    - 최대한 적은 봉지를 들고가려함
    - 3<=n<=5000
    
>>> dp, 기저값을 잘 설정해보자
'''
import sys
sys.setrecursionlimit(10**6)

n = int(input())
dp = [0]*(n+1)
def dfs(i):
    if i==3:
        dp[i] = 1
    elif i==4 or i<3:
        dp[i] = int(1e9)
    elif i==5:
        dp[i] = 1
    
    if not dp[i]:
        dp[i] = min(dfs(i-3)+1, dfs(i-5)+1)
    return dp[i]
dfs(n)

if dp[n] >= int(1e9):
    print(-1)
else:
    print(dp[n])
        
    