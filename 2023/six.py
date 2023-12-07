import math
f = open("six_in.txt", "r")
c = f.readlines()

c = [l[:-1] for l in c]

t = c[0][10:].split(" ")

t = [int(x) for x in t if x != ""]

d = c[1][10:].split(" ")
d = [int(x) for x in d if x != ""]

mult = 1

for i in range(len(d)):
    tv = t[i]
    dv = d[i] + 0.000000001
    discrim = tv * tv - 4 * dv
    if(discrim < 0):
        mult = 0
        break
    discrim = math.sqrt(discrim)
    discrim /= -2.0
    sol1 = tv/2.0 + discrim
    sol2 = tv/2.0 - discrim
    if(sol1 < 0):
        sol1 = 0
    if(sol2 > tv):
        sol2 = tv
    print(f"{sol1}, {sol2}, {tv}, {dv}")
    cnt = (int(math.floor(sol2)) - int(math.ceil(sol1)) + 1)
    print(cnt)
    mult *= cnt

print(t)



# x(t-x) >= d
# xt-x^2 >= d
# -x^2+xt-d >= 0
# x = (-t += sqrt(t^2-4d))/(-2)

print("Part 1: {}".format(mult))