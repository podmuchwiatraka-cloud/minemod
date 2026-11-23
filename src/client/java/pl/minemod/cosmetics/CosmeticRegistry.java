package pl.minemod.cosmetics;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/** Lista dostepnych kosmetykow + wybor gracza (na razie tylko lokalnie, w pamieci). */
public final class CosmeticRegistry {
    private static final List<Cosmetic> ALL = new ArrayList<>();
    private static final Map<CosmeticType, Cosmetic> EQUIPPED = new EnumMap<>(CosmeticType.class);

    private CosmeticRegistry() {}

    public static void init() {
        // Przyklady - tutaj dopisujemy kolejne kosmetyki.
        register(new Cosmetic("cape_classic", "Klasyczna pelerynka", CosmeticType.CAPE));
        register(new Cosmetic("cape_minimal", "Minimal (szara)", CosmeticType.CAPE));
        register(new Cosmetic("backpack_basic", "Plecak podstawowy", CosmeticType.BACKPACK));
        register(new Cosmetic("hat_cap", "Czapka z daszkiem", CosmeticType.HAT));
        register(new Cosmetic("wings_white", "Biale skrzydla", CosmeticType.WINGS));
        register(new Cosmetic("pet_cube", "Kostka-zwierzak", CosmeticType.PET));
    }

    public static void register(Cosmetic c) { ALL.add(c); }

    public static List<Cosmetic> byType(CosmeticType type) {
        return ALL.stream().filter(c -> c.type() == type).toList();
    }

    public static Cosmetic equipped(CosmeticType type) { return EQUIPPED.get(type); }

    /** Klikniecie w ten sam kosmetyk zdejmuje go. */
    public static void toggle(Cosmetic c) {
        if (EQUIPPED.get(c.type()) == c) EQUIPPED.remove(c.type());
        else EQUIPPED.put(c.type(), c);
    }
}
