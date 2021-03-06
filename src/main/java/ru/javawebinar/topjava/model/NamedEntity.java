package ru.javawebinar.topjava.model;

/**
 * @author Timur Muratov
 */
public class NamedEntity extends BaseEntity{
    protected String name;

    public NamedEntity(){}

    public NamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
