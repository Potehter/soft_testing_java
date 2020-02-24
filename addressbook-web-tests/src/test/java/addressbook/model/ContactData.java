package addressbook.model;

public class ContactData {
    private final String name;
    private final String midName;
    private final String surname;
    private final String nickname;
    private final String title;
    private final String company;

    public ContactData(String name, String midName, String surname, String nickname, String title, String company) {
        this.name = name;
        this.midName = midName;
        this.surname = surname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public String getMidName() {
        return midName;
    }

    public String getSurname() {
        return surname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }
}
