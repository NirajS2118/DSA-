 void updateRec(int ss,int se,int i,int si,int diff){
        if(i<ss || i>se) return;
        tree[si]+=diff;
        if(se>ss){
            int mid=(ss+se)/2;
            updateRec(ss,mid,i,2*si+1,diff);
            updateRec(mid+1,se,i,2*si+2,diff);
        }
        
    }