'''
Condition
    - TL : 2s
    - 1<=n<=1000, 1<=L,H<=1000
    - 기둥들의 위치와 높이가 주어진다
    - 땅과 몇 개의 기둥 윗면을 연결해서 창고를 만든다
        - 오목하게 들어간 부분이 없어야 한다

Question
    - 기둥들로 만들 수 있는 창고의 최소 면적을 구하라

Approach
    - 예상 분류 : 구현, 스택(예상은 되지만 어떻게 할지 모르겠음)
    - 실제 분류 : 스택

try_1) 구현 
    - 기둥 높이가 꾸준히 증가하거나 꾸준히 감소해야한다
        - 즉, 올라가다 피크를 찍고 내려가기 시작했다면, 계속해서 내려가야한다
    - 다음 우선순위대로 기둥을 선택한다
        1. 가장 높은 기둥을 먼저 선택한다
        2. 다음으로 높은 기둥을 선택하고 가장 높은 기둥과 연결한다
        3. 그 다음으로 높은 기둥을 선택한다
            - 앞에 선택한 두 기둥 사이라면 -> 선택하지 않고 넘어간다
            - 앞에 선택한 두 기둥 사이가 아니라면 -> 선택한다 
        4. 각 기둥을 선택하기 전에, 첫 번째 기둥과 마지막 기둥이 선택되었다면 선택하지 않고 종료한다
    - 기둥 선택이 끝난 후, 선택한 기둥을 위치 순서로 정렬하고, pop하여 면적을 계산한다
    - fail. 기둥 선택하기 구현 실패
    
try_2) 스택
    - reference : https://velog.io/@holawan/%EB%B0%B1%EC%A4%80-2304%EC%B0%BD%EA%B3%A0-%EB%8B%A4%EA%B0%81%ED%98%95-python
    - 위치 순으로 기둥을 정렬한 후, 가장 높은 기둥을 선택
    - 첫 번째 기둥부터, 첫 번째 기둥을 기준으로 가장 높은 기둥 전까지 면적을 계속 더함
        - 기준 >= 다음 기둥 높이 : 기준을 더함
        - 기준 < 다음 기둥 높이 : 기준 높이를 더하고, 기준을 다음 기둥 높이로 갱신
    - 반대쪽에서도 똑같이 진행한다
'''
from sys import stdin
input = stdin.readline

def input_data():
    n = int(input())
    pillar_list = [tuple(map(int,input().split())) for _ in range(n)]
    pillar_list.sort()
    # 제일 높은 기둥 위치를 찾는다
    max_h = 0
    for i in range(n):
        l,h = pillar_list[i]
        if h > max_h:
            max_idx = i
            max_h = h
    return n, pillar_list, max_idx

def solution(n, pillar_list, max_idx):
    area = 0
    # 첫 번째부터 시작
    cl, standard = pillar_list[0]
    i = 0
    while i < max_idx:
        nl, nh = pillar_list[i+1]
        if standard >= nh:
            area += (nl-cl) *standard
        else:
            area += (nl-cl) *standard
            standard = nh
        i +=1
        cl = pillar_list[i][0]
    # 끝에서 시작
    cl, standard = pillar_list[n-1]
    i = n-1
    while i > max_idx:
        nl, nh = pillar_list[i-1]
        if standard >= nh:
            area += (cl-nl) *standard
        else:
            area += (cl-nl) *standard
            standard = nh
        i -=1
        cl = pillar_list[i][0]
    area += pillar_list[max_idx][1]
    # 정답 return
    return area

print(solution(*input_data()))