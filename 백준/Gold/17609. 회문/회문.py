'''
Conditions
    - TL: 1s
    - 유사회문: 한 문자를 삭제하여 회문으로 만들 수 있는 문자열
        - 위치는 상관 없음

Goals
    - 문자열이 회문이면 0, 유사회문이면 1, 그 외에는 2를 출력

Approach
    - 문자열
    - 양쪽 끝에서 접근해보자
    - 달라지면 어떻게 처리할 것인가?
        - 한쪽씩 제거한 후 다시 회문을 확인
'''
from sys import stdin
input = stdin.readline

for _ in range(int(input())):
    s = list(input().rstrip())
    stt,end = 0, len(s)-1
    diff = 0
    while stt < end:
        if s[stt] == s[end]:
            stt +=1
            end -=1
            continue
        else:
            # 앞 문자 제거
            s1 = s[stt+1:end+1]
            if s1[:len(s1)] == s1[len(s1)::-1]:
                diff=1
                break
            # 뒷 문자 제거
            s2 = s[stt:end]
            if s2[:len(s2)] == s2[len(s2)::-1]:
                diff=1
                break
            diff=2
            break
    print(diff)