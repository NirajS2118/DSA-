 int[] tree;
    int constructST(int ss,int se,int si,int[] arr){
        if(ss==se){
            tree[si]=arr[ss];
            return arr[ss];
        }
        int mid=(ss+se)/2;
        
        return tree[si]=constructST(ss,mid,2*si+1,arr)+constructST(mid+1,se,2*si+2,arr);
    }