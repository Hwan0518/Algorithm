'''
a^b의 끝 자리수를 출력하여라
'''
for _ in range(int(input())):
    a,b = map(int,input().split())
    a %= 10
    if a == 0:
        print(10)
    elif a in [1,5,6]:
        print(a)
    elif a in [4,9]:
        print(a) if b%2 else print((a**2)%10)
    else:
        print(a**(b%4)%10) if b%4 else print((a**4)%10)