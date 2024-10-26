'''
dp인듯

dp[i] = x를 i로 변환하는데 드는 최소 연산 횟수
         = min(dp[i-n], dp[i//2], dp[i//3]) +1
'''
def solution(x, y, n):
    Inf = int(1e9)
    dp = [Inf]*3*y
    dp[x] = 0
    # 전처리
    for z in (n,x,2*x):
        cnt = 0
        for i in range(x,3*y,z):
            if dp[i] == Inf:
                dp[i] = cnt
            else:
                dp[i] = min(dp[i],cnt)
            cnt +=1
    # dp
    for i in range(x,y+1):
        # i-n
        if i-n >=0:
            a = dp[i-n]
        else:
            a = Inf
        # i//2
        if not i%2:
            b = dp[i//2]
        else:
            b = Inf
        # i//3
        if not i%3:
            c = dp[i//3]
        else:
            c = Inf
        # 갱신
        dp[i] = min(dp[i],a+1,b+1,c+1)
    return -1 if dp[y] == Inf else dp[y]