package addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
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
        this.id = Integer.MAX_VALUE;
    }

    public ContactData(int id, String name, String midName, String surname, String nickname, String title, String company) {
        this.name = name;
        this.midName = midName;
        this.surname = surname;
        this.nickname = nickname;
        this.title = title;
        this.company = company;
        this.id = id;
    }

    public ContactData(int id, String name, String surname) {
        this.name = name;
        this.midName = "";
        this.surname = surname;
        this.nickname = "";
        this.title = "";
        this.company = "";
        this.id = id;
    }

    public int getId() {
        return id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname);
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
