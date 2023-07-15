'''
Approach
    - 발표논문 n개중, (h이상 인용된 논문이 h이상 + 나머지가 h이하)일때의 h를 구한다
    - 1<=n<=1000
    
>>> 완탐으로 풀어본다
    : n편을 모두 확인 O(N) * h이상 인용된것과 아닌것의 개수파악 O(N) -> O(N^2)가능

'''
def solution(citations):
    c = citations
    for h in range(len(c),-1,-1):
        more = 0
        for compare in c:
            if compare >= h:
                more +=1
        # h번 이상 인용된 논문이 h편 이상이라면
        print(h, more)
        if more >= h:
            return h
