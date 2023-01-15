package SpireSide.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SpireSide.DefaultMod;
import com.megacrit.cardcrawl.powers.DexterityPower;

import static SpireSide.DefaultMod.makeCardPath;

public class Gambit extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Gambit.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Demonic_Gambit_Final.png");// "public static final String IMG = makeCardPath("${NAME}.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.RARE; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.SELF;  //   since they don't change much.
    private static final CardType TYPE = CardType.SKILL;       //
    public static final CardColor COLOR = CardColor.RED;

    private static final int COST = 2;  // COST = ${COST}

    private static final int MAGICNUMBER = 2;
    private static final int UPGRADE_PLUS_MAGIC_NUMBER = -1;

    // /STAT DECLARATION/


    public Gambit() { // public ${NAME}() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = MAGICNUMBER;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for(int i = 0; i < 3; i++)
            AbstractDungeon.actionManager.addToBottom(new PlayTopCardAction(AbstractDungeon.getCurrRoom().monsters.getRandomMonster((AbstractMonster)null, true, AbstractDungeon.cardRandomRng), false));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new DexterityPower(p, -baseMagicNumber), -baseMagicNumber));
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
            initializeDescription();
        }
    }
}