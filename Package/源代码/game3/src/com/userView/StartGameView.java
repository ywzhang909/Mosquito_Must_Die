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
 *  ��������
 *
 */
public class StartGameView extends SurfaceView implements SurfaceHolder.Callback{
	GameActivity activity;//activity������
	Paint paint;//��������
	//����ͼƬ
	Bitmap bgBitmap;
	//���Ƶ���ģʽ��˫��ģʽ��ť
	Bitmap solobutton;
	Bitmap doublebutton;
	Bitmap backbutton;
	public StartGameView(GameActivity activity) {
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
//		canvas.drawBitmap(solobutton,( Constant.SCREEN_WIDTH/2-solobutton.getWidth())*2/3, Constant.SCREEN_HEIGHT/2, paint);
//		canvas.drawBitmap(doublebutton,Constant.SCREEN_WIDTH/2+doublebutton.getWidth()/2, Constant.SCREEN_HEIGHT/2, paint);
		canvas.drawBitmap(backbutton, 0,0, paint);
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

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	//����ͼƬ�ķ���
	public void initBitmap(){
//		solobutton=BitmapFactory.decodeResource(this.getResources(), R.drawable.buttonsolo);
//		doublebutton=BitmapFactory.decodeResource(this.getResources(), R.drawable.buttondouble);
		bgBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.choosestyle);
		backbutton=BitmapFactory.decodeResource(this.getResources(), R.drawable.goback);	
		bgBitmap=PicScaleHelper.FullScreenScale(bgBitmap, Constant.wRatio, Constant.hRatio);
		backbutton=PicScaleHelper.scaleToFit(backbutton, Constant.ssr.ratio);
//		solobutton=PicScaleHelper.scaleToFit(solobutton, Constant.ssr.ratio);
//		doublebutton=PicScaleHelper.scaleToFit(doublebutton, Constant.ssr.ratio);

	}	
	//�ж��û��Ƿ����˷��ؼ�
	public boolean isActionOnbackButton(Bitmap button,float pointx,float pointy){
		
		return Constant.isPointInRect(pointx, pointy, 0, 0, button.getWidth(),button.getHeight());
	}
	//�ж��û��Ƿ����˵���ģʽ
		public boolean isActionOnSingleButton(Bitmap button,float pointx,float pointy){
			
			return Constant.isPointInRect(pointx, pointy, Constant.SCREEN_WIDTH/4,Constant.SCREEN_HEIGHT/2, Constant.SCREEN_WIDTH/4,Constant.SCREEN_WIDTH/4);
		}
	//��ť����
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();		
    	switch(event.getAction())
    	{
    	case MotionEvent.ACTION_DOWN:
 		if(isActionOnbackButton(backbutton, x, y)){
 			activity.sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
 		}
 		//������ѡ����ģʽ�Ͷ���ģʽ�ļ���
	else if(isActionOnSingleButton(solobutton,x,y)){
			activity.sendMessage(Constant.GOTO_CHOOSE_VIEW);
		}
//ѡ���˶���ģʽ
	else if(Constant.isPointInRect(x, y, Constant.SCREEN_WIDTH/2, Constant.SCREEN_HEIGHT/2, Constant.SCREEN_WIDTH/4,Constant.SCREEN_WIDTH/4)){
		activity.sendMessage(Constant.GOTO_COOP_VIEW);
	}
 		}
		return true;
		
	}
}
