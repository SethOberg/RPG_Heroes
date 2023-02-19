package heroes;

import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import items.*;

import java.util.HashMap;
import java.util.LinkedList;

public abstract class Hero {
    private String name;
    private int level;
    private HeroAttributes levelAttributes = new HeroAttributes();
    private HashMap<EquipmentSlot, Item> equipment = new HashMap<>();

    private LinkedList<WeaponType> validWeaponTypes = new LinkedList<>();
    private LinkedList<ArmorType> validArmorTypes = new LinkedList<>();


    //constructor
    public Hero(String name) {
        this.name = name;
        level = 1;
        initializeEquipmentSlots();
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

    public void addEquipment(EquipmentSlot equipmentSlot, Item item) {
        this.equipment.put(equipmentSlot, item);
    }

    public void setEquipment(HashMap<EquipmentSlot, Item> equipment) {
        this.equipment = equipment;
    }

    public void initializeEquipmentSlots() {
        equipment.put(EquipmentSlot.Legs, null);
        equipment.put(EquipmentSlot.Body, null);
        equipment.put(EquipmentSlot.Head, null);
        equipment.put(EquipmentSlot.Weapon, null);
    }

    //called from subclasses when creating a new object,
    //each hero start with different amounts in each stat
    public void initializeHeroAttributes(int strength, int dexterity, int intlligence) {
        this.levelAttributes.setStrength(strength);
        this.levelAttributes.setDexterity(dexterity);
        this.levelAttributes.setIntelligence(intlligence);
    }

    //The level up function is implemented in subclasses
    //since each hero gains a different amount of stats depending
    //on their subclass, e.g. mages gain more intelligence
    //than warriors when levelling up
    public abstract void levelUp();

    //A weapon is equipped if it's allowed for the subclass
    //and if the hero has the required level for the weapon
    public void equip(Weapon weapon) throws InvalidWeaponException {
        if(getValidWeaponTypes().indexOf(weapon.getWeaponType()) == -1) {
            throw new InvalidWeaponException(getClass().getSimpleName() + "s cannot equip " + weapon.getWeaponType() + "s");
        }
        else if(weapon.getRequiredLevel() > getLevel()) {
            throw new InvalidWeaponException("Too low level to equip weapon");
        } else addEquipment(EquipmentSlot.Weapon, weapon);
    }

    //Armor is equipped if it's allowed for the subclass
    //and if the hero has the required level for the armor
    public void equip(Armor armor) throws InvalidArmorException {
        if(getValidArmorTypes().indexOf(armor.getArmorType()) == -1) {
            throw new InvalidArmorException(getClass().getSimpleName() + "s cannot equip " + armor.getArmorType() + " armor");
        }
        else if(armor.getRequiredLevel() > getLevel()) {
            throw new InvalidArmorException("Too low level to equip armor");
        } else {
            addEquipment(armor.getEquipmentSlot(), armor);
        }
    }

    //The damage is implemented in subclasses that inherit from
    //the Hero base class and is calculated with the damaging attribute
    //of a hero, for e.g. the damaging attribute for mages is intelligence
    public abstract int damage();

    //The total attributes are the stats of a hero combined
    //with the stats from all equipped armor
    public HeroAttributes totalAttributes() {
        HeroAttributes totalAttributes = new HeroAttributes();
        totalAttributes.setStrength(getLevelAttributes().getStrength());
        totalAttributes.setDexterity(getLevelAttributes().getDexterity());
        totalAttributes.setIntelligence(getLevelAttributes().getIntelligence());

        getEquipment().values().stream()
                .filter((item) -> item != null)
                .filter((item) -> item.getClass().isAssignableFrom(Armor.class))
                .map((armor) -> (Armor)armor)
                .forEach((armor -> {
                    totalAttributes.setStrength(totalAttributes.getStrength() + armor.getArmorAttributes().getStrength());
                    totalAttributes.setDexterity(totalAttributes.getDexterity() + armor.getArmorAttributes().getDexterity());
                    totalAttributes.setIntelligence(totalAttributes.getIntelligence() + armor.getArmorAttributes().getIntelligence());
                }));

        return totalAttributes;
    }

    public String displayHeroDetails() {
        HeroAttributes totalAttributes = totalAttributes();
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Hero\n");
        stringBuilder.append("Name: " + getName() + "\n");
        stringBuilder.append("Type: " + getClass().getSimpleName() + "\n");
        stringBuilder.append("Level: " + getLevel() + "\n");
        stringBuilder.append("Attributes: \n");
        stringBuilder.append(" - Total strength: " + totalAttributes.getStrength() + "\n");
        stringBuilder.append(" - Total dexterity: " + totalAttributes.getDexterity() + "\n");
        stringBuilder.append(" - Total intelligence: " + totalAttributes.getIntelligence() + "\n");
        stringBuilder.append("Damage: " + damage());

        return stringBuilder.toString();
    }

}
