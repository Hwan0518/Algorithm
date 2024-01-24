stack=[]
for i in range(int(input())):
    n= int(input())
    
    if n != 0:
        stack.append(n)
    else:
        stack.pop(len(stack)-1)

print(sum(stack))