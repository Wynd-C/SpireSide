package SpireSide.cards;

import SpireSide.actions.StrikeThisAction;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ReinforcedBodyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SpireSide.DefaultMod;
import SpireSide.characters.TheDefault;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.Iterator;

import static SpireSide.DefaultMod.makeCardPath;
@AutoAdd.Ignore
public class StrikeThis extends AbstractDynamicCard {


    public static final String ID = DefaultMod.makeID(StrikeThis.class.getSimpleName());
    public static final String IMG = makeCardPath("Strike_This.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = CardColor.RED;

    private static final int COST = -1;
    private static final int UPGRADED_COST = -1;

    private static final int BLOCK = 2;
    private static final int MAGICNUMBER = 1;
    private static final int UPGRADE_PLUS_MAGICNUMBER = 1;
    // /STAT DECLARATION/


    public StrikeThis() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.tags.add(CardTags.STRIKE);
        this.baseMagicNumber = MAGICNUMBER;
        this.baseBlock = BLOCK;
    }
    public static boolean isStrike(AbstractCard c) {
        return c.hasTag(CardTags.STRIKE);
    }
    public static int countCards() {
        int count = 0;
        Iterator var1 = AbstractDungeon.player.hand.group.iterator();

        AbstractCard c;
        while (var1.hasNext()) {
            c = (AbstractCard) var1.next();
            if (isStrike(c)) {
                ++count;
            }
        }

        var1 = AbstractDungeon.player.drawPile.group.iterator();

        while (var1.hasNext()) {
            c = (AbstractCard) var1.next();
            if (isStrike(c)) {
                ++count;
            }
        }

        var1 = AbstractDungeon.player.discardPile.group.iterator();

        while (var1.hasNext()) {
            c = (AbstractCard) var1.next();
            if (isStrike(c)) {
                ++count;
            }
        }

        return count;
    }



    public void calculateCardBlock(AbstractMonster mo) {
        int realBaseBlock = this.baseBlock;
        this.baseBlock += 1 * countCards();
        super.calculateCardBlock(mo);
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }

    public void applyPowers() {
        int realBaseBlock = this.baseBlock;
        this.baseBlock += 1 * countCards();
        super.applyPowers();
        this.baseBlock = realBaseBlock;
        this.isBlockModified = this.block != this.baseBlock;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ReinforcedBodyAction(p, block, this.freeToPlayOnce, this.energyOnUse));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();;
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
