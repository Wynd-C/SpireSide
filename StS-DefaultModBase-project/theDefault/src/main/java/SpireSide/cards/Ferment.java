package SpireSide.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.IncreaseMiscAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SpireSide.DefaultMod;
import SpireSide.characters.TheDefault;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static SpireSide.DefaultMod.makeCardPath;

public class Ferment extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Ferment.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Ferment.png");// "public static final String IMG = makeCardPath("${NAME}.png");

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = CardColor.GREEN;

    private static final int COST = 2;  // COST = ${COST}

    private static final int MAGIC_NUMBER = 5;
    private static final int SECOND_MAGIC_NUMBER = 2;// UPGRADE_PLUS_DMG = ${UPGRADED_DAMAGE_INCREASE}
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = 3;

    // /STAT DECLARATION/


    public Ferment() { // public ${NAME}() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.defaultSecondMagicNumber = SECOND_MAGIC_NUMBER;
        this.defaultBaseSecondMagicNumber = this.defaultSecondMagicNumber;
        this.misc = MAGIC_NUMBER;
        this.baseMagicNumber = this.misc;
        this.exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new IncreaseMiscAction(this.uuid, this.misc, this.defaultBaseSecondMagicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new PoisonPower(m, p, this.misc), this.misc, AbstractGameAction.AttackEffect.POISON));
    }

    public void applyPowers(){
        this.baseMagicNumber = this.misc;
        super.applyPowers();
        this.initializeDescription();

    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
        }
    }

    public AbstractCard makeCopy() { return new Ferment(); }

}
