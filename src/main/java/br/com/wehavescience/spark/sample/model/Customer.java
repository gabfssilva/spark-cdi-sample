package br.com.wehavescience.spark.sample.model;

/**
 * @author Gabriel Francisco <gabfssilva@gmail.com>
 */
public class Customer {
    private int id;
    private String name;
    private String occupation;
    private int age;

    public Customer(int id, String name, String occupation, int age) {
        this.id = id;
        this.name = name;
        this.occupation = occupation;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != customer.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
