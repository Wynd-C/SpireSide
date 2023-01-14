package SpireSide.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction.ActionType;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import java.util.Iterator;

public class ExtraDrawAction extends AbstractGameAction {
    private CardType restrictedType;

    public ExtraDrawAction(int newAmount, CardType restrictedType) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = AbstractDungeon.player;
        this.amount = newAmount;
        this.restrictedType = restrictedType;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            this.addToTop(new DrawCardAction(this.source, this.amount));
        }

        this.isDone = true;
    }

}