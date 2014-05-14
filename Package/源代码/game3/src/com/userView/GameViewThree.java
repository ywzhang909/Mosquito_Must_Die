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
 * @author 山东大学赵宝琦、张霖、吕华富、于洪洋
 * 
 */
public class GameViewThree extends SurfaceView implements Callback {

	public Paint paint;// 画笔
	GameActivity activity;// activity的引用
	public Handler mhandler;
	// 用于绘制CD
	Path path;
	// 声音相关变量
	SoundPool soundPool;// 声音
	HashMap<Integer, Integer> soundPoolMap;
	MediaPlayer mMediaPlayer;
	// 用户触控点的坐标
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
	// 青蛙CD角度的标志位
	public boolean cd1 = false;
	// 青蛙CD坐标的标志位
	int initfrogx;
	int initfrogy;
	float change_x1 = initfrogx;
	float change_y1 = initfrogy;
	public int angle1 = 0;
	
	public boolean cdFlag2 = true;
	// 灭蚊剂CD角度的标志位
	public boolean cd2 = false;
	// 灭蚊剂CD坐标的标志位
	int initsprayx;
	int initsprayy;
	float change_x2 ;
	float change_y2 ;
	public int angle2 = 0;
	
	// 图片相关
	Bitmap[] fourPlant;
	Bitmap[] fourFrog;
	Bitmap bg;
	Bitmap cover;
	Bitmap[] numberBitmaps;// 时间的图片
	Bitmap breakMarkBitmap;// 时间间隔符图片
	Bitmap jifen;// 积分
	Bitmap shengji;// 升级按钮
	Bitmap toolFrog;// 工具栏青蛙图标
	Bitmap toolExpelIncense;// 工具栏驱蚊香图标
	Bitmap toolExpelPlant;// 工具栏驱蚊草图标
	Bitmap toolKillLamp;// 工具栏灭蚊灯图标
	Bitmap toolkillspray;// 工具栏灭蚊剂图标
	Bitmap toolSpiderWeb;// 工具栏蜘蛛网图标
	Bitmap toolSwatter;// 工具栏电蚊拍图标
	public Timer timer;// 计时器
	// 计时线程
	TimeThread3 timethread;
	// 刷帧线程
	GameviewDrawThread gameviewdrawThread;
	// 怪物容器
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
	int score;// 当前积分=
	int numberOfEnermy;// 敌人总数

	// 标记目前点击的是第一个驱蚊草道具
	int rankplant = 0;
	// 标记目前点击的是第一个蚊香道具
	int rankincense = 8;
	
	public int step;// 青蛙图片标志位
	// 蚊子出现相关
	public boolean isBornWenzi = false;
	// 青蛙持续时间
	public boolean FrogSteady = false;

