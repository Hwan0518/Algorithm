'''
Condition
    - TL : 1s
    - 1<=N<=50만, 2<=P<=30만
    - 손가락 움직임
        - 손가락을 한번 떼거나 붙이면 1번 움직인것
        - 같은 음에 대해
            - 이전 프렛보다 높은 프렛 -> 누르고 있던 손가락을 떼지않고, 새로운 프렛을 누른다
            - 이전 프렛보다 낮은 프렛 -> 누르고 있던 손가락을 모두 떼고, 새로운 프렛을 누른다

Question
    - 손가락 움직임의 최솟값을 구하라

Approach
    - 분류 : 스택

try_1) 스택 
    - list[n]인 1차원 배열을 만든다
    - n번의 p를 연주할 때
        - list[n]이 비어있다면 -> list[n] p를 append한다. 움직임 +=1
        - 그렇지 않다면 -> list[n]의 끝에서부터 p와 비교하여, 더 작은 수가 나올때까지 pop한다
            - pop 할때마다 움직임 +1, 새로운 프렛을 눌러야하므로 움직임 +=1
'''
from sys import stdin
input = stdin.readline

def input_data():
    n,p = map(int,input().split())
    return n,p

def solution(total_n, total_p):
    move = 0
    play = [[] for _ in range(total_n+1)]
    for _ in range(total_n):
        n,p = map(int,input().split())
        if not play[n]:
            play[n].append(p)
            move +=1
        else:
            last = play[n][-1]
            while p < last:
                play[n].pop()
                move +=1
                if play[n]:
                    last = play[n][-1]
                else:
                    break
            if p == last:
                continue
            move +=1
            play[n].append(p)
    return move

print(solution(*input_data()))