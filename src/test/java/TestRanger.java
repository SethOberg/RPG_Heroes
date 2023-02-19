import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import heroes.HeroAttributes;
import heroes.Ranger;
import items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRanger {

    //add failing test to see if CI works
    @Test
    public void testLevel() {
        var ranger = new Ranger("someName");
        assertEquals(1, ranger.getLevel());
    }

    @Test
    public void testRangerBaseStats() {
        var ranger = new Ranger("someName");
        var rangerBaseStats = ranger.getLevelAttributes();

        var rangerBaseStatsOk = rangerBaseStats.getStrength() == 1
                && rangerBaseStats.getDexterity() == 7
                && rangerBaseStats.getIntelligence() == 1 ;

        assertEquals(true, rangerBaseStatsOk);
    }

    @Test
    public void testRangerStatsLevelUp() {
        var ranger = new Ranger("someName");
        ranger.levelUp();
        var mageBaseStats = ranger.getLevelAttributes();

        var rangerBaseStatsOk = mageBaseStats.getStrength() == 2
                && mageBaseStats.getDexterity() == 12
                && mageBaseStats.getIntelligence() == 2 ;

        assertEquals(true, rangerBaseStatsOk);
    }

    @Test
    public void testLevelUp() {
        var ranger = new Ranger("someName");
        ranger.levelUp();
        assertEquals(2, ranger.getLevel());
    }

    @Test
    public void testRangerCanEquipWeapon() {
        var ranger = new Ranger("someName");
        var weapon = new Weapon("someBow", 1, EquipmentSlot.Weapon, 3, WeaponType.Bow);

       assertDoesNotThrow(() -> ranger.equip(weapon));
    }

    @Test
    public void testWeaponDealsDamage() throws InvalidWeaponException {
        var ranger = new Ranger("someName");
        var weapon = new Weapon("someBow", 1, EquipmentSlot.Weapon, 10, WeaponType.Bow);

        ranger.equip(weapon);
        assertNotEquals(1, ranger.damage());
    }

    //Armor
    @Test
    public void testRangerCannotEquipArmor() {
        var ranger = new Ranger("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("cloth", 1, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);

        assertThrows(InvalidArmorException.class, () -> ranger.equip(armor));
    }

    @Test
    public void testTooLowLevelToEquipArmor() {
        var ranger = new Ranger("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("mailArmor", 2, EquipmentSlot.Body, ArmorType.Mail, armorAttributes);

        assertThrows(InvalidArmorException.class, () -> ranger.equip(armor));
    }

    @Test
    public void testRangerCanEquipArmor() {
        var ranger = new Ranger("someName");
        var armorAttributes = new HeroAttributes(1, 1, 3);
        var armor = new Armor("mailArmor", 1, EquipmentSlot.Body, ArmorType.Mail, armorAttributes);

        assertDoesNotThrow(() -> ranger.equip(armor));
    }
}
