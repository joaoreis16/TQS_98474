package tqs.container;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "school", nullable = false)
    private String school;


    public Student() {}

    public Student(Long id, String name, String school) {
        this.id = id;
        this.name = name;
        this.school = school;
    }
    

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", school='" + getSchool() + "'" +
            "}";
    }
}
