package CabBooking.src.Models;

import CabBooking.src.enums_and_constants.*;;

public abstract class Person {
    private static int auto_id = 0;
    private int id;
    private int age;
    private String name;
    private Gender gender;

    public Person(String name, int age, Gender gender){
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.id = get_next_id();

    }
    private int get_next_id()
    {
        auto_id+=1;
        return auto_id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        
        this.id = get_next_id();
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public int getAge()
    {
        return age;
    }

    public void add_person(String name, int age, Gender gender)
    {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.id = get_next_id();
    }

    public String get_name()
    {
        return name;
    }
}
