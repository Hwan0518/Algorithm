'''
Condition
    - TL : 1s(2000만)
      ML :
    - 1<=k<=n<=30

Question
    - 파스칼 삼각형의 n번째 행의 k번째 수는 무엇인가?
      이 수는 이항계수이다

Access
    - 분류 : dp

    try_1)
        - bottom-Up으로 풀어본다
        >>> success
        
    try_2)
        - top-Down으로 풀어본다
        
'''



#define function
def bottomUp():
    if n==1:
        return 1
    
    dp[1].append(1)
    for i in range(2,n+1):
        dp[i].append(1)
        for j in range(1,i-1):
            dp[i].append(dp[i-1][j-1]+dp[i-1][j])
        dp[i].append(1)
    
    return dp[n][k-1]


def topDown(n:int,k:int) -> int:
    if n ==1:
        return 1
            
    if dp[n][k-1]:
        return dp[n][k-1]
    dp[n][k-1] = topDown(n-1,k-1) + topDown(n-1,k)
    return dp[n][k-1]



#main
n,k = map(int,input().split())
dp = [[0 for _ in range(i)] for i in range(n+1)]

# print(bottomUp())
for row in range(1,n+1):
    dp[row][0] = 1
    dp[row][row-1] = 1
    
for i in range(1,n):
    topDown(n,i)
print(dp[n][k-1])
