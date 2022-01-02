package cz.reindl.game.entity;

import cz.reindl.game.GameHub;

public enum Enemies {

    KNIGHT("Knight", 5, 3, 0, false),
    RAT("Rat", 5, 3, 0, true),
    WOLF("Wolf", 5, 3, 0, false),
    MIRROR("You", 5, 3, 0, false),
    TROLL("Troll", 5, 3, 0, false);

    public String entityName;
    public int entityHp;
    public int entityDmg;
    public int entityDefense;
    public boolean visibility;
    public GameHub hub;

    Enemies(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    Enemies(String entityName, int entityHp, int entityDmg, int entityDefense, boolean visibility) {
        this.entityName = entityName;
        this.entityHp = entityHp;
        this.entityDmg = entityDmg;
        this.entityDefense = entityDefense;
        this.visibility = visibility;
    }

    public void setEntityHp(int entityHp) {
        this.entityHp = entityHp;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public int getEntityHp() {
        return entityHp;
    }

    public int getEntityDmg() {
        return entityDmg;
    }

    public void setEntityDmg(int entityDmg) {
        this.entityDmg = entityDmg;
    }

    public int getEntityDefense() {
        return entityDefense;
    }

    public void setEntityDefense(int entityDefense) {
        this.entityDefense = entityDefense;
    }
}
