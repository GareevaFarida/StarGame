package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.BulletPool;

public class EnemyShip extends Sprite {

    private static final int INVALID_POINTER = -1;

    private Rect worldBounds;

    private Vector2 v = new Vector2(0, -0.1f);
    private Vector2 v0 = new Vector2(0.5f, 0);

    private BulletPool bulletPool;
    private TextureRegion bulletRegion;
    private Vector2 bulletV = new Vector2(0f, 0.5f);

    private boolean pressedRight;
    private boolean pressedLeft;

    private int rightPointer = INVALID_POINTER;
    private int leftPointer = INVALID_POINTER;

    private float reloadInterval = 0.2f;
    private float reloadTimer;

    private int damage;

    public void set(
        //    Object owner,
            TextureRegion region,
            Vector2 pos0,
            Vector2 v0,
            float height,
            Rect worldBounds,
            int damage
    ) {
     //   this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
    }



    public EnemyShip(TextureAtlas atlas) {
        super(atlas.findRegion("enemy2"), 1, 2, 2);
        setHeightProportion(0.15f);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setTop(worldBounds.getHalfHeight() - 0.01f);
        setXPosition();
    }

    public void setXPosition() {
        pos.x = Rnd.nextFloat(worldBounds.getLeft() + halfWidth, worldBounds.getRight() - halfWidth);
    }


    @Override
    public void update(float delta) {
        super.update(delta);
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }
}
