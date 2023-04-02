'''
Condition
    - TL : 2s(4000만)
      ML : 512(128*100만)
    - 1<=n<=10만

Question
    - 거슬러주는 동전의 최소개수를 구하여라(2,5원짜리로만)
      거슬러줄 수 없다면 -1을 출력

Access
    - 분류 : dp, 수학

    try_1)
        - n이 2,5,7의 배수가 아니라면 -1출력
        - dp[i] = 거스름돈이 i일때, 최소 동전의 개수
                = 최대5원의 개수 + 최소2원의 개수
        - 5원을 최대한 많이 써야한다
            : quotient = (n//5)
              remainder = n-(quotient*5)
            while remainder==1or3:
                quotient -=1
                remainder = n-(quotient*5)
            result = quotient + remainder//2
        
                
        
'''
"""수학풀이"""
#define function
def solution():
    # 5의 배수인지 확인
    if n%5 ==0:
        return n//5
    
    # 5의 배수가 아니라면, (5로 나눈 나머지)가 2의 배수인지 확인
    remainder = n%5
    if remainder %2 ==0:
        return (n//5) + (remainder//2)
    
    # 나머지가 2의 배수가 아니라면, (5로 나눈 나머지+5)가 2의 배수인지 확인
    if (remainder+5)%2 ==0 and n>5:
        return (n//5-1) + (remainder+5)//2
    
    # 아니라면 -1출력
    return -1    


"""dp풀이 : bottom-up"""
#define function
def bottomUp():
    for i in range(6,n+1):
        if dp[i-5]== -1 and dp[i-2]!= -1:
            dp[i] = dp[i-2] +1
        elif dp[i-5]!= -1:
            dp[i] = dp[i-5] +1
        else:
            dp[i] = -1
    
    return dp[n]
    




#main
n=int(input())
dp = [0,-1,1,-1,2,1] + [0 for _ in range(n+1)]
print(solution())
# print(bottomUp())

"""
반례)
1:-1
2:1
3:-1
4:2
5:1
6:3
7:2
8:4
9:3
10:2
"""


