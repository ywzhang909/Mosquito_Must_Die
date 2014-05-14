package com.userView;

import java.util.ArrayList;
import java.util.HashMap;

import mosquito.Mosquito;

import tool.ExpelIncense;
import tool.ExpelPlant;
import tool.Frog;
import tool.killspray;

import utility.PicScaleHelper;
import utility.TimeThread2;
import utility.TimeThread3;

import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

/**
 * 
 * @author ɽ����ѧ�Ա��������ء����������ں���
 * 
 */
public class GameViewThree extends SurfaceView implements Callback {

	public Paint paint;// ����
	GameActivity activity;// activity������
	public Handler mhandler;
	// ���ڻ���CD
	Path path;
	// ������ر���
	SoundPool soundPool;// ����
	HashMap<Integer, Integer> soundPoolMap;
	MediaPlayer mMediaPlayer;
	// �û����ص������
	int downX;
	int downY;
	boolean hasClickOnExpelPlant = false;
	boolean hasClickOnSpray = false;
	boolean hasClickOnIncense = false;
	
	boolean hasClickOnMap = false;
	boolean wantUpgrade = false;
	public int[][] map ;
	
	// boolean sureUpgrade = false;
	public boolean cdFlag1 = true;
	// ����CD�Ƕȵı�־λ
	public boolean cd1 = false;
	// ����CD����ı�־λ
	int initfrogx;
	int initfrogy;
	float change_x1 = initfrogx;
	float change_y1 = initfrogy;
	public int angle1 = 0;
	
	public boolean cdFlag2 = true;
	// ���ü�CD�Ƕȵı�־λ
	public boolean cd2 = false;
	// ���ü�CD����ı�־λ
	int initsprayx;
	int initsprayy;
	float change_x2 ;
	float change_y2 ;
	public int angle2 = 0;
	
	// ͼƬ���
	Bitmap[] fourPlant;
	Bitmap[] fourFrog;
	Bitmap bg;
	Bitmap cover;
	Bitmap[] numberBitmaps;// ʱ���ͼƬ
	Bitmap breakMarkBitmap;// ʱ������ͼƬ
	Bitmap jifen;// ����
	Bitmap shengji;// ������ť
	Bitmap toolFrog;// ����������ͼ��
	Bitmap toolExpelIncense;// ������������ͼ��
	Bitmap toolExpelPlant;// ���������ò�ͼ��
	Bitmap toolKillLamp;// ���������õ�ͼ��
	Bitmap toolkillspray;// ���������ü�ͼ��
	Bitmap toolSpiderWeb;// ������֩����ͼ��
	Bitmap toolSwatter;// ������������ͼ��
	public Timer timer;// ��ʱ��
	// ��ʱ�߳�
	TimeThread3 timethread;
	// ˢ֡�߳�
	GameviewDrawThread gameviewdrawThread;
	// ��������
	public ArrayList<Mosquito> Wlist;
	public ArrayList<ExpelPlant> expelPlantList;
	public ArrayList<killspray> sprayList;
	public ArrayList<ExpelIncense> incenseList;
	// public Bitmap[]number;
	int[] toolLevel = { 1,// frog
			1,// ExpelPlant
			1,// KillSpray
			1,// ExpelIncense
			1,// KillLamp
			1,// Swatted
			1 // SpiderWeb
	};

	int[] upgredeCost = { 100, 40, 
			100, 150, 100, 300, 150 };
	int score;// ��ǰ����=
	int numberOfEnermy;// ��������

	// ���Ŀǰ������ǵ�һ�����òݵ���
	int rankplant = 0;
	// ���Ŀǰ������ǵ�һ���������
	int rankincense = 8;
	
	public int step;// ����ͼƬ��־λ
	// ���ӳ������
	public boolean isBornWenzi = false;
	// ���ܳ���ʱ��
	public boolean FrogSteady = false;

