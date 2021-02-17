package de.handy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;



public class Paddle {
        public Vector2 position;
        Vector2 size;
        boolean horizontal;
        Rectangle hitBox;
        Rectangle hitLineL;
        Rectangle hitLineR;
        Rectangle hitLineT;
        Rectangle hitLineB;
        Vector2 windowSize;




        public Paddle(float x,float y,float xSize,float ySize,boolean horizontal) {
            position = new Vector2(x,y);
            size = new Vector2(xSize,ySize);
            this.horizontal=horizontal;
            windowSize=new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
            hitBox=new Rectangle(position.x-size.x/2.0f+windowSize.x,position.y-size.y/2.0f+windowSize.y,size.x,size.y);


        }

    public Paddle(Vector2 position,Vector2 size,boolean horizontal) {
        this.position = position;
        this.size=size;
        this.horizontal=horizontal;
        windowSize=new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        hitBox=new Rectangle(position.x-size.x/2.0f+windowSize.x,position.y-size.y/2.0f+windowSize.y,size.x,size.y);
    }
    public Paddle(float x,float y,Vector2 size,boolean horizontal) {
        position = new Vector2(x,y);
        this.size=size;
        this.horizontal=horizontal;
        windowSize=new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
        hitBox=new Rectangle(position.x-size.x/2.0f+windowSize.x,position.y-size.y/2.0f+windowSize.y,size.x,size.y);
    }


    public void draw(ShapeRenderer SR){
            hitBox.y=position.y-size.y/2.0f-windowSize.y;
            hitBox.x=position.x-size.x/2.0f-windowSize.x;

            SR.rect(position.x-size.x/2.0f,position.y-size.y/2.0f,size.x,size.y);
    }
    public void update(){
        hitBox.setPosition(position.x-size.x/2.0f-windowSize.x,position.y-size.y/2.0f-windowSize.y);


    }





}
