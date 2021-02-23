package de.handy.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;



public class Paddle {
        public Vector2 position;
        Vector2 size;

        Rectangle hitBox;
        Rectangle hitBox2;
        Vector2 windowSize;
        int paddleNumb;





        public Paddle(float x,float y,float xSize,float ySize,int paddleNumb) {
            position = new Vector2(x,y);
            size = new Vector2(xSize,ySize);
            this.paddleNumb=paddleNumb;

            windowSize=new Vector2(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);

            if(paddleNumb==0){
                hitBox=new Rectangle(position.x-size.x/2.0f-windowSize.x,position.y-size.y/2.0f-windowSize.y,size.x,2);
                hitBox2=new Rectangle(position.x-size.x/2.0f-windowSize.x,position.y-windowSize.y,size.x,size.y/3.0f);
            }
            else if(paddleNumb==1){
                hitBox=new Rectangle(position.x-size.x/2.0f-windowSize.x,position.y+size.y/2.0f-windowSize.y,size.x,2);
                hitBox2=new Rectangle(position.x-size.x/2.0f-windowSize.x,position.y-windowSize.y,size.x,size.y/3.0f);
            }
            else if(paddleNumb==2){
                hitBox=new Rectangle(position.x-size.x/2.0f-windowSize.x,position.y-size.y/2.0f-windowSize.y,2,size.y);
                hitBox2=new Rectangle(position.x-windowSize.x,position.y-size.y/2.0f-windowSize.y,size.x/3.0f,size.y);
            }
            else if(paddleNumb==3){
                hitBox=new Rectangle(position.x+size.x/2.0f-windowSize.x,position.y-size.y/2.0f-windowSize.y,2,size.y);
                hitBox2=new Rectangle(position.x-windowSize.x,position.y-size.y/2.0f-windowSize.y,size.x/3.0f,size.y);
            }


        }




    public void draw(ShapeRenderer SR){
        if(paddleNumb==0){
            hitBox.x=position.x-size.x/2.0f-windowSize.x;
            hitBox2.x=position.x-size.x/2.0f-windowSize.x;
        }
        else if(paddleNumb==1){
            hitBox.x=position.x-size.x/2.0f-windowSize.x;
            hitBox2.x=position.x-size.x/2.0f-windowSize.x;
        }
        else if(paddleNumb==2){
            hitBox.y=position.y-size.y/2.0f-windowSize.y;
            hitBox2.y=position.y-size.y/2.0f-windowSize.y;
        }
        else if(paddleNumb==3){
            hitBox.y=position.y-size.y/2.0f-windowSize.y;
            hitBox2.y=position.y-size.y/2.0f-windowSize.y;
        }

            SR.rect(position.x-size.x/2.0f,position.y-size.y/2.0f,size.x,size.y);
    }

    public void update(){
        //hitBox.setPosition(position.x-size.x/2.0f-windowSize.x,position.y-size.y/2.0f-windowSize.y);


    }
    public void hit(){

    }




}
