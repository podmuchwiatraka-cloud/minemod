package pl.minemod.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import pl.minemod.cosmetics.Cosmetic;
import pl.minemod.cosmetics.CosmeticRegistry;
import pl.minemod.cosmetics.CosmeticType;

/** Menu MineMod: szaro-bialy styl, zakladki kategorii + lista kosmetykow. */
public class MineModScreen extends Screen {
    private static final int BG_DIM   = 0xB0101010;
    private static final int PANEL    = 0xF01E1E1E;
    private static final int SIDEBAR  = 0xF0151515;
    private static final int LINE     = 0xFF3A3A3A;
    private static final int WHITE    = 0xFFFFFFFF;
    private static final int GRAY     = 0xFFAAAAAA;

    private static final int PANEL_W = 360;
    private static final int PANEL_H = 220;
    private static final int SIDE_W  = 100;

    private CosmeticType selected = CosmeticType.CAPE;

    public MineModScreen() {
        super(Text.literal("MineMod"));
    }

    private int left() { return (width - PANEL_W) / 2; }
    private int top()  { return (height - PANEL_H) / 2; }

    @Override
    protected void init() {
        clearChildren();
        int x = left(), y = top();

        // Zakladki kategorii (lewy pasek)
        int ty = y + 34;
        for (CosmeticType type : CosmeticType.values()) {
            Text label = Text.literal((type == selected ? "> " : "") + type.label);
            addDrawableChild(ButtonWidget.builder(label, b -> { selected = type; init(); })
                    .dimensions(x + 6, ty, SIDE_W - 12, 20).build());
            ty += 24;
        }

        // Lista kosmetykow wybranej kategorii (prawa strona)
        int cx = x + SIDE_W + 10;
        int cy = y + 34;
        for (Cosmetic c : CosmeticRegistry.byType(selected)) {
            boolean on = CosmeticRegistry.equipped(selected) == c;
            Text label = Text.literal(c.name() + (on ? "   [zalozone]" : ""));
            addDrawableChild(ButtonWidget.builder(label, b -> { CosmeticRegistry.toggle(c); init(); })
                    .dimensions(cx, cy, PANEL_W - SIDE_W - 20, 22).build());
            cy += 26;
        }

        addDrawableChild(ButtonWidget.builder(Text.literal("Zamknij"), b -> close())
                .dimensions(x + PANEL_W - 76, y + PANEL_H - 26, 70, 20).build());
    }

    @Override
    public void renderBackground(DrawContext ctx, int mouseX, int mouseY, float delta) {
        ctx.fill(0, 0, width, height, BG_DIM);
    }

    @Override
    public void render(DrawContext ctx, int mouseX, int mouseY, float delta) {
        renderBackground(ctx, mouseX, mouseY, delta);

        int x = left(), y = top();
        ctx.fill(x, y, x + PANEL_W, y + PANEL_H, PANEL);
        ctx.fill(x, y, x + SIDE_W, y + PANEL_H, SIDEBAR);
        ctx.fill(x + SIDE_W, y, x + SIDE_W + 1, y + PANEL_H, LINE);
        ctx.fill(x, y + 26, x + PANEL_W, y + 27, LINE);

        ctx.drawTextWithShadow(textRenderer, "MINEMOD", x + 10, y + 9, WHITE);
        ctx.drawTextWithShadow(textRenderer, selected.label, x + SIDE_W + 10, y + 9, GRAY);

        super.render(ctx, mouseX, mouseY, delta);
    }

    @Override
    public boolean shouldPause() { return false; }
}
