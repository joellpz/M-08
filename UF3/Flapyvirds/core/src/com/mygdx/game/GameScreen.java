package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class GameScreen implements Screen {
    final Bird game;
    OrthographicCamera camera;

    Stage stage;
    Player player;
    boolean dead, mamado, isFruit;

    Array<Pipe> obstacles;
    Fruit fruit;
    long lastObstacleTime;
    long mamadoTime;
    int obstacleBeforeFruit;

    float score;

    public GameScreen(final Bird gam) {
        this.game = gam;

        // create the camera and the SpriteBatch
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        player = new Player();
        player.setManager(game.manager);
        stage = new Stage();
        stage.getViewport().setCamera(camera);
        stage.addActor(player);

        // create the obstacles array and spawn the first obstacle
        obstacles = new Array<>();
        spawnObstacle();

        score = 0;
        obstacleBeforeFruit = 0;
        isFruit = false;


    }

    @Override
    public void render(float delta) {
        boolean dead = false;

        // clear the screen with a color
        ScreenUtils.clear(0.3f, 0.8f, 0.8f, 1);
        // tell the camera to update its matrices.
        camera.update();
        // tell the SpriteBatch to render in the
        // coordinate system specified by the camera.
        game.batch.setProjectionMatrix(camera.combined);
        // Game batch: Background
        game.batch.begin();
        game.batch.draw(game.manager.get("background.png", Texture.class), 0, 0);
        game.batch.end();

        // Stage batch: Actors
        stage.getBatch().setProjectionMatrix(camera.combined);
        stage.draw();

        // Game batch: HUD
        game.batch.begin();
        game.smallFont.draw(game.batch, "Score: " + (int) score, 10,
                470);
        game.batch.end();

        //La puntuació augmenta amb el temps de joc
        score += Gdx.graphics.getDeltaTime();

        // process user input
        if (Gdx.input.justTouched()) {
            player.impulso();
            if (!mamado) game.manager.get("flap.wav", Sound.class).play();
        }
        stage.act();


        // Comprova que el jugador no es surt de la pantalla.
        // Si surt per la part inferior, game over
        if (player.getBounds().y > 480 - 45)
            player.setY(480 - 45);
        if (player.getBounds().y < -45) {
            dead = true;
        }

        System.out.println(obstacleBeforeFruit);
        if (TimeUtils.nanoTime() - lastObstacleTime >= 750000000 && obstacleBeforeFruit == 5) {
            spawnFuit();
            isFruit = true;
            obstacleBeforeFruit = 0;
        }
        if (mamado && TimeUtils.nanoTime() >= mamadoTime){
            mamado = false;
            player.setMamado(false);
            player.remove();
            stage.addActor(player);
        }

        if (isFruit) {
            if (fruit.getBounds().overlaps(player.getBounds())) {
                mamado = true;
                isFruit = false;
                mamadoTime = TimeUtils.nanoTime() + 5000000000L;
                fruit.remove();
                player.setMamado(true);
                player.remove();
                stage.addActor(player);
                game.manager.get("super-mario-bros.mp3", Sound.class).play();
            }
        }
        // Comprova si cal generar un obstacle nou
        if (TimeUtils.nanoTime() - lastObstacleTime > 1500000000) {
            spawnObstacle();
            if (!mamado){
                obstacleBeforeFruit++;
            }
        }
        // Comprova si les tuberies colisionen amb el jugador
        Iterator<Pipe> iter = obstacles.iterator();
        while (iter.hasNext()) {
            Pipe pipe = iter.next();
            if (pipe.getBounds().overlaps(player.getBounds()) && !mamado) {
                dead = true;
            }
        }
        // Treure de l'array les tuberies que estan fora de pantalla
        iter = obstacles.iterator();
        while (iter.hasNext()) {
            Pipe pipe = iter.next();
            if (pipe.getX() < -64) {
                obstacles.removeValue(pipe, true);
            }
        }

        if (dead) {
            game.manager.get("fail.wav", Sound.class).play();
            game.lastScore = (int) score;
            if (game.lastScore > game.topScore)
                game.topScore = game.lastScore;
            game.setScreen(new GameOverScreen(game));
            dispose();
        }

    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    private void spawnFuit() {
        float holey = MathUtils.random(50, 230);
        Fruit fruit = new Fruit();
        fruit.setX(800);
        fruit.setY(holey);
        fruit.setManager(game.manager);
        stage.addActor(fruit);
        this.fruit = fruit;
    }

    private void spawnObstacle() {

        // Calcula la alçada de l'obstacle aleatòriament
        float holey = MathUtils.random(50, 230);
        // Crea dos obstacles: Una tubería superior i una inferior
        Pipe pipe1 = new Pipe();
        pipe1.setX(800);
        pipe1.setY(holey - 230);
        pipe1.setUpsideDown(true);
        pipe1.setManager(game.manager);

        Pipe pipe2 = new Pipe();
        pipe2.setX(800);
        pipe2.setY(holey + 200);
        pipe2.setUpsideDown(false);
        pipe2.setManager(game.manager);


        if (mamado) {
            pipe1.setSpeed(pipe1.getSpeed() * 2);
            pipe2.setSpeed(pipe2.getSpeed() * 2);
        } else {
            pipe1.setDefaultSpeed();
            pipe2.setDefaultSpeed();
        }

        obstacles.add(pipe1);
        stage.addActor(pipe1);
        obstacles.add(pipe2);
        stage.addActor(pipe2);
        lastObstacleTime = TimeUtils.nanoTime();
    }
}

