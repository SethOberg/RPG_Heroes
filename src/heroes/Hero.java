package heroes;

import items.*;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Hero {
    private String name;
    private int level;
    private HeroAttributes levelAttributes;
    private HashMap<EquipmentSlot, Item> equipment = new HashMap<>();

    private LinkedList<WeaponType> validWeaponTypes;
    private LinkedList<ArmorType> validArmorTypes;


    //constructor

    public Hero() {
        initializeSlots();
    }

    public Hero(String name) {
        this.name = name;
        initializeSlots();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public LinkedList<WeaponType> getValidWeaponTypes() {
        return validWeaponTypes;
    }

    public void setValidWeaponTypes(LinkedList<WeaponType> validWeaponTypes) {
        this.validWeaponTypes = validWeaponTypes;
    }

    public void addValidWeapon(WeaponType weaponType) {
        this.validWeaponTypes.add(weaponType);
    }

    public LinkedList<ArmorType> getValidArmorTypes() {
        return validArmorTypes;
    }

    public void setValidArmorTypes(LinkedList<ArmorType> validArmorTypes) {
        this.validArmorTypes = validArmorTypes;
    }

    public void addValidArmorType(ArmorType armorType) {
        this.validArmorTypes.add(armorType);
    }

    public HeroAttributes getLevelAttributes() {
        return levelAttributes;
    }

    public void setLevelAttributes(HeroAttributes levelAttributes) {
        this.levelAttributes = levelAttributes;
    }

    public HashMap<EquipmentSlot, Item> getEquipment() {
        return equipment;
    }

    public void setEquipment(HashMap<EquipmentSlot, Item> equipment) {
        this.equipment = equipment;
    }

    public void initializeSlots() {
        equipment.put(EquipmentSlot.Legs, null);
        equipment.put(EquipmentSlot.Body, null);
        equipment.put(EquipmentSlot.Head, null);
        equipment.put(EquipmentSlot.Weapon, null);
    }

    public void initializeHeroAttributes(int strength, int dexterity, int intlligence) {
        this.levelAttributes.setStrength(strength);
        this.levelAttributes.setDexterity(dexterity);
        this.levelAttributes.setIntelligence(intlligence);
    }

    public abstract void levelUp();

    public abstract void equip(Weapon weapon);

    public abstract void equip(Armor armor);

    public abstract void damage();

    public abstract int totalAttributes();

    public abstract String displayHeroDetails();

}
