'''
Condition
    - TL : 1s
    - 같은 종류 초밥 여러 개 가능
    - 할인행사
        - 1. k개를 연속으로 먹은 경우 할인
        - 2. 쿠폰 발행
            - 1번 행사에 참여한 경우, 쿠폰에 적힌 초밥 하나 무료 제공
            - 벨트위에 쿠폰의 초밥이 없으면 새로 만듦

Goals
    - 손님이 먹을 수 있는 초밥 가짓수의 최댓값은?

Approach
    - 투 포인터 해볼까?
    - 0번 인덱스부터 이동해서, 쿠폰 초밥을 제외하고 서로다른 초밥 가지 수 max가 될때를 찾음
    
Point
    - 벨트의 처음과 끝이 연결되어있다 -> 따라서 배열을 두 배로 늘려보자
'''
from sys import stdin
input = stdin.readline

def input_data():
    n, d, k, c = map(int,input().split())
    belt = [0]
    for _ in range(n):
        belt.append(int(input()))
    belt = belt + belt
    return n, d, k, c, belt

def two_pointer(n:int, d:int, k:int, c:int, belt:list):
    s, e = 1, 1
    cnt, max_cnt, sequence = 0, 0, 0
    sushi_types = [0]*(d+1)
    while e < 2*n+1:
        sushi = belt[e]
        if sushi == 0:
            e +=1
            continue
        if sequence < k:
            if not sushi_types[sushi]:
                cnt +=1
            sushi_types[sushi] +=1
            sequence +=1
            e +=1
        else:
            fst_s = belt[s]
            if sushi_types[fst_s] == 1:
                cnt -=1
            sushi_types[fst_s] -=1
            sequence -=1
            s +=1
        max_cnt = max(max_cnt, cnt+1 if not sushi_types[c] else cnt)
    return max_cnt

def main(n:int, d:int, k:int, c:int, belt:list):
    return two_pointer(n, d, k, c, belt)

print(main(*input_data()))