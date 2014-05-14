package com.userView;

import java.util.ArrayList;
import java.util.HashMap;

import mosquito.Mosquito;

import tool.ExpelPlant;
import tool.Frog;

import utility.PicScaleHelper;
import utility.TimeThread;

import com.publicClass.Constant;
import com.publicClass.GameActivity;
import com.publicClass.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region.Op;
import android.graphics.drawable.Drawable;
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
public class GameViewOne extends SurfaceView implements Callback {

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
	boolean hasClickOnFrog = false;
	boolean hasClickOnMap = false;
	boolean wantUpgrade = false;
	// boolean sureUpgrade = false;
	public boolean cdFlag = true;
	public int[][] map = Constant.MAP1; ;
	// �Ƕȵı�־λ
	public boolean cd = false;
	// ����ı�־λ
	int initx;
	int inity;
	float change_x = initx;
	float change_y = inity;
	public int angle = 0;
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
	TimeThread timethread;
	// ˢ֡�߳�
	GameviewDrawThread gameviewdrawThread;
	// ��������
	public ArrayList<Mosquito> Wlist;
	public ArrayList<ExpelPlant> expelPlantList;
	public ArrayList<Frog> frogList;

	// public Bitmap[]number;
	int[] toolLevel = { 1,// frog
			1,// ExpelPlant
			1,// KillSpray
			1,// ExpelIncense
			1,// KillLamp
			1, // SpiderWeb
			1// Swatted
	};

	int[] upgredeCost = { 100, 50, 
			100, 150, 100, 300, 150 };
	int score;// ��ǰ����=
	int numberOfEnermy;// ��������

	// ���Ŀǰ������ǵ�һ�����õ���
	int rank = 0;
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
	public GameViewOne(GameActivity activity, int numberOfEnermy) {
		super(activity);
		this.activity = activity;
		// ��ý��㲢����Ϊ�ɴ���
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		getHolder().addCallback(this);// ע��ص��ӿ�
		this.score = activity.score;
		this.map = Constant.MAP1;
		this.numberOfEnermy = numberOfEnermy;
	}

	// public synchronized void setMap(int [][]map){
	// this.map=map;
	// }

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
			if (Constant.isPointInRect(downX, downY, toolFrog.getWidth(),
					Constant.SCREEN_HEIGHT - toolFrog.getHeight(),
					toolFrog.getWidth(), toolFrog.getWidth())) {
				hasClickOnFrog = true;
			}
			if (Constant.isPointInRect(downX, downY, toolFrog.getWidth()*2,
					Constant.SCREEN_HEIGHT - toolFrog.getHeight(),
					toolExpelPlant.getWidth(), toolExpelPlant.getWidth())) {
				hasClickOnExpelPlant = true;
			}
			if (Constant.isPointInRect(downX, downY, toolFrog.getWidth() * 8,
					Constant.SCREEN_HEIGHT - shengji.getHeight(),
					shengji.getWidth(), shengji.getHeight())) {
				wantUpgrade = true;
				reInitBitmap();
			}

