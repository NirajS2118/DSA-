class Solution {
    int[] tree;
    int constructST(int ss,int se,int si,int[] arr){
        if(ss==se){
            tree[si]=arr[ss];
            return arr[ss];
        }
        int mid=(ss+se)/2;
        
        return tree[si]=constructST(ss,mid,2*si+1,arr)+constructST(mid+1,se,2*si+2,arr);
    }
    
    int getSum(int qs,int qe,int ss,int se,int si){
        if(se<qs || ss>qe) return 0;
        if(qs<=ss && se<=qe){
            return tree[si];
        }
        
        int mid=(ss+se)/2;
        return getSum(qs,qe,ss,mid,2*si+1)+getSum(qs,qe,mid+1,se,2*si+2);
    }
    
    void updateRec(int ss,int se,int i,int si,int diff){
        if(i<ss || i>se) return;
        tree[si]+=diff;
        if(se>ss){
            int mid=(ss+se)/2;
            updateRec(ss,mid,i,2*si+1,diff);
            updateRec(mid+1,se,i,2*si+2,diff);
        }
        
    }
    public ArrayList<Integer> rangeSumQueries(int[] arr, int[][] queries) {
        // code here
        int n=arr.length;
        tree=new int[4*n];
        constructST(0,n-1,0,arr);
        ArrayList<Integer> res=new ArrayList<>();
        
        for(int[] q:queries){
            int type=q[0];
            if(type==1){
                int l=q[1];
                int r=q[2];
                int sum=getSum(l,r,0,n-1,0);
                res.add(sum);
            }
            else if(type==2){  // type->2 update
                int i=q[1];
                int val=q[2];
                int diff=val-arr[i];
                arr[i]=val;
                updateRec(0,n-1,i,0,diff);
            }
        }
        return res;
        
        
    }
}