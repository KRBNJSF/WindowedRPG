package cz.reindl.game.entity;

public enum Enemies {

    RAT("Rat", 5, 2, 0, 5, 1, true),
    WOLF("Wolf", 8, 4, 0, 8, 1, false),
    KNIGHT("Knight", 10, 5, 0, 10, 1, false),
    OGRE("Ogre", 15, 6, 0, 15, 1, false),
    WIZARD("Wizard", 10, 9, 0, 10, 1, false),
    WITCH("WITCH", 15, 25, 0, 15, 1, false),
    ESSENCEWIZARD("Essence Wizard", 25, 10, 0, 15, 1, false),
    OCCULTISTS("Occultists", 40, 10, 0, 40, 1, false),
    GHASTLINESS("Ghastliness", 70, 15, 0, 40, 1, false),
    TWINS("Twins", 40, 5, 0, 40, 1, false),
    SPIDER("Spider", 20, 15, 0, 40, 1, false),
    TOADWARRIOR("Toad Warrior", 40, 5, 0, 40, 1, false),
    WARRIOROGRE("Warrior Ogre", 40, 8, 0, 40, 1, false),
    MIRROR("You", 10, 10, 0, 5, 1, false);

    public String entityName;
    public int entityHp;
    public int entityMaxDmg;
    public int entityMinDmg;
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

    Enemies(String entityName, int entityHp, int entityMaxDmg, int entityDefense, int entityMaxHp, int entityMinDmg, boolean visibility) {
        this.entityName = entityName;
        this.entityHp = entityHp;
        this.entityMaxDmg = entityMaxDmg;
        this.entityDefense = entityDefense;
        this.entityMaxHp = entityMaxHp;
        this.entityMinDmg = entityMinDmg;
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

    public int getEntityMaxDmg() {
        return entityMaxDmg;
    }

    public void setEntityMaxDmg(int entityMaxDmg) {
        this.entityMaxDmg = entityMaxDmg;
    }

    public int getEntityDefense() {
        return entityDefense;
    }

    public void setEntityDefense(int entityDefense) {
        this.entityDefense = entityDefense;
    }
}
