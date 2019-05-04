package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.ExplosionPool;
import ru.geekbrains.utils.ExplosionType;
import ru.geekbrains.utils.Font;

public class MedicineKit extends Sprite {
    private enum State {DESCENT, FLY}

    private MedicineKit.State state;
    private Vector2 descentV = new Vector2(0, -0.3f);
    private Vector2 v = new Vector2();
    private Vector2 v0 = new Vector2(0, -0.1f);
    private int hp = 10;
    private Rect worldbounds;
    private float decrementHpTimer;
    private float decrementHpInterval = 1f;
    private Font font;
    private StringBuilder stringBuilder = new StringBuilder();
    private ExplosionPool explosionPool;

    public MedicineKit(MainShip mainShip, Rect worldbounds, ExplosionPool explosionPool) {
        this.worldbounds = worldbounds;
        this.state = State.DESCENT;
        this.v.set(descentV);
        this.explosionPool = explosionPool;
    }

    public void set(TextureRegion[] regions,
                    int hp,
                    float heigh,
                    Font font) {
        this.regions = regions;
        this.pos.set(pos);
        this.hp = hp;
        this.pos.x = Rnd.nextFloat(worldbounds.getLeft() + this.getHalfWidth(), worldbounds.getRight() - this.getHalfWidth());
        this.setBottom(worldbounds.getTop());
        setHeightProportion(heigh);
        this.state = State.DESCENT;
        this.font = font;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        prepareHpText();
        scale -= 0.0007f;
        if (getTop() <= worldbounds.getTop()) {
            state = State.FLY;
            v.set(v0);
        }
        if (state == State.FLY) {
            decrementHpTimer += delta;
            if (decrementHpTimer >= decrementHpInterval) {
                decrementHpTimer = 0f;
                decrementHp();
            }
        }
        if (getTop() <= worldbounds.getBottom()
                || scale <= 0) {
            destroy();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        font.draw(batch, stringBuilder, pos.x, pos.y);
    }

    public void decrementHp() {
        hp--;
        prepareHpText();
        if (hp <= 0) {
            destroy();
        }
    }
    public int getHp() {
        return hp;
    }

    @Override
    public void destroy() {
        super.destroy();
        hp = 0;
    }

    public void boom() {
        Explosion explosion = explosionPool.obtain();
        explosion.set(this.getHeight(), this.pos, ExplosionType.MEDICAL_KIT);
    }


    private void prepareHpText() {
        stringBuilder.setLength(0);
        stringBuilder.append("+" + hp);
    }

    public boolean isBulletCollision(Rect bullet) {
        return !(bullet.getRight() < getLeft()
                || bullet.getLeft() > getRight()
                || bullet.getBottom() > getTop()
                || bullet.getTop() < pos.y
        );
    }
}
