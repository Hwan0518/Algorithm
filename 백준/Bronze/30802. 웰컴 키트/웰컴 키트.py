n = int(input())
applicants = list(map(int,input().split()))
t,p = map(int,input().split())
order_t = 0
for i in applicants:
    if i%t != 0:
        order_t += i//t+1
    else:
        order_t += i//t
print(order_t)
print(n//p, n%p)