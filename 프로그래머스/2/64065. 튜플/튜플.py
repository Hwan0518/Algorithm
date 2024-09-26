'''
길이가 1~n까지의 원소를 찾아서 순서대로 입력한다
'''
def solution(s):
    # 문자열 나누기
    s = s[2:len(s)-2]
    s = s.split("},{")
    sDict = {}
    for ss in s:
        before = ss.split(',')
        sDict[len(before)] = set(before)
    # 튜플 구하기
    first = sDict[1].pop()
    sDict[1].add(first)
    answer = [int(first)]
    for i in range(2, len(sDict)+1):
        answer.append(int((sDict[i]-sDict[i-1]).pop()))
    return answer