'''
Approach
    - 1<= targets <= 50만
    - 0<=s<e<=1억
    - 요격미사일을 최소로 사용
    - 폭격 : x축에 평행한 직선형태, (s,e)로 표현
            개구간은 동일 좌표에서 요격 불가능
    - 요격미사일은 실수면 다 가능, 정수일 필요x


>>> hint : https://velog.io/@mang0206/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%9A%94%EA%B2%A9-%EC%8B%9C%EC%8A%A4%ED%85%9C-python

'''

def solution(targets):
    answer = 1
    sorted_t = sorted(targets, key=lambda x:x[1])
    
    before = sorted_t[0]
    for i in range(len(targets)-1):
        current = sorted_t[i+1]
        if current[0] >= before[1]:
            answer +=1
            before = current
        
    return answer