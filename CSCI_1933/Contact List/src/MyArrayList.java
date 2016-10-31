/**
 * Created by adi on 4/1/16.
 */
public class MyArrayList implements MyList {

    public Object[] arr;

    public static void main(String[] args) {
        //testing suite

        Object test1[] = {1,2,3, "abc", "def", "ghi"};
        Object test2[] = new Object[10];
        MyArrayList test = new MyArrayList();

        System.out.println("---Add---");
        for (Object obj:test1) {test.add(obj);}
        for (Object obj:test.arr) {System.out.println(obj);}

        System.out.println("---Insert---");
        test.insert(2, "A");
        for (Object obj:test.arr) {System.out.println(obj);}

        System.out.println("---Clear---");
        test.clear();
        for (Object obj:test.arr) {System.out.println(obj);}

        System.out.println("---Contains---");
        for (Object obj:test1) {test.add(obj);}
        System.out.println(test.contains(1));
        System.out.println(test.contains("sfds"));

        System.out.println("--Get---");
        System.out.println(test.get(3));
        System.out.println(test.get(20));

        System.out.println("---indexOf---");
        System.out.println(test.indexOf(3));
        System.out.println(test.indexOf("l"));

        System.out.println("--isEmpty---");
        System.out.println(test.isEmpty());

        System.out.println("---Remove---");
        System.out.println(test.remove(4));

        System.out.println("---Remove---");
        System.out.println(test.remove("def"));
        System.out.println(test.remove("sjklfjd"));

        System.out.println("---Set---");
        test.set(1,"replace");
        for (Object obj:test.arr) {System.out.println(obj);}

        System.out.println("---size---");
        for (Object obj:test.arr) {System.out.println(obj);}
        System.out.println(test.size());
    }

    public MyArrayList() {
        // default constructor that creates an array of Objects of size 2
        arr = new Object[2];
    }

    @Override
    public boolean add(Object o) {
        if (o == null) {return false;}
        if (arr[arr.length-1] != null){
            arr = grow();
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null){
                arr[i] = o;
                return true;
            }
        }
        return true;

    }

    private Object[] grow() {
        //What to do if list cannot support additional element
        Object [] newArr = new Object[arr.length*2];
        for (int i = 0; i < arr.length; i++) {newArr[i] = arr[i];}
        return newArr;
    }

    @Override
    public boolean insert(int index, Object o) {
        if (arr[arr.length-1] != null){
            arr = grow();
        }
        for (int i = arr.length-1; i >= index ; i--) {arr[i] = arr[i-1];}
        arr[index] = o;
        return true;
    }

    @Override
    public void clear() {arr = new Object[2];}

    @Override
    public boolean contains(Object o) {
        if (o == null){return false;}
        for (Object obj:arr){
            if (o.equals(obj)){return true;}
        }
        return false;
    }

    @Override
    public Object get(int index) {
        try {return arr[index];}
        catch (Exception e){}
        return null;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {return -1;}
        for (int i = 0; i < arr.length; i++) {
            if (o.equals(arr[i])){
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        for(Object obj:arr){
            if (obj != null){return false;}
        }
        return true;
    }

    @Override
    public Object remove(int index) {
        if (arr[index]==null){return null;}

        Object temp = arr[index];
        for (int i = index-1; i < arr.length; i++) {
            try{arr[i] = arr[i+1];}
            catch (Exception e){}
        }
        return temp;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < arr.length; i++) {
            if (o.equals(arr[i])){
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void set(int index, Object o) {
        try {
            if (o == null){throw new Exception();}
            arr[index] = o;
        }catch (Exception e) {}
    }

    @Override
    public int size() {
        int count = 0;
        for (Object elem: arr){
            if (elem != null){count ++;}
        }
        return count;
    }
}
