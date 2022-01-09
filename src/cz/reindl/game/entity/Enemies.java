package cz.reindl.game.entity;

import cz.reindl.game.GameHub;

public enum Enemies {

    RAT("Rat", 5, 2, 0, 5, true),
    WOLF("Wolf", 8, 4, 0, 8, false),
    KNIGHT("Knight", 10, 5, 0, 10, false),
    OGRE("Ogre", 15, 6, 0, 15, false),
    WIZARD("Wizard", 10, 9, 0, 10, false),
    WITCH("WITCH", 15, 10, 0, 15, false),
    ESSENCEWIZARD("Essence Wizard", 15, 10, 0, 15, false),
    OCCULTISTS("Occultists", 40, 5, 0, 40, false),
    GHASTLINESS("Ghastliness", 40, 5, 0, 40, false),
    TWINS("Twins", 40, 5, 0, 40, false),
    SPIDER("Spider", 40, 5, 0, 40, false),
    TOADWARRIOR("Toad Warrior", 40, 5, 0, 40, false),
    WARRIOROGRE("Warrior Ogre", 40, 5, 0, 40, false),
    MIRROR("You", 10, 10, 0, 5, false);

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
