n= int(input())

memo = [
    [i] * i for i in range(1,n+1)
]

print(memo)