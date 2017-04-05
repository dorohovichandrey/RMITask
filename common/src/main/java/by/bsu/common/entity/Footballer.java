package by.bsu.common.entity;

import java.io.Serializable;

/**
 * Created by User on 21.03.2017.
 */
public class Footballer extends Entity<Integer> {

    private String Name;
    private String Surname;
    private Integer age;
    private Team team;

    public Footballer() {
    }

    public Footballer(String name, String surname, Integer age, Team team) {
        Name = name;
        Surname = surname;
        this.age = age;
        this.team = team;
    }

    public Footballer(Integer id, String name, String surname, Integer age, Team team) {
        super(id);
        Name = name;
        Surname = surname;
        this.age = age;
        this.team = team;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }



    @Override
    public String toString() {
        return "Footballer{" +
                "Name='" + Name + '\'' +
                ", Surname='" + Surname + '\'' +
                ", age=" + age +
                ", team=" + team +
                '}';
    }
}
