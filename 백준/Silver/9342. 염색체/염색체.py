'''
- TL :2s
- T <=20, N<=200
- idx=1 : A가 1개이상 연속
- 그다음 : F가 1개이상 연속
- 그다음 : C가 1개이상 연속
- 그다음 : 없거나, {A,B,C,D,E,F}중 1개
- 만족하면 Infected, 아니면 Good 출력

>>> 각 테스트케이스에 대해서, 문자열 처음부터 끝까지 그대로 검사하면 될 것 같다.
    TC = O(20*200)일듯?
    
    
1. 구현
    - 각 TC에 대해서 문자열 처음부터 각각의 조건을 만족하는지 확인

'''
from sys import stdin
input = stdin.readline


def inputData():
    return input().strip()


def verifyString(string):
    step = 0
    # 첫 시작을 확인
    sttIdx = 0
    if string[0] in {"B","C","D","E","F"}:
        sttIdx +=1
    
    for idx in range(sttIdx, len(string)):
        s = string[idx]
        # step 0 : A가 무조건 하나 존재
        if step == 0:
            if s == "A":
                step +=1
                continue
            else:
                return False
        # step 1 : A가 하나이상
        elif step == 1:
            if s == "A":
                continue
            elif s == "F":
                step +=1
                continue
            else:
                return False
        # step 2 : F가 하나이상
        elif step == 2:
            if s == "F":
                continue
            # F가 아닌 C라면 step +=1
            elif s == "C":
                step +=1
                continue
            # 그 외의 값은 False
            else:
                return False
        # step 3 : C가 하나이상
        elif step == 3:
            if s == "C":
                continue
            # C가 아닌, 다음 값이 존재한다면 step +=1
            elif s in {"A","B","C","D","E","F"}:
                step +=1
                continue
            # 그 외의 값이라면 False
            else:
                return False
        # step 4 : 아무것도 없어야함
        elif step == 4:
            return False
    return True
        


def solution(T):
    answer = ["Infected!"]*5
    for _ in range(10):
        answer.append("Good")
    resultList = []
    for _ in range(T):
        string = inputData()
        result = verifyString(string)
        resultList.append(result)
        print("Infected!") if result else print("Good")
    
    # Test
    # for i in range(T):
    #     if answer[i]==resultList[i]:
    #         continue
    #     else:
    #         print(answer[i], resultList[i])

solution(int(input()))