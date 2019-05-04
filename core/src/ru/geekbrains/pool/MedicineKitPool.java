package ru.geekbrains.pool;

import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.MainShip;
import ru.geekbrains.sprite.MedicineKit;

public class MedicineKitPool extends SpritesPool<MedicineKit> {
    private Rect worldBounds;
    private MainShip mainShip;
    private ExplosionPool explosionPool;

    public MedicineKitPool(Rect worldBounds, MainShip mainShip, ExplosionPool explosionPool) {
        this.worldBounds = worldBounds;
        this.mainShip = mainShip;
        this.explosionPool = explosionPool;
    }

    @Override
    protected MedicineKit newObject() {
        return new MedicineKit(mainShip, worldBounds, explosionPool);
    }

    @Override
    public void free(MedicineKit kit) {
        super.free(kit);
        kit.setScale(1f);
    }
}
