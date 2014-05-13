package com.userView;
import utility.PicScaleHelper;

import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * 
 *  ��������
 *
 */
public class ExitView extends SurfaceView implements SurfaceHolder.Callback{
	GameActivity activity;//activity������
	Paint paint;//��������
	//����ͼƬ
	Bitmap bgBitmap;
	//����Yes��No��ť
//	Bitmap yesbutton;
//	Bitmap nobutton;
//	Bitmap bmp;//���ֵ�ͼƬ
	float bmpx;//����λ��	
	public ExitView(GameActivity activity) {
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
//		canvas.drawBitmap(yesbutton,( Constant.SCREEN_WIDTH/2-yesbutton.getWidth())/2, Constant.SCREEN_HEIGHT/2, paint);
//		canvas.drawBitmap(nobutton,Constant.SCREEN_WIDTH/2+( Constant.SCREEN_WIDTH/2-yesbutton.getWidth())/2, Constant.SCREEN_HEIGHT/2, paint);
//		canvas.drawBitmap(bmp, bmpx+Constant.X_OFFSET, Constant.SCREEN_HEIGHT/4, paint);
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
		//��ʼ��ͼƬ��λ��
//		bmpx=(Constant.SCREEN_WIDTH-bmp.getWidth())/2;
		
		repaint();//���ƽ���
	}
	//�ж��û��Ƿ�����YES��
		public boolean isActionOnYesButton(float pointx,float pointy){
			
			return Constant.isPointInRect(pointx, pointy,Constant.SCREEN_WIDTH/4,Constant.SCREEN_HEIGHT/2, Constant.SCREEN_WIDTH/4,Constant.SCREEN_WIDTH/4);
		}
		//�ж��û��Ƿ�����NO��
				public boolean isActionOnNoButton(float pointx,float pointy){
					
					return Constant.isPointInRect(pointx, pointy,Constant.SCREEN_WIDTH/2,Constant.SCREEN_HEIGHT/2, Constant.SCREEN_WIDTH/4,Constant.SCREEN_WIDTH/4);
				}
		//��ť����
		@Override
		public boolean onTouchEvent(MotionEvent event) {
			int x = (int) event.getX();
			int y = (int) event.getY();		
	    	switch(event.getAction())
	    	{
	    	case MotionEvent.ACTION_DOWN:
	 		if(isActionOnYesButton( x, y)){
	 			activity.sendMessage(Constant.EXIT);
	 		}
	 		if(isActionOnNoButton( x, y)){
	 			activity.sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
	 		}
	    	}
	    	return true;
		}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
	//����ͼƬ�ķ���
	public void initBitmap(){
//		yesbutton=BitmapFactory.decodeResource(this.getResources(), R.drawable.yesbutton);
//		nobutton=BitmapFactory.decodeResource(this.getResources(), R.drawable.nobutton);
		bgBitmap=BitmapFactory.decodeResource(this.getResources(), R.drawable.existbackground);
		bgBitmap=PicScaleHelper.FullScreenScale(bgBitmap, Constant.wRatio, Constant.hRatio);
//		bmp=PicScaleHelper.scaleToFit(bmp, Constant.ssr.ratio);
		
	}	
}
