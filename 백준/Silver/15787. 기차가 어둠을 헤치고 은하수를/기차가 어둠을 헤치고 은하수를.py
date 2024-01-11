'''
요구사항
    - TL : 1s
    - 1<=n,m<=10만
    - 기차가 n개, 20개의 일렬로된 좌석 존재
    - 1<=i<=n, 1<=x<=20
    - 명령이 m개
        - 1 i x : i번째 기차에, x번째 좌석에 사람을 태워라. 이미 사람이 타있으면 x
        - 2 i x : i번째 기차에, x번째 앉은 사람을 하차. 아무도 없다면 x
        - 3 i : i번째 기차에, 승객들 모두 한칸씩 뒤로, 20번째는 하차
        - 4 i : i번째 기차에, 승객들 모두 한칸씩 앞으로, 첫번째는 하차
    - 명령이 다 끝난 후, 중복되지 않은 기차 수는?

접근
    - 구현
    - O(NlogN)까지 가능
    - 명령이 끝나고 set으로 만들면 될듯?

1. 구현

'''
from sys import stdin
input = stdin.readline

def input_data():
    n, m = map(int,input().split())
    train_list = [[False]*21 for _ in range(n+1)]
    return m, train_list

def carry_out(command, train_list):
    if len(command)==3:
        c,i,x = command
    else:
        c,i = command
    # 1인경우
    if c == 1:
        # 좌석이 차있으면 그냥 넘어간다
        if train_list[i][x]:
            return train_list
        train_list[i][x] = True
    # 2인경우
    elif c == 2:
        if not train_list[i][x]:
            return train_list
        train_list[i][x] = False
    # 3인경우
    elif c == 3:
        train_list[i][20] = False   
        for k in range(20,1,-1):
            train_list[i][k] = train_list[i][k-1]
        train_list[i][1] = False
    # 4인경우
    else:
        train_list[i][0] = False
        for k in range(1,20):
            train_list[i][k] = train_list[i][k+1]
        train_list[i][20] = False
    return train_list

def solution(m, train_list):
    # 명령 수행
    for _ in range(m):
        command = list(map(int,input().split()))
        train_list = carry_out(command, train_list)
    # 중복 제거
    result = set()
    for i in range(1,len(train_list)):
        train = tuple(train_list[i])
        result.add(train)
    return len(result)

print(solution(*input_data()))