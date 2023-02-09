package heroes;

import items.ArmorType;
import items.EquipmentSlot;
import items.Weapon;
import items.WeaponType;

import java.util.LinkedList;

public class Rogue extends Hero {

    public Rogue(String name) {
        super(name);
        initializeSlots();
        initializeHeroAttributes(2, 6, 1);
        addValidArmorAndWeaponTypes();
    }

    public void addValidArmorAndWeaponTypes() {
        //Weapons
        LinkedList<WeaponType> validWeapons = new LinkedList<>();
        validWeapons.add(WeaponType.Dagger);
        validWeapons.add(WeaponType.Sword);
        setValidWeaponTypes(validWeapons);
        //Armor
        LinkedList<ArmorType> validArmorTypes = new LinkedList<>();
        validArmorTypes.add(ArmorType.Leather);
        validArmorTypes.add(ArmorType.Mail);
        setValidArmorTypes(validArmorTypes);
    }

    @Override
    public void levelUp() {
        setLevel(getLevel() + 1);
        HeroAttributes attributes = getLevelAttributes();
        attributes.setIntelligence(attributes.getIntelligence() + 1);
        attributes.setDexterity(attributes.getDexterity() + 4);
        attributes.setStrength(attributes.getStrength() + 1);
        setLevelAttributes(attributes);
    }

    @Override
    public int damage() {
        Weapon weapon = (Weapon) getEquipment().get(EquipmentSlot.Weapon);
        HeroAttributes totalAttributes = totalAttributes();

        if(weapon != null) {
            return weapon.getWeaponDamage() * (1 + totalAttributes.getDexterity() / 100);
        } else {
            return 1;
        }
    }
}
