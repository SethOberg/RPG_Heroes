package heroes;

import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import items.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.Collectors;

public abstract class Hero {
    private String name;
    private int level;
    private HeroAttributes levelAttributes = new HeroAttributes();
    private HashMap<EquipmentSlot, Item> equipment = new HashMap<>();

    private LinkedList<WeaponType> validWeaponTypes = new LinkedList<>();
    private LinkedList<ArmorType> validArmorTypes = new LinkedList<>();


    //constructor

    public Hero() {
        level = 1;
        initializeSlots();
    }

    public Hero(String name) {
        this.name = name;
        level = 1;
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

    public void addEquipment(EquipmentSlot equipmentSlot, Item item) {
        this.equipment.put(equipmentSlot, item);
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

    public abstract void equip(Weapon weapon) throws InvalidWeaponException;

    public abstract void equip(Armor armor) throws InvalidArmorException;

    public abstract int damage();

    public HeroAttributes totalAttributes() {
        HeroAttributes totalAttributes = new HeroAttributes();
        totalAttributes.setStrength(getLevelAttributes().getStrength());
        totalAttributes.setDexterity(getLevelAttributes().getDexterity());
        totalAttributes.setIntelligence(getLevelAttributes().getIntelligence());

        //Use map to get all armor types and add
        ArrayList<Armor> heroArmor = (ArrayList<Armor>) getEquipment().entrySet().stream()
                .filter((item) -> item.getClass().isAssignableFrom(Armor.class))
                .map((armor) -> (Armor)armor).collect(Collectors.toList());

        for (Armor armor: heroArmor) {
            totalAttributes.setIntelligence(totalAttributes().getIntelligence() + armor.getArmorAttributes().getIntelligence());
            totalAttributes.setDexterity(totalAttributes().getDexterity() + armor.getArmorAttributes().getDexterity());
            totalAttributes.setStrength(totalAttributes().getStrength() + armor.getArmorAttributes().getStrength());
        }

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
