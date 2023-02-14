import exceptions.InvalidArmorException;
import exceptions.InvalidWeaponException;
import heroes.HeroAttributes;
import heroes.Ranger;
import heroes.Rogue;
import items.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestRogue {

    @Test
    public void testLevel() {
        var rogue = new Rogue("someName");
        assertEquals(1, rogue.getLevel());
    }

    @Test
    public void testRogueBaseStats() {
        var rogue = new Rogue("someName");
        var rogueBaseStats = rogue.getLevelAttributes();

        var rogueBaseStatsOk = rogueBaseStats.getStrength() == 2
                && rogueBaseStats.getDexterity() == 6
                && rogueBaseStats.getIntelligence() == 1 ;

        assertEquals(true, rogueBaseStatsOk);
    }

    @Test
    public void testRogueStatsLevelUp() {
        var rogue = new Rogue("someName");
        rogue.levelUp();
        var rogueBaseStats = rogue.getLevelAttributes();

        var rogueBaseStatsOk = rogueBaseStats.getStrength() == 3
                && rogueBaseStats.getDexterity() == 10
                && rogueBaseStats.getIntelligence() == 2 ;

        assertEquals(true, rogueBaseStatsOk);
    }

    @Test
    public void testLevelUp() {
        var rogue = new Rogue("someName");
        rogue.levelUp();
        assertEquals(2, rogue.getLevel());
    }
    @Test
    public void testRogueCanEquipWeapon() {
        var rogue = new Rogue("someName");
        var weapon = new Weapon("someSword", 1, EquipmentSlot.Weapon, 3, WeaponType.Sword);

        assertDoesNotThrow(() -> rogue.equip(weapon));
    }

    @Test
    public void testWeaponDealsDamage() throws InvalidWeaponException {
        var rogue = new Rogue("someName");
        var weapon = new Weapon("someSword", 1, EquipmentSlot.Weapon, 10, WeaponType.Sword);

        rogue.equip(weapon);
        assertNotEquals(1, rogue.damage());
    }

    //Armor
    @Test
    public void testRogueCannotEquipArmor() {
        var rogue = new Rogue("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("clothArmor", 1, EquipmentSlot.Body, ArmorType.Cloth, armorAttributes);

        assertThrows(InvalidArmorException.class, () -> rogue.equip(armor));
    }

    @Test
    public void testTooLowLevelToEquipArmor() {
        var rogue = new Rogue("someName");
        var armorAttributes = new HeroAttributes(2, 2, 1);
        var armor = new Armor("mailArmor", 2, EquipmentSlot.Body, ArmorType.Mail, armorAttributes);

        assertThrows(InvalidArmorException.class, () -> rogue.equip(armor));
    }

    @Test
    public void testRogueCanEquipArmor() {
        var rogue = new Rogue("someName");
        var armorAttributes = new HeroAttributes(1, 1, 3);
        var armor = new Armor("mailArmor", 1, EquipmentSlot.Body, ArmorType.Mail, armorAttributes);

        assertDoesNotThrow(() -> rogue.equip(armor));
    }
}
