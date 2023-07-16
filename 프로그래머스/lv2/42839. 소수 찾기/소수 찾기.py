'''
Approach
    - 길이가 1~7이하 문자열

>>> 9999999 이하의 소수를 모두 찾아본다
    

'''
from itertools import permutations
import math



def findPrime(limit):
    allNum = [True]*(limit+1)
    allNum[0] = allNum[1] = False
    # 제곱근까지만 계산해서 소수인지 확인
    for i in range(2, int(math.sqrt(limit)+1)):
        # i의 배수를 모두 false처리
        if allNum[i]:
            j = 2
            while i*j<=limit:
                allNum[i*j] = False
                j +=1
    # true인 수는 모두 프라임이다
    return allNum


def solution(numbers):
    answer = 0
    numbers = list(numbers)
    limit = 10**len(numbers)
    
    # limit까지의 수 중에서 prime 수를 찾음
    prime = findPrime(limit)
    
    # 소수 완탐
    visited = set()
    for i in range(1, len(numbers)+1): 
        perm = set(permutations(numbers,i))
        for c in perm:
            c = int(''.join(c))
            if prime[c] and c not in visited:
                print(c)
                visited.add(c)
                answer +=1
    # 완탐으로 푸는법
    # for i in range(2, limit):
    #     flag = True
    #     temp = numbers[:]
    #     if prime[i]:
    #         for s in str(i):
    #             if s not in temp:
    #                 flag = False
    #                 break
    #             temp.remove(s)
    #         if flag:
    #             answer +=1
    
    return answer