	/**
	 * 
	 * @param activity
	 * @param numberOfEnermy�����ܹ�����ֵ���������
	 */
	public GameViewThree(GameActivity activity, int numberOfEnermy) {
		super(activity);
		this.activity = activity;
		// ��ý��㲢����Ϊ�ɴ���
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		getHolder().addCallback(this);// ע��ص��ӿ�
		this.score = activity.score;
		this.map = Constant.MAP3;
		this.numberOfEnermy = numberOfEnermy;
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = (int) event.getX();
			downY = (int) event.getY();
			if (Constant.isPointInRect(downX, downY, 0, 0,
					Constant.SCREEN_WIDTH,
					Constant.SCREEN_HEIGHT - jifen.getHeight())) {
				hasClickOnMap = true;
			}
			
			if (Constant.isPointInRect(downX, downY, toolFrog.getWidth()*2,
					Constant.SCREEN_HEIGHT - toolFrog.getHeight(),
					toolExpelPlant.getWidth(), toolExpelPlant.getWidth())) {
				hasClickOnExpelPlant = true;
			}
			if (Constant.isPointInRect(downX, downY, toolFrog.getWidth()*3,
					Constant.SCREEN_HEIGHT - toolFrog.getHeight(),
					toolExpelPlant.getWidth(), toolExpelPlant.getWidth())) {
				hasClickOnSpray = true;
			}
			
			if (Constant.isPointInRect(downX, downY, toolFrog.getWidth()*4,
					Constant.SCREEN_HEIGHT - toolFrog.getHeight(),
					toolExpelPlant.getWidth(), toolExpelPlant.getWidth())) {
				hasClickOnIncense = true;
			}
			if (Constant.isPointInRect(downX, downY, toolFrog.getWidth() * 8,
					Constant.SCREEN_HEIGHT - shengji.getHeight(),
					shengji.getWidth(), shengji.getHeight())) {
				wantUpgrade = true;
				reInitBitmap();
			}

			break;
		}
		if (rankplant == 6) {
			hasClickOnExpelPlant = false;
		}
		if (rankincense == 12) {
			hasClickOnIncense = false;
		}
		
