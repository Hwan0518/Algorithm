'''
Condition
    - TL : 2s(4000만)
      ML : 128(32*100만)
    - 1<N,M<200
      -1만<= 정수 <=1만 
     
Question
    - 주어진 n*m행렬에서, 가능한 부분행렬에서 원소들의 최대 합은?
    
Access
    try_1)
        - 예상 분류 : dp or 백트래킹(X,TLE 뜰듯)
          문제 분류 : dp, 브루트포스, 누적합

        - DP로 풀어보자!
            : dp[n][m] = 행이 n이고 열이 m인 부분행렬의 내부에서 합의 최대
              bottom-up 방식을 사용해볼까?
              
              행렬을 matrix라 하면
              1. dp[0][0] = matrix[0][0]
                 dp[0][1] = max(dp[0][0], dp[0][0] + matrix[0][1])
                 dp[1][0] = max(dp[0][0], dp[0][0] + matrix[1][0])
                 dp[1][1] = max(dp[1][0], dp[0][1], dp)
                 
                 dp[r][c] = if r==c : max(dp[r][c-1], dp[r-1][c], )
                            if r>c  : max(dp[r-1][c], dp[r-1][c] + matrix[r][c])
                            if r<c  : max(dp[r][c-1], dp[r][c-1] + matrix[r][c])
            >>> 모르겠다!!
            
        - 2차원 배열의 누적합으로 해보자!
            reference : https://data-make.tistory.com/399
            
            : dp + 누적합으로 계산할 수 있다
            
            1. 단순 누적합
                dp[i][j] = (i,j)까지의 누적합
                         = (i-1,j)까지의 누적합 + (i,j-1)까지의 누적합 + (i,j)행렬값 - (i-1,j-1)까지의 누적합 (>> 두번 더해지기 때문에)
                         = dp[i-1][j] + dp[i][j-1] + matrix[i][j] - dp[i-1][j-1]
            
            2. 부분 누적합
                dp[r][c] = (i,j)부터 (r,c)까지의 부분합
                         = dp[r][c] - dp[r][j-1] - dp[i-1][c] + dp[i-1][j-1]

'''
from sys import stdin
input = stdin.readline



#define function
def solution():
    maxSum = matrix[0][0]
    
    #bottom-up 방식으로 누적합을 완성
    for i in range(1,n+1):
        for j in range(1,m+1):
            dp[i][j] = matrix[i-1][j-1] + dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1]
    
    #bottom-up 방식으로 부분합중 최댓값을 구한다
    for r in range(1,n+1):
        for c in range(1,m+1):
            for i in range(1,r+1):
                for j in range(1,c+1):
                    prefixSum = dp[r][c]-dp[r][j-1]-dp[i-1][c]+dp[i-1][j-1]
                    maxSum = max(maxSum,prefixSum)    
                   
    return maxSum



#main
n,m = map(int,input().split())
matrix = [list(map(int,input().split())) for _ in range(n)]
dp = [[0 for _ in range(m+1)] for _ in range(n+1)] #누적합 행렬이다

print(solution())