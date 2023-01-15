package SpireSide.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.AbstractPower.PowerType;

public class CustomDrawReductionPower extends AbstractPower {
    public static final String POWER_ID = "Draw Reduction";
    private static final PowerStrings powerStrings;
    public static final String NAME;
    public static final String[] DESCRIPTIONS;

    public CustomDrawReductionPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = "Draw Reduction";
        this.owner = owner;
        this.amount = amount;
        this.updateDescription();
        this.loadRegion("lessdraw");
        this.type = PowerType.DEBUFF;
        this.isTurnBased = true;
    }

    public void onInitialApplication() {
        --AbstractDungeon.player.gameHandSize;
    }

    public void atEndOfRound() {
        this.addToBot(new ReducePowerAction(this.owner, this.owner, "Draw Reduction", 1));
    }

    public void onRemove() {
        ++AbstractDungeon.player.gameHandSize;
    }

    public void updateDescription() {
        if (this.amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }

    }

    static {
        powerStrings = CardCrawlGame.languagePack.getPowerStrings("Draw Reduction");
        NAME = powerStrings.NAME;
        DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    }
}
