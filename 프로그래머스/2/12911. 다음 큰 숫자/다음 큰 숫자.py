def calc(m:int):
    cnt = 0
    while m > 1:
        if m%2 == 1:
            cnt +=1
        m = m//2
    if m == 1:
        cnt +=1
    return cnt

def solution(n):
    nCnt = calc(n)
    for m in range(n+1, int(1e9)):
        if calc(m) == nCnt:
            return m