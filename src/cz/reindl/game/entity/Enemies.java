package cz.reindl.game.entity;

import cz.reindl.game.GameHub;

public enum Enemies {

    KNIGHT("Knight", 5, 3, 0),
    BAT("Bat", 5, 3, 0),
    WOLF("Wolf", 5, 3, 0);

    public String entityName;
    public int entityHp;
    public int entityDmg;
    public int entityDefense;

    Enemies(String entityName, int entityHp, int entityDmg, int entityDefense) {
        this.entityName = entityName;
        this.entityHp = entityHp;
        this.entityDmg = entityDmg;
        this.entityDefense = entityDefense;
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
