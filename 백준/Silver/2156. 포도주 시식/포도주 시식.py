'''
Condition
    - TL : 2s
    - 1<=n<=1만, 1<=포도주 양<=1000
    - 시식 규칙
        - 잔을 선택하면, 모두 마셔야하고, 마신후 다시 원래위치로 돌려놔야한다
        - 연속으로 놓여있는 3잔을 "모두" 마실 수는 없다

Question
    - 최대로 마실 수 있는 포도주의 양을 출력

Approach
    - 분류 : dp

try_1) top-down
    - dp[i][j] : i번째 포도주를 마시고 연속j번째일 때의 포도주 양의 최댓값
                = max(dp[i-1][j+1], dp[i-2][0])
    - fail : 왜 안되는지 모르겠음
    
try_2) bottom-up
    - 세 부분으로 나누고, max값을 더한다
        - 이전 포도주를 마시지 않았을 때, 연속 0번 -> 이번에 마신다
            dp[i] = dp[i-2] + wine_list[i]
        - 이전 포도주"만" 마셨을 때, 연속 1번 -> 이번에 마신다
            dp[i] = dp[i-1] + wine_list[i]
        - 앞에 두잔 연속 마셨을 때, 연속 2번
            dp[i] = dp[i-1]
'''
from sys import stdin
input = stdin.readline

def input_data():
    global n, wine_list
    n = int(input())
    wine_list = [0] + [int(input()) for _ in range(n)]
    
def solution():
    input_data()
    dp = [0] * (n+1)
    dp[1] = wine_list[1]
    if n < 2:
        return max(dp)
    dp[2] = wine_list[1] + wine_list[2]
    for i in range(3, n+1):
        fst = dp[i-2] + wine_list[i]
        snd = dp[i-3] + wine_list[i-1] + wine_list[i]
        trd = dp[i-1]
        dp[i] = max(fst, snd, trd)
    return max(dp)

print(solution())