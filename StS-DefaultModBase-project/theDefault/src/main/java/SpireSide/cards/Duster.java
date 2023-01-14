package SpireSide.cards;

import SpireSide.DefaultMod;
import SpireSide.actions.DrawStatusAction;
import SpireSide.characters.TheDefault;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static SpireSide.DefaultMod.makeCardPath;

public class Duster extends AbstractDynamicCard {
    public static final String ID = DefaultMod.makeID(Duster.class.getSimpleName());
    public static final String IMG = makeCardPath("Duster.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.RED;

    private static final int COST = 1;
    private static final int UPGRADE_REDUCE_COST = 0;

    private static final int MAGICNUMBER = 3;

    public Duster() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = MAGICNUMBER;

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < MAGICNUMBER; i++)
            AbstractDungeon.actionManager.addToBottom(new DrawStatusAction(MAGICNUMBER, p));
        AbstractDungeon.actionManager.addToBottom(new ExhaustAction(1, false, true, false));
    }
    /* public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUse = super.canUse(p, m);
        if (!canUse) {
            return false;
        } else {
            Iterator var4 = p.drawPile.group.iterator();

            while(var4.hasNext()) {
                AbstractCard c = (AbstractCard)var4.next();
                if (c.type != CardType.STATUS) {
                    canUse = false;
                }
            }

            return canUse;
        }
    }
*/

    // This code was taken from Clash, ergo it is checking if the ENTIRE draw pile consists of
    // Status cards before returning whether the card can be used.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_REDUCE_COST);
            initializeDescription();
        }
    }
}