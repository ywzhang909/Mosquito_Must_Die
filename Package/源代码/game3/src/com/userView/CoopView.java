package com.userView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import utility.ClientThread;
import utility.ManagerThread;
import utility.PicScaleHelper;
import utility.ServerThread;
import utility.Target;

import com.publicClass.Constant;
import com.publicClass.GameActivity;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
//import android.net.wifi.p2p.WifiP2pDevice;
//import android.net.wifi.p2p.WifiP2pManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.SurfaceHolder.Callback;
import android.widget.Toast;

import com.publicClass.R;

public class CoopView extends SurfaceView implements Callback {
	String host;
	public int choose=-1;
	public int choose1 = -1;
	public int choose2 = -1;
	public int capture = 1;
	public boolean ready1 = false, ready2 = false;
	public boolean isHost;

	GameActivity activity;

	private Bitmap cho_blue, cho_wifi, back, create, join, background;
	private Bitmap refresh, cancel, next, previous, find;
	private Bitmap readyMark, getReady;
	private Bitmap captures[];
	private Paint paint;

	private int situation = 1;// 1:wifi?bluetooth?back?
	// 2:create?join?back?
	// 3:join
	// 4:room
	// 5:search
	// 6:wifi's create?join?back?
	// 7:wifi search
	// 8:wifi room

	private DrawSelfThread drawSelf;
	private boolean flag;

	private int page = 1;
	private int numberOfPage = 5;
	private int totlePage = 1;

