'''
- TL : 0.3s (대략 700만)
- 1<=n<=10만, 1<=m<=50만
- 맨처음 커서는 문장의 맨 뒤에 위치함
- 명령어 L D B P$ 에 따라서 수행
    - L : 커서 왼쪽으로, 단 커서가 문장 맨 앞이면 무시
    - D : 커서 오른쪽으로, 단 커서가 문쟁 맨 뒤면 무시
    - B : 커서 왼쪽을 삭제하고 커서위치-1, 단 커서가 문장 맨 앞이면 무시
모든 명령어를 수행한 후 편집기에 나타나있는 문자열 출력

>>> NlogN까지 가능
>>> 그냥 구현하면 될 것 같음

1. 구현
    >>> TLE, insert와 del을 처리할 방법이 필요
2. 구현
    - insert와 del이 O(1)인 deque를 사용
    >>> TLE, deque는 이중 연결리스트로, 중간값에 대해서는 접근이 느리다!!!
3. 구현
    - reference : https://velog.io/@j1min/Python-%EB%B0%B1%EC%A4%80-1406-%EC%97%90%EB%94%94%ED%84%B0-%ED%92%80%EC%9D%B4
    - 커서를 기준으로 리스트를 좌,우로 나누어서 deque.popleft, appendleft를 사용한다
    >>> popleft, pop, appendleft, append로 O(1)로 수행가능

''' 
from collections import deque
from sys import stdin
input = stdin.readline

def input_data():
    string = input().strip()
    m = int(input())
    return string, m

def processing(command):
    c_type = command[0]
    # command를 L,D,B,P로 나눠서 처리
    if c_type == "L":
        # 커서 좌측이 존재한다면 -> 커서가 처음이 아니라면
        if len(leftString) != 0:
            # 커서 좌측 맨끝을, 커서 우측 제일처음으로 옮김
            moved = leftString.pop()
            rightString.appendleft(moved)
    elif c_type == "D":
        # 커서 우측이 존재한다면 -> 커서가 제일 끝이 아니라면
        if len(rightString):
            # 커서 우측 맨 처음을, 커서 좌측 제일 끝으로 옮김
            moved = rightString.popleft()
            leftString.append(moved)
    elif c_type == "B":
        # 커서 좌측이 존재한다면 삭제
        if len(leftString) != 0:
            leftString.pop()
    else:
        # 커서 좌측에 요소 추가
        leftString.append(command[1])

def solution(string, m):
    global leftString, rightString
    # 커서 왼쪽, 오른쪽으로 리스트를 나눈다
    leftString = deque(string)
    rightString = deque()
    # m번의 명령을 실행
    for _ in range(m):
        command = input().split()
        processing(command)
    # 정답 반환
    return ''.join(leftString+rightString)
    

print(solution(*input_data()))