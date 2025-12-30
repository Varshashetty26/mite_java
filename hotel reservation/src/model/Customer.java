package model;
//customer class
public class Customer {
    //required customer details
    private String firstname;
    private String lastName;
    private String email;
    //for validating email according to the format
    private static final String emailRegex="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,10}$";
    //constructor
    public Customer (String firstname,String lastName,String email){
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("Error: Invalid email format. Expected format: name@domain.extension");
        }
        this.firstname=firstname;
        this.lastName=lastName;
        this.email=email;
    }
    //getter method

    public String getEmail() {
        return email;
    }
    //toString()

    @Override
    public String toString() {
        return "\n" +
                "firstname: " + firstname + "\n" +
                "lastName: " + lastName + "\n" +
                "email: " + email;
    }
}
