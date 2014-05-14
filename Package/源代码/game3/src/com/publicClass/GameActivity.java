package com.publicClass;

import java.util.ArrayList;

import com.publicClass.Constant;
import com.userView.AboutView;
import com.userView.ChooseView;
import com.userView.CoopView;
import com.userView.ExitView;
import com.userView.FailView;
import com.userView.GameViewFive;
import com.userView.GameViewFour;
import com.userView.GameViewOne;
import com.userView.GameViewSix;
import com.userView.GameViewThree;
import com.userView.GameViewTwo;
import com.userView.HelpView;
import com.userView.SystemView;
import com.userView.MainMenuView;
import com.userView.ScoreView;
import com.userView.StartGameView;
import com.userView.SoundSwitchButton;
import com.userView.WelcomeView;
import com.userView.WinView;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
//import android.net.wifi.p2p.WifiP2pDevice;
//import android.net.wifi.p2p.WifiP2pDeviceList;
//import android.net.wifi.p2p.WifiP2pManager;
//import android.net.wifi.p2p.WifiP2pManager.Channel;
//import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.RecognizerIntent;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
//标识所有SurfaceView的常量类
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
 
 
 
/**
 * 
 * @author 山东大学赵宝琦、张霖、吕华富、于洪洋
 *
 */
public class GameActivity extends Activity {
	
	//界面跳转相关
	int currentView;//标识当前的界面
	WelcomeView welcomeView;//欢迎界面
	MainMenuView mainMenuView;//主菜单界面
	SystemView SystemView;//帮助界面
	StartGameView startGameView;//关卡选择界面
	GameViewOne gameViewOne;//游戏时的界面
	GameViewTwo gameViewTwo;//游戏时的界面
	GameViewThree gameViewThree;//游戏时的界面
	GameViewFour gameViewFour;//游戏时的界面
	GameViewFive gameViewFive;//游戏时的界面
	GameViewSix gameViewSix;//游戏时的界面
	
