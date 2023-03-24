'''
Condition
    - TL : 2s(4000만)
      ML : 128(32*100만)
    - 1<=n<=30

Question
    - 3*n 크기를 2*1, 1*2로 채우는 경우의 수를 구하여라

Access
    - 예상 분류 : dp
      실제 분류 : dp

    try_1)
        - dp[n][i] = n번째에서 2*1타일을 i개 썼을때 벽을 채우는 경우의 수
            dp[1][i] = 0
            
            dp[2][0] = 0
            dp[2][1] = 2
            dp[2][2] = 0
            dp[2][3] = 1
            
            .
            .
            .
            dp[n][0] = 0
            if n%2 ==0:
                dp[n][1] = dp[n-2] +2
                dp[n][2] = dp[n-2]
                dp[n][3] = dp[n-2] +1
            else:
                dp[n] = 0 
    
    try_2)
        - clone coding : https://jyeonnyang2.tistory.com/51
        
'''
#define function
def solution():
    dp[2] = 3
    for  i in range(4,n+1):
        if i%2 == 0:
            dp[i] = dp[i-2]*3 + sum(dp[:i-2])*2 +2
        else:
            dp[i] = 0
    
            

#main
n=int(input())
dp = [0] + [0 for _ in range(n+1)]
solution()
print(dp[n])