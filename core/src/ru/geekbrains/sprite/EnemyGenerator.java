package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;
import ru.geekbrains.math.Rnd;
import ru.geekbrains.pool.EnemyShipPool;

public class EnemyGenerator extends Sprite {

    private float reloadInterval = 2;
    private float reloadTimer;
    private TextureAtlas atlas;
    private TextureRegion enemyShipRegion;
    private EnemyShipPool enemyShipPool;
    private Rect worldBounds;
    private Vector2 enemyShipV = new Vector2(0f, -0.1f);

    public EnemyGenerator(TextureAtlas atlas, EnemyShipPool enemyShipPool) {

        this.atlas = atlas;
        this.enemyShipPool = enemyShipPool;
        this.enemyShipRegion = atlas.findRegion("enemy2");
        enemyShipRegion.setRegionWidth(enemyShipRegion.getRegionWidth()/2);

    }

    @Override
    public void update(float delta) {
        super.update(delta);
        enemyShipPool.updateActiveSprites(delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
    }

    public void freeAllDestroyedActiveSprites() {
        enemyShipPool.freeAllDestroyedActiveSprites();
    }

    public void drawActiveSprites(SpriteBatch batch) {
        enemyShipPool.drawActiveSprites(batch);
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        for (EnemyShip ship : enemyShipPool.getActiveObjects()
        ) {
            ship.resize(worldBounds);
        }
    }

    public void shoot() {
        EnemyShip enemyShip = enemyShipPool.obtain();
        getRndPosition();
        enemyShip.set(enemyShipRegion, pos, enemyShipV, 0.15f, worldBounds, 1);
    }

    private void getRndPosition() {
        pos.x = Rnd.nextFloat(worldBounds.getLeft() + halfWidth, worldBounds.getRight() - halfWidth);
        pos.y = worldBounds.getHalfHeight() - 0.01f;
    }
}
