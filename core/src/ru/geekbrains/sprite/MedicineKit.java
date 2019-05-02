package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.utils.Font;

public class MedicineKit extends Sprite {
    private enum State {DESCENT, FLY}

    private MedicineKit.State state;
    private static Vector2 descentV = new Vector2(0, -0.3f);
    private static Vector2 v = new Vector2();
    private static Vector2 v0 = new Vector2(0,-0.01f);
    private int hp = 10;
    private Rect worldbounds;
    private float reloadTimer;
    private float reloadInterval = 1f;
    private Font font;
    private StringBuilder stringBuilder = new StringBuilder();

    private MainShip mainShip;

    public MedicineKit(MainShip mainShip, Rect worldbounds) {
        this.mainShip = mainShip;
        this.worldbounds = worldbounds;
        this.state = State.DESCENT;
        this.v.set(descentV);
    }

    public void set(TextureRegion[] regions,
                    int hp,
                    Vector2 v0,
                    float heigh,
                    Font font) {
        this.regions = regions;
        this.pos.set(pos);
        this.hp = hp;
        this.v0.set(v0);
        this.pos.x = Rnd.nextFloat(worldbounds.getLeft()+halfWidth,worldbounds.getRight()-halfWidth);
        setBottom(worldbounds.getTop());
        setHeightProportion(heigh);
        this.state = State.DESCENT;
        this.font = font;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v,delta);
        prepareHpText();
        scale-=0.001f;
        if (state == State.DESCENT &
                getTop() <= worldbounds.getTop()) {
            state = State.FLY;
            v.set(v0);
        }
        if (state == State.FLY) {
            reloadTimer += delta;
            if (reloadTimer >= reloadInterval) {
                reloadTimer = 0f;
                decrementHp();
            }
        }
        if (getTop()<=worldbounds.getBottom()
        ||scale<=0){
            destroy();
        }
    }

    @Override
    public void draw(SpriteBatch batch) {
        super.draw(batch);
        font.draw(batch,stringBuilder,pos.x,pos.y);
    }

    private void decrementHp() {
        hp--;
        prepareHpText();
        if (hp <= 0) {
            destroy();
        }
    }

    private void prepareHpText() {
        stringBuilder.setLength(0);
        stringBuilder.append("+"+hp);
    }

    public int getHp() {
        return hp;
    }
}
