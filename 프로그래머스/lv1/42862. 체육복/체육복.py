'''
- 전체 2<=n<=30
- 도난 번호 lost
- 여벌 번호 reserve
>>> 체육수업 들을 수 있는 학생 최댓값

lost의 +- 1값이 reserve에 들어있는지 확인
들어있다면 answer를 증가시키고, reserve에서 그 값을 뺌
'''
def solution(n, lost, reserve):
    # n명중에 lost를 제외한 학생은 체육복이 존재함
    noUniform = set(lost) - set(reserve)
    answer = n-len(noUniform)
    # reserve중 도난당한 사람을 제외한 사람만 빌려줄 수 있다
    canBorrow = set(reserve) - set(lost)
    for i in noUniform:
        # i의 앞사람이 여벌 옷 존재하는 경우
        if (i-1) in canBorrow:
            answer+=1
            canBorrow.remove(i-1)
        # i의 뒷사람이 여벌 옷 존재하는 경우
        elif (i+1) in canBorrow:
            answer+=1
            canBorrow.remove(i+1)
            
    
    return answer