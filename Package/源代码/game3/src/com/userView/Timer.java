package com.userView;

import com.publicClass.Constant;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
/**
 * 
 *计时器类
 *
 */
public class Timer {
	GameViewOne gameViewOne;
	GameViewTwo gameViewTwo;
	GameViewThree gameViewThree;
	GameViewFour gameViewFour;
	GameViewFive gameViewFive;
	GameViewSix gameViewSix;
	
	
	private final int totalSecond=2*60;//总秒数
	private Bitmap breakMarkBitmap;//时间分隔符的位图
	private Bitmap[] numberBitmaps;	//数字位图
	private int leftSecond=totalSecond;
	float endX;//数字的右上点坐标
	float endY;
	int numberWidth;
	int numberHeight;
	int breakMarkWidth;
	int breakMarkHeight;
	public Timer(GameViewOne gameViewOne,Bitmap breakMarkBitmap,Bitmap[] numberBitmaps)
	{
		this.gameViewOne=gameViewOne;
		this.breakMarkBitmap=breakMarkBitmap;
		this.numberBitmaps=numberBitmaps;
		endX= Constant.SCREEN_WIDTH-numberBitmaps[0].getWidth()*1-breakMarkBitmap.getWidth()*2;
		endY=numberBitmaps[0].getHeight()/2;
		numberWidth=numberBitmaps[0].getWidth();
		numberHeight=numberBitmaps[0].getHeight();		
		breakMarkWidth=breakMarkBitmap.getWidth();
		breakMarkHeight=breakMarkBitmap.getHeight();
	}
	
	public Timer(GameViewTwo gameViewTwo,Bitmap breakMarkBitmap,Bitmap[] numberBitmaps)
	{
		this.gameViewTwo=gameViewTwo;
		this.breakMarkBitmap=breakMarkBitmap;
		this.numberBitmaps=numberBitmaps;
		endX= Constant.SCREEN_WIDTH-numberBitmaps[0].getWidth()*1-breakMarkBitmap.getWidth()*2;
		endY=numberBitmaps[0].getHeight()/2;
		numberWidth=numberBitmaps[0].getWidth();
		numberHeight=numberBitmaps[0].getHeight();		
		breakMarkWidth=breakMarkBitmap.getWidth();
		breakMarkHeight=breakMarkBitmap.getHeight();
	}
	

	public Timer(GameViewThree gameViewThree,Bitmap breakMarkBitmap,Bitmap[] numberBitmaps)
	{
		this.gameViewThree=gameViewThree;
		this.breakMarkBitmap=breakMarkBitmap;
		this.numberBitmaps=numberBitmaps;
		endX= Constant.SCREEN_WIDTH-numberBitmaps[0].getWidth()*1-breakMarkBitmap.getWidth()*2;
		endY=numberBitmaps[0].getHeight()/2;
		numberWidth=numberBitmaps[0].getWidth();
		numberHeight=numberBitmaps[0].getHeight();		
		breakMarkWidth=breakMarkBitmap.getWidth();
		breakMarkHeight=breakMarkBitmap.getHeight();
	}
	public Timer(GameViewFour gameViewFour, Bitmap breakMarkBitmap,Bitmap[] numberBitmaps) {
		this.gameViewFour=gameViewFour;
		this.breakMarkBitmap=breakMarkBitmap;
		this.numberBitmaps=numberBitmaps;
		endX= Constant.SCREEN_WIDTH-numberBitmaps[0].getWidth()*1-breakMarkBitmap.getWidth()*2;
		endY=numberBitmaps[0].getHeight()/2;
		numberWidth=numberBitmaps[0].getWidth();
		numberHeight=numberBitmaps[0].getHeight();		
		breakMarkWidth=breakMarkBitmap.getWidth();
		breakMarkHeight=breakMarkBitmap.getHeight();	}