			break;
		}
		if (rank == 6) {
			hasClickOnExpelPlant = false;
		}
		if (hasClickOnExpelPlant || hasClickOnFrog) {
			// �û�ѡ�������ò�
			if (wantUpgrade && hasClickOnExpelPlant && toolLevel[1] == 1
					&& score >= upgredeCost[1]) {
				toolLevel[1]++;
				score -= upgredeCost[1];
				initTool();				
				
				wantUpgrade = hasClickOnExpelPlant = false;
				
			}
			
			if (hasClickOnExpelPlant && hasClickOnMap) {
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
					rank++;
					// ������Ϣ
					timethread.mhandler.sendEmptyMessage(rank);
					hasClickOnExpelPlant = false;
					hasClickOnMap = false;
				} else {
					hasClickOnExpelPlant = false;
					hasClickOnMap = false;
				}

			}
			// ѡ�������ܵ���
			if (hasClickOnFrog && hasClickOnMap) {
				// �ж�cdʱ�䵽û��
				if (cdFlag) {

					Frog killers = new Frog(this, downX, downY);
					
					if(killers.haveRight1()){
						frogList.add(killers);

					// Ϊÿһֻ���Ӹ��ĵ�ͼ
					map = killers.effect(this.map);
					for (Mosquito m : Wlist) {
						if(m!=null)
						m.setmap(map);
					}
					timethread.mhandler.sendEmptyMessage(7);
					timethread.mhandler.sendEmptyMessage(8);
					// ��ʾ���������δ��غ�,ʵ����һ�����õ��߶���
					hasClickOnFrog = false;
					hasClickOnMap = false;
					cdFlag = false;
					}else{
						hasClickOnFrog = false;
						hasClickOnMap = false;
					}
					
				} else {
					hasClickOnFrog = false;
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
		Wlist.add(createWenzi2(1, 1));
		// ��ʼ��ʢ�ŵ��ߵ�����
		expelPlantList = new ArrayList<ExpelPlant>();
		frogList = new ArrayList<Frog>();
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

				// cdʱ��
				case 7:
					cdFlag = true;
					break;
				// ���ܳ���ʱ��
				case 8:
					for (Mosquito m : Wlist) {
						map = frogList.get(0).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					frogList.set(0, null);
					frogList.remove(0);
					break;

				}
			}
		};
		initx = toolFrog.getWidth();
		inity = Constant.SCREEN_HEIGHT - toolFrog.getHeight();
		change_x = initx;
		change_y = inity;
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
		numberOfEnermy=3;
		angle=0;
		cd=false;
		cdFlag=true;
		int[] i=activity.currentGrade();
		score=0;
		rank=0;

