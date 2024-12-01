'''
예상=a, 실제=b
불만도 = |a-b|
불만도 총 합을 최소로 할 때, 불만도는?

1. 그리디인가?
- 예상이랑 최대한 비슷하게 등수를 매겨보자
    ex) 15312 -> 15432 불만도=(4-3 + 3-1 = 3)
- 정렬시킨다음 냅다 밀어도 될듯 싶음
-> TLE: 중복되는 값이 많으면 TLE 발생함

2. 그리디로 계속
- 예상점수를 이미 정렬해놨으니, 이전 점수+1 부터 탐색하면 될듯?
'''
from sys import stdin

def calc(expect:list):
    answer = 0
    real = 1
    # 정렬된 예상 등수를 서칭
    for e in expect:
        # 실제 등수와 예측 등수가 다른 경우
        if e != real:
            answer += abs(e-real)
        real+=1
    return answer

def solution():
    input = stdin.readline
    n = int(input())
    expect = [int(input()) for _ in range(n)]
    expect.sort()
    return calc(expect)

print(solution())
