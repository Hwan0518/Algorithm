'''
자카드유사도
- 두 집합의 교집합 크기 / 두 집합의 합집합 크기
- 두 집합이 모두 공집합이면 1이다
- 다중집합의 교집합 -> 더 적은쪽, 합집합 -> 더 큰쪽

조건
- 알파벳만
- 대소문자 구분 x
- 두 글자씩 끊어서 집합으로

flow
1. 두 글자씩 끊어서 집합 생성
2. 두 집합의 교집합과 합집합 구하기
3. 자카드 유사도 계산
'''
import string
# 유사도 계산
def a(c1, c2):
    if c1 == 0 and c2 == 0:
        return 65536
    return int(c1/c2*65536)

# 두 집합의 교집합 합집합 구하기
def b(s1, s2):
    setS1 = set(s1)
    setS2 = set(s2)
    allS = set(setS1 | setS2)
    c1,c2 = 0,0
    #교집합, 합집합 크기
    for i in allS:
        c1 += min(s1.count(i), s2.count(i))
        c2 += max(s1.count(i), s2.count(i))
    return c1, c2

# 두 글자씩 끊어서 집합 생성
def c(s):
    stringSet = []
    s.replace(' ', '')
    for i in range(len(s)-1):
        apb1 = s[i].upper()
        apb2 = s[i+1].upper()
        if apb1 not in alpha or apb2 not in alpha:
            continue
        stringSet.append(apb1+apb2)
    return stringSet

def solution(str1, str2):
    global alpha
    alpha = set(string.ascii_uppercase)
    # 1. 두 글자씩 끊어서 집합 생성
    s1,s2 = c(str1), c(str2)
    # 2. 두 집합의 교집합과 합집합 크기 구하기
    c1,c2 = b(s1, s2)
    # 3. 유사도 계산
    answer = a(c1, c2)
    return answer