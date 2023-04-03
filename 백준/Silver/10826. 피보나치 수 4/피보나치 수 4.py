'''
Condition
    - TL : 1s(2000만)
      ML : 256(64*100만)
    - 0<=n<=1만

Question
    - n번째 피보나치 수를 구하여라

Access
    - 분류 : dp

    try_1)
        - bottom-Up 형식으로 구현해본다
            >>> O(n), 성공
            
        
'''

#define function
def solution():
    if n==0:
        return 0
    elif n==1:
        return 1
    
    dp[1] = 1
    for i in range(2,n+1):
        dp[i] = dp[i-1] + dp[i-2]

    return dp[n]
    

#main
n = int(input())
dp = [0 for _ in range(n+1)]
print(solution())