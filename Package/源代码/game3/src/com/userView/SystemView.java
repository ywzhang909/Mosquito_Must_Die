package com.userView;
import utility.PicScaleHelper;

import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
/**
 * 
 * ϵͳ���ý��棨���֡���Ч�����������ڣ�
 *
 */
public class SystemView extends SurfaceView implements SurfaceHolder.Callback{
	GameActivity activity;//activity������
	
	Paint paint;//��������
	//�߳�����
	DrawThread drawThread;//�����߳�����	
	//�����������ⰴťͼƬ
	Bitmap onBitmap;
	Bitmap offBitmap;
	//���ֵ�ͼƬ	
	private Bitmap yinyueOnBitmap;
	private Bitmap yinyueOffBitmap;
	private Bitmap yinxiaoOnBitmap;
	private Bitmap yinxiaoOffBitmap;
	//���˵������ⰴť��������
	SoundSwitchButton yinyueBtn;
	SoundSwitchButton yinxiaoBtn;	
	//���������ڰ�ťͼƬ
	private Bitmap helpbuttonBitmap;
	private Bitmap aboutbuttonitmap;
	
	private Bitmap backButton;
	
	//����ͼƬ
	Bitmap bgBitmap;
	private HelpView helpView;
	private AboutView aboutView;
	
	
	public SystemView(GameActivity activity) {
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
		canvas.drawColor(Color.GRAY);
		canvas.drawBitmap(bgBitmap, 0, 0, paint);
		//�������ⰴť
		yinyueBtn.drawSelf(canvas, paint);
		yinxiaoBtn.drawSelf(canvas, paint);
		canvas.drawBitmap(helpbuttonBitmap, Constant.SCREEN_WIDTH/2-helpbuttonBitmap.getWidth()/2, Constant.SCREEN_HEIGHT/5*3, paint);//��������
		canvas.drawBitmap(aboutbuttonitmap, Constant.SCREEN_WIDTH/2-helpbuttonBitmap.getWidth()/2,  Constant.SCREEN_HEIGHT/4*3, paint);//�������ⰴť
		canvas.drawBitmap(backButton, 0,  0, paint);//�������ⰴť

	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();		
    	switch(event.getAction())
    	{
    	case MotionEvent.ACTION_DOWN:
    		//��������ְ�ť��
    		if(yinyueBtn.isActionOnButtonOn(x, y))
    		{
    			yinyueBtn.switchOn();
    			activity.setBackGroundMusicOn(true);
    		}
    		else if(yinyueBtn.isActionOnButtonOff(x, y))
    		{
    			yinyueBtn.switchOff();
    			activity.setBackGroundMusicOn(false);
    		}
    		//�������Ч��ť��
    		else if(yinxiaoBtn.isActionOnButtonOn(x, y))
    		{
    			yinxiaoBtn.switchOn();
    			activity.setSoundOn(true);
    		}
    		else if(yinxiaoBtn.isActionOnButtonOff(x, y))
    		{
    			yinxiaoBtn.switchOff();
    			activity.setSoundOn(false);
    		}    	
    		else if(isActionOnhelpButton(helpbuttonBitmap,x, y))
    		{		
    			activity.sendMessage(Constant.GOTO_HELP_VIEW);
    		}    	
    		else if(isActionOnaboutButton(aboutbuttonitmap,x, y))
    		{		
    			activity.sendMessage(Constant.GOTO_ABOUT_VIEW);    		
    			} 
    		else if(isActionOnBackButton(backButton,x, y))
    		{		
    			activity.sendMessage(Constant.GOTO_MAIN_MENU_VIEW);    		
    			}    
    		break;	
    	}
    	
		return true;
	}
	
	public boolean isActionOnhelpButton(Bitmap button,float pointx,float pointy){
	
		return Constant.isPointInRect(pointx, pointy, Constant.SCREEN_WIDTH/2-helpbuttonBitmap.getWidth()/2, Constant.SCREEN_HEIGHT/5*3, button.getWidth(),button.getHeight());
	}
	
	public boolean isActionOnaboutButton(Bitmap button,float pointx,float pointy){

		return Constant.isPointInRect(pointx, pointy, Constant.SCREEN_WIDTH/2-helpbuttonBitmap.getWidth()/2,Constant.SCREEN_HEIGHT/4*3,button.getWidth(),button.getHeight());
	}
	
