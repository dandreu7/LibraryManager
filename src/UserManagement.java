import java.io.*;
import java.util.ArrayList;

public class UserManagement {
    private String username;
    private String password;
    private boolean admin;
    private boolean isMember;

    private static final String FILE_PATH = FirstTimeSetUp.getDesktop() + File.separator + "Library"
            + File.separator + "Members" + File.separator + "Members.txt";

    // Constructor
    public UserManagement(String username, String password, boolean admin, boolean isMember) {
        this.username = username;
        this.password = password;
        this.admin = admin;
        this.isMember = isMember;
    }

    // Convert User object to string for file storage
    @Override
    public String toString() {
        return username + "," + password + "," + admin + "," + isMember;
    }

    // Parse a user from a string
    public static UserManagement fromString(String line) {
        String[] parts = line.split(",");
        return new UserManagement(
                parts[0],
                parts[1],
                Boolean.parseBoolean(parts[2]),
                Boolean.parseBoolean(parts[3])
        );
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean isAdmin() { return admin; }
    public boolean isMember() { return isMember; }

    // Setters
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setAdmin(boolean admin) { this.admin = admin; }
    public void setMember(boolean member) { isMember = member; }

    // File operations
    public static ArrayList<UserManagement> loadUsers() {
        ArrayList<UserManagement> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(UserManagement.fromString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    public static void saveUsers(ArrayList<UserManagement> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (UserManagement user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    // Add a new user
    public static void addUser(ArrayList<UserManagement> users, UserManagement newUser) {
        users.add(newUser);
        saveUsers(users);
        System.out.println("User added successfully!");
    }

    // Delete a user by username
    public static void deleteUser(ArrayList<UserManagement> users, String username) {
        users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
        saveUsers(users);
        System.out.println("User deleted successfully!");
    }

    // Find a user by username
    public static UserManagement findUser(ArrayList<UserManagement> users, String username) {
        for (UserManagement user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null; // Return null if user not found
    }
}
