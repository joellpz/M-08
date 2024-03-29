package com.mygdx.game;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
    Rectangle bounds;
    AssetManager manager;
    float speedy, gravity;
    boolean mamado;

    Player() {
        setX(200);
        setY(280 / 2 - 64 / 2);
        setSize(64, 45);
        bounds = new Rectangle();
        speedy = 0;
        gravity = 850f;
        mamado = false;
    }

    @Override
    public void act(float delta) {
        //Actualitza la posició del jugador amb la velocitat vertical
        moveBy(0, speedy * delta);
        //Actualitza la velocitat vertical amb la gravetat
        speedy -= gravity * delta;
        bounds.set(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(manager.get(mamado ? "mamado_bird.png" : "bird.png", Texture.class),
                getX(), getY());
    }

    public boolean isMamado() {
        return mamado;
    }

    public void setMamado(boolean mamado) {
        this.mamado = mamado;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setManager(AssetManager manager) {
        this.manager = manager;
    }

    void impulso() {
        speedy = 300f;
    }
}