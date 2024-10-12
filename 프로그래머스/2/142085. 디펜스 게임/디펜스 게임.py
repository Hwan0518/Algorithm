from heapq import *
def solution(n, k, enemy):
    rCnt = len(enemy)
    # 모든 라운드에 무적권을 사용할 수 있는 경우
    if k >= rCnt:
        return rCnt
    # 일단 무적권을 사용하고, 사용한 라운드를 기억해둔 후, 다른 라운드에서 필요하다면 사용을 취소한다
    used = []
    for i in range(rCnt):
        e = enemy[i]
        # 무적권 사용할 수 있다면 사용
        if k:
            heappush(used, e)
            k -=1
        # 무적권 사용할 수 없을때, 무적권 사용했던 라운드중 적의 수가 최소일때와 비교
        else:
            if used:
                usedMin = heappop(used)
            else:
                usedMin = e
            # 이번 라운드의 적과, 무적권을 사용했던 라운드중 적의 수가 최소일때를 비교
            if e > usedMin:
                curE = usedMin
                usingM = e
            else:
                curE = e
                usingM = usedMin
            # 더 많은쪽에서 무적권 사용
            heappush(used, usingM)
            # 남은 n으로 curE를 막을 수 있는지 확인
            if n >= curE:
                n -= curE
            # 막을 수 없다면 라운드 종료
            else:
                i-=1
                break
    return i+1