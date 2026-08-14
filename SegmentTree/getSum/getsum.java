 int getSum(int qs,int qe,int ss,int se,int si){
        if(se<qs || ss>qe) return 0;
        if(qs<=ss && se<=qe){
            return tree[si];
        }
        
        int mid=(ss+se)/2;
        return getSum(qs,qe,ss,mid,2*si+1)+getSum(qs,qe,mid+1,se,2*si+2);
    }