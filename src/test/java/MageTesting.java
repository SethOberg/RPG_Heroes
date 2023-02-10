import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import heroes.HeroAttributes;
import heroes.Mage;
import items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class MageTesting {

    //General tests
    @Test
    void testLevel() {
        var mage = new Mage("someName");
        assertEquals(1, mage.getLevel());
    }

    @Test
    void testLevelUp() {
        var mage = new Mage("someName");
        mage.levelUp();
        assertEquals(2, mage.getLevel());
    }

    @Test
    void noWeaponEquipped() {
        var mage = new Mage("someName");
        assertEquals(1, mage.damage());
    }

    @Test
    void mageDisplayDetails() {
        var mage = new Mage("someName");
        assertNotEquals(null, mage.displayHeroDetails());
    }

    //Item specific tests

    //Weapons
    @Test
    void mageCannotEquipWeapon() {
        var mage = new Mage("someName");
        var weapon = new Weapon("someAxe", 1, EquipmentSlot.Weapon, 3, WeaponType.Axe);

        try {
            mage.equip(weapon);
        } catch (InvalidWeaponException e) {
        }
        assertEquals(null, mage.getEquipment().get(EquipmentSlot.Weapon));
    }

    @Test
    void tooLowLevelToEquipWeapon() {
        var mage = new Mage("someName");
        var weapon = new Weapon("someStaff", 2, EquipmentSlot.Weapon, 3, WeaponType.Staff);

        try {
            mage.equip(weapon);
        } catch (InvalidWeaponException e) {
        }
        assertEquals(null, mage.getEquipment().get(EquipmentSlot.Weapon));
    }

    @Test
    void mageCanEquipWeapon() throws InvalidWeaponException {
        var mage = new Mage("someName");
        var weapon = new Weapon("someWand", 1, EquipmentSlot.Weapon, 3, WeaponType.Wand);

        mage.equip(weapon);
        assertNotEquals(null, mage.getEquipment().get(EquipmentSlot.Weapon));
    }

    @Test
    void weaponDealsDamage() throws InvalidWeaponException {
        var mage = new Mage("someName");
        var weapon = new Weapon("someWand", 1, EquipmentSlot.Weapon, 10, WeaponType.Wand);

        mage.equip(weapon);
        assertNotEquals(1, mage.damage());
    }

    //Armor
    @Test
    void mageCannotEquipArmor() {
        var mage = new Mage("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("mailArmor", 1, EquipmentSlot.Body, ArmorType.Mail, armorAttributes);

        try {
            mage.equip(armor);
        } catch (InvalidArmorException e) {
        }

        assertEquals(null, mage.getEquipment().get(EquipmentSlot.Body));
    }

    @Test
    void tooLowLevelToEquipArmor() {
        var mage = new Mage("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("clothArmor", 2, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);

        try {
            mage.equip(armor);
        } catch (InvalidArmorException e) {
        }

        assertEquals(null, mage.getEquipment().get(EquipmentSlot.Body));
    }

    @Test
    void mageCanEquipArmor() throws InvalidArmorException {
        var mage = new Mage("someName");
        var armorAttributes = new HeroAttributes(1, 1, 3);
        var armor = new Armor("clothArmor", 1, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);

        mage.equip(armor);
        assertNotEquals(null, mage.getEquipment().get(EquipmentSlot.Body));
    }
}
