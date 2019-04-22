package ru.geekbrains.pool;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Bullet;
import ru.geekbrains.sprite.EnemyShip;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    TextureAtlas atlas;

    public EnemyShipPool(TextureAtlas atlas) {
        this.atlas = atlas;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(atlas);
    }
}
