package heroes;

import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import items.*;

import java.util.LinkedList;

public class Mage extends Hero {
    public Mage(String name) {
        super(name);
        initializeSlots();
        initializeHeroAttributes(1, 1, 8);
        validArmorAndWeaponTypes();
    }

    public void validArmorAndWeaponTypes() {
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
    public void equip(Weapon weapon) throws InvalidWeaponException {
        if(getValidWeaponTypes().indexOf(weapon.getWeaponType()) == -1) {
            throw new InvalidWeaponException("Mage cannot equip " + weapon.getWeaponType() + "s");
        }
        else if(weapon.getRequiredLevel() > getLevel()) {
            throw new InvalidWeaponException("Too low level to equip weapon");
        } else addEquipment(EquipmentSlot.Weapon, weapon);
    }

    @Override
    public void equip(Armor armor) throws InvalidArmorException {
        if(getValidArmorTypes().indexOf(armor.getArmorType()) == -1) {
            throw new InvalidArmorException("Mage cannot equip " + armor.getArmorType() + " armor");
        }
        else if(armor.getRequiredLevel() > getLevel()) {
            throw new InvalidArmorException("Too low level to equip armor");
        } else {
            addEquipment(armor.getEquipmentSlot(), armor);
        }
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
