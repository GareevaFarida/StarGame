package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class EnemyShip extends Sprite {

    private Rect worldBounds;
    private Vector2 v = new Vector2(0,0);
    private int damage;

    public EnemyShip(){
        regions = new TextureRegion[1];
    }

    public EnemyShip(TextureAtlas atlas) {

        //  regions = new TextureRegion[1];
//        ////////////
        super(atlas.findRegion("enemy2"), 1, 2, 2);
//       // this.bulletPool = bulletPool;
//        //this.bulletRegion = atlas.findRegion("bulletEnemy");
        setHeightProportion(0.15f);
//        ///////////////

    }

//    public void set(
//          //  TextureRegion region,
//            Vector2 pos0,
//            Vector2 v0,
//            float height,
//            Rect worldBounds,
//            int damage
//    ) {
//        this.regions[0] = region;
//        this.pos.set(pos0);
//        this.v.set(v0);
//        setHeightProportion(height);
//        this.worldBounds = worldBounds;
//        this.damage = damage;
//    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        this.worldBounds = worldBounds;
        setHeight(worldBounds.getHeight()-0.05f);
    }

    public int getDamage() {
        return damage;
    }
}
