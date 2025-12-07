package Class;

public class Employee {
    private String id;
    private String name;
    private boolean gender;
    private double salary;

    public Employee(){}

    public Employee(String a, String b, boolean c, double d) {
    	this.id = a;
    	this.name = b;
    	this.gender =c;
    	this.salary = d;
    }
    public String getId() {return id;}
}