//		for(int ii=0;ii<12;ii++){
//			for(int j=0;j<20;j++){
//				Log.i("mapsame", Constant.MAP2[ii][j]+"");
//				if(Constant.MAP2[ii][j]==-1){
//					Constant.MAP2[ii][j]=1;
//				}
//			}
//		}
		
		for(int n=0;n<Constant.MAP1.length;n++)
		   for(int x=0;x<Constant.MAP1[0].length;x++){
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
		
		
		shengji = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.shengji);// ������ť
		
			
		initTool();

		bg = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.playground1);
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
					R.drawable.toolfrog);// ����������ͼ��
			break;
		default:
			toolFrog = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.toolfrog);
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
					R.drawable.untoolkillspray);// ���������ü�ͼ��
			break;
		default:
			toolkillspray = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolkillspray);// ���������ü�ͼ��
		}
		switch (toolLevel[3]) {
		case 1:
			toolExpelIncense= BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolexpelincense);// ���������õ�ͼ��
			break;
		default:
			toolExpelIncense = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolexpelincense);// ���������õ�ͼ��
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
//		if (score >= upgredeCost[0] && toolLevel[0] < 2)
//			toolFrog = BitmapFactory.decodeResource(this.getResources(),
//					R.drawable.toolfrog_can);		
		if (score >= upgredeCost[1] && toolLevel[1] < 2)
			toolExpelPlant = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.toolexpelplant_can);
		
	}

	// �������ӵķ���
	public Mosquito createWenzi1(int amout, int level) {
		Mosquito m1 = null;
		for (int i = 0; i < amout; i++) {
			m1 = new Mosquito(level, 1, 1, this);
			// numberOfEnermy++;
			m1.setEnd(18, 1);
			m1.setSpeed(true);
//			m1.setHelmet(true);
		}
		return m1;
	}

	// �������ӵķ���
	public Mosquito createWenzi2(int amout, int level) {
		Mosquito m2 = null;
		for (int i = 0; i < amout; i++) {
			m2 = new Mosquito(level, 1, 6, this);
			// numberOfEnermy++;
			m2.setEnd(18, 1);
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
		
		canvas.drawBitmap(toolSpiderWeb,toolFrog.getWidth()*6,
				Constant.SCREEN_HEIGHT- toolFrog.getHeight(), paint);
		
		canvas.drawBitmap(toolSwatter,toolFrog.getWidth()*7,
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
		for (Frog k : frogList) {
			k.drawSelf(step, canvas, paint);
		}

		if (cd) {
			canvas.save();
			canvas.clipPath(makePathDash(change_x, change_y));
			canvas.drawBitmap(cover, initx, inity, null);
			canvas.restore();
		}
	}

	// �������ڻ������ߵĵ�Ԫ·���ķ���
	private Path makePathDash(float x6, float y6) {
		Path p = new Path();
		// ����δӵ����м�㿪ʼ˳ʱ��
		float x1 = initx + toolFrog.getWidth() / 2;
		float y1 = inity + toolFrog.getWidth()  / 2;

		float x2 = initx + toolFrog.getWidth() ;
		float y2 = inity;

		float x3 = initx + toolFrog.getWidth() ;
		float y3 = inity + toolFrog.getWidth() ;

		float x4 = initx;
		float y4 = inity + toolFrog.getWidth() ;

		float x5 = initx;
		float y5 = inity;
		if (y6 == inity) {
			p.moveTo(x1, y1);
			p.lineTo(x6, y6);
			p.lineTo(x2, y2);
			p.lineTo(x3, y3);
			p.lineTo(x4, y4);
			p.lineTo(x5, y5);
			p.lineTo(x1, y1);
			return p;
		} else if (x6 == initx + toolFrog.getWidth() ) {
			p.moveTo(x1, y1);
			p.lineTo(x6, y6);
			p.lineTo(x3, y3);
			p.lineTo(x4, y4);
			p.lineTo(x5, y5);
			p.lineTo(x1, y1);
			return p;
		} else if (y6 == inity + toolFrog.getWidth() ) {
			p.moveTo(x1, y1);
			p.lineTo(x6, y6);
			p.lineTo(x4, y4);
			p.lineTo(x5, y5);
			p.lineTo(x1, y1);
			return p;
		} else if (x6 == initx) {
			p.moveTo(x1, y1);
			p.lineTo(x6, y6);
			p.lineTo(x5, y5);
			p.lineTo(x1, y1);
			return p;
		}
		// return p;
		return null;
	}

	// ���ߵı仯����ͨ������ı仯�õ��仯������
	public void changeShuijingXY(int angle) {
		if (angle >= 0 && angle < 90) {
			change_x = (float) (initx + toolFrog.getWidth()  / 2 - toolFrog.getWidth()  / 2 * Math.tan((45 - angle)
					* Math.PI / 180));
			change_y = inity;
		} else if (angle >= 90 && angle < 180) {
			change_x = initx + toolFrog.getWidth() ;
			change_y = (float) (inity + toolFrog.getWidth()  / 2 - toolFrog.getWidth()  / 2 * Math
					.tan((135 - angle) * Math.PI / 180));
		} else if (angle >= 180 && angle < 270) {
			change_x = (float) (initx + toolFrog.getWidth()  / 2 + toolFrog.getWidth()  / 2 * Math
					.tan((225 - angle) * Math.PI / 180));
			change_y = inity + toolFrog.getWidth() ;
		} else if (angle >= 270 && angle <= 360) {
			change_x = initx;
			change_y = (float) (inity + toolFrog.getWidth()  / 2 + toolFrog.getWidth()  / 2 * Math
					.tan((315 - angle) * Math.PI / 180));
		}

	}

	// ���������̵߳ķ���
	public void createAllThread() {
		timethread = new TimeThread(this);
		gameviewdrawThread = new GameviewDrawThread(this);
	}

	// �������̵߳ķ���
	public void startAllThread() {
		timethread.start();
		gameviewdrawThread.start();
	}
	/**
	 * ����ͼ��
	 * 
	 * @param bitmap
	 * @param width
	 * @param height
	 */
	public static Bitmap zoom(Bitmap bitmap, int width, int height) {
		if (bitmap == null) {
			return null;
		}
		int w = bitmap.getWidth();
		int h = bitmap.getHeight();
		Matrix matrix = new Matrix();
		float scaleWidth = ((float) width / w);
		float scaleHeight = ((float) height / h);
		matrix.postScale(scaleWidth, scaleHeight);
		return Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
	}
}
