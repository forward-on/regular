/**
 * @author : ly.
 * @Date : 2018/4/26.
 */
public class Person {

    private Integer ageOne;
    private Integer ageTwo;
    private Integer maxAge;
    private Integer minAge;
    private String name;
    private String address;

    public Integer getAgeOne() {
        return ageOne;
    }

    public void setAgeOne(Integer ageOne) {
        this.ageOne = ageOne;
    }

    public Integer getAgeTwo() {
        return ageTwo;
    }

    public void setAgeTwo(Integer ageTwo) {
        this.ageTwo = ageTwo;
    }

    public Integer getMaxAge() {
        return getAgeOne() > getAgeTwo() ? getAgeOne() : getAgeTwo();
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Integer getMinAge() {
        return getAgeOne() < getAgeTwo() ? getAgeOne() : getAgeTwo();
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ageOne=" + ageOne +
                ", ageTwo=" + ageTwo +
                ", maxAge=" + maxAge +
                ", minAge=" + minAge +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.setAgeOne(20);
        person.setAgeTwo(30);
        System.out.println(person.toString());
        System.out.println(person.getMaxAge());
    }
}
