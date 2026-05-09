package Mission03.domain;

public abstract class Lion {
    protected String name;
    protected String major;
    protected int generation;

    public Lion(String name, String major, int generation) {
        this.name = name;
        this.major = major;
        this.generation = generation;
    }

    public String getInfo() {
        return generation + "기 " + name + " (" + major + ")";
    }

    public abstract void introduce();
}
