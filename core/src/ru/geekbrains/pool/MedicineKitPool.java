package ru.geekbrains.pool;

import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.MainShip;
import ru.geekbrains.sprite.MedicineKit;

public class MedicineKitPool extends SpritesPool<MedicineKit> {
    private Rect worldBounds;
    private MainShip mainShip;

    public MedicineKitPool(Rect worldBounds, MainShip mainShip) {
        this.worldBounds = worldBounds;
        this.mainShip = mainShip;
    }

    @Override
    protected MedicineKit newObject() {
        return new MedicineKit(mainShip,worldBounds);
    }

    @Override
    public void free(MedicineKit kit) {
        super.free(kit);
        kit.setScale(1f);
    }
}
