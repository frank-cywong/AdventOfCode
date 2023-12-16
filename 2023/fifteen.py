import math
import sys

#sys.setrecursionlimit(100000)

# testing input?
TEST_IN = False

f_in_str = "fifteen_in.txt"
f_test_str = "fifteen_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

c = [l[:-1] for l in c]

p = []
for i in range(256):
    p.append({})
print(p)

def transpose(l1, l2):
 
    # we have nested loops in comprehensions
    # value of i is assigned using inner loop
    # then value of item is directed by row[i]
    # and appended to l2
    l2 =[[row[i] for row in l1] for i in range(len(l1[0]))]
    return l2

def hash(s):
    cv = 0
    for c in s:
        cv += ord(c)
        cv *= 17
        cv %= 256
    return cv

q = c[0].split(",")

pl = [0] * 256

def proc(l):
    if(l[-1] == "-"):
        l = l[:-1]
        h = hash(l)
        if(l in p[h]):
            posv = p[h][l][1]
            p[h].pop(l)
            for k,v in p[h].items():
                if(v[1] > posv):
                    v[1] -= 1;
            pl[h] -= 1;
    else:
        l = l.split("=")
        vq = int(l[1])
        l = l[0]
        h = hash(l)
        if(l in p[h]):
            p[h][l][0] = vq
        else:
            pl[h] += 1
            p[h][l] = [vq, pl[h]]

o = 0
for l in q:
    proc(l)
    o += hash(l);

print(p)

o2 = 0
for i in range(256):
    indv = 1
    for k,v in p[i].items():
        o2 += v[0] * indv * (i + 1)
        indv += 1

print(o)
print(o2)
