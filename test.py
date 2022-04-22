

tab = [ ["0","01","02","03","04","05","1"],
        ["10","0","12","13","14","15","1"],
        ["20","21","0","23","24","25","1"],
        ["30","31","32","1","34","35","1"],
        ["40","44","42","43","53","45","1"],
        ["50","54","52","53","54","1","1"] ]
col=7
row=6

br=False
for i in range(1,col-3):
    acc=1
    j=row-1
    iTemp=i
    if(br==True):
        break

    while(row-iTemp>3-acc and j>0):

        if (tab[j][iTemp]==tab[j-1][iTemp+1]):
            acc+=1
        else:
            acc=1

        print (tab[j][iTemp])
        print (tab[j-1][iTemp+1])
        print('.')

        if(acc==4):
            print("WIN")
            br=True
            break

        iTemp+=1
        j-=1














