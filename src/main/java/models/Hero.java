package models;

public class Hero {
    private String name;
    private int age;
    private String superPower;
    private String weakness;
    private int id;
    private int squadId;

    public Hero(String name, int age, String superPower, String weakness, int squadId){
        this.name = name;
        this.age = age;
        this.superPower = superPower;
        this.weakness = weakness;
        this.squadId = squadId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Hero hero = (Hero) o;

        if (age != hero.age) return false;
        if (squadId != hero.squadId) return false;
        if (!name.equals(hero.name)) return false;
        return superPower.equals(hero.superPower);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        result = 31 * result + superPower.hashCode();
        result = 31 * result + squadId;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSuperPower() {
        return superPower;
    }

    public void setSuperPower(String superPower) {
        this.superPower = superPower;
    }

    public String getWeakness() {
        return weakness;
    }

    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSquadId() {
        return squadId;
    }

    public void setSquadId(int squadId) {
        this.squadId = squadId;
    }

}
