package de.handy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


public class GameOverScreen extends ScreenAdapter {

    HandyGame game;
    Rectangle restartButtonHitBox;
    SpriteBatch batch;
    Texture img;
    Vector2 restartButtonSize;
    Vector2 restartButtonPosition;
    public GameOverScreen(HandyGame game) {
        this.game = game;

        batch=new SpriteBatch();
        img=new Texture("badlogic.jpg");
        restartButtonSize=new Vector2(Gdx.graphics.getHeight()/3,Gdx.graphics.getHeight()/6);
        restartButtonPosition=new Vector2(Gdx.graphics.getWidth()/4-restartButtonSize.x/2.0f,Gdx.graphics.getHeight()/6);
        restartButtonHitBox=new Rectangle(restartButtonPosition.x,restartButtonPosition.y,restartButtonSize.x,restartButtonSize.y);



    }

    @Override
    public void render(float delta) {
        Vector2 cursorPosition=new Vector2(Gdx.input.getX(),-1*(Gdx.input.getY()-Gdx.graphics.getHeight()));
        if((Gdx.input.isTouched()||Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
                &&restartButtonHitBox.contains(cursorPosition)){
            game.setScreen(new GameScreen(game));
        }

        System.out.println(Gdx.input.getX()+"||"+(-1*(Gdx.input.getY()-Gdx.graphics.getHeight())));

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        batch.begin();
        batch.draw(img,restartButtonPosition.x,restartButtonPosition.y,restartButtonSize.x,restartButtonSize.y);
        batch.end();

    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
