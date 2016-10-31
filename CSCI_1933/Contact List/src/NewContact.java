/**
 * Created by pidap004 on 3/8/16.
 */
public class NewContact extends Contact{
    private String email;
    private String group;
    private String quickRef; //Shorthand or nickname


    public NewContact() {}

    public NewContact (String name, long phone, String address, String comments,
                        String email, String group, String quickRef) {
        super(name, phone, address, comments);//Calls constructor for name/phone/address/comments
        this.email = email;
        this.group = group;
        this.quickRef = quickRef;
    }




    @Override
    public String toString() {return  super.toString() + "|" + email + "|" + group + "|" + quickRef;}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getQuickRef() {
        return quickRef;
    }

    public void setQuickRef(String quickRef) {
        this.quickRef = quickRef;
    }
}
