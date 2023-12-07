def isDigit(c):
    return (ord('0') <= ord(c) and ord(c) <= ord('9'))

def isSymbol(c):
    if(c != '.'):
        return not isDigit(c)

import re
f = open("three_in.txt", "r")
c = f.readlines()
a = [([x for x in s[:-1]]) for s in c]

v = [ [-1 for x in s[:-1]] for s in c]

def get_prod(i, j): # gets number val including i, j digit
    startj = j
    endj = j
    while(startj >= 1 and (v[i][startj - 1] == 1)):
        startj -= 1;
    while(endj < (len(a[i]) - 1) and (v[i][endj + 1] == 1)):
        endj += 1;
    num_str = ""
    for addj in range(startj, endj + 1):
        num_str = num_str + a[i][addj]
    return(int(num_str))

def compt_adj_num(i, j): # assuming a[i][j] gear, spaghetti code
    adj_count = 0
    adj_prod = 1
    if(j >= 1 and v[i][j-1] == 1):
        adj_count += 1
        adj_prod *= get_prod(i, j-1)
    if(j < len(a[i]) - 1 and v[i][j+1] == 1):
        adj_count += 1
        adj_prod *= get_prod(i, j+1)
    if(i >= 1):
        if(v[i-1][j] == 1):
            adj_count += 1
            adj_prod *= get_prod(i-1, j)
        else:
            if(j >= 1 and v[i-1][j-1] == 1):
                adj_count += 1
                adj_prod *= get_prod(i-1, j-1)
            if(j < len(a[i-1]) - 1 and v[i-1][j+1] == 1):
                adj_count += 1
                adj_prod *= get_prod(i-1, j+1)
    if(i < len(a)):
        if(v[i+1][j] == 1):
            adj_count += 1
            adj_prod *= get_prod(i+1, j)
        else:
            if(j >= 1 and v[i+1][j-1] == 1):
                adj_count += 1
                adj_prod *= get_prod(i+1, j-1)
            if(j < len(a[i+1]) - 1 and v[i+1][j+1] == 1):
                adj_count += 1
                adj_prod *= get_prod(i+1, j+1)
    if(adj_count == 2):
        return adj_prod
    return 0

for i in range(len(a)):
    for j in range(len(a[i])):
        numkeep = False
        if(isDigit(a[i][j])): 
            for offseti in range(-1, 2, 1):
                for offsetj in range(-1, 2, 1):
                    try:
                        if(isSymbol(a[i+offseti][j+offsetj])):
                            numkeep = True
                            break
                    except:
                        pass
                if(numkeep):
                    break
        v[i][j] = (1 if numkeep else 0)

mod = True
while mod:
    mod = False
    for i in range(len(a)):
        for j in range(len(a[i])):
            if(isDigit(a[i][j])):
                if not v[i][j]:
                    if (j >= 1 and (v[i][j-1] == 1)):
                        mod = True
                        v[i][j] = 1
                    if (j < (len(a[i]) - 1) and (v[i][j+1] == 1)):
                        mod = True
                        v[i][j] = 1

numsum = 0
curinnum = False
curnumstr = ""
for i in range(len(a)):
    for j in range(len(a[i])):
        if(not isDigit(a[i][j])):
            if(curinnum):
                numsum += int(curnumstr)
                print(curnumstr)
                curnumstr = ""
            curinnum = False
        else:
            if(v[i][j]):
                if(curinnum):
                    curnumstr = curnumstr + a[i][j]
                else:
                    curnumstr = a[i][j]
                    curinnum = True
    if(curinnum):
        numsum += int(curnumstr)
        print(curnumstr)
        curnumstr = ""
    curinnum = False

#print(v)
#print(a)
print("PART 1 TOTAL: {}".format(numsum))

prod = 0 # this is sum but too lazy to change
for i in range(len(a)):
    for j in range(len(a[i])):
        if(a[i][j] == '*'):
            add_prod = compt_adj_num(i, j)
            print(add_prod)
            prod += add_prod

print("PART 2 TOTAL: {}".format(prod))
