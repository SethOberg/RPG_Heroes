package heroes;

import items.ArmorType;
import items.EquipmentSlot;
import items.Weapon;
import items.WeaponType;

import java.util.LinkedList;

public class Warrior extends Hero {

    public Warrior(String name) {
        super(name);
        initializeEquipmentSlots();
        initializeHeroAttributes(5, 2, 1);
        addValidArmorAndWeaponTypes();
    }

    public void addValidArmorAndWeaponTypes() {
        //Weapons
        LinkedList<WeaponType> validWeapons = new LinkedList<>();
        validWeapons.add(WeaponType.Axe);
        validWeapons.add(WeaponType.Hammer);
        validWeapons.add(WeaponType.Sword);
        setValidWeaponTypes(validWeapons);
        //Armor
        LinkedList<ArmorType> validArmorTypes = new LinkedList<>();
        validArmorTypes.add(ArmorType.Mail);
        validArmorTypes.add(ArmorType.Plate);
        setValidArmorTypes(validArmorTypes);
    }

    @Override
    public void levelUp() {
        setLevel(getLevel() + 1);
        HeroAttributes attributes = getLevelAttributes();
        attributes.setIntelligence(attributes.getIntelligence() + 1);
        attributes.setDexterity(attributes.getDexterity() + 2);
        attributes.setStrength(attributes.getStrength() + 3);
        setLevelAttributes(attributes);
    }

    @Override
    public int damage() {
        Weapon weapon = (Weapon) getEquipment().get(EquipmentSlot.Weapon);
        HeroAttributes totalAttributes = totalAttributes();

        if(weapon != null) {
            return weapon.getWeaponDamage() * (1 + totalAttributes.getStrength() / 100);
        } else {
            return 1;
        }
    }
}
