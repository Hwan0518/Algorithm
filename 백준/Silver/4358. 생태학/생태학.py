'''
Condition
    - TL : 1s
    - name<=30
    - t <= 1만
    - n <= 100만

Question
    - 전체 나무가 주어졌을 때, 각 종이 전체에 몇 %를 차지하는지 사전순으로 출력하여라(백분률 소수점 4째자리까지 반올림) 

Approach
    - 예상 분류 : 해시
    - 실제 분류 : 해시, 문자열
    - 딕셔너리에 각 나무 이름을 추가한 후, +1씩 해주고 마지막에 계산한다

try_1) 
    -
'''
from collections import defaultdict
from sys import stdin
input = stdin.readline

def input_data():
    return input().strip()

def solution():
    cnt = 0
    tree_dict = defaultdict(int)
    # 나무 입력 받음
    tree = input_data()
    while tree:
        tree_dict[tree] +=1
        cnt +=1
        tree = input_data()
    # 전체 비율 계산
    sorted_tree = sorted(tree_dict.keys())
    for t in sorted_tree:
        ratio = (tree_dict[t] / cnt)*100
        print(f"{t} {ratio:.4f}")

solution()