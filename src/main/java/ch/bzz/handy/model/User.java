package ch.bzz.handy.model;

public class User {
    private String userUUID;
    private String username;
    private String password;
    private String role;


    public User(){
        setRole("guest");
    }
    /**
     * nimmt die userUUID
     *
     * @return Wert von userUUID
     */
    public String getUserUUID() {
        return userUUID;
    }

    /**
     * setzt userUUID
     *
     * @param userUUID setzt den Wert
     */
    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    /**
     * nimmt username
     *
     * @return Wert von username
     */

    public String getUsername() {
        return username;
    }

    /**
     * setzt username
     *
     * @param username setzt den Wert
     */

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * nimmt passwort
     *
     * @return Wert von passwort
     */

    public String getPassword() {
        return password;
    }

    /**
     * setzt passwort
     *
     * @param password setzt den Wert von passwort
     */

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * nimmt Rolle
     *
     * @return Wert von Rolle
     */

    public String getRole() {
        return role;
    }

    /**
     * setzt Rolle
     *
     * @param role setzt den Wert von Rolle
     */
    public void setRole(String role) {
        this.role = role;
    }
}
