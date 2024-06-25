'''
Conditions
    - 두 원은 모두 중심이 원점이다
    - r1, r2는 반지름이다
    - 원 위의 점도 포함한다

Goals
    - 두 원 사이의 공간에서 (x,y) 좌표가 모두 정수인 점의 개수는?

Approach
    - r1 < r2 일때
        (r1**2) <= (x**2) + (y**2) <= (r2**2)를 만족하는 x,y를 구한다
    - 1사분면 결과만 구한 후 *4를 한다
    - x가 정해졌을 때, (r1**2 - x**2) <= y**2 <= (r2**2 - x**2)를 활용해본다
    - 즉, y범위의 최소 최대값을 알 수 있으므로 O(1)로 계산할 수 있다는 것이다
'''
import math

def calc(x:int, r1:int, r2:int):
    # 최솟값 = 계산값에서 올림한 정수값
    min_v = r1**2 - x**2
    if min_v < 0:
        min_y = 0
    else:
        min_y = math.ceil(min_v**0.5)
    # 최댓값 = 계산값에서 내림한 정수값
    max_v = r2**2 - x**2
    if max_v < 0:
        max_y = 0
    else:
        max_y = math.floor(max_v**0.5)
    # 개수 = min_y - max_y +1
    result = 2*(max_y - min_y +1)
    # x가 r1 밖에 존재한다면 -1을 해준다
    return result if (-r1<x<r1) else result-1
    
def solution(r1, r2):
    answer = 0
    for x in range(-r2, r2+1):
        answer += calc(x, r1, r2)
    return answer