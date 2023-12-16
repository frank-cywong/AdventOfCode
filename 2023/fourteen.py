import math
import sys

#sys.setrecursionlimit(100000)

# testing input?
TEST_IN = False

f_in_str = "fourteen_in.txt"
f_test_str = "fourteen_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

c = [l[:-1] for l in c]

def transpose(l1, l2):
 
    # we have nested loops in comprehensions
    # value of i is assigned using inner loop
    # then value of item is directed by row[i]
    # and appended to l2
    l2 =[[row[i] for row in l1] for i in range(len(l1[0]))]
    return l2

ct = []
ct = transpose(c, ct);

sv = 0
for rc in ct:
    putpos = 0
    roundcnt = 0
    nv = len(rc)
    rc.append("#")
    for i in range(len(rc)):
        if(rc[i] == "O"):
            roundcnt += 1
        elif(rc[i] == "#"):
            for j in range(roundcnt):
                sv += (nv - putpos - j)
                #print(f"ADDED: {nv-putpos-j}")
            putpos = i + 1
            roundcnt = 0

print(sv)