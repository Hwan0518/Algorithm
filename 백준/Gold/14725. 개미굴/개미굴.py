'''
Condition
    - TL : 1s(2000만)
      ML : 256(64*10만)
    - 1<=N<=1000, 1<=K<=15, 1<=t<=15

Question
    - 개미굴 층을 --로 구분하여 구조를 출력
      같은 층은 사전 순서로 출력

Access
    - clone coding : https://velog.io/@kimdukbae/BOJ-14725-%EA%B0%9C%EB%AF%B8%EA%B5%B4-Python

'''
from sys import stdin
input = stdin.readline

#define function
def solution():
    result=[]
    # foods 내부를 순서대로 출력
    for i in range(n):
        c_room = foods[i] #현재 개미굴
        # 1. i==0일때는, 중복 없이 그대로 다 출력하면 됨
        if i ==0:
            room_size = len(c_room)
            for j in range(room_size):
                result.append('--'*j + c_room[j])
            continue
        # 2. i>=1에서는, 겹치는 원소를 제외하고 출력한다
        idx = 0 #idx는 겹치는 root 개미굴부터 시작해서 겹치는 개미굴의 개수이다
        b_room = foods[i-1]
        for j in range(len(c_room)):
            # 이전 리스트에 j번째 원소가 없거나, 현재 리스트의 j번쨰 원소가 다르다면 겹치지 않으므로 출력
            if len(b_room)<=j or c_room[j] != b_room[j]:
                break
            else:
                idx = j+1
        for j in range(idx,len(c_room)):
            result.append('--'*j + c_room[j])
    return result

#main
n=int(input())
foods = []
for i in range(n):
    input_list = input().split()
    foods.append(input_list[1:])
foods.sort()
for i in solution():
    print(i)

