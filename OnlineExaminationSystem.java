import java.util.*;
class User {
    private String username;
    private String password;
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
public class OnlineExaminationSystem {
    private static User currentUser;
    private static Map<String, String> users = new HashMap<>();
    private static Scanner scan = new Scanner(System.in);
    private static int score = 0;
    public static void main(String[] args) {
        users.put("user1", "pass1");
        System.out.println("Welcome to Online Examination System");
        if (login()) {
            System.out.println("Login successful!");
            mainMenu();
        } else {
            System.out.println("Invalid user or password..");
        }
    }
    private static boolean login() {
        System.out.print("Enter username: ");
        String username = scan.nextLine();
        System.out.print("Enter password: ");
        String password = scan.nextLine();
        if (users.containsKey(username) && users.get(username).equals(password)) {
            currentUser = new User(username, password);
            return true;
        }
        return false;
    }
    private static void mainMenu() {
        while (true) {
            System.out.println("\n1. Update Profile/Password");
            System.out.println("2. Start Exam");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");
            int choice = scan.nextInt();
            scan.nextLine();
            switch (choice) {
                case 1:
                    updateProfile();
                    break;
                case 2:
                    startExam();
                    break;
                case 3:
                    System.out.println("Logged out successfully.");
                    return;
                default:
                    System.out.println("Invalid option! Try again.");
            }
        }
    }
    private static void updateProfile() {
        System.out.print("Enter new username: ");
        String newUsername = scan.nextLine();
        System.out.print("Enter new password: ");
        String newPassword = scan.nextLine();
        users.put(newUsername, newPassword);
        users.remove(currentUser.getUsername());
        currentUser.setUsername(newUsername);
        currentUser.setPassword(newPassword);
        System.out.println("Profile updated successfully!");
    }

    private static void startExam() {
        System.out.println("Exam started! You have 30 seconds.");
        long startTime = System.currentTimeMillis();
        long endTime = startTime + 30000;

        String[][] questions = {
            {"Java is a:", "1. Programming Language", "2. Fruit", "3. Vehicle", "4. Movie", "1"},
            {"Which keyword is used to inherit a class in Java?", "1. implement", "2. inherit", "3. extends", "4. super", "3"},
            {"Which method is used to print in Java?", "1. print()", "2. println()", "3. display()", "4. show()", "2"}
        };
        for (String[] q : questions) {
            if (System.currentTimeMillis() > endTime) {
                System.out.println("Time's up! Auto-submitting...");
                break;
            }
            System.out.println(q[0]);
            for (int i = 1; i <= 4; i++) {
                System.out.println(q[i]);
            }
            System.out.print("Your answer: ");
            String answer = scan.nextLine();
            if (answer.equals(q[5])) {
                score++;
                System.out.println("Correct!");
            } else {
                System.out.println("Wrong!");
            }
        }
        System.out.println("\nExam finished!");
        System.out.println("Your score: " + score + "/" + questions.length);
    }
}