		if (hasClickOnExpelPlant || hasClickOnSpray || hasClickOnIncense) {
			// �û�ѡ�������ò�
			if (wantUpgrade && hasClickOnExpelPlant && toolLevel[1] == 1
					&& score >= upgredeCost[1]) {
				toolLevel[1]++;
				score -= upgredeCost[1];
				initTool();				
				
				wantUpgrade = hasClickOnExpelPlant = false;
				
			}
			
			if (hasClickOnExpelPlant && hasClickOnMap) {
                Log.e("plant", ""+ toolLevel[1]);
				// ��ʾ���������δ��غ�,ʵ����һ�����õ��߶���
				ExpelPlant Driver = new ExpelPlant(this, toolLevel[1], downX, downY);
				// �ж����õ����Ƿ����·�ϣ���û����·�ϣ�����Ч
				if (Driver.haveRight(this.map)) {
					expelPlantList.add(Driver);
					// Ϊÿһ�����Ӹ��ĵ�ͼ
					map = Driver.effect(this.map);

					for (Mosquito m : Wlist) {
						if(m!=null)
						m.setmap(map);
					}
					rankplant++;
					// ������Ϣ
					timethread.mhandler.sendEmptyMessage(rankplant);
					hasClickOnExpelPlant = false;
					hasClickOnMap = false;
				} else {
					hasClickOnExpelPlant = false;
					hasClickOnMap = false;
				}

			}
			        // �û�ѡ��������
					if (wantUpgrade && hasClickOnIncense && toolLevel[3] == 1
								&& score >= upgredeCost[3]) {
							toolLevel[3]++;
							score -= upgredeCost[3];
							initTool();				
							
							wantUpgrade = hasClickOnIncense = false;
							
						}
						
						if (hasClickOnIncense && hasClickOnMap) {
			                Log.e("plant", ""+ toolLevel[1]);
							// ��ʾ���������δ��غ�,ʵ����һ�����õ��߶���
							ExpelIncense Driver = new ExpelIncense(this, toolLevel[3], downX, downY);
							// �ж����õ����Ƿ����·�ϣ���û����·�ϣ�����Ч
							if (Driver.haveRight3(this.map)) {
								incenseList.add(Driver);
								// Ϊÿһ�����Ӹ��ĵ�ͼ
								map = Driver.effect(this.map);

								for (Mosquito m : Wlist) {
									if(m!=null)
									m.setmap(map);
								}
								rankincense++;
								Log.e("rankxiang",""+rankincense);
								// ������Ϣ
								timethread.mhandler.sendEmptyMessage(rankincense);
								hasClickOnIncense = false;
								hasClickOnMap = false;
							} else {
								hasClickOnIncense = false;
								hasClickOnMap = false;
							}

						}			
			
			if (wantUpgrade && hasClickOnSpray && toolLevel[2] == 1
					&& score >= upgredeCost[2]) {
				toolLevel[2]++;
				score -= upgredeCost[2];
				initTool();							
				wantUpgrade = hasClickOnSpray = false;
				
			}
			// ѡ�������ü�����
			if (hasClickOnSpray && hasClickOnMap) {
				// �ж�cdʱ�䵽û��
				if (cdFlag2) {

					killspray spray = new killspray(this,toolLevel[2], downX, downY);
					if(spray.haveRight3()){
					sprayList.add(spray);

					// Ϊÿһֻ���Ӹ��ĵ�ͼ
					map = spray.effect(this.map);
					for (Mosquito m : Wlist) {
						if(m!=null)
						m.setmap(map);
					}
					timethread.mhandler.sendEmptyMessage(7);
					timethread.mhandler.sendEmptyMessage(8);
					// ��ʾ���������δ��غ�,ʵ����һ�����õ��߶���
					hasClickOnSpray = false;
					hasClickOnMap = false;
					cdFlag2 = false;
					}else{
						hasClickOnSpray = false;
						hasClickOnMap = false;
					}
				} else {
					hasClickOnSpray = false;
				}
			}
			
			
		} else {
			// �û�û��ѡ�иõ���
			downX = 0;
			downY = 0;
			hasClickOnMap = false;
		}
		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		score=activity.getCurrent();

		initSounds();// ��ʼ��������Դ
		// ��ʼ����ֻ����
		Wlist = new ArrayList<Mosquito>();
		Wlist.add(createWenzi1(1, 1));
		Wlist.add(createWenzi2(1, 1));
		Wlist.add(createWenzi3(1, 1));
		// ��ʼ��ʢ�ŵ��ߵ�����
		expelPlantList = new ArrayList<ExpelPlant>();
		incenseList= new ArrayList<ExpelIncense>();
		sprayList = new ArrayList<killspray>();
		
		// ��ʼ����������
		mMediaPlayer = MediaPlayer.create(activity, R.raw.backsound);
		mMediaPlayer.setLooping(true);
		if (activity.isBackGroundMusicOn())// ������������
		{
			mMediaPlayer.start();
		}
		// ���ʳ�ʼ��
		paint = new Paint();
		initBitmap();

