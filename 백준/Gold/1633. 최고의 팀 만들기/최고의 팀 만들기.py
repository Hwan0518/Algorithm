'''
Condition
    - TL : 1s(2000만)
      ML :
    - 팀은 총 30명(흑15 + 백15)
    - 플레이어 수 <= 1000

Question
    - 

Access
    - 예상 분류 : dp, 우선순위큐, 그리디
      실제 분류 :

    try_1)
        - dp[i][j][k] = k번째에서 백이i개, 흑이j개 선택됐을 때, 능력치의 합
                      = max(k번째를 선택x, k가 백일때, k가 흑일때)
                      = max(
                          dp[i][j][k-1],
                          dp[i-1][j][k-1] + ability[k][0],
                          dp[i][j-1][k-1] + ability[k][1])
                        
        - 기저조건 : i==0 && j==0 >> 하나도 선택하지 않았으므로 0
                      
'''
from sys import stdin
input = stdin.readline


#Define function
def dfs(i,j,k):
    if (i==0 and j==0) or k==0:
        return 0        
    
    if dp[i][j][k] == -1:
        dp[i][j][k] = max(dfs(i,j,k-1), 
                          (dfs(i-1,j,k-1) + ability[k][0]) if i>0 else 0,
                          (dfs(i,j-1,k-1) + ability[k][1]) if j>0 else 0)
    return dp[i][j][k]



#Main
ability = []
ability.append((0,0))
while True:
    try:
        w,b = map(int,input().split())
        ability.append((w,b))        
    except:
        break
dp = [[[-1]*(len(ability)+1) for _ in range(16)] for _ in range(16)]
dfs(15,15,len(ability)-1)
print(max(dp[15][15]))
