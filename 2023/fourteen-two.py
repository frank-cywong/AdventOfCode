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


def do_op(ct):
    cct = []
    #print("CT:")
    #print(ct)
    for rc in ct:
        putpos = 0
        roundcnt = 0
        #print("RC:")
        #print(rc);
        nv = len(rc)
        rc.append("#")
        ncv = []
        for i in range(len(rc)):
            if(rc[i] == "#"):
                ncv += ["O"] * roundcnt
                ncv += ["."] * (i - putpos - roundcnt)
                if(i != nv):
                    ncv += "#"
                putpos = i + 1
                roundcnt = 0
            if(rc[i] == "O"):
                roundcnt += 1
        #print("NCV:")
        #print(ncv)
        #print(rc)
        cct.append(ncv)
    #print("CCT:")
    #print(cct)
    return cct

def flip_rows(x):
    y = []
    for l in x:
        b = l
        b.reverse()
        y.append(b)
    return y

def do_cyc(ct):
    #print("N")
    # north op:
    cct = []
    cct = transpose(ct, cct)
    cct = do_op(cct)
    c3t = []
    c3t = transpose(cct, c3t)
    #print("W")
    # west op
    c3t = do_op(c3t)
    #print("S")
    # south op
    c4t = []
    c4t = transpose(c3t, c4t)
    #print(c4t)
    c4t = flip_rows(c4t)
    #print(c4t)
    c4t = do_op(c4t)
    c4t = flip_rows(c4t)
    c5t = []
    c5t = transpose(c4t, c5t)
    # east op
    #print("E")
    c5t = flip_rows(c5t)
    c5t = do_op(c5t)
    c5t = flip_rows(c5t)
    return c5t;

xcnt = 0
hist = [""]
ll = len(c[0])
while True:
    if(xcnt % 10 == 0):
        print(xcnt)
    c = do_cyc(c)
    xcnt += 1
    scmp = ""
    for l in c:
        scmp += "".join(l)
    if(scmp in hist):
        v = hist.index(scmp)
        rep = xcnt - v
        target = 1000000000
        t_diff = target - v
        t_mod = t_diff % rep
        nt = t_mod + v
        target_str = hist[nt]
        print(target_str)
        c = []
        sq = ""
        iq = 0
        for cvc in target_str:
            sq += cvc
            iq += 1
            if(iq == ll):
                c.append(sq)
                sq = ""
                iq = 0
        print(c)
        break
    hist.append(scmp)
    print(scmp)

cnsub = len(c)
sv = 0
for i in range(len(c)):
    for sc in c[i]:
        if(sc == "O"):
            sv += (cnsub - i)

print(f"Part 2:{sv}")
