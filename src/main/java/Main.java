import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import heroes.Hero;
import heroes.HeroAttributes;
import heroes.Mage;
import items.*;

public class Main {
    public static void main(String[] args) throws InvalidArmorException, InvalidWeaponException {
        HeroAttributes armorAttributes = new HeroAttributes(1 ,1, 3);
        Item armor = new Armor("thing", 1,
                EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);
        Item weapon = new Weapon("weapon", 1, EquipmentSlot.Weapon, 3, WeaponType.Staff);

        Hero mage = new Mage("testHero");
        System.out.println(mage.displayHeroDetails());
        System.out.println();

        mage.equip((Armor) armor);
        mage.equip((Weapon) weapon);
        mage.levelUp();
        System.out.println(mage.displayHeroDetails());
    }
}