import math
import sys

#sys.setrecursionlimit(100000)

# testing input?
TEST_IN = False

f_in_str = "eleven_in.txt"
f_test_str = "eleven_test.txt"

f = open(f_test_str if TEST_IN else f_in_str, "r")
c = f.readlines()

c = [l[:-1] for l in c]

def all_dot(s):
    for v in s:
        if(v != "."):
            return False
    return True

def cl(s):
    return [v for v in s];

c2 = [];
for s in c:
    if all_dot(s):
        c2.append(cl(s))
    c2.append(cl(s))

def transpose(l1, l2):
 
    # we have nested loops in comprehensions
    # value of i is assigned using inner loop
    # then value of item is directed by row[i]
    # and appended to l2
    l2 =[[row[i] for row in l1] for i in range(len(l1[0]))]
    return l2

#print(c2)

c3 = []

c4 = []

c3 = transpose(c2, c3);
for s in c3:
    if all_dot(s):
        c4.append(s)
    c4.append(s)

c5 = []

c5 = transpose(c4, c5);

for l in c5:
    print("".join(l))

stars = []
for i in range(len(c5)):
    for j in range(len(c5[i])):
        if(c5[i][j] == "#"):
            stars.append([i, j])

sv = 0

for i in range(len(stars)):
    for j in range(i + 1, len(stars)):
        s1 = stars[i]
        s2 = stars[j]
        sv += abs(s1[0] - s2[0]) + abs(s1[1] - s2[1])

stars2 = []
for i in range(len(c)):
    for j in range(len(c[i])):
        if(c[i][j] == "#"):
            stars2.append([i, j])

sv2 = 0

for i in range(len(stars2)):
    for j in range(i + 1, len(stars2)):
        s1 = stars2[i]
        s2 = stars2[j]
        sv2 += abs(s1[0] - s2[0]) + abs(s1[1] - s2[1])

print("Part 1: {}".format(sv))
print("Part 2: {}".format((sv-sv2)*999999+sv2))