package com.userView;

import com.publicClass.R;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceHolder;


public class GameviewDrawThread extends Thread {
	int sleepSpan = 50;
	boolean flag = true;
	GameViewOne gameViewOne;
	GameViewTwo gameViewTwo;
	GameViewThree gameViewThree;
	GameViewFour gameViewFour;
	GameViewFive gameViewFive;
	GameViewSix gameViewSix;
	
	SurfaceHolder surfaceHolder;
	public GameviewDrawThread(GameViewOne gameViewOne){
		 
		this.gameViewOne=gameViewOne;
		this.surfaceHolder=gameViewOne.getHolder();
		
	}
	public GameviewDrawThread(GameViewTwo gameViewTwo){
		this.gameViewTwo=gameViewTwo;
		this.surfaceHolder=gameViewTwo.getHolder();
	}
	
	public GameviewDrawThread(GameViewThree gameViewThree){
		this.gameViewThree=gameViewThree;
		this.surfaceHolder=gameViewThree.getHolder();
	}
	
	public GameviewDrawThread(GameViewFour gameViewFour){
		this.gameViewFour=gameViewFour;
		this.surfaceHolder=gameViewFour.getHolder();
	}
    public GameviewDrawThread(GameViewFive gameViewFive){
		this.gameViewFive=gameViewFive;
		this.surfaceHolder=gameViewFive.getHolder();
	}
    public GameviewDrawThread(GameViewSix gameViewSix) {
    	this.gameViewSix=gameViewSix;
		this.surfaceHolder=gameViewSix.getHolder();	}
@Override
public void run(){
	 if(gameViewOne!=null){
	Canvas c;
	while(flag){
		c=null;
		try{
	    c=this.surfaceHolder.lockCanvas(null);
	    this.gameViewOne.onDraw(c);
		}finally{
			if(c!=null){
				this.surfaceHolder.unlockCanvasAndPost(c);
			}
		}
		try{
			Thread.sleep(sleepSpan);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
 }
	 else if(gameViewTwo!=null){
			Canvas c;
			while(flag){
				c=null;
				try{
			    c=this.surfaceHolder.lockCanvas(null);
			    this.gameViewTwo.onDraw(c);
				}finally{
					if(c!=null){
						this.surfaceHolder.unlockCanvasAndPost(c);
					}
				}
				try{
					Thread.sleep(sleepSpan);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		 }
	 else if(gameViewThree!=null){
			Canvas c;
			while(flag){
				c=null;
				try{
			    c=this.surfaceHolder.lockCanvas(null);
			    this.gameViewThree.onDraw(c);
				}finally{
					if(c!=null){
						this.surfaceHolder.unlockCanvasAndPost(c);
					}
				}
				try{
					Thread.sleep(sleepSpan);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		 }
	 else if(gameViewFour!=null){
			Canvas c;
			while(flag){
				c=null;
				try{
			    c=this.surfaceHolder.lockCanvas(null);
			    this.gameViewFour.onDraw(c);
				}finally{
					if(c!=null){
						this.surfaceHolder.unlockCanvasAndPost(c);
					}
				}
				try{
					Thread.sleep(sleepSpan);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		 }
	 else if(gameViewFive!=null){
			Canvas c;
			while(flag){
				c=null;
				try{
			    c=this.surfaceHolder.lockCanvas(null);
			    this.gameViewFive.onDraw(c);
				}finally{
					if(c!=null){
						this.surfaceHolder.unlockCanvasAndPost(c);
					}
				}
				try{
					Thread.sleep(sleepSpan);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		 }
	 else if(gameViewSix!=null){
			Canvas c;
			while(flag){
				c=null;
				try{
			    c=this.surfaceHolder.lockCanvas(null);
			    this.gameViewSix.onDraw(c);
				}finally{
					if(c!=null){
						this.surfaceHolder.unlockCanvasAndPost(c);
					}
				}
				try{
					Thread.sleep(sleepSpan);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		 }
}
public void setFlag(boolean b){
	this.flag=b;
}
}



