package it.uniroma2.dicii.foodmood.model;

public enum Role {
    CUSTOMER(1, "custumer"),
    USER(2, "user");

    private final int id;
    private final String dbValue;

    Role(int id, String dbValue) {
        this.id = id;
        this.dbValue = dbValue;
    }

    public int getId() {
        return id;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static Role fromInt(int id) {
        for (Role role : values()) {
            if (role.getId() == id) {
                return role;
            }
        }
        return null;
    }
}