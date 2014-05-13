package com.userView;
import utility.PicScaleHelper;

import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 
 *  ʤ������
 *
 */
public class WinView extends SurfaceView implements SurfaceHolder.Callback{
	GameActivity activity;//activity������
	Paint paint;//��������
	
	Bitmap bgBitmap;//����ͼƬ
	
	public WinView(GameActivity activity) {
		super(activity);
		this.activity=activity;
		//��ý��㲢����Ϊ�ɴ���
		this.requestFocus();
        this.setFocusableInTouchMode(true);
		getHolder().addCallback(this);//ע��ص��ӿ�		
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);  	
		//���Ʊ���
		canvas.drawBitmap(bgBitmap, 0, 0, paint);
			}
	//���»��Ƶķ���
    public void repaint()
	{
		Canvas canvas=this.getHolder().lockCanvas();
		try
		{
			synchronized(canvas)
			{
				onDraw(canvas);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if(canvas!=null)
			{
				this.getHolder().unlockCanvasAndPost(canvas);
			}
		}
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {		
	}
	@Override
	public void surfaceCreated(SurfaceHolder holder){
		paint=new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����	
		initBitmap();//��ʼ��λͼ��Դ				
		repaint();//���ƽ���
	}
	//�ж��û��Ƿ����˽�����һ�ذ�ť
		public boolean isActionOnEnterNextButton(float pointx,float pointy){
			
			return Constant.isPointInRect(pointx, pointy, Constant.SCREEN_WIDTH/8,Constant.SCREEN_HEIGHT/4, Constant.SCREEN_WIDTH/5,Constant.SCREEN_HEIGHT/8);
		}
	//�ж��û��Ƿ����˷������˵���ť
		public boolean isActionOnReturnManuButton(float pointx,float pointy){
					
			return Constant.isPointInRect(pointx, pointy,Constant.SCREEN_WIDTH/8,Constant.SCREEN_HEIGHT/2-Constant.SCREEN_HEIGHT/16, Constant.SCREEN_WIDTH/5,Constant.SCREEN_HEIGHT/8);
		}
	//�ж��û��Ƿ��������水ť
		public boolean isActionOnReplayButton(float pointx,float pointy){
					
			return Constant.isPointInRect(pointx, pointy,Constant.SCREEN_WIDTH/8,Constant.SCREEN_HEIGHT/2+Constant.SCREEN_HEIGHT/8, Constant.SCREEN_WIDTH/5,Constant.SCREEN_HEIGHT/8);
		}
		//��ť����
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			int x = (int) event.getX();
			int y = (int) event.getY();		
	    	switch(event.getAction())
	    	{
	    	case MotionEvent.ACTION_DOWN:
	 		if(isActionOnEnterNextButton( x, y)){
	 			if(activity.achievement==2)
		 			activity.sendMessage(Constant.GOTO_2);
	 			if(activity.achievement==3)
		 			activity.sendMessage(Constant.GOTO_3);
	 			if(activity.achievement==4)
		 			activity.sendMessage(Constant.GOTO_4);
	 			if(activity.achievement==5)
		 			activity.sendMessage(Constant.GOTO_5);
	 			if(activity.achievement==6)
		 			activity.sendMessage(Constant.GOTO_6);
//	 			if(activity.achievement==7)
//		 			activity.sendMessage(Constant.GOTO_7);
//	 			if(activity.achievement==8)
//		 			activity.sendMessage(Constant.GOTO_8);
//	 			if(activity.achievement==9)
//		 			activity.sendMessage(Constant.GOTO_9);
	 		}
	 		if(isActionOnReturnManuButton( x, y)){
	 			activity.sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
	 		}
	 		if(isActionOnReplayButton( x, y)){
	 			if(activity.achievement==2){
		 			activity.sendMessage(Constant. GOTO_1);
		 			activity.achievement--;
	 			}
	 			if(activity.achievement==3){
		 			activity.sendMessage(Constant.GOTO_2);
		 			activity.achievement--;
	 			}
	 			if(activity.achievement==4){
		 			activity.sendMessage(Constant.GOTO_3);
		 			activity.achievement--;

	 			}	 			
	 			if(activity.achievement==5){
		 			activity.sendMessage(Constant.GOTO_4);
		 			activity.achievement--;

	 			}
	 			if(activity.achievement==6){
		 			activity.sendMessage(Constant.GOTO_5);
		 			activity.achievement--;

	 			}
	 			
	 		}
	    	}
	    	return true;
		}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	//����ͼƬ�ķ���
	public void initBitmap(){
		
		bgBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.winbackground);
	    bgBitmap=PicScaleHelper.FullScreenScale(bgBitmap, Constant.wRatio, Constant.hRatio);
		
	}	
}
