'''
Condition
- 지름길은 일방통행
- 역주행 불가능
- n<=12, d<=1만

Goals
- 운전해야 하는 거리 최솟값

Approach
- greedy? -> dp로 풀어보자
- dp : i번 지름길을 택했을 때 최소로 움직인 거리 (선택한 경우 or 선택 안한 경우)
'''
def bottom_up():
    global dp
    # i=0인 지점부터 i=d인 지점까지 확인
    for i in range(d+1):
        # dp[i] = i지점에서 현재값과 i-1 지점으로부터 +1 이동했을때를 비교
        dp[i] = min(dp[i-1]+1, dp[i])
        # 지름길을 확인
        for stt, end, dist in short_cut:
            if i == stt and end <= d:
                # dp[end] = end까지 이동할 때, 지름길을 택한것과 택하지 않은 것 중 최솟값
                dp[end] = min(dp[end], dp[i]+dist)

def solution():
    global d, short_cut, dp
    n,d = map(int,input().split())
    short_cut = []
    for _ in range(n):
        short_cut.append(list(map(int,input().split())))
    # 시작점 순으로 정렬
    short_cut.sort()
    # dp는 i 지점에서 이동거리의 최솟값이다. 초기값은 그냥 i로 맞춰준다(i = 고속도로 i미터 지점을 의미)
    dp = [i for i in range(d+1)]
    bottom_up()
    return dp[-1]
    
print(solution())