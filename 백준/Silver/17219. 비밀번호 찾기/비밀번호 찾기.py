from sys import stdin
input = stdin.readline
n,m = map(int,input().split())
dt = {}

for _ in range(n):
    inputs = input().strip().split()
    dt[inputs[0]] = inputs[1]

for _ in range(m):
    print(dt[input().strip()])