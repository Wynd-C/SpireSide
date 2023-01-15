package SpireSide.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.actions.unique.GreedAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardColor;
import com.megacrit.cardcrawl.cards.AbstractCard.CardRarity;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTarget;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.GainGoldTextEffect;
import com.megacrit.cardcrawl.vfx.GainPennyEffect;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import com.megacrit.cardcrawl.vfx.SpotlightPlayerEffect;

public class PocketAction extends AbstractGameAction {
    private int increaseGold;
    private static final float DURATION = 0.1F;
    private AbstractMonster targetMonster;

    public PocketAction(int increaseGold, AbstractMonster m){
        this.duration = DURATION;
        this.actionType = ActionType.WAIT;
        this.increaseGold = increaseGold;
        this.targetMonster = m;
    }
    public void update(){
        if (this.targetMonster != null && this.targetMonster.getIntentBaseDmg() <= 0){
            AbstractDungeon.actionManager.addToBottom(new GainGoldAction(increaseGold));
            AbstractDungeon.effectList.add(new RainingGoldEffect(increaseGold * 5, true));
            AbstractDungeon.effectList.add(new GainGoldTextEffect(increaseGold));
        }
        this.isDone = true;
    }
}
