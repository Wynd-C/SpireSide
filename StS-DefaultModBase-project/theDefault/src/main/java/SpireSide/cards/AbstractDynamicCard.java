package SpireSide.cards;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractDynamicCard extends AbstractDefaultCard {

    // "How come DefaultCommonAttack extends CustomCard and not DynamicCard like all the rest?"

    // Well every card, at the end of the day, extends CustomCard.
    // Abstract Default Card extends CustomCard and builds up on it, adding a second magic number. Your card can extend it and
    // bam - you can have a second magic number in that card (Learn Java inheritance if you want to know how that works).
    // Abstract Dynamic Card builds up on Abstract Default Card even more and makes it so that you don't need to add
    // the NAME and the DESCRIPTION into your card - it'll get it automatically. Of course, this functionality could have easily
    // Been added to the default card rather than creating a new Dynamic one, but was done so to deliberately.

    public AbstractDynamicCard(final String id,
                               final String img,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);

    }
    public void calculateCardBlock(AbstractMonster mo) {
        this.applyPowersToBlock();
        AbstractPlayer player = AbstractDungeon.player;
        this.isDamageModified = false;
        if (!this.isMultiDamage && mo != null) {
            float tmp = (float)this.baseDamage;
            Iterator var9 = player.relics.iterator();

            while(var9.hasNext()) {
                AbstractRelic r = (AbstractRelic)var9.next();
                tmp = r.atDamageModify(tmp, this);
                if (this.baseDamage != (int)tmp) {
                    this.isDamageModified = true;
                }
            }

            AbstractPower p;
            for(var9 = player.powers.iterator(); var9.hasNext(); tmp = p.atDamageGive(tmp, this.damageTypeForTurn, this)) {
                p = (AbstractPower)var9.next();
            }

            tmp = player.stance.atDamageGive(tmp, this.damageTypeForTurn, this);
            if (this.baseDamage != (int)tmp) {
                this.isDamageModified = true;
            }

            for(var9 = mo.powers.iterator(); var9.hasNext(); tmp = p.atDamageReceive(tmp, this.damageTypeForTurn, this)) {
                p = (AbstractPower)var9.next();
            }

            for(var9 = player.powers.iterator(); var9.hasNext(); tmp = p.atDamageFinalGive(tmp, this.damageTypeForTurn, this)) {
                p = (AbstractPower)var9.next();
            }

            for(var9 = mo.powers.iterator(); var9.hasNext(); tmp = p.atDamageFinalReceive(tmp, this.damageTypeForTurn, this)) {
                p = (AbstractPower)var9.next();
            }

            if (tmp < 0.0F) {
                tmp = 0.0F;
            }

            if (this.baseDamage != MathUtils.floor(tmp)) {
                this.isDamageModified = true;
            }

            this.damage = MathUtils.floor(tmp);
        } else {
            ArrayList<AbstractMonster> m = AbstractDungeon.getCurrRoom().monsters.monsters;
            float[] tmp = new float[m.size()];

            int i;
            for(i = 0; i < tmp.length; ++i) {
                tmp[i] = (float)this.baseDamage;
            }

            Iterator var6;
            AbstractPower p;
            for(i = 0; i < tmp.length; ++i) {
                var6 = player.relics.iterator();

                while(var6.hasNext()) {
                    AbstractRelic r = (AbstractRelic)var6.next();
                    tmp[i] = r.atDamageModify(tmp[i], this);
                    if (this.baseDamage != (int)tmp[i]) {
                        this.isDamageModified = true;
                    }
                }

                for(var6 = player.powers.iterator(); var6.hasNext(); tmp[i] = p.atDamageGive(tmp[i], this.damageTypeForTurn, this)) {
                    p = (AbstractPower)var6.next();
                }

                tmp[i] = player.stance.atDamageGive(tmp[i], this.damageTypeForTurn, this);
                if (this.baseDamage != (int)tmp[i]) {
                    this.isDamageModified = true;
                }
            }

            for(i = 0; i < tmp.length; ++i) {
                var6 = ((AbstractMonster)m.get(i)).powers.iterator();

                while(var6.hasNext()) {
                    p = (AbstractPower)var6.next();
                    if (!((AbstractMonster)m.get(i)).isDying && !((AbstractMonster)m.get(i)).isEscaping) {
                        tmp[i] = p.atDamageReceive(tmp[i], this.damageTypeForTurn, this);
                    }
                }
            }

            for(i = 0; i < tmp.length; ++i) {
                for(var6 = player.powers.iterator(); var6.hasNext(); tmp[i] = p.atDamageFinalGive(tmp[i], this.damageTypeForTurn, this)) {
                    p = (AbstractPower)var6.next();
                }
            }

            for(i = 0; i < tmp.length; ++i) {
                var6 = ((AbstractMonster)m.get(i)).powers.iterator();

                while(var6.hasNext()) {
                    p = (AbstractPower)var6.next();
                    if (!((AbstractMonster)m.get(i)).isDying && !((AbstractMonster)m.get(i)).isEscaping) {
                        tmp[i] = p.atDamageFinalReceive(tmp[i], this.damageTypeForTurn, this);
                    }
                }
            }

            for(i = 0; i < tmp.length; ++i) {
                if (tmp[i] < 0.0F) {
                    tmp[i] = 0.0F;
                }
            }

            this.multiDamage = new int[tmp.length];

            for(i = 0; i < tmp.length; ++i) {
                if (this.baseDamage != MathUtils.floor(tmp[i])) {
                    this.isDamageModified = true;
                }

                this.multiDamage[i] = MathUtils.floor(tmp[i]);
            }

            this.damage = this.multiDamage[0];
        }

    }
}