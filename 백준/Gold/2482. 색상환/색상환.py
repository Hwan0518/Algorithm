'''
Condition
    - TL : 1s(2000만)
      ML : 128(32*100만)  
    - 4<=N<=1000, 1<=K<=N

Question
    - N색상환에서, K개의 색을 고를 수 있는 경우의수%(10억3)은?

Access
    - 예상 분류 : dp
      실제 분류 : dp
      
    try_2) 클론코딩 : https://ca.ramel.be/149
        - dp[i][j] : i개의 색 중에 j개를 선택하는 경우의 수
                    = i번째를 선택한 경우 + i번째를 선택하지 않은 경우
                    = (i를 선택해서 i-1을 선택못하고 뽑아야 하는 나머지가 j-1개이므로, i-2개 색중에서 j-1개를 선택)
                      + (i를 선택안해서 i-1부터 가능하고 뽑아야 하는 나머지가 j개이므로, i-1개 색중에 j개를 선택하는 경우의 수)
                    = dp[i-2][j-1] + dp[i-1][j]
        - 여기서 i==n이라면, i가 끝지점이므로 첫번째 색도 선택을 못한다
                    if i ==n :
                        dp[i][j] = i번째 선택한 경우 + i번째 선택하지 않은 경우
                                 = (i-1과,1을 선택못할때 j-1개를 뽑는경우)
                                   + (i를 선택안해서 1과 i-1 다뽑을 수 있을때 j개를 뽑는경우)
                                 = dp[i-3][j-1] + dp[i-1][j]
'''
#define function
def solution():
    dp=[[0 for _ in range(k+1)] for _ in range(n+1)]
    
    # 색을 0,1개 뽑는 경우 갱신
    for i in range(n+1):
        dp[i][0] = 1
        dp[i][1] = i
    
    # 전체 탐색
    for i in range(2,n+1):
        for j in range(2,k+1):
            if i == n:
                dp[i][j] = dp[i-3][j-1] + dp[i-1][j]
            else:
                dp[i][j] = dp[i-2][j-1] + dp[i-1][j]
            dp[i][j] %= divisor

    return dp[n][k]





#main
n=int(input())
k=int(input())
divisor = 1_000_000_003
print(solution())