		timer = new Timer(this, breakMarkBitmap, numberBitmaps);// ������ʱ������
		paint.setAntiAlias(true);
		// �и��ʼ��
		path = new Path();
		// ��ʱ�߳̿���
		createAllThread();
		startAllThread();
		timethread.setFlag(true);
		mhandler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					// ���²��������Ӽ�������б�
					Wlist.add(createWenzi1(1, 1));
					Wlist.add(createWenzi2(1, 1));
					break;
				case 1:
					// Ϊÿһֻ���ӻָ���ͼ
					for (Mosquito m : Wlist) {
						map = expelPlantList.get(0).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					expelPlantList.set(0, null);

					break;
				case 2:
					// Ϊÿһֻ���ӻָ���ͼ
					for (Mosquito m : Wlist) {
						map = expelPlantList.get(1).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					expelPlantList.set(1, null);
					break;
				case 3:
					// Ϊÿһֻ���ӻָ���ͼ
					for (Mosquito m : Wlist) {
						map = expelPlantList.get(2).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					expelPlantList.set(2, null);
					break;
				case 4:
					// Ϊÿһֻ���ӻָ���ͼ
					for (Mosquito m : Wlist) {
						map = expelPlantList.get(3).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					expelPlantList.set(3, null);
					break;
				case 5:
					// Ϊÿһֻ���ӻָ���ͼ
					for (Mosquito m : Wlist) {
						map = expelPlantList.get(4).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					expelPlantList.set(4, null);
					break;
				case 6:
					// Ϊÿһֻ���ӻָ���ͼ
					for (Mosquito m : Wlist) {
						map = expelPlantList.get(5).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					expelPlantList.set(5, null);
					break;

				// ���ü�����ʱ��
				case 7:
					for (Mosquito m : Wlist) {
						map = sprayList.get(0).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					sprayList.set(0, null);
					sprayList.remove(0);
					break;
					
				//��������
				case 8:
					// Ϊÿһֻ���ӻָ���ͼ
					for (Mosquito m : Wlist) {
						map = incenseList.get(0).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					incenseList.set(0, null);
					break;
				case 9:
					// Ϊÿһֻ���ӻָ���ͼ
					for (Mosquito m : Wlist) {
						map = incenseList.get(1).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					incenseList.set(1, null);
					break;
				case 10:
					// Ϊÿһֻ���ӻָ���ͼ
					for (Mosquito m : Wlist) {
						map = incenseList.get(2).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					incenseList.set(2, null);
					break;
				case 11:
					// Ϊÿһֻ���ӻָ���ͼ
					for (Mosquito m : Wlist) {
						map = incenseList.get(3).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					incenseList.set(3, null);
					break;

				}
			}
		};
		initfrogx = toolFrog.getWidth();
		initfrogy = Constant.SCREEN_HEIGHT - toolFrog.getHeight();
		change_x1 = initfrogx;
		change_y1 = initfrogy;
		
		initsprayx = toolFrog.getWidth()+toolFrog.getWidth()*2;
		initsprayy = Constant.SCREEN_HEIGHT - toolkillspray.getHeight();
		change_x1 = initsprayx;
		change_y1 = initsprayy;
		
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		stopAllThreads();
		while (retry) {// ���ϵ�ѭ����ֱ�������߳̽���
			joinAllThreads();
			retry = false;
		}
		// ֹͣ��������
		if (mMediaPlayer.isPlaying()) {
			mMediaPlayer.stop();
		}
		activity.score = this.score;
		activity.trackGrade(score);
		activity.updateGrade();
		
		for (Mosquito m : Wlist){
			Wlist.set(Wlist.indexOf(m), null);
		}
		toolLevel = new int[] { 1, 1, 1, 1, 1, 1, 1 };
		numberOfEnermy=5;
		
		angle1=0;
		cd1=false;
		cdFlag1=true;
		
		angle2=0;
		cd2=false;
		cdFlag2=true;
		
		int[] i=activity.currentGrade();
		score=0;
		rankplant=0;
		rankincense=8;

		
		for(int n=0;n<Constant.MAP2.length;n++)
		   for(int x=0;x<Constant.MAP2[0].length;x++){
			   if(map[n][x]==-1)
			      map[n][x]=1;
		   }


	}

	// ������Ϸ�ķ���
	public void overGame() {
		stopAllThreads();
		// ֹͣ��������
		if (mMediaPlayer.isPlaying()) {
			mMediaPlayer.stop();
		}
	}

	private void stopAllThreads() {
		// �ر������߳�
		timethread.setToDate(false);
		gameviewdrawThread.setFlag(false);
	}

	public void joinAllThreads() {

	}

	// ��ʼ�������ķ���
	public void initSounds() {
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		soundPoolMap = new HashMap<Integer, Integer>();

	}

	// ���������ķ���
	public void playSound(int sound, int loop) {
		AudioManager mgr = (AudioManager) getContext().getSystemService(
				Context.AUDIO_SERVICE);
		float streamVolumeCurrent = mgr
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float streamVolumeMax = mgr
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		float volume = streamVolumeCurrent / streamVolumeMax;
		soundPool.play(soundPoolMap.get(sound), volume, volume, 1, loop, 1f);
	}

	// ����ͼƬ�ķ���
	public void initBitmap() {

		// ���ؼ�ʱͼƬ
		numberBitmaps = new Bitmap[] {
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number0),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number1),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number2),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number3),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number4),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number5),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number6),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number7),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number8),
				BitmapFactory.decodeResource(this.getResources(),
						R.drawable.number9) };

		breakMarkBitmap = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.breakmark);
		jifen = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.jifen);// ����

		if(score >= upgredeCost[1]||score >= upgredeCost[2]||score >= upgredeCost[3])
		shengji = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.shengji);// ������ť
		else
		shengji = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.unshengji);// ������ť
	
		initTool();

		bg = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.playground3);
		bg = PicScaleHelper.FullScreenScale(bg, Constant.wRatio,
				Constant.hRatio);
		cover = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.cover);
		cover = PicScaleHelper.scaleToFit(cover, Constant.ssr.ratio);
	}

	private void initTool() {
		switch (toolLevel[0]) {
		case 1:
			toolFrog = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.toolfrog3);// ����������ͼ��
			break;
		default:
			toolFrog = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.toolfrog3);
		}
		
		switch (toolLevel[1]) {
		case 1:
			 toolExpelPlant = BitmapFactory.decodeResource(
					this.getResources(), R.drawable.toolexpelplant);// ���������ò�ͼ��
			
			break;
		default:
			
					 toolExpelPlant = BitmapFactory.decodeResource(
						this.getResources(), R.drawable.toolexpelplant2);// ���������ò�ͼ��
				
		}
		
		switch (toolLevel[2]) {
		case 1:
			toolkillspray = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.toolkillspray);// ���������ü�ͼ��
			break;
		default:
			toolkillspray = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.toolkillspray2);// ���������ü�ͼ��
		}
		switch (toolLevel[3]) {
		case 1:
			 toolExpelIncense= BitmapFactory.decodeResource(this.getResources(),
						R.drawable.toolexpelincense);
			
			break;
		default:
	         toolExpelIncense= BitmapFactory.decodeResource(this.getResources(),
						R.drawable.toolexpelincense2);
		}
		switch (toolLevel[4]) {
		case 1:
			toolKillLamp = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolkilllamp);// ������������ͼ��
			break;
		default:
			toolKillLamp = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolkilllamp);// ���������ü�ͼ��
		}		
		switch (toolLevel[5]) {
		case 1:
			toolSwatter = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolswatter);// ������������ͼ��
			break;
		default:
			toolSwatter = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolswatter);// ������������ͼ��
		}
		switch (toolLevel[6]) {
		case 1:
			toolSpiderWeb = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolspiderweb);// ������֩����ͼ��
			break;
		default:
			toolSpiderWeb = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolspiderweb);// ������֩����ͼ��
		}
	}

	private void reInitBitmap() {
			
		if (score >= upgredeCost[1] && toolLevel[1] < 2)
			toolExpelPlant = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.toolexpelplant_can);
		if (score >= upgredeCost[2] && toolLevel[2] < 2)
			toolkillspray = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.toolkillspray_can);
		if (score >= upgredeCost[3] && toolLevel[3] < 2)
			toolExpelIncense = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.toolexpelincense_can);
	}

	
	// �������ӵķ���
	public Mosquito createWenzi1(int amout, int level) {
		Mosquito m1 = null;
		for (int i = 0; i < amout; i++) {
			m1 = new Mosquito(level, 1, 1, this);
			// numberOfEnermy++;
			m1.setEnd(18, 5);
			m1.setSpeed(true);
			//m1.setHelmet(true);
		}
		return m1;
	}

	// �������ӵķ���
	public Mosquito createWenzi2(int amout, int level) {
		Mosquito m2 = null;
		for (int i = 0; i < amout; i++) {
			m2 = new Mosquito(level, 1, 8, this);
			// numberOfEnermy++;
			m2.setEnd(18, 5);
		}
		return m2;
	}
	
	// �������ӵķ���
		public Mosquito createWenzi3(int amout, int level) {
			Mosquito m2 = null;
			for (int i = 0; i < amout; i++) {
				m2 = new Mosquito(level, 1, 5, this);
				// numberOfEnermy++;
				m2.setEnd(18, 5);
			}
			return m2;
		}
		
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		// ���Ʊ���
		canvas.drawColor(Color.GRAY);
		canvas.drawBitmap(bg, 0, 0, paint);
		// ����ʱ��
		timer.drawSelf(canvas, paint);
		// ����
		canvas.drawBitmap(jifen, 0, 0, paint);
		int a = score / 1000;
		int b = (score - a * 1000) / 100;
		int c = (score - a * 1000 - b * 100)/10;
        int d = score- a * 1000 - b * 100-c * 10; 
		canvas.drawBitmap(numberBitmaps[a], jifen.getWidth(), 0, paint);
		canvas.drawBitmap(numberBitmaps[b], jifen.getWidth()+numberBitmaps[0].getWidth(), 0,
				paint);
		canvas.drawBitmap(numberBitmaps[c],
				jifen.getWidth()+numberBitmaps[0].getWidth()*2, 0, paint);
		canvas.drawBitmap(numberBitmaps[d],
				jifen.getWidth()+3 * numberBitmaps[0].getWidth(), 0, paint);
		// ���Ƶ�����
		// canvas.drawBitmap(jifen, 0, Constant.SCREEN_HEIGHT -
		// jifen.getHeight(),
		// paint);
		canvas.drawBitmap(toolFrog, toolFrog.getWidth(), 
				Constant.SCREEN_HEIGHT- toolFrog.getHeight(), paint);
		
		canvas.drawBitmap(toolExpelPlant,toolFrog.getWidth()*2, 
				Constant.SCREEN_HEIGHT- toolFrog.getHeight(), paint);
		
		canvas.drawBitmap(toolkillspray,toolFrog.getWidth()*3,
			    Constant.SCREEN_HEIGHT- toolFrog.getHeight(), paint);
		
		canvas.drawBitmap(toolExpelIncense,toolFrog.getWidth()*4,
				Constant.SCREEN_HEIGHT- toolFrog.getHeight(), paint);
		
		canvas.drawBitmap(toolKillLamp,toolFrog.getWidth()*5,
				Constant.SCREEN_HEIGHT- toolFrog.getHeight(), paint);
		
		canvas.drawBitmap(toolSwatter,toolFrog.getWidth()*7,
				Constant.SCREEN_HEIGHT- toolFrog.getHeight(), paint);
		
		canvas.drawBitmap(toolSpiderWeb,toolFrog.getWidth()*6,
				Constant.SCREEN_HEIGHT- toolFrog.getHeight(), paint);
		
		canvas.drawBitmap(shengji,toolFrog.getWidth()*8,
				Constant.SCREEN_HEIGHT- shengji.getHeight(), paint);
		
		

		// ��������
		for (Mosquito m : Wlist) {
			if (m != null) {
				if (m.state == 2) {
					activity.sendMessage(Constant.GOTO_FAIL_VIEW);
				}
				if (m.state == -1) {
					score += m.score;
					numberOfEnermy--;
					Wlist.set(Wlist.indexOf(m), null);
					if (numberOfEnermy == 0)
						activity.sendMessage(Constant.GOTO_WIN_VIEW);
				} else
					m.drawself(canvas, m.locX, m.locY, paint, m);
			}			
		}
		// �ڵ�ͼ�ϻ��Ƶ���
		for (ExpelPlant e : expelPlantList) {
			if (e != null)
				e.drawSelf(canvas, paint);

		}
		for (ExpelIncense i : incenseList) {
			if (i != null)
			i.drawSelf( canvas, paint);
		}
        for (killspray s : sprayList){
        	if (s != null) 
        	s.drawSelf( canvas, paint);
        }
        
		if (cd1) {
			canvas.save();
			canvas.clipPath(makePathDash1(change_x1, change_y1));
			canvas.drawBitmap(cover, initfrogx, initfrogy, null);
			canvas.restore();
		}
		if (cd2) {
			canvas.save();
			canvas.clipPath(makePathDash2(change_x2, change_y2));
			canvas.drawBitmap(cover, initsprayx, initsprayy, null);
			canvas.restore();
		}
		
	}

	// �������ڻ������ߵĵ�Ԫ·���ķ���
	private Path makePathDash1(float x6, float y6) {
		Path p = new Path();
		// ����δӾ����м�㿪ʼ˳ʱ��
		float x1 = initfrogx + toolFrog.getWidth() / 2;
		float y1 = initfrogy + toolFrog.getWidth()  / 2;

		float x2 = initfrogx + toolFrog.getWidth() ;
		float y2 = initfrogy;

		float x3 = initfrogx + toolFrog.getWidth() ;
		float y3 = initfrogy + toolFrog.getWidth() ;

		float x4 = initfrogx;
		float y4 = initfrogy + toolFrog.getWidth() ;

		float x5 = initfrogx;
		float y5 = initfrogy;
		if (y6 == initfrogy) {
			p.moveTo(x1, y1);
			p.lineTo(x6, y6);
			p.lineTo(x2, y2);
			p.lineTo(x3, y3);
			p.lineTo(x4, y4);
			p.lineTo(x5, y5);
			p.lineTo(x1, y1);
			return p;
		} else if (x6 == initfrogx + toolFrog.getWidth() ) {
			p.moveTo(x1, y1);
			p.lineTo(x6, y6);
			p.lineTo(x3, y3);
			p.lineTo(x4, y4);
			p.lineTo(x5, y5);
			p.lineTo(x1, y1);
			return p;
		} else if (y6 == initfrogy + toolFrog.getWidth() ) {
			p.moveTo(x1, y1);
			p.lineTo(x6, y6);
			p.lineTo(x4, y4);
			p.lineTo(x5, y5);
			p.lineTo(x1, y1);
			return p;
		} else if (x6 == initfrogx) {
			p.moveTo(x1, y1);
			p.lineTo(x6, y6);
			p.lineTo(x5, y5);
			p.lineTo(x1, y1);
			return p;
		}
		// return p;
		return null;
	}
	// �������ڻ������ߵĵ�Ԫ·���ķ���
		private Path makePathDash2(float x6, float y6) {
			Path p = new Path();
			// ����δӾ����м�㿪ʼ˳ʱ��
			float x1 = initsprayx + toolFrog.getWidth() / 2;
			float y1 = initsprayy + toolFrog.getWidth()  / 2;

			float x2 = initsprayx + toolFrog.getWidth() ;
			float y2 = initsprayy;

			float x3 = initsprayx + toolFrog.getWidth() ;
			float y3 = initsprayy + toolFrog.getWidth() ;

			float x4 = initsprayx;
			float y4 = initsprayy + toolFrog.getWidth() ;

			float x5 = initsprayx;
			float y5 = initsprayy;
			if (y6 == initsprayy) {
				p.moveTo(x1, y1);
				p.lineTo(x6, y6);
				p.lineTo(x2, y2);
				p.lineTo(x3, y3);
				p.lineTo(x4, y4);
				p.lineTo(x5, y5);
				p.lineTo(x1, y1);
				return p;
			} else if (x6 == initsprayx + toolFrog.getWidth() ) {
				p.moveTo(x1, y1);
				p.lineTo(x6, y6);
				p.lineTo(x3, y3);
				p.lineTo(x4, y4);
				p.lineTo(x5, y5);
				p.lineTo(x1, y1);
				return p;
			} else if (y6 == initsprayy + toolFrog.getWidth() ) {
				p.moveTo(x1, y1);
				p.lineTo(x6, y6);
				p.lineTo(x4, y4);
				p.lineTo(x5, y5);
				p.lineTo(x1, y1);
				return p;
			} else if (x6 == initsprayx) {
				p.moveTo(x1, y1);
				p.lineTo(x6, y6);
				p.lineTo(x5, y5);
				p.lineTo(x1, y1);
				return p;
			}
			// return p;
			return null;
		}

	// ˮ������ı仯����ͨ������ı仯�õ��仯������
	public void changeShuijingXY1(int angle) {
		if (angle >= 0 && angle < 90) {
			change_x1 = (float) (initfrogx + toolFrog.getWidth()  / 2 - toolFrog.getWidth()  / 2 * Math.tan((45 - angle)
					* Math.PI / 180));
			change_y1 = initfrogy;
		} else if (angle >= 90 && angle < 180) {
			change_x1 = initfrogx + toolFrog.getWidth() ;
			change_y1 = (float) (initfrogy + toolFrog.getWidth()  / 2 - toolFrog.getWidth()  / 2 * Math
					.tan((135 - angle) * Math.PI / 180));
		} else if (angle >= 180 && angle < 270) {
			change_x1 = (float) (initfrogx + toolFrog.getWidth()  / 2 + toolFrog.getWidth()  / 2 * Math
					.tan((225 - angle) * Math.PI / 180));
			change_y1 = initfrogy + toolFrog.getWidth() ;
		} else if (angle >= 270 && angle <= 360) {
			change_x1 = initfrogx;
			change_y1 = (float) (initfrogy + toolFrog.getWidth()  / 2 + toolFrog.getWidth()  / 2 * Math
					.tan((315 - angle) * Math.PI / 180));
		}

	}
	// ˮ������ı仯����ͨ������ı仯�õ��仯������
		public void changeShuijingXY2(int angle) {
			if (angle >= 0 && angle < 90) {
				change_x2 = (float) (initsprayx + toolFrog.getWidth()  / 2 - toolFrog.getWidth()  / 2 * Math.tan((45 - angle)
						* Math.PI / 180));
				change_y2 = initsprayy;
			} else if (angle >= 90 && angle < 180) {
				change_x2 = initsprayx + toolFrog.getWidth() ;
				change_y2 = (float) (initsprayy + toolFrog.getWidth()  / 2 - toolFrog.getWidth()  / 2 * Math
						.tan((135 - angle) * Math.PI / 180));
			} else if (angle >= 180 && angle < 270) {
				change_x2 = (float) (initsprayx + toolFrog.getWidth()  / 2 + toolFrog.getWidth()  / 2 * Math
						.tan((225 - angle) * Math.PI / 180));
				change_y2 = initsprayy + toolFrog.getWidth() ;
			} else if (angle >= 270 && angle <= 360) {
				change_x2 = initsprayx;
				change_y2 = (float) (initsprayy + toolFrog.getWidth()  / 2 + toolFrog.getWidth()  / 2 * Math
						.tan((315 - angle) * Math.PI / 180));
			}

		}
	// ���������̵߳ķ���
	public void createAllThread() {
		timethread = new TimeThread3(this);
		gameviewdrawThread = new GameviewDrawThread(this);
	}

	// �������̵߳ķ���
	public void startAllThread() {
		timethread.start();
		gameviewdrawThread.start();
	}

	
}
