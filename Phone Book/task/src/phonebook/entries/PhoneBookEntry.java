package phonebook.entries;

public class PhoneBookEntry {

    private String phoneNum;
    private String name;

    public PhoneBookEntry(String phoneNum, String name) {
        this.phoneNum = phoneNum;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public String getName() {
        return name;
    }
}
