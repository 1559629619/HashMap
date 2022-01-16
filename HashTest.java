package hash.HashMap;

public class HashTest {

    public static void main(String[] args) {
        HashMMap h=new HashMMap();
        h.put(1,"大肥鹅");
        h.put(2,"小肥鹅");
        h.put(3,"小瘦鹅");
        h.del(2);
        System.out.println(h.get(1));
        System.out.println(h.get(2));
        System.out.println(h.get(3));
        System.out.println(h.get(4));
    }
}
class HashMMap{
    Node [] arr=new Node[16];
    void put(Object k,Object v){
        int hash=hhash(k.hashCode())&(arr.length-1);
        if(arr[hash]==null){
            Node t=new Node(v);
            arr[hash]=t;
            t.k=k;
            arr[hash]=t;
            return;
        }
        Node tmp=arr[hash];
        while(tmp.next!=null){
            if(tmp.val.equals(v))
                {tmp.val=v;
                tmp.k=k;
                return;}
            tmp=tmp.next;
        }
        tmp.next=new Node(v);
    }
    Object get(Object k){
        //效果与对长度求余是相同的，效率更高
        int hash=hhash(k.hashCode())&(arr.length-1);
        Node tmp=arr[hash];
        while(tmp!=null){
            if(k.equals(tmp.k))
                return tmp.val;
            tmp=tmp.next;
        }
        return "null";
    }
    Boolean del(Object k){
        int hash=hhash(k.hashCode())&(arr.length-1);
        Node tmp=arr[hash];
        Node cur=arr[hash];
        while(tmp!=null){
            if(k.equals(tmp.k))
            {
                if(cur==tmp){
                    arr[hash]=null;
                    return true;
                }
                while(cur.next!=tmp){
                    cur=cur.next;
                }
                cur.next=tmp.next;
                return true;
            }

            tmp=tmp.next;
        }
        return false;
    }
    //计算hash   为了将高16位也能尽量用到，右移16位后与自己异或，减轻hash冲突
    int hhash(int hashcode){
        return hashcode^(hashcode>>>16);
    }

}
class Node {
    Node next;
    Object k;
    Object val;
    int hash;
    Node(){

    }
    Node(Object val){
        this.val=val;
    }

}