/**
 * Created by adi on 4/1/16.
 */
public class MyNodeList implements MyList {

    public static void main(String[] args) {
        //testing suite

        Object test1[] = {1,2,3, "abc", "def", "ghi"};
        MyNodeList test = new MyNodeList();

        System.out.println("---Add---");
        for (Object obj:test1){test.add(obj);}
        test.print();

        System.out.println("---insert---");
        test.insert(2,"insertion");
        test.print();

        System.out.println("---Contains---");
        System.out.println(test.contains(1));
        System.out.println(test.contains("sfds"));

        System.out.println("---Get---");
        System.out.println(test.get(5));

        System.out.println("---indexOf---");
        System.out.println(test.indexOf("abc"));
        System.out.println(test.indexOf("abdc"));

        System.out.println("---isEmpty---");
        System.out.println(test.isEmpty());

        System.out.println("---remove---");
        System.out.println(test.remove(4));
        test.print();

        System.out.println("---remove---");
        System.out.println(test.remove("abc"));
        test.print();

        System.out.println("---set---");
        test.set(5, "set");
        test.print();

        System.out.println("---size---");
        System.out.println(test.size());
    }


    private Node head;
    MyNodeList() {head = new Node();}


    @Override
    public boolean add(Object o) {
        if (o == null){return false;}
        Node current = head;
        while (current.getNext() != null){current = current.getNext();}

        Node next = new Node (o, null);
        current.setNext(next);
        return true;
    }

    private void print(){
        Node current = head;
        try{
            while (true){
                System.out.println(current.getData());
                current = current.getNext();
            }
        } catch (Exception e){}
    }

    @Override
    public boolean insert(int index, Object o) {
        if (o==null){return false;}
        Node current = head;
        try {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        }catch (Exception e){return false;}
        Node next = new Node (o,current.getNext());
        current.setNext(next);
        return true;
    }

    @Override
    public void clear() {head.setNext(null);}

    @Override
    public boolean contains(Object o) {
        if (o == null){return false;}
        Node current = head;
        while (current.getNext() != null){
            if (o.equals(current.getData())){return true;}
            current = current.getNext();
        }
        return false;
    }

    @Override
    public Object get(int index) {
        Node current = head;
        try {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        }catch (Exception e){return null;}
        return current.getData();
    }

    @Override
    public int indexOf(Object o) {
        if (o == null){return -1;}
        Node current = head;
        int index = 0;
        try {
            while (true){
                current = current.getNext();
                index ++;
                if (current.getData().equals(o)){
                    return index;
                }
            }
        }catch (Exception e){}
        return -1;
    }

    @Override
    public boolean isEmpty() {
        if (head.getNext() != null){return false;}
        else {return false;}
    }

    @Override
    public Object remove(int index) {
        Node current = head;
        try {
            for (int i = 0; i < index-1; i++) {
                current = current.getNext();
            }
        }catch (Exception e){return null;}

        Object ret = current.getNext().getData();
        current.setNext(current.getNext().getNext());

        return ret;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null){return false;}
        Node current = head;
        try{
            while(true){
                current = current.getNext();
                if (o.equals(current.getNext().getData())){
                    current.setNext(current.getNext().getNext());
                    return true;
                }
            }
        }catch (Exception e){return false;}
    }

    @Override
    public void set(int index, Object o) {
        Node current = head;
        try {
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
        }catch (Exception e){}

        current.setData(o);
    }

    @Override
    public int size() {
        Node current = head;
        int count = 0;
        while (current.getNext() != null){
            current = current.getNext();
            count ++;
        }
        return count;
    }
}
