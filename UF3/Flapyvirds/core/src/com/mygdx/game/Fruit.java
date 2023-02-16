package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Fruit extends Actor {
    Rectangle bounds;
    AssetManager manager;

    Fruit() {
        setSize(64, 45);
        bounds = new Rectangle();
        setVisible(false);
    }

    @Override
    public void act(float delta) {
        moveBy(-200 * delta, 0);
        bounds.set(getX(), getY(), getWidth(), getHeight());
        if (!isVisible())
            setVisible(true);
        if (getX() < -64)
            remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(manager.get("golpe-mortal.png", Texture.class), getX(), getY());
    }



    public Rectangle getBounds() {
        return bounds;
    }

    public void setManager(AssetManager manager) {
        this.manager = manager;
    }
}
