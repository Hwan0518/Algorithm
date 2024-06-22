'''
Condition
    - TL : 1s
    - 

Goals
    - 첫 번째 단어와 비슷한 단어가 몇 개인지 출력

Approach
    - dict 사용해보자
        - 한 단어를 바꿨을 때 비슷한 경우: 양 쪽에서 diff = 1이 되어야 함
        - 한 단어만을 추가하거나 제거했을 때 비슷한 경우: 한 쪽에서 diff = 1이 되어야 함
        >> 어쨌거나 둘 다 diff >1이면 안되네?
'''
from collections import defaultdict
from sys import stdin
input = stdin.readline

def check(w: str):
    d = defaultdict(int)
    for s in w:
        d[s] +=1
    return d

def compare(f_d:defaultdict, w_d: defaultdict):
    # f_d의 원소 수를 w_d에서 빼기
    for fs, fv in f_d.items():
        w_d[fs] -= fv
    # w_d에서 1과 -1의 개수를 파악, 1과 -1 밖의 값이 있다면 False를 return
    plus = 0
    minus = 0
    for wv in w_d.values():
        if wv < -1 or wv > 1:
            return False
        elif wv == 1:
            plus +=1
        elif wv == -1:
            minus +=1
    # plus와 minus가 둘 다 1 이하라면 True
    return True if plus <= 1 and minus <=1 else False

def main():
    n = int(input())
    f = input().strip()
    f_d = check(f)
    # 첫 번째 단어와 비교
    cnt = 0
    for _ in range(n-1):
        w_d = check(input().strip())
        # 비슷한 단어를 체크
        if compare(f_d, w_d):
            cnt +=1
    return cnt

print(main())