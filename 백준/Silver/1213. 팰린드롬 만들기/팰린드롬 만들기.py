'''
입력받은 문자열을 팰린드롬으로 바꿔서 출력
- 불가능하면 "I'm Sorry Hansoo" 를 출력
- 사전순으로 앞서는 것을 출력
    - 정렬
    - 앞에서부터 2 개씩 꺼내서 팰린드롬 만듦
    - 2 개가 안된다면 맨 뒤로 보냄
'''
from collections import deque

def printAnswer():
    if len(single) >1:
        print("I'm Sorry Hansoo")
    else:
        print1 = ''.join(half)
        print2 = str(single.pop()) if single else ''
        print3 = ''.join(half[::-1])
        print(print1+print2+print3)

def calc(lst:list):
    global single, half
    while lst:
        fst = lst.popleft()
        if lst:
            sec = lst.popleft()
        else:
            single.append(fst)
            break
        # 같다면 하나만 집어넣음
        if fst == sec:
            half.append(fst)
        # 다르다면 fst를 single로, sec는 다시 맨 앞으로
        else:
            single.append(fst)
            lst.appendleft(sec)

def solution():
    global half,single,result
    sList = sorted(list(input().strip()))
    half = []
    single = []
    # 계산
    calc(deque(sList))
    # 결과 출력
    printAnswer()

solution()