import java.util.*;

/**
 * 多例的设计模式 不允许对象的共有创建 ，只能在初始化的时候创建好
 */
class PersonDemo{
    private String name;
    private String birthday;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "PersonDemo{" +
                "name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}


public class Color {
    private String name;
    private Integer age ;
    private PersonDemo personDemo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public static void main(String[] args) {

    }

    public PersonDemo getPersonDemo() {
        return personDemo;
    }

    public void setPersonDemo(PersonDemo personDemo) {
        this.personDemo = personDemo;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Color)) return false;
        Color color = (Color) o;
        return Objects.equals(name, color.name) &&
                Objects.equals(age, color.age) &&
                /*在此属性比较的时候，可以发现因为该属性也是一个简单java类对象
                所以在比较的时候直接用了equals()方法，所以比较的还可能是内存地址
                 */
                Objects.equals(personDemo, color.personDemo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, age, personDemo);
    }

}