	public boolean isActionOnBackButton(Bitmap button,float pointx,float pointy){

		return Constant.isPointInRect(pointx, pointy, 0,0,button.getWidth(),button.getHeight());
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder){
		paint=new Paint();//��������
		paint.setAntiAlias(true);//�򿪿����	
		createAllThreads();//���������߳�
		initBitmap();//��ʼ��λͼ��Դ	
		//�������ⰴť����
		int btnX=(Constant.SCREEN_WIDTH-onBitmap.getWidth()-yinyueOnBitmap.getWidth())/2;
		yinyueBtn=new SoundSwitchButton(yinyueOnBitmap,yinyueOffBitmap,onBitmap,offBitmap,btnX,Constant.SOUND_BTN_Y1,activity.isBackGroundMusicOn());
		yinxiaoBtn=new SoundSwitchButton(yinxiaoOnBitmap,yinxiaoOffBitmap,onBitmap,offBitmap,btnX,Constant.SOUND_BTN_Y2,activity.isSoundOn());
		startAllThreads();//���������߳�
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		  boolean retry = true;
	        stopAllThreads();
	        while (retry) {
	            try {
	            	drawThread.join();
	                retry = false;
	            } 
	            catch (InterruptedException e) {e.printStackTrace();}//���ϵ�ѭ����ֱ��ˢ֡�߳̽���
	        }
	}
	//����ͼƬ�ķ���
	public void initBitmap(){
		onBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.on);
		offBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.off);
		yinyueOnBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.yinyuekai);
		yinyueOffBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.yinyueguan);
		yinxiaoOnBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.yinxiaokai);
		yinxiaoOffBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.yinxiaoguan);	
		bgBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.viewbackground);
		helpbuttonBitmap= BitmapFactory.decodeResource(this.getResources(), R.drawable.helpbutton);	
		aboutbuttonitmap= BitmapFactory.decodeResource(this.getResources(), R.drawable.aboutbutton);		
		backButton = BitmapFactory.decodeResource(this.getResources(), R.drawable.goback);		
				
		onBitmap=PicScaleHelper.scaleToFit(onBitmap, Constant.ssr.ratio);
		offBitmap=PicScaleHelper.scaleToFit(offBitmap, Constant.ssr.ratio);
		yinyueOnBitmap=PicScaleHelper.scaleToFit(yinyueOnBitmap, Constant.ssr.ratio);		
		yinyueOffBitmap=PicScaleHelper.scaleToFit(yinyueOffBitmap, Constant.ssr.ratio);
		yinxiaoOnBitmap=PicScaleHelper.scaleToFit(yinxiaoOnBitmap, Constant.ssr.ratio);		
		yinxiaoOffBitmap=PicScaleHelper.scaleToFit(yinxiaoOffBitmap, Constant.ssr.ratio);
		bgBitmap=PicScaleHelper.FullScreenScale(bgBitmap, Constant.wRatio, Constant.hRatio);
		helpbuttonBitmap=PicScaleHelper.scaleToFit(helpbuttonBitmap, Constant.ssr.ratio);
		backButton=PicScaleHelper.scaleToFit(backButton, Constant.ssr.ratio);
		aboutbuttonitmap=PicScaleHelper.scaleToFit(aboutbuttonitmap, Constant.ssr.ratio);
	}
	void createAllThreads()
	{
		drawThread=new DrawThread(this);//���������߳�		
	}
	void startAllThreads()
	{
		drawThread.setFlag(true);     
		drawThread.start();
	}
	void stopAllThreads()
	{
		drawThread.setFlag(false);       
	}
	private class DrawThread extends Thread{
		private boolean flag = true;	
		private int sleepSpan = 100;
		SystemView fatherView;
		SurfaceHolder surfaceHolder;
		public DrawThread(SystemView fatherView){
			this.fatherView = fatherView;
			this.surfaceHolder = fatherView.getHolder();
		}
		public void run(){
			Canvas c;
	        while (this.flag) {
	            c = null;
	            try {
	            	// ������������
	                c = this.surfaceHolder.lockCanvas(null);
	                synchronized (this.surfaceHolder) {
	                	fatherView.onDraw(c);//����
	                }
	            } finally {
	                if (c != null) {
	                	//���ͷ���
	                    this.surfaceHolder.unlockCanvasAndPost(c);
	                }
	            }
	            try{
	            	Thread.sleep(sleepSpan);//˯��ָ��������
	            }
	            catch(Exception e){
	            	e.printStackTrace();//��ӡ��ջ��Ϣ
	            }
	        }
		}
		public void setFlag(boolean flag) {
			this.flag = flag;
		}
	}
}
