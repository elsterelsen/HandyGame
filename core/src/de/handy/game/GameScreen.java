package de.handy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;


public class GameScreen extends ScreenAdapter {
    public OrthographicCamera camera;
    Viewport viewport;
    SpriteBatch batch;
    Texture ballTexture;
    ShapeRenderer SR;
    Paddle[] paddles;
    Vector2 paddlesize;
    Ball[] balls;
    int ballCount;
    float ballsize;
    Random random;
    HandyGame game;
    float movementFaktor;


    public GameScreen(HandyGame game) {
        random=new Random();
        SR=new ShapeRenderer();
        camera=new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        //viewport=new ScreenViewport(camera);
        camera.translate(Gdx.graphics.getWidth()/2.0f,Gdx.graphics.getHeight()/2.0f);
        camera.update();

        batch=new SpriteBatch();
        ballTexture=new Texture("badlogic.jpg");

        paddlesize=new Vector2(Gdx.graphics.getHeight()/4,Gdx.graphics.getHeight()/20);
        paddles=new Paddle[4];
        paddles[0]=new Paddle(Gdx.graphics.getWidth()/2.0f,Gdx.graphics.getHeight() - paddlesize.y,paddlesize,false);
        paddles[1]=new Paddle(Gdx.graphics.getWidth()/2.0f,paddlesize.y,paddlesize,false);
        paddles[2]=new Paddle(Gdx.graphics.getWidth()-paddlesize.y,Gdx.graphics.getHeight()/2.0f,paddlesize.y,paddlesize.x,true);
        paddles[3]=new Paddle(paddlesize.y,Gdx.graphics.getHeight()/2.0f,paddlesize.y,paddlesize.x,true);

        movementFaktor=Gdx.graphics.getWidth()/1280;

        balls=new Ball[5];
        ballsize=paddlesize.y*2.4f;
        balls[0]=new Ball(1,1,ballsize, random.nextInt(100)-50,
                -50,ballTexture,movementFaktor);
        ballCount=1;
        this.game=game;

    }



    @Override
    public void render (float delta) {
        gameOver();
        PaddleSteuerung(delta);
        ballSteuerung(delta);

        System.out.println(Gdx.graphics.getWidth()+""+Gdx.graphics.getHeight());


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        SR.begin(ShapeRenderer.ShapeType.Filled);
        SR.setProjectionMatrix(camera.combined);
        SR.setColor(1,1,1,1);
        for(int i=0;i<4;i++){
            paddles[i].draw(SR);
        }
        SR.end();
        batch.begin();
        batch.setProjectionMatrix(camera.projection);
        int i=0;
        while(i!=ballCount){
            balls[i].checkCollision(paddles[0],paddles[1],paddles[2],paddles[3]);
            balls[i].update(delta);
            balls[i].draw(batch);
            i++;
        }
        batch.end();
    }

    @Override
    public void dispose () {
        batch.dispose();
        ballTexture.dispose();
        SR.dispose();
    }


    public void PaddleSteuerung(float delta){
        float xResetPaddlePosition=Gdx.graphics.getWidth()- paddles[1].size.x/2;
        float yResetPaddlePosition=Gdx.graphics.getHeight()- paddles[2].size.y/2;
        for(int i=0;i<2;i++){
            paddles[i].position.x +=Gdx.input.getAccelerometerY()*delta*300;
            paddles[i].update();
            if(paddles[i].position.x>xResetPaddlePosition){
                paddles[i].position.x=xResetPaddlePosition;
            }
            else if(paddles[i].position.x<paddlesize.x/2){
                paddles[i].position.x=paddlesize.x/2;
            }
        }
        for(int i=2;i<4;i++){
            paddles[i].position.y -=Gdx.input.getAccelerometerX()*delta*200;
            paddles[i].update();
            if(paddles[i].position.y>yResetPaddlePosition){
                paddles[i].position.y=yResetPaddlePosition;
            }
            else if(paddles[i].position.y<paddlesize.x/2){
                paddles[i].position.y=paddlesize.x/2;

            }
        }


    }
    public void ballSteuerung(float delta){
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            balls[0].position.y+=3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            balls[0].position.y-=3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            balls[0].position.x-=3;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            balls[0].position.x+=3;
        }

    }

    public void gameOver(){
        if(ballCount<1){

            Gdx.input.vibrate(500);
            game.setScreen(new GameOverScreen(game));
        }
        else {
            for(int i=ballCount;i>0;i--){
                if (balls[i-1].outsideOfScreen()){
                    if(balls[i-1]!=balls[ballCount-1]){
                        balls[i-1]=balls[ballCount-1];
                        ballCount--;
                    }
                    else{
                      balls[i-1]=null;
                      ballCount--;
                    }
                }
            }

        }
    }


}
