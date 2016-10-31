import java.io.Serializable;

/**
 * Created by pidap004 on 2/23/16.
 */
public class Contact implements Serializable{
    private String name;
    private long phone;
    private String address;
    private String comments;

    public Contact (){}

    public Contact (String name, long phone, String address, String comments) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.comments = comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return  name + "|" + phone + "|" + address + "|" + comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (phone != contact.phone) return false;
        if (name != null ? !name.equals(contact.name) : contact.name != null) return false;
        if (address != null ? !address.equals(contact.address) : contact.address != null) return false;
        return !(comments != null ? !comments.equals(contact.comments) : contact.comments != null);

    }
}
