package fxs.free.com.autocompletetextview.demo;

public class UserVM implements Cloneable {
    private String name;
    private String tel;
    private String email;

    public UserVM(String name, String tel, String email) {
        this.name = name;
        this.tel = tel;
        this.email = email;
    }

    @Override
    public UserVM clone() {

        UserVM userVM = null;

        try {
            userVM = (UserVM) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return userVM;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel == null ? "" : tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
