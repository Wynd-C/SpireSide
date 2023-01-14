package SpireSide.cards;

import SpireSide.actions.ParryAndRiposteAction;
import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SpireSide.DefaultMod;


import static SpireSide.DefaultMod.makeCardPath;

// public class Parry and Riposte extends AbstractDynamicCard
public class ParryAndRiposte extends AbstractDynamicCard {



    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(ParryAndRiposte.class.getSimpleName());
    public static final String IMG = makeCardPath("Parry_And_Riposte.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = CardColor.RED;

    private static final int COST = 1;  // COST = 1

    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DAMAGE = 2;

    private static final int MAGICNUMBER = 3;
    private static final int UPGRADE_PLUS_MAGICNUMBER = 1;

    // /STAT DECLARATION/


    public ParryAndRiposte() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseMagicNumber = MAGICNUMBER;
        this.exhaust = true;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn)));
        AbstractDungeon.actionManager.addToBottom(new ParryAndRiposteAction(this.baseMagicNumber, m));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DAMAGE);
            upgradeMagicNumber(UPGRADE_PLUS_MAGICNUMBER);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
