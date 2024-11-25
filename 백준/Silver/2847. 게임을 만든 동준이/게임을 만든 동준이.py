'''
점수를 내리는 것을 최소한으로 하는 방법
- 항상 내릴 수 밖에 없으니 가장 뒤에서부터 탐색을 시작하자
'''

def goDown(score:list):
    cnt = 0
    for i in range(n-2, -1, -1):
        next = score[i+1]
        cur = score[i]
        # next가 더 큰 경우
        if cur < next:
            continue
        # 그렇지 않은 경우
        diff = cur - next
        score[i] = cur-diff-1
        cnt += diff+1
    return cnt

def main():
    global n, minDownCnt
    n = int(input())
    score = [int(input()) for _ in range(n)]
    return goDown(score)

print(main())