package cat.urv.comm.padm.persistence;

public class Contact {

    String name;
    String surnames;
    int age;

    public Contact(String name, String surnames, int age) {
        this.name = name;
        this.surnames = surnames;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurnames() {
        return surnames;
    }

    public void setSurnames(String surnames) {
        this.surnames = surnames;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
