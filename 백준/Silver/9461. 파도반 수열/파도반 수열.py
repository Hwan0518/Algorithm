for _ in range(int(input())):
    p=[1,1,1]
    n=int(input())
    if n>3:
        for i in range(3, n):
            p.append(p[i-3]+p[i-2])
    print(p[n-1])