	ScoreView scoreview;//积分榜界面
	ExitView exitview;//退出界面
	AboutView aboutview;//关于界面
	HelpView helpview;//帮助界面
	WinView winview;//胜利界面
	FailView fail;//失败界面
	ChooseView chooseView;//关卡选择界面
	public CoopView coopView;//多人合作界面
	public  int achievement=1;//标记当前关卡数
	public Receiver receiver;
	String action;
	boolean hasAction=false;
	//音效相关，暂时弄成只有开关两个状态的
	private boolean backGroundMusicOn=true;//背景音乐是否开启的标志
	private boolean soundOn=true;//音效是否开启的标志
	public int score;//积分
	//记录游戏运行的次数
	static int initTime=0;//初始化的次数
	public static final int requestCode=1;//开启蓝牙的请求
	public static final int requestCode2=0;
//	public WifiP2pManager  mManager;
//	public Channel mChannel;
//	public WifiReceive wfReceive;
	public IntentFilter mIntentFilter;
//	public PeerListListener myPeerListListener;
//	public WifiP2pDeviceList WifiDevicelist;
	//定义用于处理页面跳转的handler
	Handler mHandler=new Handler(){
		public void handleMessage(Message msg){
			switch (msg.what) {
			case Constant.GOTO_WELCOME_VIEW:
				goWelcomeView();
				break;
			case Constant.GOTO_MAIN_MENU_VIEW:
				goMainMenuView();
				break;
			
            case Constant.GOTO_SYSTEM_VIEW:
				goSystemView();
				break;	
            case Constant.GOTO_ABOUT_VIEW:
            	goAboutView();
            	break;
            case Constant.GOTO_HELP_VIEW:
            	goHelpView();
            	break;
            case Constant.GOTO_START_GAME_VIEW:
            	goStartGameView();
				break;
            case Constant.GOTO_CHOOSE_VIEW:
            	goChooseView();
            	break;
            case Constant.GOTO_SCORE_VIEW:
				goScoreView();
				break;	
            case Constant.GOTO_1:
            	goGameViewOne();
            	break;
            case Constant.GOTO_2:
            	goGameViewTwo();
            	break;
            case Constant.GOTO_3:
            	goGameViewThree();
            	break;
            case Constant.GOTO_4:
            	goGameViewFour();
            	break;
            case Constant.GOTO_5:
            	goGameViewFive();
            	break;
            case Constant.GOTO_6:
            	goGameViewSix();
            	break;
            case Constant.GOTO_7:
            	goGameViewTwo();
            	break;
            case Constant.GOTO_8:
            	goGameViewTwo();
            	break;
//            case Constant.GOTO_9:
//            	goGameViewTwo();
//            	break;
            case Constant.GOTO_EXIT_VIEW:
            	goExitView();
            	break;
            case Constant.GOTO_FAIL_VIEW:
            	goFailView();
            	break;
            case Constant.GOTO_WIN_VIEW:
            	Log.e("achievement", "before");
            		achievement=achievement+1;
            		Log.e("achievementb", achievement+"");
                	updateAchievement(achievement);
                	Log.e("achievement", getAchievement()+"");
                	goWinView();
            	break;
            case Constant.EXIT:
            	System.exit(0);
            	break;
            case Constant.GOTO_COOP_VIEW:
            	goCoopView();
            	break;
            case Constant.GOTO_1B:
                goGameView();
            	break;
            case Constant.EnableBluetooth:
            	//如果蓝牙没有打开
    		Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(i,requestCode);
            break;
            case Constant.EnableDiscovery:
            	//打开可见性
            	Intent discoverableIntent = new
				Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
				discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
				startActivityForResult(discoverableIntent,requestCode2);
            	break;
			}
		}
	};
	//监听蓝牙是否开启成功
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {   
		if(requestCode==1){
		switch(resultCode){
		 case RESULT_OK:
	    	 //如果开启成功了
	     coopView.mHandler.sendEmptyMessage(1);
	     break;
	    default :
	    coopView.mHandler.sendEmptyMessage(-1);
	    	break;
		}
		}else if(requestCode==0){
			switch(resultCode){
			case  300:
				coopView.mHandler.sendEmptyMessage(2);
				break;
			default :
				coopView.mHandler.sendEmptyMessage(-2);
				break;
			} 
		}
		//捕获语音识别结果
		else if(requestCode==1234){
			if(resultCode==RESULT_OK){
				ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			    action=result.get(0);
			    hasAction=true;
				//如果当前在主菜单界面，只能跳转到start_game/system/exit/score
		        if(currentView==Constant.GOTO_MAIN_MENU_VIEW){
		        	if(action.equals("开始游戏")||action.equals("游戏")){
		        		goStartGameView();
		        	}
		        	if(action.equals("系统设置")||action.equals("设置")){
		        		goSystemView();
		        	}
		        	if(action.equals("积分榜")||action.equals("积分")){
		        		goScoreView();
		        	}
		        	if(action.equals("退出游戏")&&action.equals("退出")){
		        		goExitView();
		        	}
		        }
		        //当前在startgame
		        if(currentView==Constant.GOTO_START_GAME_VIEW){
		        	if(action.equals("单人模式")||action.equals("单人")){
		        		goChooseView();
		        	}
		        	if(action.equals("双人模式")||action.equals("双人")||action.equals("多人模式")||action.equals("多人")){
		        		goCoopView();
		        	}
		        }
		        //当前是系统设置
		    	if(currentView==Constant.GOTO_SYSTEM_VIEW){
		    		if(action.equals("关于游戏")||action.equals("关于")){
		    			goAboutView();
		    		}
		    		if(action.equals("游戏帮助")||action.equals("帮助")){
		    			goHelpView();
		    		}
		    	}
		    	//当前为退出界面
		    	if(currentView==Constant.GOTO_EXIT_VIEW){
		    		if(action.equals("退出")||action.equals("确定")){
		    		sendMessage(Constant.EXIT);
		    		}
		    		if(action.equals("取消")||action.equals("返回")){
		    		sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
		    		}
		    		}
		    	if(currentView==Constant.GOTO_CHOOSE_VIEW){
		    		if(action.equals("一")){
		    		sendMessage(Constant.GOTO_1);
		    		}
		    		if(action.equals("二")){
		    		sendMessage(Constant.GOTO_2);
		    		}
		    		if(action.equals("三")){
		    		sendMessage(Constant.GOTO_3);
		    		}
		    		if(action.equals("四")){
		    		sendMessage(Constant.GOTO_4);
		    		}
		    		if(action.equals("五")){
		    		sendMessage(Constant.GOTO_5);
		    		}
		    		if(action.equals("六")){
		    		sendMessage(Constant.GOTO_6);
		    		}
		    		if(action.equals("七")){
		    		sendMessage(Constant.GOTO_7);
		    		}
		    		if(action.equals("八")){
		    		sendMessage(Constant.GOTO_8);
		    		}
		    		if(action.equals("返回")||action.equals("取消")){
		    		sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
		    		}
		    		}
			}else{
				Toast.makeText(this,"要使用语音功能，请先连接无线网络", Toast.LENGTH_LONG).show();
			}
		}
		}//防止用户说法不一，这里进行了模糊查询处理
	 public boolean isBackGroundMusicOn() {
			return backGroundMusicOn;
		}
		public void setBackGroundMusicOn(boolean backGroundMusicOn) {
			this.backGroundMusicOn = backGroundMusicOn;
		}
		
