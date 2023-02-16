import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import heroes.HeroAttributes;
import heroes.Mage;
import items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestMage {

    //General tests
    @Test
    public void testLevel() {
        var mage = new Mage("someName");
        assertEquals(1, mage.getLevel());
    }

    @Test
    public void testMageBaseStats() {
        var mage = new Mage("someName");
        var mageBaseStats = mage.getLevelAttributes();

        var mageBaseStatsOk = mageBaseStats.getStrength() == 1
                && mageBaseStats.getDexterity() == 1
                && mageBaseStats.getIntelligence() == 8 ;

        assertEquals(true, mageBaseStatsOk);
    }

    @Test
    public void testMageStatsLevelUp() {
        var mage = new Mage("someName");
        mage.levelUp();
        var mageBaseStats = mage.getLevelAttributes();

        var mageBaseStatsOk = mageBaseStats.getStrength() == 2
                && mageBaseStats.getDexterity() == 2
                && mageBaseStats.getIntelligence() == 13 ;

        assertEquals(true, mageBaseStatsOk);
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
    public void testMageCannotEquipWeaponAsArmor() {
        var mage = new Mage("someName");
        var weapon = new Weapon("someWand", 1, EquipmentSlot.Weapon, 3, WeaponType.Wand);

        try {
            mage.equip(weapon);
        } catch (InvalidWeaponException e) {
        }
        assertTrue(mage.getEquipment().get(EquipmentSlot.Body) == null
                && mage.getEquipment().get(EquipmentSlot.Legs) == null
                && mage.getEquipment().get(EquipmentSlot.Head) == null);
    }

    @Test
    public void testMageCannotEquipArmorAsWeapon() {
        var mage = new Mage("someName");
        var armorAttributes = new HeroAttributes(1, 1, 3);
        var armor = new Armor("clothArmor", 1, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);


        try {
            mage.equip(armor);
        } catch (InvalidArmorException e) {
        }
        assertTrue(mage.getEquipment().get(EquipmentSlot.Weapon) == null);
    }


    @Test
    public void testTooLowLevelToEquipWeapon() {
        var mage = new Mage("someName");
        var weapon = new Weapon("someStaff", 2, EquipmentSlot.Weapon, 3, WeaponType.Staff);

        assertThrows(InvalidWeaponException.class, () -> mage.equip(weapon));
    }

    @Test
    public void testMageCanEquipWeapon() {
        var mage = new Mage("someName");
        var weapon = new Weapon("someWand", 1, EquipmentSlot.Weapon, 3, WeaponType.Wand);
        assertDoesNotThrow(() -> mage.equip(weapon));
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

        assertThrows(InvalidArmorException.class, () -> mage.equip(armor));
    }

    @Test
    public void testTotalAttributes() {
        var mage = new Mage("someName");
        var armorAttributes = new HeroAttributes(2, 2, 2);
        var armorLegs = new Armor("clothArmorLegs", 1, EquipmentSlot.Legs, ArmorType.Cloth, armorAttributes);
        var armorBody = new Armor("clothArmorBody", 1, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);
        var armorHead = new Armor("clothArmorHead", 1, EquipmentSlot.Head, ArmorType.Cloth, armorAttributes);

        try {
            mage.equip(armorLegs);
            mage.equip(armorBody);
            mage.equip(armorHead);
        } catch(InvalidArmorException e) {
        }

        HeroAttributes total = mage.totalAttributes();

        assertTrue(total.getStrength() == 7
                && total.getDexterity() == 7
                && total.getIntelligence() == 14);
    }

    @Test
    public void testTotalAttributesTwoArmorItemsEquipped() {
        var mage = new Mage("someName");
        var armorAttributes = new HeroAttributes(2, 2, 2);
        var armorLegs = new Armor("clothArmorLegs", 1, EquipmentSlot.Legs, ArmorType.Cloth, armorAttributes);
        var armorBody = new Armor("clothArmorBody", 1, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);

        try {
            mage.equip(armorLegs);
            mage.equip(armorBody);
        } catch(InvalidArmorException e) {
        }

        HeroAttributes total = mage.totalAttributes();

        assertTrue(total.getStrength() == 5
                && total.getDexterity() == 5
                && total.getIntelligence() == 12);
    }

    @Test
    public void testTooLowLevelToEquipArmor() {
        var mage = new Mage("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("clothArmor", 2, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);

        assertThrows(InvalidArmorException.class, () -> mage.equip(armor));
    }

    @Test
    public void testMageCanEquipArmor() {
        var mage = new Mage("someName");
        var armorAttributes = new HeroAttributes(1, 1, 3);
        var armor = new Armor("clothArmor", 1, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);
        assertDoesNotThrow(() -> mage.equip(armor));
    }
}
