9 1 3 2

k=2

M = [-2,-1]
L = [ 3,9]

for i in range(left, pivot):
    if A[i] > A[pivot]:
        continue
    else:
        pivotpoint += 1
        temp = A[pivotpoint]
        A[pivotpoint] = A[i]
        A[i] = temp

pivotpoint += 1
temp = A[pivotpoint]
A[pivotpoint] = A[pivot]
A[pivot] = temp

return pivotpoint