package heroes;

import items.ArmorType;
import items.EquipmentSlot;
import items.Weapon;
import items.WeaponType;

import java.util.LinkedList;

public class Mage extends Hero {
    public Mage(String name) {
        super(name);
        initializeEquipmentSlots();
        initializeHeroAttributes(1, 1, 8);
        addValidArmorAndWeaponTypes();
    }

    public void addValidArmorAndWeaponTypes() {
        //Weapons
        LinkedList<WeaponType> validWeapons = new LinkedList<>();
        validWeapons.add(WeaponType.Staff);
        validWeapons.add(WeaponType.Wand);
        setValidWeaponTypes(validWeapons);
        //Armor
        LinkedList<ArmorType> validArmorTypes = new LinkedList<>();
        validArmorTypes.add(ArmorType.Cloth);
        setValidArmorTypes(validArmorTypes);
    }

    @Override
    public void levelUp() {
        setLevel(getLevel() + 1);
        HeroAttributes attributes = getLevelAttributes();
        attributes.setIntelligence(attributes.getIntelligence() + 5);
        attributes.setDexterity(attributes.getDexterity() + 1);
        attributes.setStrength(attributes.getStrength() + 1);
        setLevelAttributes(attributes);
    }

    @Override
    public int damage() {
        Weapon weapon = (Weapon) getEquipment().get(EquipmentSlot.Weapon);
        HeroAttributes totalAttributes = totalAttributes();

        if(weapon != null) {
            return weapon.getWeaponDamage() * (1 + totalAttributes.getIntelligence() / 100);
        } else {
            return 1;
        }
    }

}
