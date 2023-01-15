package SpireSide.cards;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
import com.megacrit.cardcrawl.actions.defect.EvokeOrbAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import SpireSide.DefaultMod;
import SpireSide.characters.TheDefault;
import com.megacrit.cardcrawl.orbs.AbstractOrb;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.powers.watcher.MarkPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import static SpireSide.DefaultMod.makeCardPath;

// public class ${NAME} extends AbstractDynamicCard
public class Versatility extends AbstractDynamicCard {

    // TEXT DECLARATION

    public static final String ID = DefaultMod.makeID(Versatility.class.getSimpleName()); // USE THIS ONE FOR THE TEMPLATE;
    public static final String IMG = makeCardPath("Versatility.png");// "public static final String IMG = makeCardPath("${NAME}.png");


    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final CardRarity RARITY = CardRarity.UNCOMMON; //  Up to you, I like auto-complete on these
    private static final CardTarget TARGET = CardTarget.ENEMY;  //   since they don't change much.
    private static final CardType TYPE = CardType.ATTACK;       //
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 1;  // COST = ${COST

    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int MAGIC_NUMBER = 2;
    // /STAT DECLARATION/


    public Versatility() { // public ${NAME}() - This one and the one right under the imports are the most important ones, don't forget them
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = MAGIC_NUMBER;
        baseMagicNumber = magicNumber;
        this.showEvokeValue = true;
        this.showEvokeOrbCount = 1;
        this.tags.add(CardTags.STRIKE);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.WATCHER) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new VigorPower(p, 4), 4));
        }
        else if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.DEFECT) {
            AbstractDungeon.actionManager.addToBottom(new EvokeOrbAction(1));
        }
        else if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
            this.addToBot(new ApplyPowerAction(p, p, new BlurPower(p, 1), 1));
        }
        else if (AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.IRONCLAD) {
            this.addToBot(new ApplyPowerAction(p, p, new RagePower(p, 3), 3));
        }
        else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new VulnerablePower(m, this.magicNumber, false), this.magicNumber));
        }
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
