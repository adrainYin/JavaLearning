package unity;

public class Dept {
    private String dname;
    private String did;
    private Integer telephone;
    private Long number;

    @Override
    public String toString() {
        return "Dept{" +
                "dname='" + dname + '\'' +
                ", did='" + did + '\'' +
                ", telephone=" + telephone +
                ", number=" + number +
                ", company=" + company +
                '}';
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }



    private Company company ;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }
}
