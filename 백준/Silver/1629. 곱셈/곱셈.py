'''
reference : https://velog.io/@grace0st/%EA%B3%B1%EC%85%88-%EB%B0%B1%EC%A4%80-1629%EB%B2%88-%ED%8C%8C%EC%9D%B4%EC%8D%AC
- 나머지 분배법칙 : (A*B)%C = (A%C) * (B%C) %C
- A^(B+C) = A^B * A^C

ex) 
10^11%12
= (10^5 * 10^6)%12
= (10^5 % 12) * (10^6 % 12) % 12
= (10^5 % 12) * (10^5 * 10 % 12) % 12
= ((10^2 * 10^2 * 10) % 12) * ((10^2 * 10^2 * 10) % 12 * 10) % 12
'''


a,b,c = map(int,input().split())
# 분할정복
def dfs(a, n):
    # 지수가 1인 경우 : a^1 == a 
    if n == 1:
        return a%c
    
    tmp = dfs(a, n//2)
    # 지수가 짝수 : 정확히 절반으로 나누어짐
    if n%2 == 0:
        division = (tmp * tmp) % c
    # 지수가 홀수 : 절반, 절반+a 로 나누어짐
    else:
        division = (tmp * tmp * a) % c
    return division

print(dfs(a,b))