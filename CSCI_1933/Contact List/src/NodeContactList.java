import java.io.ObjectOutputStream; // output stream for Object
import java.io.FileOutputStream; // generic output stream
import java.io.ObjectInputStream; // input stream for Objects
import java.io.FileInputStream; // generic input stream
import java.io.*; // provide easy access to all IO Exceptions

/**
 * Created by pidap004 on 4/1/16.
 */
public class NodeContactList extends MyNodeList {
    private int ptr = -1;
    public Contact[] contactList = new Contact[20];
    public int listLength;

    public static void main(String[] args) {

        Contact contact1 = new Contact ("Jim", 1111111111, "123 Main St.", "Kind of a shitty person");
        Contact contact2 = new Contact ("Dovolis", 1234567890, "UMN", "unnatural love for while loops");
        NewContact newcontact1 = new NewContact("Jordan Kleist", 9999999999l, "Middlebrook", "NERD", "no", "CSCI 1133H", "Jordy");
        NewContact newcontact2 = new NewContact ("Henry the Unicorn", 666, "Yes", "Has rainbow poops", "unicornh@magical.com", "DNE", "HenHen");

        //Testing for Part 2
        /*ArrayContactList test = new ArrayContactList();
        Contact adder [] = {contact1, contact2, newcontact1, newcontact2};
        for (Contact contact:adder) {
            test.add(contact);
        }
        for (Contact contact:test.contactList){
            if (contact != null) {System.out.println(contact.toString());}
        }*/

        //Testing for Part 3
        /*ArrayContactList test = new ArrayContactList();
        ArrayContactList newList = new ArrayContactList();
        Contact adder [] = {contact1, contact2, newcontact1, newcontact2};
        for (Contact contact:adder) {test.add(contact);}
        for (Contact contact:newList.contactList){
            if (contact != null) {System.out.println(contact.toString());}
        }*/
        //testing for Part 4
        /*ArrayContactList test = new ArrayContactList();
        ArrayContactList newList = new ArrayContactList();

        Contact adder [] = {contact1, contact2, newcontact1, newcontact2};
        for (Contact contact:adder) {test.add(contact);}
        for (Contact contact:adder) {newList.addInOrder(contact);}

        for (Contact contact:test.contactList) {
            if (contact != null) {
                System.out.println(contact.toString());
            }
        }
        System.out.println("------------------------");
        for (Contact contact:newList.contactList) {
            if (contact != null) {
                System.out.println(contact.toString());
            }
        }*/
    }

    public boolean add(Contact c) {
        return super.add(c);

    }

    public Contact find(String name) {
        for (int curr = ptr; curr < size(); curr++) {
            if (get(curr) != null && get(curr).getName().contains(name)) {
                ptr = curr;
                return get(curr);
            }
        }
        for (int curr = 0; curr < ptr; curr++) {
            if (get(curr).getName().contains(name)) {
                ptr = curr;
                return get(curr);
            }
        }
        return null;
    }

    public Contact remove() {
        Contact curr = (Contact) get(ptr);
        set(ptr, null);
        for (int i = ptr + 1; i < size(); i++) {
            if (get(i) != null) {
                set(i-1, get(i));
                set(i, null);
            }
        }
        ptr = size();
        return curr;
    }


    public Contact getCurrent() {
        return (Contact)super.get(ptr);
    }

    public Contact get(int i) {
        return (Contact)super.get(i);
    }

    public Contact next() {
        if (ptr > size()) {
            ptr = 0;
        }
        else {ptr++;}
        return get(ptr);

    }

    public Contact previous() {
        if (ptr < 0) {
            ptr = size();
        }
        else {ptr -= 1;}

        return get(ptr);
    }

    public void sort (){
        int swaps = -1;
        int compare;
        String curr;
        String next;
        Contact holder;
        while (swaps != 0) {
            swaps = 0;
            for (int i = 0; i < size()-1;i++) {
                try {
                    curr = get(i).getName();
                    next = get(i+1).getName();
                    compare = curr.compareTo(next);
                    if (compare > 0) {
                        holder = get(1);
                        set(i, i+1);
                        set(i+1, holder);
                        swaps++;
                    }
                } catch (Exception e) {}
            }

        }
    }

    public boolean addInOrder (Contact c) {
        String currName = c.getName();
        String nextName;
        Contact temp;

        for (int i = 0; i < size(); i++) {
            try {
                nextName = get(i).getName();
                int compare = currName.compareTo(nextName);
                if (compare < 0) { //implement code for case of ArrayContactList is full
                    for (int j = size(); j >= i ; j--) { //move everything down one, found insert point
                        try {set(j+1, get(j));
                        } catch (Exception e) {}
                    }
                    //System.out.println("Before modification: " + contactList[i].toString());
                    set(i, c);
                    //System.out.println("After modification : "+ contactList[i].toString());
                    return true;
                }
            }catch (Exception e) {}
        }
        add(c);
        return true;
    }
}

