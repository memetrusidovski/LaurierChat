import json
from random import randint

with open('modelSandbox/annotated_fb_data_valid.txt', 'r') as f:
    q = f.readlines()
    


for line in q[0:25000]:
    l = line.split("\t")
    r = randint(0,10)

    dmp = {
            "sentence": l[3][0:-1],
            "isComplete": 0
        }


    y = json.dumps(dmp)
    print(y, ",")
    #print(l[3])
