package com.lambdaschool.crudyorders.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//The entity allowing interaction with the customers table
@Entity
@Table(name="customers")
public class Customer {

    //The primary key number (long) of the customer's table
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long custcode;

    //The name (String) of the customer. Cannot be null
    @Column(nullable = false)
    private String custname;

    //The city (String) of the customer.
    private String custcity;

    //The country (String) of the customer.
    private String custcountry;

    //The grade (String) of the customer.
    private String grade;

    //Used to determine if the field openingamt has been set or is NULL, meaning 0.0 for a double value
    //Does not get saved to the database
    @Transient
    public boolean hasvalueforopeningamt = false;
    //The openingamt (double) of the customer's account.
    private double openingamt;

    //Used to determine if the field outstandingamt has been set or is NULL, meaning 0.0 for a double value
    //Does not get saved to the database
    @Transient
    public boolean hasvalueforoutstandingamt = false;
    //The amount outstanding (double) on the customer's account
    private double outstandingamt;

    //Used to determine if the field paymentamt has been set or is NULL, meaning 0.0 for a double value
    //Does not get saved to the database
    @Transient
    public boolean hasvalueforpaymentamt = false;
    //The payment amount (double) on the customer's account
    private double paymentamt;

    //Used to determine if the field receiveamt has been set or is NULL, meaning 0.0 for a double value
    //Does not get saved to the database
    @Transient
    public boolean hasvalueforreceiveamt = false;
    //The amount received (double) on the customer's account
    private double receiveamt;

    //The phone number (String) of the agent. No predefined format.
    private String phone;

    //The workingarea, geographical area, (String) of the customer
    private String workingarea;

    //A foreign key to the agent table
    //Forms a Many-To-One relation with the agent table. Many customers to one agent.
    //Contains an object pointer to the full agent object
    @ManyToOne
    @JoinColumn(name="agentcode", nullable=false)
    @JsonIgnoreProperties(value="customers", allowSetters = true)
    private Agent agent;

    //List of orders associated with this customer. Does not get saved in the database directly
    //Forms a One-To-Many relationship to orders. One customer to many orders
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties(value="customer", allowSetters = true)
    private List<Order> orders = new ArrayList<>();

    //Default constructor primarily used by the JPA
    public Customer() {
    }

    /*
        Given the params, create a new customer object.
        <p>
        custcode is autogenerated

        @param custname             The name (String) of the customer
        @param custcity             The city (String) of the customer
        @param workingarea          The workingarea, geographical location, (String) of the customer
        @param custcountry          The country (String) of the customer
        @param grade                The grade (String) of the customer
        @param openingamt           The openingamt (double) of the customer's account
        @param receiveamt           The amount received (double) on the customer's account
        @param paymentamt           The payment amount (double) on the customer's account
        @param outstandingamt       The amount outstanding (double) on the customer's account
        @param phone                The phone number (String) of the agent
        @param agent                The agent record associated with this customer
                                    orders are added outside of this constructor
     */
    public Customer(String custname,
                    String custcity,
                    String workingarea,
                    String custcountry,
                    String grade,
                    double openingamt,
                    double receiveamt,
                    double paymentamt,
                    double outstandingamt,
                    String phone,
                    Agent agent) {

        this.custcity = custcity;
        this.custcountry = custcountry;
        this.custname = custname;
        this.grade = grade;
        this.openingamt = openingamt;
        this.outstandingamt = outstandingamt;
        this.paymentamt = paymentamt;
        this.phone = phone;
        this.receiveamt = receiveamt;
        this.workingarea = workingarea;
        this.agent = agent;
    }

    //Getter custcode
    //@return The primary key number (long) of the customer's table
    public long getCustcode() {
        return custcode;
    }

    //Setter for the custcode - used primarily when the seeding data
    //@param custcode The new primary key number (long) of the customer's table
    public void setCustcode(long custcode) {
        this.custcode = custcode;
    }

    //Getter for the custcity
    //@return The city (String) of the customer
    public String getCustcity() {
        return custcity;
    }

    //Setter for the custcity
    //@param custcity The new name (String) of the customer
    public void setCustcity(String custcity) {
        this.custcity = custcity;
    }

    //Getter for the custcountry
    //@return The city (String) of the customer
    public String getCustcountry() {
        return custcountry;
    }

    //Setter for custcountry
    //@param custcountry The new country (String) of the customer
    public void setCustcountry(String custcountry) {
        this.custcountry = custcountry;
    }

    //Getter for custname
    //@return The name (String) of the customer.
    public String getCustname() {
        return custname;
    }

    //Setter for custname
    //@param custname The new name (String) of the customer
    public void setCustname(String custname) {
        this.custname = custname;
    }

    //Getter for grade
    //@return The city (String) of the customer
    public String getGrade() {
        return grade;
    }

    //Setter for grade
    //@param grade The new grade (String) of the customer
    public void setGrade(String grade) {
        this.grade = grade;
    }

    //Getter for paymentamt
    //@return The payment amount (double) on the customer's account
    public double getPaymentamt() {
        return paymentamt;
    }

    //Setter for paymentamt
    //@params paymentamt The new payment amount (double) on the customer's account
    public void setPaymentamt(double paymentamt) {
        hasvalueforpaymentamt = true;
        this.paymentamt = paymentamt;
    }

    //Getter for openingamt
    //@return The openingamt (double) of the customer's account
    public double getOpeningamt() {
        return openingamt;
    }

    //Setter for openingamt
    //@param openingamt The new openingamt (double) of the customer's account
    public void setOpeningamt(double openingamt) {
        hasvalueforopeningamt = true;
        this.openingamt = openingamt;
    }

    //Getter for oustandingamt
    //@return The outstandingamt (double) of the customer's account
    public double getOutstandingamt() {
        return outstandingamt;
    }

    //Setter for oustandingamt
    //@param oustandingamt The new amount outstanding (double) of the customer's account
    public void setOutstandingamt(double outstandingamt) {
        hasvalueforoutstandingamt = true;
        this.outstandingamt = outstandingamt;
    }

    //Getter for receiveamt
    //@return The amount received (double) on the customer's account
    public double getReceiveamt() {
        return receiveamt;
    }

    //Setter for receiveamt
    //@param receiveamt The new amount received (double) on the customer's account
    public void setReceiveamt(double receiveamt) {
        hasvalueforreceiveamt = true;
        this.receiveamt = receiveamt;
    }

    //Getter for phone
    //@return The phone number (String) of the agent
    public String getPhone() {
        return phone;
    }

    //Setter for phone
    //@param phone The new phone number (String) of the agent
    public void setPhone(String phone) {
        this.phone = phone;
    }

    //Getter for workingarea
    //@return The workingarea, geographical location, (String) of the customer
    public String getWorkingarea() {
        return workingarea;
    }

    //Setter for workingarea
    //@param workingarea The new workignarea, geographical location, (String) of the customer
    public void setWorkingarea(String workingarea) {
        this.workingarea = workingarea;
    }

    //Getter for agent
    //@return The agent record assigned to this customer
    public Agent getAgent() {
        return agent;
    }

    //Setter for agent
    //@param agent The new agent record assigned to this customer
    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    //Getter orders
    //@return List of orders for this customer.
    public List<Order> getOrders() {
        return orders;
    }

    //Setter orders
    //@param orders A new list of orders for this customer.
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
