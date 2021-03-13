public class ArrayDeque<T>{
    private int size;
    private int nextFirst;
    private int nextLast;
    public T[] items;

    private double calcUsage(){
        return size/items.length;
    }

    public ArrayDeque(){
        items = (T []) new Object[8];
        nextLast = 0;
        nextFirst = 0;
        size = 0;

    }
    public ArrayDeque(ArrayDeque other){
        if (other.size()>8){
            this.resize();
        }
        for(int i=0;i<other.size();i+=1){

        }
    }
    private void resize(){
        //full
        if(nextFirst==nextLast) {
            int len = items.length * 2;
            T[] newitems = (T[]) new Object[len];
            for (int i = 0; i < size; i += 1) {
                newitems[i] = items[nextFirst % items.length];
                nextFirst += 1;
            }
            items = newitems;
        }
        //usage<0.25
        else{
            int len = (int) (items.length*0.3);
            T[] newitems = (T[]) new Object[len];
            for (int i = 0; i < size; i += 1) {
                newitems[i] = items[nextFirst % items.length];
                nextFirst += 1;
            }
            items = newitems;
        }


    }

    public void addFirst(T item){
        items[nextFirst] = item;
        nextFirst=(nextFirst==0)? items.length-1 : nextFirst-1;
        if(nextFirst==nextLast){
            this.resize();
        }

        size +=1;

    }

    public void addLast(T item){

        items[nextLast] = item;
        nextLast = (nextLast== items.length-1)?0:nextLast+1;
        if(nextLast==nextFirst){
            this.resize();
        }
        size +=1;

    }
    public boolean isEmpty(){
        return size==0;
    }

    public void printDeque(){
        int i = nextFirst +1;
        int cnt = size;
        while(cnt>0){
            System.out.print(items[i]);
            i = (i+1)% items.length;
            cnt -=1;
        }
        System.out.println();
    }

    public T removeFirst(){
        if(size==0){
            return null;
        }
        if(this.calcUsage()<0.25 && items.length>=16){
            this.resize();
        }
        nextFirst +=1;
        size -=1;
        return items[nextFirst-1];
    }

    public T removeLast(){
        if(size==0){
            return null;
        }
        if(this.calcUsage()<0.25){
            this.resize();
        }
        nextLast -=1;
        size -=1;
        return items[nextLast+1];
    }

    public T get(int index){
        if(index>size-1){return null;}
        int i = nextFirst +1;
        while(index>0){
            i = (i+1)% items.length;
            index -=1;
        }
        return items[i];
    }

    public int size(){
        return size;
    }
}
