package Mission04.domain;

public abstract class Lion {
    protected String name;
    protected String major;
    protected int generation;
    protected String part;

    public Lion(String name, String major, int generation, String part) {
        this.name = name;
        this.major = major;
        this.generation = generation;
        this.part = part;
    }

    public String getName() { return name; }
    public String getPart() { return part; }

    public String getInfo() {
        return generation + "기 " + name + " (" + major + ") [" + part + "]";
    }

    public abstract void introduce();
}
