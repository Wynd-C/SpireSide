package SpireSide.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import SpireSide.DefaultMod;
import SpireSide.util.TextureLoader;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;

import static SpireSide.DefaultMod.makeRelicOutlinePath;
import static SpireSide.DefaultMod.makeRelicPath;

public class CTeapot extends CustomRelic {

    public static final String ID = DefaultMod.makeID("CTeapot");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("cteapot.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));
    public CTeapot() {
        super(ID, IMG, OUTLINE, RelicTier.COMMON, LandingSound.MAGICAL);
    }


    @Override
    public void atBattleStart(){
        this.counter = 0;
    }

    @Override
    public void atTurnStart(){
        ++this.counter;
        if (this.counter == 5) {
            this.beginPulse();
            this.flash();
            AbstractDungeon.player.gainGold(5);
            AbstractDungeon.effectList.add(new GainGoldTextEffect(5));
        }
    }

       // Description
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}

