package cz.reindl.game.entity;

import cz.reindl.game.GameHub;

public enum Enemies {

    RAT("Rat", 3, 3, 0, 3, true),
    WOLF("Wolf", 4, 3, 0, 4, false),
    KNIGHT("Knight", 5, 3, 0, 5, false),
    OGRE("Ogre", 5, 3, 0, 5, false),
    WIZARD("Wizard", 10, 3, 0, 5, false),
    MIRROR("You", 5, 3, 0, 5, false);

    public String entityName;
    public int entityHp;
    public int entityDmg;
    public int entityDefense;
    public int entityMaxHp;
    public boolean visibility;

    Enemies(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    Enemies(String entityName, int entityHp, int entityDmg, int entityDefense, int entityMaxHp, boolean visibility) {
        this.entityName = entityName;
        this.entityHp = entityHp;
        this.entityDmg = entityDmg;
        this.entityDefense = entityDefense;
        this.entityMaxHp = entityMaxHp;
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
