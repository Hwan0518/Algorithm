'''
- 1<=n<=10억
- 1<=time<=10억
- 1<=심사관<=10만
- 모든 사람이 심사를 받는데 걸리는 시간을 최소로
>>> 각 심사관이 처리한 사람을 m1,m2,m3 ... 라 할때,
    m1 + m2 + m3 + ... = n이 되도록 한다
    이때 time*m의 최댓값이 최소가 되도록 해야한다

>>> 매개변수로 풀어야 할 것 같은데?
    - 최댓값이 x일때 m1+m2+...=n을 만족하는가?
    - times를 정렬할 수 있음
    
>>> greedy적으로 풀어보는건?
    - 최소 시간에 먼저 집어넣고, 이후 초과되면 다른 시간도 올려주는 방법
    
>>>>>>> 매개변수로 풀어보자
'''
def solution(n, times):
    # 각 심사관이 몇명을 통과시켰는지 확인하는 함수
    def check(target, takeTime, times):
        cur = 0
        # 각 심사관이 통과시킨 사람을 cur에 더해줌
        for examiner in times:
            cur += takeTime//examiner
        # 전체 통과 인원이 target과 같다면 true를 리턴
        return True if cur >= target else False
    
    # 심사 시간의 최솟값을 찾는 이분탐색 : 심사시간을 매개변수로 잡음
    def binary_search(target, times):
        l = 1
        r = times[-1] * target
        minTime = 1e9
        while l<=r:
            mid = (l+r)//2
            # check를 통과했다면, 시간이 충분하다는 뜻이므로 더 줄여봄
            if check(target, mid, times):
                minTime = mid
                r = mid-1
            else:
                l = mid+1
        return minTime
    
    # 이분탐색을 하기 위해 times를 정렬하여 사용
    times.sort()
    answer = binary_search(n,times)
    return answer

