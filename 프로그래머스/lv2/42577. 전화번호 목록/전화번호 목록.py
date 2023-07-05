'''
Approach
    - 접두어인 번호가 존재하는지 확인(있으면 false, 없으면 true)
    - 1<=n<=100만
    - 중복없음
    
>>> 문자열 슬라이싱?
    : O(N^2)라 안됨
    
>>> 해시를 어떻게 사용하지?
    : 단순히 한 번호가 다른 번호의 맨 앞에 존재하는지만 확인하면 됨
      그걸 어떻게?

'''

def solution(phone_book:list):
    phone_book.sort()    
    for i in range(len(phone_book)-1):
        cur = phone_book[i]
        nextNum = phone_book[i+1]
        length = len(cur)
        if cur[0] != phone_book[i+1][0]:
            continue
        if cur == nextNum[:length]:
            return False
    return True
        