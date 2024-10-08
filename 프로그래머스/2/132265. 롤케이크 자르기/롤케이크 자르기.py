'''
1. 완탐 돌리면서 set으로 중복 제거한 수를 확인해보면 될 것 같음
-> O(100만) * O(1만) = TLE
2. 슬라이싱 방법을 바꿔보자. pop을 사용하면 될듯
-> 여전히 set(tuple(topping))이 있어서 TLE
3. dict를 사용하면 쉽게 풀릴 것 같은데,,, 이러면 메모리를 너무 많이 사용하는 것 같지만 try
'''
def solution(topping):
    answer = 0
    s1 = {}
    s2 = {}
    # s2 init
    for i in topping:
        if i in s2:
            s2[i] +=1
        else:
            s2[i] = 1
    # searching
    for i in range(1, len(topping)):
        num = topping.pop()
        if num in s1:
            s1[num] +=1
        else:
            s1[num] = 1
        s2[num] -=1
        if s2[num] <= 0:
            s2.pop(num)
        if len(s1) == len(s2):
            answer +=1
    return answer