		public boolean isSoundOn() {
			return soundOn;
		}
		public void setSoundOn(boolean soundOn) {
			this.soundOn = soundOn;
		}
		//关于achievement的操作
		
		//获取当前achievement
		public int getAchievement(){
	    SharedPreferences data=getSharedPreferences("config", 0);
	    String s=data.getString("achievement", null);
	   int achievement=Integer.parseInt(s);
//	    Log.e("fsdsds", achievement+"");
	    return achievement;
		}
		
		//更新achievement
		public void updateAchievement(int a){
			Editor config=getSharedPreferences("config", 0).edit();
			config.putString("achievement", a+"");
			config.commit();
		}
	//下面为跳转方法
	
	//跳转到主选项菜单的方法，这里需要考虑如果在游戏中退回这里，
	//则需要关闭所有的在游戏中开启的线程
	public void goMainMenuView(){
		//如果在游戏中退回这里
		if(gameViewOne!=null){
			//gameView.stopAllThread();
		}
		if(mainMenuView==null){
			mainMenuView=new MainMenuView(GameActivity.this);
		}
		setContentView(mainMenuView);
		currentView=Constant.GOTO_MAIN_MENU_VIEW;
	}
	//去chooseview界面的方法
	public void goChooseView(){
		if(chooseView==null){
			chooseView=new ChooseView(GameActivity.this);
		}
		setContentView(chooseView);
		currentView=Constant.GOTO_CHOOSE_VIEW;
	}
	//去合作界面的方法
	public void goCoopView(){
		if(coopView==null){
			coopView=new CoopView(GameActivity.this);
		}
		setContentView(coopView);
		currentView=Constant.GOTO_COOP_VIEW;
	}
	//去欢迎界面的方法
	public void goWelcomeView(){
		if(welcomeView==null){
			welcomeView=new WelcomeView(GameActivity.this);
		}
		setContentView(welcomeView);
		currentView=Constant.GOTO_WELCOME_VIEW;
	}
	
	
	//去系统界面的方法
	public void goSystemView(){
		if(SystemView==null){
			SystemView=new SystemView(GameActivity.this);
		}
		setContentView(SystemView);
		currentView=Constant.GOTO_SYSTEM_VIEW;
	}
	
	//去关于界面的方法
		public void goAboutView(){
			if(aboutview==null){
				aboutview=new AboutView(GameActivity.this);
			}
			setContentView(aboutview);
			currentView=Constant.GOTO_ABOUT_VIEW;
		}
		//去帮助界面的方法
		public void goHelpView(){
					if(helpview==null){
						helpview=new HelpView(GameActivity.this);
					}
					setContentView(helpview);
					currentView=Constant.GOTO_HELP_VIEW;
				}
	//去选择界面的方法
	public void goStartGameView(){
		if(startGameView==null){
			startGameView=new StartGameView(GameActivity.this);
		}
		setContentView(startGameView);
		currentView=Constant.GOTO_START_GAME_VIEW;
	}
	
	
	