	public Timer(GameViewFive gameViewFive, Bitmap breakMarkBitmap,
			Bitmap[] numberBitmaps) {
		
		this.gameViewFive=gameViewFive;
		this.breakMarkBitmap=breakMarkBitmap;
		this.numberBitmaps=numberBitmaps;
		endX= Constant.SCREEN_WIDTH-numberBitmaps[0].getWidth()*1-breakMarkBitmap.getWidth()*2;
		endY=numberBitmaps[0].getHeight()/2;
		numberWidth=numberBitmaps[0].getWidth();
		numberHeight=numberBitmaps[0].getHeight();		
		breakMarkWidth=breakMarkBitmap.getWidth();
		breakMarkHeight=breakMarkBitmap.getHeight();
		
	}

	public Timer(GameViewSix gameViewSix, Bitmap breakMarkBitmap,
			Bitmap[] numberBitmaps) {

		this.gameViewSix=gameViewSix;
		this.breakMarkBitmap=breakMarkBitmap;
		this.numberBitmaps=numberBitmaps;
		endX= Constant.SCREEN_WIDTH-numberBitmaps[0].getWidth()*1-breakMarkBitmap.getWidth()*2;
		endY=numberBitmaps[0].getHeight()/2;
		numberWidth=numberBitmaps[0].getWidth();
		numberHeight=numberBitmaps[0].getHeight();		
		breakMarkWidth=breakMarkBitmap.getWidth();
		breakMarkHeight=breakMarkBitmap.getHeight();	}

	//绘制时间的方法
	public void drawSelf(Canvas canvas,Paint paint)
	{
		int second=this.leftSecond%60;
		int minute=this.leftSecond/60;		
		//绘制秒钟
		drawNumberBitmap(second,numberBitmaps,Constant.SCREEN_WIDTH/2+breakMarkBitmap.getWidth()/2,0,canvas, paint);
		//绘制分隔符
		int secondLength=(second+"").length()<=1 ? (second+"").length()+1 : (second+"").length();
		float breakMarkX=endX-secondLength*numberWidth-breakMarkWidth;
		float breakMarkY=endY;
		canvas.drawBitmap(breakMarkBitmap, Constant.SCREEN_WIDTH/2-breakMarkBitmap.getWidth()/2,0,paint);//绘制时间分隔符图片

		//canvas.drawBitmap(breakMarkBitmap, breakMarkX+Constant.X_OFFSET, breakMarkY+Constant.Y_OFFSET,paint);//绘制时间分隔符图片
		//绘制分钟
		float miniteEndX=breakMarkX;
		float miniteEndY=endY;
		drawNumberBitmap(minute,numberBitmaps,Constant.SCREEN_WIDTH/2-breakMarkBitmap.getWidth()/2-numberBitmaps[0].getWidth()*2,0,canvas, paint);
		if(leftSecond==0){
	    if(gameViewOne!=null){
		gameViewOne.activity.sendMessage(Constant.GOTO_WIN_VIEW);
	    }
	    else if(gameViewTwo!=null){
			gameViewTwo.activity.sendMessage(Constant.GOTO_WIN_VIEW);
		    }
	    else if(gameViewThree!=null){
	    	gameViewThree.activity.sendMessage(Constant.GOTO_WIN_VIEW);
		    }
	    else if(gameViewFour!=null){
	    	gameViewFour.activity.sendMessage(Constant.GOTO_WIN_VIEW);
			}
	    else if(gameViewFive!=null){
	    	gameViewFive.activity.sendMessage(Constant.GOTO_WIN_VIEW);
			}
	    else if(gameViewSix!=null){
	    	gameViewSix.activity.sendMessage(Constant.GOTO_WIN_VIEW);
			}
		}
	}
	//减少时间的方法
	public void subtractTime(int second)
	{
		if(this.leftSecond>=1)
		{
			this.leftSecond-=second;
		}
	}
	//画数字图片的方法
	public void drawNumberBitmap(int number,Bitmap[] numberBitmaps,float endX,float endY,Canvas canvas,Paint paint)
	{
		String numberStr=number+"";
		if(number<10){//保证至少有两位
			numberStr="0"+numberStr;
		}
		for(int i=0;i<numberStr.length();i++)
		{
			char c=numberStr.charAt(i);
			canvas.drawBitmap
			(
					numberBitmaps[c-'0'], 
					endX-numberWidth*(numberStr.length()-i-2), 
					endY, 
					paint
			);
		}
	}
	public int getLeftSecond() {
		return leftSecond;
	}
}
