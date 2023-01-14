package SpireSide.actions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.NoDrawPower;


public class DrawStatusAction extends AbstractGameAction {
    private int MagicNumber;
    private AbstractPlayer Player;

    public DrawStatusAction(int MagicNumber, AbstractPlayer p){
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.amount = MagicNumber;
    }
    public void update() {
        this.isDone = true;
        if(AbstractDungeon.player.hand.size() >= BaseMod.MAX_HAND_SIZE || AbstractDungeon.player.hasPower(NoDrawPower.POWER_ID)) {
            return;
        }
        for (AbstractCard c : AbstractDungeon.player.drawPile.group){
            if (c.type == CardType.STATUS)
            {
                AbstractDungeon.player.drawPile.group.remove(c);
                AbstractDungeon.player.drawPile.addToTop(c);
                AbstractDungeon.actionManager.addToTop(new DrawCardAction(1));
                return;
            }
        }
    }
}
