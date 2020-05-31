package models;

public class Squad {
    private String name;
    private int size;
    private String source;
    private int id;

    public Squad(String name, int size, String source){
        this.name = name;
        this.size = size;
        this.source = source;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Squad squad = (Squad) o;

        if (size != squad.size) return false;
        if (id != squad.id) return false;
        if (!name.equals(squad.name)) return false;
        return source.equals(squad.source);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + size;
        result = 31 * result + source.hashCode();
        result = 31 * result + id;
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
