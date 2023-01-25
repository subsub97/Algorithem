# 1764 try1
import sys

n,m = map(int,sys.stdin.readline().strip().split())

a = set()
b = set()
for i in range(n):
    a.add(input())
for j in range(m):
    b.add(input())

result = sorted(list(a&b))
print(len(result))
for name in result:
    print(name)



