import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import heroes.Hero;
import heroes.HeroAttributes;
import heroes.Mage;
import items.*;

public class Main {
    public static void main(String[] args) throws InvalidArmorException, InvalidWeaponException {
        HeroAttributes armorAttributes = new HeroAttributes(2 ,2, 2);
        Item clothArmorLegs = new Armor("clothArmorBody", 1,
                EquipmentSlot.Legs, ArmorType.Cloth, armorAttributes);
        Item clothArmorBody = new Armor("clothArmorBody", 1,
                EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);
        Item clothArmorHead = new Armor("clothArmorBody", 1,
                EquipmentSlot.Head, ArmorType.Cloth, armorAttributes);
        Item weapon = new Weapon("weapon", 1, EquipmentSlot.Weapon, 3, WeaponType.Staff);

        Hero mage = new Mage("testHero");
        System.out.println(mage.displayHeroDetails());
        System.out.println();

        mage.equip((Armor) clothArmorLegs);
        mage.equip((Armor) clothArmorBody);
        mage.equip((Armor) clothArmorHead);

        var total = mage.totalAttributes();
        System.out.println(total.getStrength());
        System.out.println(total.getDexterity());
        System.out.println(total.getIntelligence());

        //mage.equip((Weapon) weapon);
        //mage.levelUp();
        System.out.println(mage.displayHeroDetails());

    }
}