package unity;

public class Emp {
    private String ename;
    private String eage;
    private Dept dept ;

    @Override
    public String toString() {
        return "Emp{" +
                "ename='" + ename + '\'' +
                ", eage='" + eage + '\'' +
                ", dept=" + dept +
                ", salary=" + salary +
                '}';
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    private Double salary;

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEage() {
        return eage;
    }

    public void setEage(String eage) {
        this.eage = eage;
    }

}
