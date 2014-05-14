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
//��ʶ����SurfaceView�ĳ�����
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
 
 
 
/**
 * 
 * @author ɽ����ѧ�Ա��������ء����������ں���
 *
 */
public class GameActivity extends Activity {
	
	//������ת���
	int currentView;//��ʶ��ǰ�Ľ���
	WelcomeView welcomeView;//��ӭ����
	MainMenuView mainMenuView;//���˵�����
	SystemView SystemView;//��������
	StartGameView startGameView;//�ؿ�ѡ�����
	GameViewOne gameViewOne;//��Ϸʱ�Ľ���
	GameViewTwo gameViewTwo;//��Ϸʱ�Ľ���
	GameViewThree gameViewThree;//��Ϸʱ�Ľ���
	GameViewFour gameViewFour;//��Ϸʱ�Ľ���
	GameViewFive gameViewFive;//��Ϸʱ�Ľ���
	GameViewSix gameViewSix;//��Ϸʱ�Ľ���
	
	ScoreView scoreview;//���ְ����
	ExitView exitview;//�˳�����
	AboutView aboutview;//���ڽ���
	HelpView helpview;//��������
	WinView winview;//ʤ������
	FailView fail;//ʧ�ܽ���
	ChooseView chooseView;//�ؿ�ѡ�����
	public CoopView coopView;//���˺�������
	public  int achievement=1;//��ǵ�ǰ�ؿ���
	public Receiver receiver;
	String action;
	boolean hasAction=false;
	//��Ч��أ���ʱŪ��ֻ�п�������״̬��
	private boolean backGroundMusicOn=true;//���������Ƿ����ı�־
	private boolean soundOn=true;//��Ч�Ƿ����ı�־
	public int score;//����
	//��¼��Ϸ���еĴ���
	static int initTime=0;//��ʼ���Ĵ���
	public static final int requestCode=1;//��������������
	public static final int requestCode2=0;
//	public WifiP2pManager  mManager;
//	public Channel mChannel;
//	public WifiReceive wfReceive;
	public IntentFilter mIntentFilter;
//	public PeerListListener myPeerListListener;
//	public WifiP2pDeviceList WifiDevicelist;
	//�������ڴ���ҳ����ת��handler
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
            	//�������û�д�
    		Intent i=new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(i,requestCode);
            break;
            case Constant.EnableDiscovery:
            	//�򿪿ɼ���
            	Intent discoverableIntent = new
				Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
				discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
				startActivityForResult(discoverableIntent,requestCode2);
            	break;
			}
		}
	};
	//���������Ƿ����ɹ�
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {   
		if(requestCode==1){
		switch(resultCode){
		 case RESULT_OK:
	    	 //��������ɹ���
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
		//��������ʶ����
		else if(requestCode==1234){
			if(resultCode==RESULT_OK){
				ArrayList<String> result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			    action=result.get(0);
			    hasAction=true;
				//�����ǰ�����˵����棬ֻ����ת��start_game/system/exit/score
		        if(currentView==Constant.GOTO_MAIN_MENU_VIEW){
		        	if(action.equals("��ʼ��Ϸ")||action.equals("��Ϸ")){
		        		goStartGameView();
		        	}
		        	if(action.equals("ϵͳ����")||action.equals("����")){
		        		goSystemView();
		        	}
		        	if(action.equals("���ְ�")||action.equals("����")){
		        		goScoreView();
		        	}
		        	if(action.equals("�˳���Ϸ")&&action.equals("�˳�")){
		        		goExitView();
		        	}
		        }
		        //��ǰ��startgame
		        if(currentView==Constant.GOTO_START_GAME_VIEW){
		        	if(action.equals("����ģʽ")||action.equals("����")){
		        		goChooseView();
		        	}
		        	if(action.equals("˫��ģʽ")||action.equals("˫��")||action.equals("����ģʽ")||action.equals("����")){
		        		goCoopView();
		        	}
		        }
		        //��ǰ��ϵͳ����
		    	if(currentView==Constant.GOTO_SYSTEM_VIEW){
		    		if(action.equals("������Ϸ")||action.equals("����")){
		    			goAboutView();
		    		}
		    		if(action.equals("��Ϸ����")||action.equals("����")){
		    			goHelpView();
		    		}
		    	}
		    	//��ǰΪ�˳�����
		    	if(currentView==Constant.GOTO_EXIT_VIEW){
		    		if(action.equals("�˳�")||action.equals("ȷ��")){
		    		sendMessage(Constant.EXIT);
		    		}
		    		if(action.equals("ȡ��")||action.equals("����")){
		    		sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
		    		}
		    		}
		    	if(currentView==Constant.GOTO_CHOOSE_VIEW){
		    		if(action.equals("һ")){
		    		sendMessage(Constant.GOTO_1);
		    		}
		    		if(action.equals("��")){
		    		sendMessage(Constant.GOTO_2);
		    		}
		    		if(action.equals("��")){
		    		sendMessage(Constant.GOTO_3);
		    		}
		    		if(action.equals("��")){
		    		sendMessage(Constant.GOTO_4);
		    		}
		    		if(action.equals("��")){
		    		sendMessage(Constant.GOTO_5);
		    		}
		    		if(action.equals("��")){
		    		sendMessage(Constant.GOTO_6);
		    		}
		    		if(action.equals("��")){
		    		sendMessage(Constant.GOTO_7);
		    		}
		    		if(action.equals("��")){
		    		sendMessage(Constant.GOTO_8);
		    		}
		    		if(action.equals("����")||action.equals("ȡ��")){
		    		sendMessage(Constant.GOTO_MAIN_MENU_VIEW);
		    		}
		    		}
			}else{
				Toast.makeText(this,"Ҫʹ���������ܣ�����������������", Toast.LENGTH_LONG).show();
			}
		}
		}//��ֹ�û�˵����һ�����������ģ����ѯ����
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
		//����achievement�Ĳ���
		
		//��ȡ��ǰachievement
		public int getAchievement(){
	    SharedPreferences data=getSharedPreferences("config", 0);
	    String s=data.getString("achievement", null);
	   int achievement=Integer.parseInt(s);
//	    Log.e("fsdsds", achievement+"");
	    return achievement;
		}
		
		//����achievement
		public void updateAchievement(int a){
			Editor config=getSharedPreferences("config", 0).edit();
			config.putString("achievement", a+"");
			config.commit();
		}
	//����Ϊ��ת����
	
	//��ת����ѡ��˵��ķ�����������Ҫ�����������Ϸ���˻����
	//����Ҫ�ر����е�����Ϸ�п������߳�
	public void goMainMenuView(){
		//�������Ϸ���˻�����
		if(gameViewOne!=null){
			//gameView.stopAllThread();
		}
		if(mainMenuView==null){
			mainMenuView=new MainMenuView(GameActivity.this);
		}
		setContentView(mainMenuView);
		currentView=Constant.GOTO_MAIN_MENU_VIEW;
	}
	//ȥchooseview����ķ���
	public void goChooseView(){
		if(chooseView==null){
			chooseView=new ChooseView(GameActivity.this);
		}
		setContentView(chooseView);
		currentView=Constant.GOTO_CHOOSE_VIEW;
	}
	//ȥ��������ķ���
	public void goCoopView(){
		if(coopView==null){
			coopView=new CoopView(GameActivity.this);
		}
		setContentView(coopView);
		currentView=Constant.GOTO_COOP_VIEW;
	}
	//ȥ��ӭ����ķ���
	public void goWelcomeView(){
		if(welcomeView==null){
			welcomeView=new WelcomeView(GameActivity.this);
		}
		setContentView(welcomeView);
		currentView=Constant.GOTO_WELCOME_VIEW;
	}
	
	
	//ȥϵͳ����ķ���
	public void goSystemView(){
		if(SystemView==null){
			SystemView=new SystemView(GameActivity.this);
		}
		setContentView(SystemView);
		currentView=Constant.GOTO_SYSTEM_VIEW;
	}
	
	//ȥ���ڽ���ķ���
		public void goAboutView(){
			if(aboutview==null){
				aboutview=new AboutView(GameActivity.this);
			}
			setContentView(aboutview);
			currentView=Constant.GOTO_ABOUT_VIEW;
		}
		//ȥ��������ķ���
		public void goHelpView(){
					if(helpview==null){
						helpview=new HelpView(GameActivity.this);
					}
					setContentView(helpview);
					currentView=Constant.GOTO_HELP_VIEW;
				}
	//ȥѡ�����ķ���
	public void goStartGameView(){
		if(startGameView==null){
			startGameView=new StartGameView(GameActivity.this);
		}
		setContentView(startGameView);
		currentView=Constant.GOTO_START_GAME_VIEW;
	}
	
	
	
	//ȥ��Ϸ��������ķ���
	public void goScoreView(){
		if(scoreview==null){
			scoreview=new ScoreView(GameActivity.this);
		}
		setContentView(scoreview);
		currentView=Constant.GOTO_SCORE_VIEW;
	}
	
	//ȥ˫����Ϸ�Ľ�
	public void goGameView(){
		if(gameViewOne==null){
			gameViewOne=new GameViewOne(GameActivity.this,2);
		}
		setContentView(gameViewOne);
		currentView=Constant.GOTO_1;
	}
//ȥ��һ����Ϸ����ķ���
	public void goGameViewOne(){
		if(gameViewOne==null){
			gameViewOne=new GameViewOne(GameActivity.this,3);
		}
		setContentView(gameViewOne);
		currentView=Constant.GOTO_1;
	}
	//ȥ�ڶ�����Ϸ����ķ���
		public void goGameViewTwo(){
			if(gameViewTwo==null){
				gameViewTwo=new GameViewTwo(GameActivity.this,5);
			}
			setContentView(gameViewTwo);
			currentView=Constant.GOTO_2;
		}
	//ȥ��������Ϸ����ķ���
		public void goGameViewThree(){
			if(gameViewThree==null){
				gameViewThree=new GameViewThree(GameActivity.this,5);
			}
			setContentView(gameViewThree);
			currentView=Constant.GOTO_3;
		}
	//ȥ���Ĺ���Ϸ����ķ���
		public void goGameViewFour(){
			if(gameViewFour==null){
				gameViewFour=new GameViewFour(GameActivity.this,6);
			}
			setContentView(gameViewFour);
			currentView=Constant.GOTO_4;
		}
		
		//ȥ�������Ϸ����ķ���
		public void goGameViewFive(){
			if(gameViewFive==null){
				gameViewFive=new GameViewFive(GameActivity.this,8);
			}
			setContentView(gameViewFive);
			currentView=Constant.GOTO_5;
		}		

		//ȥ��������Ϸ����ķ���
		public void goGameViewSix(){
			if(gameViewSix==null){
				gameViewSix=new GameViewSix(GameActivity.this,4);
			}
			
			setContentView(gameViewSix);
			currentView=Constant.GOTO_6;
		}		
				
	//ȥ�˳�����ķ���
		public void goExitView(){
			if(exitview==null){
				exitview=new ExitView(GameActivity.this);
			}
			setContentView(exitview);
			currentView=Constant.GOTO_EXIT_VIEW;
		}
	//ȥʧ�ܽ���ķ���
		public void goFailView(){
			if(fail==null){
				fail=new FailView(GameActivity.this);
			}
			setContentView(fail);
			currentView=Constant.GOTO_FAIL_VIEW;
		}
		//ȥʤ������ķ���
		public void goWinView(){
			if(winview==null){
				winview=new WinView(GameActivity.this);
			}
			setContentView(winview);
			currentView=Constant.GOTO_WIN_VIEW;
		}
	//����Ϊ�����û��������ؼ�����������
	@Override
	    public boolean onKeyDown(int keyCode, KeyEvent e)
	    {
		    switch (keyCode) {
			//4������Ƿ��ؼ�
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
		        	i.putExtra(RecognizerIntent.EXTRA_PROMPT, "��ʼ����" );
		        	startActivityForResult(i,1234);  
		        	}catch(Exception ee){
		        		Toast.makeText(this, "�Ҳ��������豸", Toast.LENGTH_SHORT).show();
		        	}
		    	return true;
				
			}
		    return false;
	    }
	 
	 //������Ϣ�ķ���
	 public void sendMessage(int what){
		 Message msg=mHandler.obtainMessage(what);
		 mHandler.sendMessage(msg);
	 }
	 
	//��һ��������Ϸʱ���������ļ�
		 public void createConfig(){
			 Editor config=getSharedPreferences("config", 0).edit();
			 config.putString("game", "������ֻ����");
			 config.putString("author", "ɽ����ѧandroidʵ����11��");
			 config.putString("player1", 0+"");
			 config.putString("player2", 0+"");
			 config.putString("player3", 0+"");
			 config.putString("player4", 0+"");
			 config.putString("player5", 0+"");
			 config.putString("current", 0+"");
			 config.putString("achievement", 1+"");
			 config.commit();
		 }
		 //�������ļ��л����ʱ��Ϸ���ֵķ���
		 public void trackGrade(int i){
			 Editor config=getSharedPreferences("config", 0).edit();
			 config.putString("current",i+"");
			 config.commit();
		 }
		 //�˳���Ϸview�󣬵��÷������Ƚ�current��ǰ��λ���֣������»��ְ�
		 public void updateGrade(){
			 Editor config=getSharedPreferences("config", 0).edit();
			 SharedPreferences data=getSharedPreferences("config", 0);
			 //��ȡ��ǰ�ĵ���λ����
			 String Splayer5=data.getString("player5", null);
			 int player5=Integer.parseInt(Splayer5);
			 //��ȡ��ʱ����
			 String Scurrent=data.getString("current", null);
			 int current=Integer.parseInt(Scurrent);
			 if(current<=player5){
			 }
			 //�����ʱ���ִ��ڵ���λ
			 if(current>player5){
				 //��ȡ����λ����
				 String Splayer4=data.getString("player4", null);
				 int player4=Integer.parseInt(Splayer4);
				 if(player4>=current){
					 config.putString("player5", current+"");
				 }
				 //�������λС�ڴ�ʱ����
				 if(player4<current){
					 //��ȡ����λ����
					 String Splayer3=data.getString("player3", null);
					 int player3=Integer.parseInt(Splayer3);
					 if(player3>=current){
						 config.putString("player5", player4+"");
						 config.putString("player4", current+"");
					 }
					 //�����ʱ���ִ��ڵ���λ����
					 if(current>player3){
						//��ȡ�ڶ�λ����
						 String Splayer2=data.getString("player2", null);
						 int player2=Integer.parseInt(Splayer2);
						 if(player2>=current){
							 config.putString("player5", player4+"");
							 config.putString("player4", player3+"");
							 config.putString("player3", current+"");
						 }
						 //�����ʱ���ִ��ڵڶ�λ����
						 if(current>player2){
							//��ȡ��һλ����
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
		 //���㵱ǰ����
		 public void clearCurrent(){
			 Editor config=getSharedPreferences("config", 0).edit();
			 config.putString("current", 0+"");
			 config.commit();
		 }
		 //��ȡ��ǰ����
		 public int getCurrent(){
			 SharedPreferences data=getSharedPreferences("config", 0);
			 String s=data.getString("current",null);
			 int i=Integer.parseInt(s);
			 return i;
		 }
		 //��ȡ��ǰ�����ķ�����������ְ�ʱ����
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
	        //ȫ��
			requestWindowFeature(Window.FEATURE_NO_TITLE); 
			getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
			              WindowManager.LayoutParams.FLAG_FULLSCREEN);
	        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//����Ϊ����
	        //��Ϸ������ֻ���������ý�������������������ͨ������
	        setVolumeControlStream(AudioManager.STREAM_MUSIC);
	        //��ȡ�ֱ���
	        DisplayMetrics dm=new DisplayMetrics();
	        getWindowManager().getDefaultDisplay().getMetrics(dm);  
	        if(initTime==0){
	        	Constant.initConst(dm.widthPixels, dm.heightPixels);//��ʼ������
	        	createConfig();
	        	initTime++;
	        }
	        trackGrade(0);
	        updateGrade();
	        currentGrade();
	        goWelcomeView();//ȥϵͳ����  
	        //ע�������
	        receiver = new Receiver();
	        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
	        registerReceiver(receiver, filter); 
//	        //wifi����
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
				Log.e("����", "���豸");
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