	//去游戏结束界面的方法
	public void goScoreView(){
		if(scoreview==null){
			scoreview=new ScoreView(GameActivity.this);
		}
		setContentView(scoreview);
		currentView=Constant.GOTO_SCORE_VIEW;
	}
	
	//去双人游戏的界
	public void goGameView(){
		if(gameViewOne==null){
			gameViewOne=new GameViewOne(GameActivity.this,2);
		}
		setContentView(gameViewOne);
		currentView=Constant.GOTO_1;
	}
//去第一关游戏界面的方法
	public void goGameViewOne(){
		if(gameViewOne==null){
			gameViewOne=new GameViewOne(GameActivity.this,3);
		}
		setContentView(gameViewOne);
		currentView=Constant.GOTO_1;
	}
	//去第二关游戏界面的方法
		public void goGameViewTwo(){
			if(gameViewTwo==null){
				gameViewTwo=new GameViewTwo(GameActivity.this,5);
			}
			setContentView(gameViewTwo);
			currentView=Constant.GOTO_2;
		}
	//去第三关游戏界面的方法
		public void goGameViewThree(){
			if(gameViewThree==null){
				gameViewThree=new GameViewThree(GameActivity.this,5);
			}
			setContentView(gameViewThree);
			currentView=Constant.GOTO_3;
		}
	//去第四关游戏界面的方法
		public void goGameViewFour(){
			if(gameViewFour==null){
				gameViewFour=new GameViewFour(GameActivity.this,6);
			}
			setContentView(gameViewFour);
			currentView=Constant.GOTO_4;
		}
		
		//去第五关游戏界面的方法
		public void goGameViewFive(){
			if(gameViewFive==null){
				gameViewFive=new GameViewFive(GameActivity.this,8);
			}
			setContentView(gameViewFive);
			currentView=Constant.GOTO_5;
		}		

		//去第六关游戏界面的方法
		public void goGameViewSix(){
			if(gameViewSix==null){
				gameViewSix=new GameViewSix(GameActivity.this,4);
			}
			
			setContentView(gameViewSix);
			currentView=Constant.GOTO_6;
		}		
				
