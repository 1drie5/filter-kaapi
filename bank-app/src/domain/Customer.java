package domain;

public class Customer {
    private String id;
    private String name;
    private String email;

    public Customer(String email, String id, String name) {
        this.email = email;
        this.id = id;
        this.name = name;
    }
}
