class Solution {
    int n;
    int[] tree;
    int constructST(int ss,int se,int si,int[] arr){
        if(ss==se){
            tree[si]=ss;
            return ss;
        }
        int mid=(ss+se)/2;
        int lidx=constructST(ss,mid,2*si+1,arr);
        int ridx=constructST(mid+1,se,2*si+2,arr);
        return tree[si]=arr[lidx]>arr[ridx]?lidx:ridx;
    }
    
    int RMIQ(int qs,int qe,int ss,int se,int si,int[] heights){
        if(se<qs || ss>qe) return -1;
        if(qs<=ss && se<=qe){
            return tree[si];
        }
        int mid=(ss+se)/2;
        int lidx=RMIQ(qs,qe,ss,mid,2*si+1,heights);
        int ridx=RMIQ(qs,qe,mid+1,se,2*si+2,heights);

        if(lidx==-1) return ridx;
        if(ridx==-1) return lidx;
        return (heights[lidx]>heights[ridx])?lidx:ridx;
    }
    public int[] leftmostBuildingQueries(int[] heights, int[][] queries) {
        n=heights.length;
        tree=new int[4*n];
        constructST(0,n-1,0,heights);
        int in=0;
        int[] res=new int[queries.length];
        for(int[] q:queries){
            int min_idx=Math.min(q[0],q[1]);
            int max_idx=Math.max(q[0],q[1]);

            if(min_idx==max_idx || heights[max_idx]>heights[min_idx]) {
                 res[in++]=max_idx;
                 continue;
            }
            

            
            int l=max_idx+1;
            int h=n-1;
            int pidx=Integer.MAX_VALUE;

            while(l<=h){
                int mid=l+(h-l)/2;
                int idx=RMIQ(l,mid,0,n-1,0,heights);  // act as mid

                if(heights[idx]>Math.max(heights[min_idx],heights[max_idx])){
                    //possible ans
                    pidx=Math.min(pidx,idx);
                    h=mid-1;
                }
                else{
                    l=mid+1;
                }
            }
            if(pidx==Integer.MAX_VALUE){
                res[in++]=-1;
            }
           else res[in++]=pidx;
        }
        return res;
    }
}