package com.publicClass;




import utility.ScreenScaleResult;


/**
 * 
 * @author 山东大学赵宝琦、张霖、吕华富、于洪洋
 *
 */
public class Constant {
	//多人游戏名称
	public static final String GAME_NAME="game3";
	//屏幕参数
	public static int SCREEN_WIDTH;//屏幕的宽度
	public static int SCREEN_HEIGHT;//屏幕的高度
	
	public static ScreenScaleResult ssr;//屏幕缩放计算的结果
	public static final int EXIT = -1;// 退出游戏
	public static final int GOTO_FAIL_VIEW = -2;// 失败界面
	public static final int GOTO_WIN_VIEW = -3;// 过关界面
	public static final int GOTO_WELCOME_VIEW = 0;// 欢迎界面
	public static final int GOTO_MAIN_MENU_VIEW = 1;// 主菜单界面//游戏菜单的选项菜单
	public static final int GOTO_START_GAME_VIEW = 2;// 关卡选择界面
	public static final int GOTO_SOUND_CONTORL_VIEW = 3;// 声音控制界面
	public static final int GOTO_SYSTEM_VIEW = 4;// 帮助界面//游戏玩法
	public static final int GOTO_1 = 6;// 游戏界面
	public static final int GOTO_SCORE_VIEW = 7;// 游戏积分
	public static final int GOTO_EXIT_VIEW = 8;// 退出界面
	public static final int GOTO_HELP_VIEW = 9;// 游戏帮助
	public static final int GOTO_ABOUT_VIEW = 10;// 关于界面//游戏版本和版权
	public static final int GOTO_CHOOSE_VIEW=11;
	public static final int GOTO_COOP_VIEW=99;
	public static final int GOTO_2 = 12;
	public static final int GOTO_3 = 13;
	public static final int GOTO_4 = 14;
	public static final int GOTO_5 = 15;
	public static final int GOTO_6 = 16;
	public static final int GOTO_7 = 17;
	public static final int GOTO_8 = 18;
	public static final int EnableBluetooth=100;//开启蓝牙
	public static final int EnableDiscovery=200;//打开设备蓝牙可见性
	public static final int GOTO_1B=110;
	//size=20*12
	//第一关地图
			public static final int[][] MAP1=//0 不可通过 1可通过
				{
					
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
					{0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0},
					{0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0},
					{0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0},
					{0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0},
					{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
					{0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
				};
			//第二关地图
			public static final int[][] MAP2=//0 不可通过 1可通过
						{
							
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
							{0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1,0},
							{0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
							{0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
							{0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
							{0,1,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0},
							{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						};
			//第三关地图
					public static final int[][] MAP3=//0 不可通过 1可通过
								{
									
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0},
									{0,1,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,0},
									{0,1,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,0},
									{0,1,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,0},
									{0,1,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,0},
									{0,1,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,0},
									{0,1,0,0,0,0,0,0,0,0,0,1,0,1,1,1,1,1,1,0},
									{0,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1,1,1,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
									{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
								};	
					//第四关地图
					public static final int[][] MAP4=//0 不可通过 1可通过
						{
							
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
							{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
							{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
							{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
							{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
							{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
							{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
							{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
							{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						};		
					
					//第五关地图
					public static final int[][] MAP5=//0 不可通过 1可通过
						{
							

						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
						{0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,1,0},
						{0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,1,0},
						{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
						{0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,1,0},
						{0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,1,0},
						{0,1,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,1,0},
						{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
						{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					};
		
				//第六关地图
				public static final int[][] MAP6=//0 不可通过 1可通过
					{
						


					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
					{0,1,-1,-1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,1,1,1,1,1,-1,-1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
					{0,1,-1,-1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
					{0,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
					
					};		
								
			
				
				
	//音乐与音效按钮
	public static float SOUND_BTN_Y1=50;
	public static float SOUND_BTN_Y2=120;
	//分辨率与格子相关
	public static final int G_WIDTH=20;//横着有6个格子
	public static final int G_HEIGHT=12;//竖着有10个格子
	public static final int G_SIZE=40;//每个格子宽高分辨率均为20
	//适应全屏的缩放比例
	public static float wRatio;
	public static float hRatio;
	//关于小背景的常量
		public static float BMP_Y=150;
		static float HELP_Y=150;
	
	static int screenWidthTest=800;//测试机屏幕宽度
	static int screenHeightTest=480;//测试机屏幕高度
	
	//游戏界面左上角的坐标
	public static float X_OFFSET;
	public static float Y_OFFSET;
	
	public static void initConst(int screenWidth,int screenHeight)
	{   
		//屏幕的尺寸
		SCREEN_WIDTH=screenWidth;
		SCREEN_HEIGHT=screenHeight;
		
		//适应全屏的缩放比例(即保证不同尺寸大小的屏幕运行时可以全屏)
		wRatio=screenWidth/(float)screenWidthTest;
		hRatio=screenHeight/(float)screenHeightTest;
		//计算屏幕的结果
		ssr=utility.ScreenScaleUtil.calScale(screenWidth, screenHeight);		
		X_OFFSET=ssr.lcuX;
		Y_OFFSET=ssr.lcuY;

	}
	//倒数第二个参数为宽（x方向上的），倒数第一个参数为高（y方向上的）
	public static boolean isPointInRect//一个点是否在矩形内（包括边界）
	(
			float pointx,float pointy,
			float xLeftTop,float yLeftTop,float length,float height
	)
	{
		if(
				pointx>=xLeftTop&&pointx<=xLeftTop+length&&
				pointy>=yLeftTop&&pointy<=yLeftTop+height
		  )
		  {
			  return true;
		  }
		return false;
	}
}
