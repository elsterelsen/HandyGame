package de.handy.game;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public  class Powerup {
Circle hitCircle;
float duration;
Vector2 position;
float size;
Random R;

    public void collision(){
        for(int i=GameScreen.ballCount-1;i>=0;i--) {
            if (GameScreen.balls[i].hitCircle.contains(this.hitCircle)) {

                aktivierung();
                GameScreen.passivePowerups.add(GameScreen.activePowerups.removeValue(this,true));

            }
        }
    }

    public  void aktivierung(){


    }
    public void deaktivierung(){

    }
    public void draw(){

    }

    public void countdown(float delta){
        duration-=delta;
        if(duration<0){
            deaktivierung();
            GameScreen.passivePowerups.removeValue(this,true);
        }
    }




}
