package de.handy.game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Ball {
    Random random;
    Vector2 position;
    float size;
    float radius;
    Vector2 movement;
    Vector2 movementScale;
    Texture img;
    Rectangle hitBox;
    float movementFaktor;


    public Ball(float x,float y,float size,float xMovement,float yMovement,Texture img,float movementFaktor) {

        this.size = size;
        this.movementFaktor=movementFaktor;
        random=new Random();
        radius=size/2;
        position = new Vector2(x-radius,y-radius);
        movement=new Vector2(xMovement*movementFaktor,yMovement*movementFaktor);
        movementScale=new Vector2(1.1f,1.1f);
        this.img=img;
        hitBox=new Rectangle(position.x,position.y,size,size);


    }

    public void updatePosition(float delta) {
        position.x +=movement.x*delta;
        position.y +=movement.y*delta;
        hitBox.setPosition(position);
    }
    public void update(float delta){


        updatePosition(delta);
    }

    public void draw(SpriteBatch batch){

        batch.draw(img,position.x,position.y,size,size);
    }

    public void dispose(){
        img.dispose();
    }

    public void setPosition(Vector2 position) {
        this.position.x *= position.x;
        this.position.y *= position.y;
        hitBox.x= position.x;
        hitBox.y= position.y;
    }


    public void checkCollision(Paddle paddle0,Paddle paddle1,Paddle paddle2,Paddle paddle3){
        if(hitBox.overlaps(paddle0.hitBox)){
                movement.y*=-1.0f*movementScale.y;
                position.y=paddle0.position.y-paddle0.windowSize.y-paddle0.size.y/2-size;
            if(movement.x<0){movement.x-=(random.nextInt(25)+1)*movementFaktor;}
            else{movement.x+=(random.nextInt(25)+1)*movementFaktor;}
        }
        if(hitBox.overlaps(paddle1.hitBox)){
                movement.y*=-1.0f*movementScale.y;
                position.y=paddle1.position.y-paddle1.windowSize.y+paddle1.size.y/2+2;
                if(movement.x<0){movement.x-=(random.nextInt(25)+1)*movementFaktor;}
            else{movement.x+=(random.nextInt(25)+1)*movementFaktor;}
        }
        if(hitBox.overlaps(paddle2.hitBox)){
                movement.x*=-1.0f*movementScale.x;
                position.x=paddle2.position.x-paddle2.windowSize.x-paddle2.size.x/2-size;
            if(movement.y<0){movement.y-=(random.nextInt(25)+1)*movementFaktor;}
            else{movement.y+=(random.nextInt(25)+1)*movementFaktor;}
        }
        if(hitBox.overlaps(paddle3.hitBox)){
                movement.x*=-1.0f*movementScale.x;
                position.x=paddle3.position.x-paddle3.windowSize.x+paddle3.size.x/2+2;
            if(movement.y<0){movement.y-=(random.nextInt(25)+1)*movementFaktor;}
            else{movement.y+=(random.nextInt(25)+1)*movementFaktor;}
        }
    }
    public boolean outsideOfScreen(){
        return position.x< -Gdx.graphics.getWidth()/2||position.x> Gdx.graphics.getWidth()/2
                ||position.y<-Gdx.graphics.getHeight()/2||position.y>Gdx.graphics.getHeight()/2;
    }

}
