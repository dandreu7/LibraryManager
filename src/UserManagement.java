public class UserManagement {
    private String username;
    private String password;
    private boolean admin;
    private boolean isMember;
    public UserManagement(String username, String password, boolean admin, boolean isMember) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.isMember = isMember;
    }
    //Getters and Setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isAdmin() {
        return admin;
    }
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public boolean isMember() {
        return isMember;
    }
    public void setMember(boolean member) {
        isMember = member;
    }

}
