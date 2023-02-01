'''
Condition
    - TL : 1s (2000만)
      ML : 128(32*10만)
    - 1<=K<=45
    - A는 B로, B는 BA로
    - 분류 : dp

Question
    - 버튼을 K번 눌렀을 때, 화면에 A와 B의 개수는?

Access
    - 분류가 dp이므로, 전체를 해결하기 위한 부분 문제를 찾는다
    - display[k] = 버튼을 k번 눌렀을 때 화면에 표시되는 문자열, 이라 했을 때
        display를 나열해보면 display[k] = display[k-1] + display[k-2]인 규칙을 알 수 있다
    - 따라서 위를 사용해서 dp[k] = 버튼을 k번 눌렀을 때 화면에 나오는 A,B의 개수라 하면
        dp[k] = dp[k-1] + dp[k-2]이다
'''
#define function
def solution():
    for i in range(2,k+1):
        dp[i] = [dp[i-2][0]+dp[i-1][0], dp[i-2][1]+dp[i-1][1]]
    return dp[k]

#main
k=int(input())
dp=[[1,0],[0,1]] + [[] for _ in range(k-1)]
print(*solution())