	/**
	 * 
	 * @param activity
	 * @param numberOfEnermy本关总共会出现的蚊子数量
	 */
	public GameViewThree(GameActivity activity, int numberOfEnermy) {
		super(activity);
		this.activity = activity;
		// 获得焦点并设置为可触控
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		getHolder().addCallback(this);// 注册回调接口
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
			// 用户选中了驱蚊草
			if (wantUpgrade && hasClickOnExpelPlant && toolLevel[1] == 1
					&& score >= upgredeCost[1]) {
				toolLevel[1]++;
				score -= upgredeCost[1];
				initTool();				
				
				wantUpgrade = hasClickOnExpelPlant = false;
				
			}
			
			if (hasClickOnExpelPlant && hasClickOnMap) {
                Log.e("plant", ""+ toolLevel[1]);
				// 表示监听到两次触控后,实例化一个驱蚊道具对象
				ExpelPlant Driver = new ExpelPlant(this, toolLevel[1], downX, downY);
				// 判断驱蚊道具是否点在路上，如没点在路上，则无效
				if (Driver.haveRight(this.map)) {
					expelPlantList.add(Driver);
					// 为每一个蚊子更改地图
					map = Driver.effect(this.map);

					for (Mosquito m : Wlist) {
						if(m!=null)
						m.setmap(map);
					}
					rankplant++;
					// 发送信息
					timethread.mhandler.sendEmptyMessage(rankplant);
					hasClickOnExpelPlant = false;
					hasClickOnMap = false;
				} else {
					hasClickOnExpelPlant = false;
					hasClickOnMap = false;
				}

			}
			        // 用户选中了蚊香
					if (wantUpgrade && hasClickOnIncense && toolLevel[3] == 1
								&& score >= upgredeCost[3]) {
							toolLevel[3]++;
							score -= upgredeCost[3];
							initTool();				
							
							wantUpgrade = hasClickOnIncense = false;
							
						}
						
						if (hasClickOnIncense && hasClickOnMap) {
			                Log.e("plant", ""+ toolLevel[1]);
							// 表示监听到两次触控后,实例化一个驱蚊道具对象
							ExpelIncense Driver = new ExpelIncense(this, toolLevel[3], downX, downY);
							// 判断驱蚊道具是否点在路上，如没点在路上，则无效
							if (Driver.haveRight3(this.map)) {
								incenseList.add(Driver);
								// 为每一个蚊子更改地图
								map = Driver.effect(this.map);

								for (Mosquito m : Wlist) {
									if(m!=null)
									m.setmap(map);
								}
								rankincense++;
								Log.e("rankxiang",""+rankincense);
								// 发送信息
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
			// 选中了灭蚊剂道具
			if (hasClickOnSpray && hasClickOnMap) {
				// 判断cd时间到没到
				if (cdFlag2) {

					killspray spray = new killspray(this,toolLevel[2], downX, downY);
					if(spray.haveRight3()){
					sprayList.add(spray);

					// 为每一只蚊子更改地图
					map = spray.effect(this.map);
					for (Mosquito m : Wlist) {
						if(m!=null)
						m.setmap(map);
					}
					timethread.mhandler.sendEmptyMessage(7);
					timethread.mhandler.sendEmptyMessage(8);
					// 表示监听到两次触控后,实例化一个灭蚊道具对象
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
			// 用户没有选中该道具
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

		initSounds();// 初始化声音资源
		// 初始化三只蚊子
		Wlist = new ArrayList<Mosquito>();
		Wlist.add(createWenzi1(1, 1));
		Wlist.add(createWenzi2(1, 1));
		Wlist.add(createWenzi3(1, 1));
		// 初始化盛放道具的容器
		expelPlantList = new ArrayList<ExpelPlant>();
		incenseList= new ArrayList<ExpelIncense>();
		sprayList = new ArrayList<killspray>();
		
		// 初始化背景音乐
		mMediaPlayer = MediaPlayer.create(activity, R.raw.backsound);
		mMediaPlayer.setLooping(true);
		if (activity.isBackGroundMusicOn())// 开启背景音乐
		{
			mMediaPlayer.start();
		}
		// 画笔初始化
		paint = new Paint();
		initBitmap();

		timer = new Timer(this, breakMarkBitmap, numberBitmaps);// 创建计时器对象
		paint.setAntiAlias(true);
		// 切割初始化
		path = new Path();
		// 计时线程开启
		createAllThread();
		startAllThread();
		timethread.setFlag(true);
		mhandler = new Handler() {
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case 0:
					// 将新产生的蚊子加入绘制列表
					Wlist.add(createWenzi1(1, 1));
					Wlist.add(createWenzi2(1, 1));
					break;
				case 1:
					// 为每一只蚊子恢复地图
					for (Mosquito m : Wlist) {
						map = expelPlantList.get(0).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					expelPlantList.set(0, null);

					break;
				case 2:
					// 为每一只蚊子恢复地图
					for (Mosquito m : Wlist) {
						map = expelPlantList.get(1).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					expelPlantList.set(1, null);
					break;
				case 3:
					// 为每一只蚊子恢复地图
					for (Mosquito m : Wlist) {
						map = expelPlantList.get(2).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					expelPlantList.set(2, null);
					break;
				case 4:
					// 为每一只蚊子恢复地图
					for (Mosquito m : Wlist) {
						map = expelPlantList.get(3).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					expelPlantList.set(3, null);
					break;
				case 5:
					// 为每一只蚊子恢复地图
					for (Mosquito m : Wlist) {
						map = expelPlantList.get(4).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					expelPlantList.set(4, null);
					break;
				case 6:
					// 为每一只蚊子恢复地图
					for (Mosquito m : Wlist) {
						map = expelPlantList.get(5).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					expelPlantList.set(5, null);
					break;

				// 灭蚊剂持续时间
				case 7:
					for (Mosquito m : Wlist) {
						map = sprayList.get(0).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					sprayList.set(0, null);
					sprayList.remove(0);
					break;
					
				//管理蚊香
				case 8:
					// 为每一只蚊子恢复地图
					for (Mosquito m : Wlist) {
						map = incenseList.get(0).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					incenseList.set(0, null);
					break;
				case 9:
					// 为每一只蚊子恢复地图
					for (Mosquito m : Wlist) {
						map = incenseList.get(1).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					incenseList.set(1, null);
					break;
				case 10:
					// 为每一只蚊子恢复地图
					for (Mosquito m : Wlist) {
						map = incenseList.get(2).reMap(map);
						if(m!=null)
						m.setmap(map);
					}
					incenseList.set(2, null);
					break;
				case 11:
					// 为每一只蚊子恢复地图
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
		while (retry) {// 不断地循环，直到其它线程结束
			joinAllThreads();
			retry = false;
		}
		// 停止背景音乐
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

	// 结束游戏的方法
	public void overGame() {
		stopAllThreads();
		// 停止背景音乐
		if (mMediaPlayer.isPlaying()) {
			mMediaPlayer.stop();
		}
	}

	private void stopAllThreads() {
		// 关闭所有线程
		timethread.setToDate(false);
		gameviewdrawThread.setFlag(false);
	}

	public void joinAllThreads() {

	}

	// 初始化声音的方法
	public void initSounds() {
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);
		soundPoolMap = new HashMap<Integer, Integer>();

	}

	// 播放声音的方法
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

	// 加载图片的方法
	public void initBitmap() {

		// 加载计时图片
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
				R.drawable.jifen);// 积分

		if(score >= upgredeCost[1]||score >= upgredeCost[2]||score >= upgredeCost[3])
		shengji = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.shengji);// 升级按钮
		else
		shengji = BitmapFactory.decodeResource(this.getResources(),
				R.drawable.unshengji);// 升级按钮
	
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
					R.drawable.toolfrog3);// 工具栏青蛙图标
			break;
		default:
			toolFrog = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.toolfrog3);
		}
		
		switch (toolLevel[1]) {
		case 1:
			 toolExpelPlant = BitmapFactory.decodeResource(
					this.getResources(), R.drawable.toolexpelplant);// 工具栏驱蚊草图标
			
			break;
		default:
			
					 toolExpelPlant = BitmapFactory.decodeResource(
						this.getResources(), R.drawable.toolexpelplant2);// 工具栏驱蚊草图标
				
		}
		
		switch (toolLevel[2]) {
		case 1:
			toolkillspray = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.toolkillspray);// 工具栏灭蚊剂图标
			break;
		default:
			toolkillspray = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.toolkillspray2);// 工具栏灭蚊剂图标
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
					R.drawable.untoolkilllamp);// 工具栏灭蚊拍图标
			break;
		default:
			toolKillLamp = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolkilllamp);// 工具栏灭蚊剂图标
		}		
		switch (toolLevel[5]) {
		case 1:
			toolSwatter = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolswatter);// 工具栏电蚊拍图标
			break;
		default:
			toolSwatter = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolswatter);// 工具栏电蚊拍图标
		}
		switch (toolLevel[6]) {
		case 1:
			toolSpiderWeb = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolspiderweb);// 工具栏蜘蛛网图标
			break;
		default:
			toolSpiderWeb = BitmapFactory.decodeResource(this.getResources(),
					R.drawable.untoolspiderweb);// 工具栏蜘蛛网图标
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

	
	// 生成蚊子的方法
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

	// 生成蚊子的方法
	public Mosquito createWenzi2(int amout, int level) {
		Mosquito m2 = null;
		for (int i = 0; i < amout; i++) {
			m2 = new Mosquito(level, 1, 8, this);
			// numberOfEnermy++;
			m2.setEnd(18, 5);
		}
		return m2;
	}
	
	// 生成蚊子的方法
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
		// 绘制背景
		canvas.drawColor(Color.GRAY);
		canvas.drawBitmap(bg, 0, 0, paint);
		// 绘制时间
		timer.drawSelf(canvas, paint);
		// 积分
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
		// 绘制道具栏
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
		
		

		// 绘制蚊子
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
		// 在地图上绘制道具
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

	// 创建用于绘制虚线的单元路径的方法
	private Path makePathDash1(float x6, float y6) {
		Path p = new Path();
		// 多边形从晶体中间点开始顺时针
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
	// 创建用于绘制虚线的单元路径的方法
		private Path makePathDash2(float x6, float y6) {
			Path p = new Path();
			// 多边形从晶体中间点开始顺时针
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

	// 水晶晶体的变化坐标通过方向的变化得到变化的坐标
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
	// 水晶晶体的变化坐标通过方向的变化得到变化的坐标
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
	// 创建所有线程的方法
	public void createAllThread() {
		timethread = new TimeThread3(this);
		gameviewdrawThread = new GameviewDrawThread(this);
	}

	// 打开所有线程的方法
	public void startAllThread() {
		timethread.start();
		gameviewdrawThread.start();
	}

	
}