	//去退出界面的方法
		public void goExitView(){
			if(exitview==null){
				exitview=new ExitView(GameActivity.this);
			}
			setContentView(exitview);
			currentView=Constant.GOTO_EXIT_VIEW;
		}
	//去失败界面的方法
		public void goFailView(){
			if(fail==null){
				fail=new FailView(GameActivity.this);
			}
			setContentView(fail);
			currentView=Constant.GOTO_FAIL_VIEW;
		}
		//去胜利界面的方法
		public void goWinView(){
			if(winview==null){
				winview=new WinView(GameActivity.this);
			}
			setContentView(winview);
			currentView=Constant.GOTO_WIN_VIEW;
		}
	//下面为监听用户按“返回键”和搜索键
	@Override
	    public boolean onKeyDown(int keyCode, KeyEvent e)
	    {
		    switch (keyCode) {
			//4代表的是返回键
		    case 4:
				switch (currentView) {
				case Constant.GOTO_WELCOME_VIEW:
					break;
				case Constant.GOTO_MAIN_MENU_VIEW:
					clearCurrent();
					System.exit(0);
					break;
				case Constant.GOTO_ABOUT_VIEW:
				case Constant.GOTO_HELP_VIEW:
					goSystemView();
					break;
				case Constant.GOTO_1:
				case Constant.GOTO_SYSTEM_VIEW:
				case Constant.GOTO_START_GAME_VIEW:
				case Constant.GOTO_SOUND_CONTORL_VIEW:
				case Constant.GOTO_EXIT_VIEW:
				     goMainMenuView();
				     break;
				}
				return true;
		    case KeyEvent.KEYCODE_SEARCH:
		    	try{
		        	Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		        	i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM); 
		        	i.putExtra(RecognizerIntent.EXTRA_PROMPT, "开始语音" );
		        	startActivityForResult(i,1234);  
		        	}catch(Exception ee){
		        		Toast.makeText(this, "找不到语音设备", Toast.LENGTH_SHORT).show();
		        	}
		    	return true;
				
			}
		    return false;
	    }
	 
	 //发送消息的方法
	 public void sendMessage(int what){
		 Message msg=mHandler.obtainMessage(what);
		 mHandler.sendMessage(msg);
	 }
	 
	//第一次启动游戏时创建配置文件
		 public void createConfig(){
			 Editor config=getSharedPreferences("config", 0).edit();
			 config.putString("game", "拍死那只蚊子");
			 config.putString("author", "山东大学android实验室11级");
			 config.putString("player1", 0+"");
			 config.putString("player2", 0+"");
			 config.putString("player3", 0+"");
			 config.putString("player4", 0+"");
			 config.putString("player5", 0+"");
			 config.putString("current", 0+"");
			 config.putString("achievement", 1+"");
			 config.commit();
		 }
		 //在配置文件中缓存此时游戏积分的方法
		 public void trackGrade(int i){
			 Editor config=getSharedPreferences("config", 0).edit();
			 config.putString("current",i+"");
			 config.commit();
		 }
		 //退出游戏view后，调用方法，比较current和前五位积分，并更新积分榜
		 public void updateGrade(){
			 Editor config=getSharedPreferences("config", 0).edit();
			 SharedPreferences data=getSharedPreferences("config", 0);
			 //获取当前的第五位积分
			 String Splayer5=data.getString("player5", null);
			 int player5=Integer.parseInt(Splayer5);
			 //获取此时积分
			 String Scurrent=data.getString("current", null);
			 int current=Integer.parseInt(Scurrent);
			 if(current<=player5){
			 }
			 //如果此时积分大于第五位
			 if(current>player5){
				 //获取第四位积分
				 String Splayer4=data.getString("player4", null);
				 int player4=Integer.parseInt(Splayer4);
				 if(player4>=current){
					 config.putString("player5", current+"");
				 }
				 //如果第四位小于此时积分
				 if(player4<current){
					 //获取第三位积分
					 String Splayer3=data.getString("player3", null);
					 int player3=Integer.parseInt(Splayer3);
					 if(player3>=current){
						 config.putString("player5", player4+"");
						 config.putString("player4", current+"");
					 }
					 //如果此时积分大于第三位积分
					 if(current>player3){
						//获取第二位积分
						 String Splayer2=data.getString("player2", null);
						 int player2=Integer.parseInt(Splayer2);
						 if(player2>=current){
							 config.putString("player5", player4+"");
							 config.putString("player4", player3+"");
							 config.putString("player3", current+"");
						 }
						 //如果此时积分大于第二位积分
						 if(current>player2){
							//获取第一位积分
							 String Splayer1=data.getString("player1", null);
							 int player1=Integer.parseInt(Splayer1);
							 if(player1>current){
								 config.putString("player5", player4+"");
								 config.putString("player4", player3+"");
								 config.putString("player3", player2+"");
								 config.putString("player2", current+"");
							 }
							 if(current>player1){
								 config.putString("player5", player4+"");
								 config.putString("player4", player3+"");
								 config.putString("player3", player2+"");
								 config.putString("player2", player1+"");
								 config.putString("player1", current+"");
							 }
						 }
					 }
				 }
			 }
			 config.commit();
		 }
		 //清零当前积分
		 public void clearCurrent(){
			 Editor config=getSharedPreferences("config", 0).edit();
			 config.putString("current", 0+"");
			 config.commit();
		 }
		 //获取当前积分
		 public int getCurrent(){
			 SharedPreferences data=getSharedPreferences("config", 0);
			 String s=data.getString("current",null);
			 int i=Integer.parseInt(s);
			 return i;
		 }
		 //获取当前排名的方法，进入积分榜时调用
		 public int[] currentGrade(){
			 SharedPreferences data=getSharedPreferences("config", 0);
			 String Splayer1=data.getString("player1", null);
			 int player1=Integer.parseInt(Splayer1);
			 String Splayer2=data.getString("player2", null);
			 int player2=Integer.parseInt(Splayer2);
			 String Splayer3=data.getString("player3", null);
			 int player3=Integer.parseInt(Splayer3);
			 String Splayer4=data.getString("player4", null);
			 int player4=Integer.parseInt(Splayer4);
			 String Splayer5=data.getString("player5", null);
			 int player5=Integer.parseInt(Splayer5);
			 int[] i={player1,player2,player3,player4,player5};
			 return i;
		 }
	 @Override
	    public void onCreate(Bundle savedInstanceState){
	        super.onCreate(savedInstanceState);    
	        //全屏
			requestWindowFeature(Window.FEATURE_NO_TITLE); 
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
			              WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置为横屏
	        //游戏过程中只允许调整多媒体音量，而不允许调整通话音量
	        setVolumeControlStream(AudioManager.STREAM_MUSIC);
	        //获取分辨率
	        DisplayMetrics dm=new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(dm);  
	        if(initTime==0){
	        	Constant.initConst(dm.widthPixels, dm.heightPixels);//初始化常量
	        	createConfig();
	        	initTime++;
	        }
	        trackGrade(0);
	        updateGrade();
	        currentGrade();
	        goWelcomeView();//去系统界面  
	        //注册接收器
	        receiver = new Receiver();
	        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	        registerReceiver(receiver, filter); 
//	        //wifi部分
//	        mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
//            mChannel = mManager.initialize(this, getMainLooper(), null);
//            wfReceive = new WifiReceive(mManager, mChannel , this);
//            mIntentFilter = new IntentFilter();
//            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
//            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
//            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
//            mIntentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
//	           
//            myPeerListListener=new PeerListListener() {
//				
//				@Override
//				public void onPeersAvailable(WifiP2pDeviceList peers) {
//					// TODO Auto-generated method stub
//					coopView.wifiDevice=peers.getDeviceList();
//					coopView.wifiDeviceList=(WifiP2pDevice[])coopView.wifiDevice.toArray();
//				}
//			};
	 }
	 /* register the broadcast receiver with the intent values to be matched */
	 @Override
	 protected void onResume() {
	     super.onResume();
	 }
	 /* unregister the broadcast receiver */
	 @Override
	 protected void onPause() {
	     super.onPause();
//	     unregisterReceiver(wfReceive);
	 }
	 public class Receiver extends BroadcastReceiver{
        
		@Override
		public void onReceive(Context context, Intent intent) {
			String action=intent.getAction();
			if(action.equals(BluetoothDevice.ACTION_FOUND)){
				BluetoothDevice device=intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				coopView.CurDeviceList.add(device);
				Log.e("CUR", coopView.CurDeviceList.get(0).getName()+"");
				Log.e("搜索", "新设备");
			}
			
		}
		 
	 }
//	 public class WifiReceive extends BroadcastReceiver{
//		 WifiP2pManager manager;
//		 Channel channel;
//		public WifiReceive(WifiP2pManager m,Channel channel,Context c){
//			this.manager=m;
//			this.channel=channel;
//		}
//		@Override
//		public void onReceive(Context arg0, Intent arg1) {
//			  String action = arg1.getAction();
//			    if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
//			        int state = arg1.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
//			        if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED) {
//			               coopView.mHandler.sendEmptyMessage(5);
//			        } else {
//			            // Wi-Fi Direct is not enabled
//			        	coopView.mHandler.sendEmptyMessage(6);
//			        }
//			    }
//			    if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
//
//		    	    // request available peers from the wifi p2p manager. This is an
//		    	    // asynchronous call and the calling activity is notified with a
//		    	    // callback on PeerListListener.onPeersAvailable()
//		    	    if (manager != null) {
//		    	        manager.requestPeers(channel, myPeerListListener);
//		    	    }
//		    	}
//		}
		 
	 }
