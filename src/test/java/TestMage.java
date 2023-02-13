import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import heroes.HeroAttributes;
import heroes.Mage;
import items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class TestMage {

    //General tests
    @Test
    public void testLevel() {
        var mage = new Mage("someName");
        assertEquals(1, mage.getLevel());
    }

    @Test
    public void testLevelUp() {
        var mage = new Mage("someName");
        mage.levelUp();
        assertEquals(2, mage.getLevel());
    }

    @Test
    public void testNoWeaponEquipped() {
        var mage = new Mage("someName");
        assertEquals(1, mage.damage());
    }

    @Test
    public void testMageDisplayDetails() {
        var mage = new Mage("someName");
        assertNotEquals(null, mage.displayHeroDetails());
    }

    //Item specific tests

    //Weapons
    @Test
    public void testMageCannotEquipWeapon() {
        var mage = new Mage("someName");
        var weapon = new Weapon("someAxe", 1, EquipmentSlot.Weapon, 3, WeaponType.Axe);

        try {
            mage.equip(weapon);
        } catch (InvalidWeaponException e) {
        }
        assertEquals(null, mage.getEquipment().get(EquipmentSlot.Weapon));
    }

    @Test
    public void testTooLowLevelToEquipWeapon() {
        var mage = new Mage("someName");
        var weapon = new Weapon("someStaff", 2, EquipmentSlot.Weapon, 3, WeaponType.Staff);

        try {
            mage.equip(weapon);
        } catch (InvalidWeaponException e) {
        }
        assertEquals(null, mage.getEquipment().get(EquipmentSlot.Weapon));
    }

    @Test
    public void testMageCanEquipWeapon() throws InvalidWeaponException {
        var mage = new Mage("someName");
        var weapon = new Weapon("someWand", 1, EquipmentSlot.Weapon, 3, WeaponType.Wand);

        mage.equip(weapon);
        assertNotEquals(null, mage.getEquipment().get(EquipmentSlot.Weapon));
    }

    @Test
    public void testWeaponDealsDamage() throws InvalidWeaponException {
        var mage = new Mage("someName");
        var weapon = new Weapon("someWand", 1, EquipmentSlot.Weapon, 10, WeaponType.Wand);

        mage.equip(weapon);
        assertNotEquals(1, mage.damage());
    }

    //Armor
    @Test
    public void testMageCannotEquipArmor() {
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
    public void testTooLowLevelToEquipArmor() {
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
    public void testMageCanEquipArmor() throws InvalidArmorException {
        var mage = new Mage("someName");
        var armorAttributes = new HeroAttributes(1, 1, 3);
        var armor = new Armor("clothArmor", 1, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);

        mage.equip(armor);
        assertNotEquals(null, mage.getEquipment().get(EquipmentSlot.Body));
    }
}
