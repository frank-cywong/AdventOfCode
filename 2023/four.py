def isDigit(c):
    return (ord('0') <= ord(c) and ord(c) <= ord('9'))

def isSymbol(c):
    if(c != '.'):
        return not isDigit(c)

import re
f = open("four_in.txt", "r")
c = f.readlines()

def str_to_arr(sx):
    v = sx.split(" ")
    b = []
    for c in v:
        if(len(c) > 0):
            b.append(int(c))
    return b

mult = [1] * len(c)
print("RC: {}".format(len(c)))

sum_v = 0
curind = 0
tc = 0
for s in c:
    s = s[:-1] # remove newline
    a = s.split(": ")
    p1 = a[1]
    b = p1.split(" | ")
    p1 = b[0]
    p2 = b[1]
    #print(p1)
    #print(p2)
    p1 = str_to_arr(p1)
    p2 = str_to_arr(p2)
    #print(p1)
    pc = 1
    mc = 0
    anyy = False
    for n in p2:
        if(n in p1):
            anyy = True
            pc *= 2
            mc += 1
    if not anyy:
        pc = 0
    else: 
        pc //= 2
    sum_v += pc
    tc += mult[curind]
    for i in range(curind + 1, curind + mc + 1):
        mult[i] += mult[curind]
    curind += 1
    #print(pc)

print("PART 1 TOTAL: {}".format(sum_v))
print("PART 2 TOTAL: {}".format(tc))