package com.emailxl.consked.external_db;

/**
 * @author ECG
 */

public class WorkerExt {
    private int workerIdExt;
    private int isDisabled;
    private String lastLoginTime;
    private String phone;
    private String email;
    private String smsemail;
    private String passwordHash;
    private String resetCodeHash;
    private String firstName;
    private String middleName;
    private String lastName;
    private String externalAuthentication;
    private String authrole;

    // Constructors
    public WorkerExt() {
    }

    public WorkerExt(int workerIdExt, int isDisabled, String lastLoginTime, String phone, String email,
                     String smsemail, String passwordHash, String resetCodeHash, String firstName,
                     String middleName, String lastName, String externalAuthentication,
                     String authrole) {
        this.workerIdExt = workerIdExt;
        this.isDisabled = isDisabled;
        this.lastLoginTime = lastLoginTime;
        this.phone = phone;
        this.email = email;
        this.smsemail = smsemail;
        this.passwordHash = passwordHash;
        this.resetCodeHash = resetCodeHash;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.externalAuthentication = externalAuthentication;
        this.authrole = authrole;
    }

    // workerIdExt functions
    public int getWorkerIdExt() {
        return this.workerIdExt;
    }

    public void setWorkerIdExt(int workerIdExt) {
        this.workerIdExt = workerIdExt;
    }

    // isDisabled functions
    public int getIsDisabled() {
        return this.isDisabled;
    }

    public void setIsDisabled(int isDisabled) {
        this.isDisabled = isDisabled;
    }

    // lastLoginTime functions
    public String getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    // phone functions
    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // email functions
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // smsemail functions
    public String getSmsemail() {
        return this.smsemail;
    }

    public void setSmsemail(String smsemail) {
        this.smsemail = smsemail;
    }

    // passwordHash functions
    public String getPasswordHash() {
        return this.passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    // resetCodeHash functions
    public String getResetCodeHash() {
        return this.resetCodeHash;
    }

    public void setResetCodeHash(String resetCodeHash) {
        this.resetCodeHash = resetCodeHash;
    }

    // firstName functions
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // middleName functions
    public String getMiddleName() {
        return this.middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    // lastName functions
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // externalAuthentication functions
    public String getExternalAuthentication() {
        return this.externalAuthentication;
    }

    public void setExternalAuthentication(String externalAuthentication) {
        this.externalAuthentication = externalAuthentication;
    }

    // authrole functions
    public String getAuthrole() {
        return this.authrole;
    }

    public void setAuthrole(String authrole) {
        this.authrole = authrole;
    }

    @Override
    public String toString() {
        return getClass().getName() + "[" +
                "workerIdExt=" + workerIdExt + ", " +
                "isDisabled=" + isDisabled + ", " +
                "lastLoginTime=" + lastLoginTime + ", " +
                "phone=" + phone + ", " +
                "email=" + email + ", " +
                "smsemail=" + smsemail + ", " +
                "passwordHash=" + passwordHash + ", " +
                "resetCodeHash=" + resetCodeHash + ", " +
                "firstName=" + firstName + ", " +
                "middleName=" + middleName + ", " +
                "lastName=" + lastName + ", " +
                "externalAuthentication=" + externalAuthentication + ", " +
                "authrole=" + authrole + "]";
    }
}
