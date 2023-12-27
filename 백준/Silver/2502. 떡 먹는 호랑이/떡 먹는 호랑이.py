'''
요구사항
    - TL : 1s
    - 3<=D<=30, 10<=K<=10만
    - D째 날에 준 떡의 개수가 K개이다
    - 준 떡의 수 : i번째 날 = (i-2)번째 날 + (i-1)번째 날
    - 항상 a<=b이다
    - 첫 날에 준 떡의 개수와 둘째 날에 준 떡의 개수를 출력하여라

접근
    - DP로 풀자

1. DP(bottom-up)
    - dp[i] = dp[i-1] + dp[i-2]
    - dp[1] = a, dp[2] = b라 했을 때, a b의 갯수를 알 수 있다
    - 이때 a<=b이고, 가능한 b의 개수가 k//cnt_b이므로 완전탐색을 해본다
    - 최댓값이 10만이므로 O(N)으로 가능하다
'''
def input_data():
    d,k = map(int,input().split())
    return d,k

def solution(d,k):
    dp = [[0,0] for _ in range(d+1)]
    dp[1][0] = 1
    dp[2][1] = 1
    for i in range(3,d+1):
        dp[i][0] = dp[i-2][0] + dp[i-1][0]
        dp[i][1] = dp[i-2][1] + dp[i-1][1]
    # a,b의 갯수가 정해졌음 -> a<=b이므로, b를 먼저 구하고 a를 구한다
    cnt_a = dp[d][0]
    cnt_b = dp[d][1]
    max_b = k//cnt_b
    for i in range(1, max_b):
        b = i
        a = (k - b*cnt_b) // cnt_a
        if a>=1 and b>=a and (a*cnt_a) + (b*cnt_b) == k:
            print(a)
            print(b)
            break

solution(*input_data())