	float width = Constant.SCREEN_WIDTH, height = Constant.SCREEN_HEIGHT;
	float dividL, dividH, mTextSize, fFontHeight;
	private float[][] btnLoc = new float[12][2];
	// 标识当前view是否为CoopView，为CoopView时为true，为GameView时为false
	public boolean currentFlag = true;
	public BluetoothAdapter mBluetoothAdapter;
	// 设备是否支持蓝牙，初始值为0，为1时支持蓝牙，为-1时不支持蓝牙
	public int haveBluetooth = 0;
	// 设备蓝牙是否开启，初始值为0，为1时蓝牙开启，为-1时蓝牙开启失败
	public int isEnable = 0;
	public boolean hasIn = false;
	public String player;
	public ArrayList<BluetoothDevice> PreDeviceList;
	public ArrayList<BluetoothDevice> CurDeviceList;
	public ManagerThread managerThread;
//	public Collection<WifiP2pDevice> wifiDevice;
//	public  WifiP2pDevice[] wifiDeviceList;
	// 处理通知的handler
	public Handler mHandler = new Handler() {
		@SuppressLint("ShowToast")
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				Toast.makeText(activity, "连接成功！", 5).show();
				situation = 2;
				repaint();
				break;
			case -1:
				Toast.makeText(activity, "连接失败，请重试！", 5).show();
				break;
			case 2:
				Toast.makeText(activity, "本机可见", 5);
				server = new ServerThread(CoopView.this);
				server.start();
				break;
			case -2:
				Toast.makeText(activity, "可见性修改失败，请重试", 5);
				break;
			case 3:
				// managerThread = new ManagerThread(server.socket);
				// managerThread.start();
				managerThread = server.managerThread;
				// server.cancelSocket();
				hasIn = true;
				break;
			case 4:
				Log.e("case4", "ok");
				// managerThread=new ManagerThread(client.mSocket);
				// managerThread.start();
				managerThread = client.managerThread;
				Log.e("case4", "after");
				client.setFlag(false);
				situation = 4;
				capture = 1;
				player = mBluetoothAdapter.getName();
				Target t = new Target(3, player, 0, 0);
				managerThread.write(t);
				repaint();
				break;
			case 5:
				Toast.makeText(activity, "wifi连接", Toast.LENGTH_LONG).show();
				break;
			case 6:
				Toast.makeText(activity, "wifi断开", Toast.LENGTH_LONG).show();
				break;
			case 7:
				Toast.makeText(activity, "搜索失败", Toast.LENGTH_LONG).show();
				break;
			case 8:
				Toast.makeText(activity, "搜索成功，正在请求列表，请等待", Toast.LENGTH_LONG).show();
				break;
			case 99:
				if (((Target) msg.obj).getValue().equals("0")) {
					ready1 = true;
					if (ready1 && ready2)
						switch (capture) {
						case 1:
							activity.setContentView(new GameViewOne(activity, 3));
							break;
						case 2:
							activity.setContentView(new GameViewTwo(activity, 5));
							break;
						case 3:
							activity.setContentView(new GameViewThree(activity,
									5));
							break;
						case 4:
							activity.setContentView(new GameViewFour(activity,
									6));
							break;
						case 5:
							activity.setContentView(new GameViewFive(activity,
									8));
							break;
						case 6:
							activity.setContentView(new GameViewSix(activity, 4));
							break;
						}
				}
				if (((Target) msg.obj).getCategory() == 3) {
					player = ((Target) msg.obj).getValue();
				}
				break;
			}
		}
	};
	// 服务端线程
	ServerThread server;

	// 客户端线程
	ClientThread client;

	public CoopView(GameActivity act) {
		super(act);
		activity = act;
		CurDeviceList = new ArrayList<BluetoothDevice>();
		// 获得焦点并设置为可触控
		this.requestFocus();
		this.setFocusableInTouchMode(true);
		getHolder().addCallback(this);// 注册回调接口
	}

	// 获取蓝牙设备，并判断设备是否支持蓝牙
	public void CheckBluetooth() {
		mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		host = mBluetoothAdapter.getName();
		if (mBluetoothAdapter == null) {
			// 如果设备上不支持蓝牙
			haveBluetooth = -1;
		} else {
			haveBluetooth = 1;
		}
	}

	// 开启蓝牙设备
	public void EnableBluetooth() {
		if (!mBluetoothAdapter.isEnabled()) {
			activity.sendMessage(Constant.EnableBluetooth);
		} else {
			isEnable = 1;
		}
	}

	// 获取以前曾经连接过的设备列表
	public ArrayList<BluetoothDevice> getPreviousList() {
		Set<BluetoothDevice> pairedDevices = mBluetoothAdapter
				.getBondedDevices();
		// 如果存在绑定的设备
		if (pairedDevices.size() > 0) {
			PreDeviceList = new ArrayList<BluetoothDevice>();
			for (BluetoothDevice device : pairedDevices) {
				PreDeviceList.add(device);
			}
			return PreDeviceList;
		} else {
			return null;
		}
	}

	// 搜索当前可用设备
	public void getCurrentList() {
		mBluetoothAdapter.startDiscovery();
	}

	// 取消搜索
	// cancelDiscovery()
	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		CheckBluetooth();
		if (haveBluetooth == -1) {
			Toast.makeText(activity, "本机没有蓝牙设备", 5).show();
		}
		dividH = height / 16;
		dividL = width / 16;
		mTextSize = 40;
		initBitmap();
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setTextSize(mTextSize);
		paint.setTextAlign(Align.LEFT);
		paint.setColor(Color.WHITE);
		fFontHeight = (float) Math.ceil(paint.getFontMetrics().descent
				- paint.getFontMetrics().ascent) + 20;
		repaint();
	}

	// 重新绘制的方法
	public void repaint() {
		Canvas canvas = this.getHolder().lockCanvas();
		try {
			synchronized (canvas) {
				onDraw(canvas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				this.getHolder().unlockCanvasAndPost(canvas);
			}
		}
	}

	private void initBitmap() {
		background = BitmapFactory.decodeResource(getResources(),
				R.drawable.viewbackground);
		background = PicScaleHelper.FullScreenScale(background,
				Constant.wRatio, Constant.hRatio);
		captures = new Bitmap[] {
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
						R.drawable.number9), };
		btnLoc[8] = new float[] { width / 2 - captures[0].getWidth(), dividH };// capture

		// 1
		cho_blue = BitmapFactory.decodeResource(getResources(),
				R.drawable.bluetooth);
		btnLoc[0] = new float[] { width / 4 - cho_blue.getWidth() / 2,
				height / 2 - cho_blue.getHeight() / 2 };// 1_bule;2_create
		cho_wifi = BitmapFactory
				.decodeResource(getResources(), R.drawable.wifi);
		btnLoc[1] = new float[] { width / 2 - cho_blue.getWidth() / 2,
				height / 2 - cho_blue.getHeight() / 2 };// 1_wifi;2_join
		back = BitmapFactory.decodeResource(getResources(), R.drawable.close);
		btnLoc[2] = new float[] { 3 * width / 4 - cho_blue.getWidth() / 2,
				height / 2 - cho_blue.getHeight() / 2 };// 1_back;2_back;4_5_back
		// 2
		create = BitmapFactory.decodeResource(getResources(), R.drawable.add);
		join = BitmapFactory.decodeResource(getResources(), R.drawable.find);
		refresh = BitmapFactory.decodeResource(getResources(),
				R.drawable.refresh);

		// 3
		cancel = BitmapFactory
				.decodeResource(getResources(), R.drawable.cancel);
		btnLoc[9] = new float[] { width - dividL - back.getWidth(),
				height - back.getHeight() };

		previous = BitmapFactory.decodeResource(getResources(),
				R.drawable.previous);
		btnLoc[4] = new float[] { dividL, dividH + numberOfPage * fFontHeight };// 5_3_pre
		btnLoc[6] = new float[] { width / 8 - previous.getWidth() / 2,
				height / 4 };// 4

		next = BitmapFactory.decodeResource(getResources(), R.drawable.next);
		btnLoc[5] = new float[] { width - dividL - next.getWidth(),
				dividH + numberOfPage * fFontHeight };// 3_5_next
		btnLoc[7] = new float[] { 7 * width / 8 - previous.getWidth() / 2,// 4
				height / 4 };

		find = BitmapFactory.decodeResource(getResources(), R.drawable.find);
		btnLoc[3] = new float[] { dividL, height - find.getHeight() };// find,refresh

		// 4
		readyMark = BitmapFactory.decodeResource(getResources(),
				R.drawable.ready);
		getReady = BitmapFactory.decodeResource(getResources(), R.drawable.in);
		btnLoc[10] = new float[] { dividL,
				height - dividH - getReady.getHeight() };
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {

	}

	@SuppressLint({ "HandlerLeak", "ShowToast" })
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float downX = event.getX(), downY = event.getY();
		boolean isDown = false;
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (situation == 1) {
				if (Constant
						.isPointInRect(downX, downY, btnLoc[0][0],
								btnLoc[0][1], cho_blue.getWidth(),
								cho_blue.getHeight())
						&& haveBluetooth == 1 && !isDown) {
					// 蓝牙
					Log.e("pressBtn", situation + " cho_blue");
					isDown = true;
					EnableBluetooth();
					if (isEnable == 1) {
						mHandler.sendEmptyMessage(1);
					}
					// situation=2;
					// repaint();
				}
				if (Constant
						.isPointInRect(downX, downY, btnLoc[1][0],
								btnLoc[1][1], cho_wifi.getWidth(),
								cho_wifi.getHeight())
						&& !isDown) {
					// wifi
					isDown = true;
//					activity.registerReceiver(activity.wfReceive,
//							activity.mIntentFilter);
					situation = 6;
					repaint();
					// Toast.makeText(activity, "功能尚未实现", 5);
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[2][0],
						btnLoc[2][1], back.getWidth(), back.getHeight())) {
					// 返回
					Log.e("pressBtn", situation + " back");
					isDown = true;
					situation = 1;
					activity.sendMessage(Constant.GOTO_START_GAME_VIEW);
				}
			}

			if (situation == 2) {
				if (Constant
						.isPointInRect(downX, downY, btnLoc[0][0],
								btnLoc[0][1], cho_blue.getWidth(),
								cho_blue.getHeight())
						&& !isDown) {
					Log.e("pressBtn", situation + " create");
					// create
					isDown = true;
					isHost = true;
					situation = 4;
					repaint();
					// 开启serversocket
					activity.sendMessage(Constant.EnableDiscovery);
				}
				if (Constant
						.isPointInRect(downX, downY, btnLoc[1][0],
								btnLoc[1][1], cho_wifi.getWidth(),
								cho_wifi.getHeight())
						&& !isDown) {
					Log.e("pressBtn", situation + " join");
					// join
					isDown = true;
					getPreviousList();
					situation = 3;
					repaint();
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[2][0],
						btnLoc[2][1], back.getWidth(), back.getHeight())
						&& !isDown) {
					// 返回
					Log.e("pressBtn", situation + " back");
					isDown = true;
					situation = 1;
					activity.sendMessage(Constant.GOTO_START_GAME_VIEW);
				}
			}
			if (situation == 3) {
				if (Constant.isPointInRect(downX, downY, dividL, dividH, width
						- 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听0
					isDown = true;
					if (PreDeviceList.size() != 0
							&& 0 + numberOfPage * (page - 1) < PreDeviceList
									.size()) {
						choose1 = 0 + (page - 1) * numberOfPage;
						player = PreDeviceList.get(choose1).getName();
						Log.e("pressBtn", situation + " preList" + choose1);
						client = new ClientThread(PreDeviceList.get(choose1),
								this);
						client.start();
						// situation = 4;
						// capture = 1;
						// repaint();
					}
				}
				if (Constant.isPointInRect(downX, downY, dividL, dividH + 1
						* fFontHeight, width - 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听1
					isDown = true;
					if (CurDeviceList.size() != 0
							&& 1 + numberOfPage * (page - 1) < PreDeviceList
									.size()) {
						choose1 = 1 + (page - 1) * numberOfPage;
						player = PreDeviceList.get(choose1).getName();
						Log.e("pressBtn", situation + " preList" + choose1);
						client = new ClientThread(PreDeviceList.get(choose1),
								this);
						client.start();
						// situation = 4;
						// capture = 1;
						// repaint();
					}
				}
				if (Constant.isPointInRect(downX, downY, dividL, dividH + 2
						* fFontHeight, width - 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听2
					isDown = true;
					if (PreDeviceList.size() != 0
							&& 2 + numberOfPage * (page - 1) < PreDeviceList
									.size()) {
						choose1 = 2 + (page - 1) * numberOfPage;
						player = PreDeviceList.get(choose1).getName();
						Log.e("pressBtn", situation + " preList" + choose1);
						client = new ClientThread(PreDeviceList.get(choose1),
								this);
						client.start();
						// situation = 4;
						// capture = 1;
						// repaint();
					}
				}
				if (Constant.isPointInRect(downX, downY, dividL, dividH + 3
						* fFontHeight, width - 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听3
					isDown = true;
					if (PreDeviceList.size() != 0
							&& 3 + numberOfPage * (page - 1) < PreDeviceList
									.size()) {
						choose1 = 3 + (page - 1) * numberOfPage;
						player = PreDeviceList.get(choose1).getName();
						Log.e("pressBtn", situation + " preList" + choose1);
						client = new ClientThread(PreDeviceList.get(choose1),
								this);
						client.start();
						// situation = 4;
						// capture = 1;
						// repaint();
					}
				}
				if (Constant.isPointInRect(downX, downY, dividL, dividH + 4
						* fFontHeight, width - 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听4
					isDown = true;
					if (PreDeviceList.size() != 0
							&& 4 + numberOfPage * (page - 1) < PreDeviceList
									.size()) {
						choose1 = 4 + (page - 1) * numberOfPage;
						player = PreDeviceList.get(choose1).getName();
						Log.e("pressBtn", situation + " preList" + choose1);
						client = new ClientThread(PreDeviceList.get(choose1),
								this);
						client.start();
						// situation = 4;
						// capture = 1;
						// repaint();
					}
				}

				// 向上翻页
				if (Constant
						.isPointInRect(downX, downY, btnLoc[4][0],
								btnLoc[4][1], previous.getWidth(),
								previous.getHeight())
						&& page > 1 && !isDown) {
					Log.e("pressBtn", situation + " previous");
					isDown = true;
					page--;
					repaint();
				}
				// 向下翻页
				if (Constant
						.isPointInRect(downX, downY, btnLoc[5][0],
								btnLoc[5][1], previous.getWidth(),
								previous.getHeight())
						&& page <= totlePage && !isDown) {
					Log.e("pressBtn", situation + " next");
					isDown = true;
					page++;
					repaint();
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[3][0],
						btnLoc[3][1], find.getWidth(), find.getHeight())
						&& !isDown) {
					Log.e("pressBtn", situation + " find");
					// 搜索按钮监听
					isDown = true;
					page = 1;
					situation = 5;
					drawSelf = new DrawSelfThread(this);
					drawSelf.start();
					repaint();
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[9][0],
						btnLoc[9][1], cancel.getWidth(), cancel.getHeight())
						&& !isDown) {
					Log.e("pressBtn", situation + " back");
					situation = 2;
					page=1;
					repaint();
				}
			}
			if (situation == 4) {
				if (Constant
						.isPointInRect(downX, downY, btnLoc[6][0],
								btnLoc[6][1], previous.getWidth(),
								previous.getHeight())
						&& capture > 1 && !isDown) {
					Log.e("pressBtn", situation + " prev" + "iousCap");
					// 上一关
					isDown = true;
					capture--;
					repaint();
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[7][0],
						btnLoc[7][1], next.getWidth(), next.getHeight())
						&& capture < 9 && !isDown) {
					Log.e("pressBtn", situation + " nextCap");
					// 下一关
					isDown = true;
					capture++;
					repaint();
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[10][0],
						btnLoc[10][1], getReady.getWidth(),
						getReady.getHeight())
						&& !isDown && hasIn) {
					Log.e("pressBtn", situation + " getReady");
					// 准备
					isDown = true;
					ready2 = true;
					if (ready1 && ready2)
						switch (capture) {
						case 1:
							activity.setContentView(new GameViewOne(activity, 3));
							break;
						case 2:
							activity.setContentView(new GameViewTwo(activity, 5));
							break;
						case 3:
							activity.setContentView(new GameViewThree(activity,
									5));
							break;
						case 4:
							activity.setContentView(new GameViewFour(activity,
									6));
							break;
						case 5:
							activity.setContentView(new GameViewFive(activity,
									8));
							break;
						case 6:
							activity.setContentView(new GameViewSix(activity, 4));
							break;
						}
					Target t = new Target(0, "0", 0, 0);
					managerThread.write(t);
					repaint();
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[9][0],
						btnLoc[9][1], cancel.getWidth(), cancel.getHeight())
						&& !isDown) {
					// 取消
					isDown = true;
					ready1 = ready2 = false;
					situation = 2;
					capture=1;
					repaint();
				}
			}

			if (situation == 5) {
				Log.e("press", "5");
				if (Constant.isPointInRect(downX, downY, dividL, dividH, width
						- 2 * dividL, fFontHeight)
						&& !isDown) {
					Log.e("press", "55");
					// 列表监听0
					isDown = true;
					Log.i("Prelist", "" + 0);
					if (CurDeviceList.size() != 0
							&& 0 + numberOfPage * (page - 1) < CurDeviceList
									.size()) {
						choose2 = 0 + (page - 1) * numberOfPage;
						flag = false;
						client = new ClientThread(CurDeviceList.get(0), this);
						client.start();
					}
				}
				if (Constant.isPointInRect(downX, downY, dividL, dividH + 1
						* fFontHeight, width - 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听1
					Log.i("Prelist", "" + 1);
					isDown = true;
					if (CurDeviceList.size() != 0
							&& 1 + numberOfPage * (page - 1) < CurDeviceList
									.size()) {
						choose2 = 1 + (page - 1) * numberOfPage;
						flag = false;
						client = new ClientThread(CurDeviceList.get(choose2),
								this);
						client.start();
					}
				}
				if (Constant.isPointInRect(downX, downY, dividL, dividH + 2
						* fFontHeight, width - 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听2
					isDown = true;
					if (PreDeviceList.size() != 0
							&& 2 + numberOfPage * (page - 1) < PreDeviceList
									.size()) {
						choose2 = 2 + (page - 1) * numberOfPage;
						flag = false;
						client = new ClientThread(CurDeviceList.get(choose2),
								this);
						client.start();
					}
				}
				if (Constant.isPointInRect(downX, downY, dividL, dividH + 3
						* fFontHeight, width - 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听3
					isDown = true;
					if (CurDeviceList.size() != 0
							&& 3 + numberOfPage * (page - 1) < CurDeviceList
									.size()) {
						choose2 = 3 + (page - 1) * numberOfPage;
						flag = false;
						client = new ClientThread(CurDeviceList.get(choose2),
								this);
						client.start();
					}
				}
				if (Constant.isPointInRect(downX, downY, dividL, dividH + 4
						* fFontHeight, width - 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听4
					isDown = true;
					if (CurDeviceList.size() != 0
							&& 4 + numberOfPage * (page - 1) < CurDeviceList
									.size()) {
						choose2 = 4 + (page - 1) * numberOfPage;
						flag = false;
						client = new ClientThread(CurDeviceList.get(choose2),
								this);
						client.start();
					}
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[9][0],
						btnLoc[9][1], back.getWidth(), back.getHeight())
						&& !isDown) {
					Log.e("pressBtn", situation + " cancelFind");
					// 搜索取消监听
					isDown = true;
					flag = false;
					mBluetoothAdapter.cancelDiscovery();
					situation = 2;
					page=1;
					// 解除广播接收器的注册
					activity.unregisterReceiver(activity.receiver);
					repaint();
				}
				// 向上翻页
				if (Constant
						.isPointInRect(downX, downY, btnLoc[4][0],
								btnLoc[4][1], previous.getWidth(),
								previous.getHeight())
						&& page > 1 && !isDown) {
					Log.e("pressBtn", situation + " curPrevious");
					isDown = true;
					page--;
					repaint();
				}
				// 向下翻页
				if (Constant
						.isPointInRect(downX, downY, btnLoc[5][0],
								btnLoc[5][1], previous.getWidth(),
								previous.getHeight())
						&& page <= totlePage && !isDown) {
					Log.e("pressBtn", situation + " curNext");
					isDown = true;
					page++;
					repaint();
				}
			}
			if (situation == 6) {
				if (Constant
						.isPointInRect(downX, downY, btnLoc[0][0],
								btnLoc[0][1], cho_blue.getWidth(),
								cho_blue.getHeight())
						&& !isDown) {
					Log.e("pressBtn", situation + " create");
					// create
					isDown = true;
					isHost = true;
					situation = 8;
					repaint();

				}
				if (Constant
						.isPointInRect(downX, downY, btnLoc[1][0],
								btnLoc[1][1], cho_wifi.getWidth(),
								cho_wifi.getHeight())
						&& !isDown) {
					// join
					Log.e("pressBtn", situation + " join");
					isDown = true;
//					activity.mManager.discoverPeers(activity.mChannel, new WifiP2pManager.ActionListener() {
//					    @Override
//					    public void onSuccess() {
//					    	activity.coopView.mHandler.sendEmptyMessage(8);
//					    	situation=8;
//					    	repaint();
//					    }
//					    @Override
//					    public void onFailure(int reasonCode) {
//					     activity.coopView.mHandler.sendEmptyMessage(7);
//					    }
//					});
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[2][0],
						btnLoc[2][1], back.getWidth(), back.getHeight())
						&& !isDown) {
					// 返回
					Log.e("pressBtn", situation + " back");
					isDown = true;
					situation = 1;
					activity.sendMessage(Constant.GOTO_START_GAME_VIEW);
				}
			}
			if (situation == 7) {
				if (Constant.isPointInRect(downX, downY, dividL, dividH, width
						- 2 * dividL, fFontHeight)
						&& !isDown) {
					Log.e("press", "55");
					// 列表监听0
					isDown = true;
					Log.i("Prelist", "" + 0);
//					if (wifiDeviceList.length != 0
//							&& 0 + numberOfPage * (page - 1) < wifiDeviceList.length) {
//						choose = 0 + (page - 1) * numberOfPage;
//
//					}
				}
				if (Constant.isPointInRect(downX, downY, dividL, dividH + 1
						* fFontHeight, width - 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听1
					Log.i("Prelist", "" + 1);
					isDown = true;
//					if (wifiDeviceList.length != 0
//							&& 1 + numberOfPage * (page - 1) < wifiDeviceList.length) {
//						choose = 1 + (page - 1) * numberOfPage;
//
//					}
				}
				if (Constant.isPointInRect(downX, downY, dividL, dividH + 2
						* fFontHeight, width - 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听2
					isDown = true;
//					if (wifiDeviceList.length != 0
//							&& 2 + numberOfPage * (page - 1) <wifiDeviceList.length) {
//						choose = 2 + (page - 1) * numberOfPage;
//
//					}
				}
				if (Constant.isPointInRect(downX, downY, dividL, dividH + 3
						* fFontHeight, width - 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听3
					isDown = true;
//					if (wifiDeviceList.length != 0
//							&& 3 + numberOfPage * (page - 1) < wifiDeviceList.length) {
//						choose = 3 + (page - 1) * numberOfPage;
//
//					}
				}
				if (Constant.isPointInRect(downX, downY, dividL, dividH + 4
						* fFontHeight, width - 2 * dividL, fFontHeight)
						&& !isDown) {
					// 列表监听4
					isDown = true;
//					if (wifiDeviceList.length != 0
//							&& 4 + numberOfPage * (page - 1) < wifiDeviceList.length) {
//						choose = 4 + (page - 1) * numberOfPage;
//
//					}
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[9][0],
						btnLoc[9][1], back.getWidth(), back.getHeight())
						&& !isDown) {
					Log.e("pressBtn", situation + " cancelFind");
					// 搜索取消监听
					isDown = true;

					situation = 2;
					page=1;
					repaint();
				}
				// 向上翻页
				if (Constant
						.isPointInRect(downX, downY, btnLoc[4][0],
								btnLoc[4][1], previous.getWidth(),
								previous.getHeight())
						&& page > 1 && !isDown) {
					Log.e("pressBtn", situation + " curPrevious");
					isDown = true;
					page--;
					repaint();
				}
				// 向下翻页
				if (Constant
						.isPointInRect(downX, downY, btnLoc[5][0],
								btnLoc[5][1], previous.getWidth(),
								previous.getHeight())
						&& page <= totlePage && !isDown) {
					Log.e("pressBtn", situation + " curNext");
					isDown = true;
					page++;
					repaint();
				}
			}
			if (situation == 8) {
				if (Constant
						.isPointInRect(downX, downY, btnLoc[6][0],
								btnLoc[6][1], previous.getWidth(),
								previous.getHeight())
						&& capture > 1 && !isDown) {
					Log.e("pressBtn", situation + " prev" + "iousCap");
					// 上一关
					isDown = true;
					capture--;
					repaint();
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[7][0],
						btnLoc[7][1], next.getWidth(), next.getHeight())
						&& capture < 9 && !isDown) {
					Log.e("pressBtn", situation + " nextCap");
					// 下一关
					isDown = true;
					capture++;
					repaint();
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[10][0],
						btnLoc[10][1], getReady.getWidth(),
						getReady.getHeight())
						&& !isDown && hasIn) {
					Log.e("pressBtn", situation + " getReady");
					// 准备
					isDown = true;
					ready2 = true;
					if (ready1 && ready2)
						switch (capture) {
						case 1:
							activity.setContentView(new GameViewOne(activity, 3));
							break;
						case 2:
							activity.setContentView(new GameViewTwo(activity, 5));
							break;
						case 3:
							activity.setContentView(new GameViewThree(activity,
									5));
							break;
						case 4:
							activity.setContentView(new GameViewFour(activity,
									6));
							break;
						case 5:
							activity.setContentView(new GameViewFive(activity,
									8));
							break;
						case 6:
							activity.setContentView(new GameViewSix(activity, 4));
							break;
						}
					Target t = new Target(0, "0", 0, 0);
					managerThread.write(t);
					repaint();
				}
				if (Constant.isPointInRect(downX, downY, btnLoc[9][0],
						btnLoc[9][1], cancel.getWidth(), cancel.getHeight())
						&& !isDown) {
					// 取消
					isDown = true;
					ready1 = ready2 = false;
					situation = 2;
					repaint();
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			isDown = false;
			break;
		}
		return super.onTouchEvent(event);
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.BLACK);
		canvas.drawBitmap(background, 0, 0, paint);
		switch (situation) {
		case 1:
			Log.i("situation", "" + 1);
			if (haveBluetooth == 1)
				canvas.drawBitmap(cho_blue, btnLoc[0][0], btnLoc[0][1], paint);
			canvas.drawBitmap(cho_wifi, btnLoc[1][0], btnLoc[1][1], paint);
			canvas.drawBitmap(back, btnLoc[2][0], btnLoc[2][1], paint);
			break;
		case 2:
			Log.i("situation", "" + 2);
			canvas.drawBitmap(create, btnLoc[0][0], btnLoc[0][1], paint);
			canvas.drawBitmap(join, btnLoc[1][0], btnLoc[1][1], paint);
			canvas.drawBitmap(back, btnLoc[2][0], btnLoc[2][1], paint);
			break;
		case 3:
			Log.i("situation", "" + 3);
			// previous
			if (page > 1)
				canvas.drawBitmap(previous, btnLoc[4][0], btnLoc[4][1], paint);
			// next
			if (page < totlePage)
				canvas.drawBitmap(next, btnLoc[5][0], btnLoc[5][1], paint);

			// search new device button
			canvas.drawBitmap(find, btnLoc[3][0], btnLoc[3][1], paint);
			// history link
			for (int i = 0; i < numberOfPage
					&& (i + (page - 1) * numberOfPage) < PreDeviceList.size(); i++) {
				String name = PreDeviceList.get(i + (page - 1) * numberOfPage)
						.getName();
				canvas.drawText(name, dividL, dividH + i * fFontHeight, paint);
				canvas.drawLine(dividL, dividH + fFontHeight * i, width
						- dividL, dividH + fFontHeight * i, paint);
			}
			canvas.drawBitmap(back, btnLoc[9][0], btnLoc[9][1], paint);
			break;
		case 4:
			Log.i("situation", "" + 4);
			if (capture > 1)// previous
				canvas.drawBitmap(previous, btnLoc[6][0], btnLoc[6][1], paint);
			if (capture < 9)// next
				canvas.drawBitmap(next, btnLoc[7][0], btnLoc[7][1], paint);
			// map
			canvas.drawBitmap(captures[capture - 1], btnLoc[7][0],
					btnLoc[7][1], paint);
			float textH = captures[0].getHeight() + btnLoc[7][1] + 40;
			if (!isHost) {
				canvas.drawText(choose1 > choose2 ? PreDeviceList.get(choose1)
						.getName() : CurDeviceList.get(choose2).getName(),
						dividL, textH, paint);
			} else {
				canvas.drawText(player, dividL, textH, paint);
			}
			if (ready1)
				canvas.drawBitmap(readyMark, width - dividL, textH, paint);

			canvas.drawText(host, dividL, textH + fFontHeight * 6 / 5, paint);
			if (ready2)
				canvas.drawBitmap(readyMark, width - dividL, textH, paint);
			if (hasIn)
				canvas.drawBitmap(getReady, btnLoc[10][0], btnLoc[10][1], paint);
			canvas.drawBitmap(cancel, btnLoc[9][0], btnLoc[9][1], paint);
			break;
		case 5:
			Log.i("situation", "" + 5);
			// previous
			if (page > 1)
				canvas.drawBitmap(previous, btnLoc[4][0], btnLoc[4][1], paint);
			// next
			if (page < totlePage)
				canvas.drawBitmap(next, btnLoc[5][0], btnLoc[5][1], paint);

			// new link
			if (CurDeviceList.size() != 0) {
				if (CurDeviceList.get(0) != null)
					for (int i = 0; i < numberOfPage
							&& (i + (page - 1) * numberOfPage) < CurDeviceList
									.size(); i++) {
						String name = CurDeviceList.get(
								i + (page - 1) * numberOfPage).getName();
						canvas.drawText(name, dividL, dividH + 6 * fFontHeight
								/ 5 * i, paint);
						canvas.drawLine(dividL, dividH + fFontHeight * i,
								width, dividH + fFontHeight * i, paint);
					}
			}
			canvas.drawBitmap(back, btnLoc[9][0], btnLoc[9][1], paint);
			break;
		case 6:
			Log.i("situation", "" + 6);
			canvas.drawBitmap(create, btnLoc[0][0], btnLoc[0][1], paint);
			canvas.drawBitmap(join, btnLoc[1][0], btnLoc[1][1], paint);
			canvas.drawBitmap(back, btnLoc[2][0], btnLoc[2][1], paint);
			break;
		case 7:
			Log.i("situation", "" + 7);
			// previous
			if (page > 1)
				canvas.drawBitmap(previous, btnLoc[4][0], btnLoc[4][1], paint);
			// next
			if (page < totlePage)
				canvas.drawBitmap(next, btnLoc[5][0], btnLoc[5][1], paint);

			// link
//			if (wifiDeviceList.length != 0) {
//				if (CurDeviceList.get(0) != null)
//					for (int i = 0; i < numberOfPage
//							&& (i + (page - 1) * numberOfPage) < wifiDeviceList.length; i++) {
//						String name =wifiDeviceList[i + (page - 1) * numberOfPage]
//								.toString();
//						canvas.drawText(name, dividL, dividH + 6 * fFontHeight
//								/ 5 * i, paint);
//						canvas.drawLine(dividL, dividH + fFontHeight * i,
//								width, dividH + fFontHeight * i, paint);
//					}
//			}
			canvas.drawBitmap(back, btnLoc[9][0], btnLoc[9][1], paint);
			break;
		case 8:
			
			Log.i("situation", "" + 8);
			if (capture > 1)// previous
				canvas.drawBitmap(previous, btnLoc[6][0], btnLoc[6][1], paint);
			if (capture < 9)// next
				canvas.drawBitmap(next, btnLoc[7][0], btnLoc[7][1], paint);
			// map
			canvas.drawBitmap(captures[capture - 1], btnLoc[7][0],
					btnLoc[7][1], paint);
			float textHW = captures[0].getHeight() + btnLoc[7][1] + 40;
//			if (!isHost) {
//				canvas.drawText(wifiDeviceList[choose].toString(),
//						dividL, textHW, paint);
//			} else {
//				canvas.drawText("Me", dividL, textHW, paint);
//			}
			if (ready1)
				canvas.drawBitmap(readyMark, width - dividL, textHW, paint);

			canvas.drawText(host, dividL, textHW + fFontHeight * 6 / 5, paint);
			if (ready2)
				canvas.drawBitmap(readyMark, width - dividL, textHW, paint);
			if (hasIn)
				canvas.drawBitmap(getReady, btnLoc[10][0], btnLoc[10][1], paint);
			canvas.drawBitmap(cancel, btnLoc[9][0], btnLoc[9][1], paint);
			break;
		}

	}

	/**
	 * 旋转图形
	 * 
	 * @param b
	 * @param degree
	 */
	public static Bitmap rotate(Bitmap b, int degree) {
		if (degree == 0 || b == null)
			return b;
		Matrix m = new Matrix();
		m.setRotate(degree, b.getWidth() / 2, b.getHeight() / 2);
		return Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m,
				true);
	}

	class DrawSelfThread extends Thread {
		CoopView coopView;
		SurfaceHolder surfaceHolder;

		public DrawSelfThread(CoopView coop) {
			coopView = coop;
			surfaceHolder = coop.getHolder();
			flag = true;
		}

		@Override
		public void run() {
			super.run();
			Canvas c;
			while (flag) {
				c = null;
				try {
					getCurrentList();
					totlePage = CurDeviceList.size() / 10 + 1;
					c = this.surfaceHolder.lockCanvas(null);
					this.coopView.onDraw(c);
				} finally {
					if (c != null) {
						this.surfaceHolder.unlockCanvasAndPost(c);
					}
				}
				try {
					
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
