package SpireSide.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.PummelDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Pummel;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.localization.CardStrings;
import SpireSide.DefaultMod;
import SpireSide.characters.TheDefault;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static SpireSide.DefaultMod.makeCardPath;

// public class ${NAME} extends AbstractDynamicCard
public class Pinch extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Pinch.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Pinch.png");// "public static final String IMG = makeCardPath("${NAME}.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;



    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.COMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = CardColor.RED;

    private static final int COST = 0;  // COST = ${COST}

    private static final int DAMAGE = 4;    // DAMAGE = ${DAMAGE}
    //private static final int UPGRADE_PLUS_DMG = -1;  // UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}
    //private static final int ATTACKS = 1;
    private static final int UPGRADE_PLUS_ATTACKS = 1;
    private static final int STRENGTH = 1;

    // /STAT DECLARATION/


    public Pinch() { // public ${NAME}() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        //magicNumber = baseMagicNumber = ATTACKS;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = STRENGTH;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p,defaultBaseSecondMagicNumber)));
        if(!upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new LoseStrengthPower(p, defaultBaseSecondMagicNumber)));
        }
    }

    /* for (int i = 0; i < magicNumber; i++)
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn))
        );
    {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, defaultBaseSecondMagicNumber)));
    }
}
*/
    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            //this.upgradeDamage(UPGRADE_PLUS_DMG);
            //this.upgradeMagicNumber(UPGRADE_PLUS_ATTACKS);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}