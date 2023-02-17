package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Pipe extends Actor {
    Rectangle bounds;
    boolean upsideDown;
    AssetManager manager;
    boolean done;
    int speed;
    boolean dino;
    boolean sube;

    Pipe() {
        setSize(64, 230);
        bounds = new Rectangle();
        setVisible(false);
        setDefaultSpeed();
        done = false;
    }

    @Override
    public void act(float delta) {
        if (dino) {
            if(sube) moveBy(speed * delta, 100*delta);
            else moveBy(speed * delta, -100*delta);;

            if (getY() > 480) sube = false;
            else if (getY() < 10) sube = true;
        }else{
            moveBy(speed * delta, 0);
        }
        bounds.set(getX(), getY(), getWidth(), getHeight());
        if (!isVisible())
            setVisible(true);
        if (getX() < -64)
            remove();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (dino) batch.draw(manager.get("dino.png", Texture.class), getX(), getY());
        else batch.draw(manager.get(upsideDown ? "pipe_up.png" :
                "pipe_down.png", Texture.class), getX(), getY());
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setDino(boolean dino) {
        this.dino = dino;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setDefaultSpeed() {
        this.speed = -200;
    }

    public int getSpeed() {
        return speed;
    }


    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isUpsideDown() {
        return upsideDown;
    }

    public void setUpsideDown(boolean upsideDown) {
        this.upsideDown = upsideDown;
    }

    public void setManager(AssetManager manager) {
        this.manager = manager;
    }
}