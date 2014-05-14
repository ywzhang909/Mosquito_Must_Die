package com.publicClass;




import utility.ScreenScaleResult;


/**
 * 
 * @author ɽ����ѧ�Ա��������ء����������ں���
 *
 */
public class Constant {
	//������Ϸ����
	public static final String GAME_NAME="game3";
	//��Ļ����
	public static int SCREEN_WIDTH;//��Ļ�Ŀ��
	public static int SCREEN_HEIGHT;//��Ļ�ĸ߶�
	
	public static ScreenScaleResult ssr;//��Ļ���ż���Ľ��
	public static final int EXIT = -1;// �˳���Ϸ
	public static final int GOTO_FAIL_VIEW = -2;// ʧ�ܽ���
	public static final int GOTO_WIN_VIEW = -3;// ���ؽ���
	public static final int GOTO_WELCOME_VIEW = 0;// ��ӭ����
	public static final int GOTO_MAIN_MENU_VIEW = 1;// ���˵�����//��Ϸ�˵���ѡ��˵�
	public static final int GOTO_START_GAME_VIEW = 2;// �ؿ�ѡ�����
	public static final int GOTO_SOUND_CONTORL_VIEW = 3;// �������ƽ���
	public static final int GOTO_SYSTEM_VIEW = 4;// ��������//��Ϸ�淨
	public static final int GOTO_1 = 6;// ��Ϸ����
	public static final int GOTO_SCORE_VIEW = 7;// ��Ϸ����
	public static final int GOTO_EXIT_VIEW = 8;// �˳�����
	public static final int GOTO_HELP_VIEW = 9;// ��Ϸ����
	public static final int GOTO_ABOUT_VIEW = 10;// ���ڽ���//��Ϸ�汾�Ͱ�Ȩ
	public static final int GOTO_CHOOSE_VIEW=11;
	public static final int GOTO_COOP_VIEW=99;
	public static final int GOTO_2 = 12;
	public static final int GOTO_3 = 13;
	public static final int GOTO_4 = 14;
	public static final int GOTO_5 = 15;
	public static final int GOTO_6 = 16;
	public static final int GOTO_7 = 17;
	public static final int GOTO_8 = 18;
	public static final int EnableBluetooth=100;//��������
	public static final int EnableDiscovery=200;//���豸�����ɼ���
	public static final int GOTO_1B=110;
	//size=20*12
	//��һ�ص�ͼ
			public static final int[][] MAP1=//0 ����ͨ�� 1��ͨ��
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
			//�ڶ��ص�ͼ
			public static final int[][] MAP2=//0 ����ͨ�� 1��ͨ��
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
			//�����ص�ͼ
					public static final int[][] MAP3=//0 ����ͨ�� 1��ͨ��
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
					//���Ĺص�ͼ
					public static final int[][] MAP4=//0 ����ͨ�� 1��ͨ��
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
					
					//����ص�ͼ
					public static final int[][] MAP5=//0 ����ͨ�� 1��ͨ��
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
		
				//�����ص�ͼ
				public static final int[][] MAP6=//0 ����ͨ�� 1��ͨ��
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
								
			
				
				
	//��������Ч��ť
	public static float SOUND_BTN_Y1=50;
	public static float SOUND_BTN_Y2=120;
	//�ֱ�����������
	public static final int G_WIDTH=20;//������6������
	public static final int G_HEIGHT=12;//������10������
	public static final int G_SIZE=40;//ÿ�����ӿ�߷ֱ��ʾ�Ϊ20
	//��Ӧȫ�������ű���
	public static float wRatio;
	public static float hRatio;
	//����С�����ĳ���
		public static float BMP_Y=150;
		static float HELP_Y=150;
	
	static int screenWidthTest=800;//���Ի���Ļ���
	static int screenHeightTest=480;//���Ի���Ļ�߶�
	
	//��Ϸ�������Ͻǵ�����
	public static float X_OFFSET;
	public static float Y_OFFSET;
	
	public static void initConst(int screenWidth,int screenHeight)
	{   
		//��Ļ�ĳߴ�
		SCREEN_WIDTH=screenWidth;
		SCREEN_HEIGHT=screenHeight;
		
		//��Ӧȫ�������ű���(����֤��ͬ�ߴ��С����Ļ����ʱ����ȫ��)
		wRatio=screenWidth/(float)screenWidthTest;
		hRatio=screenHeight/(float)screenHeightTest;
		//������Ļ�Ľ��
		ssr=utility.ScreenScaleUtil.calScale(screenWidth, screenHeight);		
		X_OFFSET=ssr.lcuX;
		Y_OFFSET=ssr.lcuY;

	}
	//�����ڶ�������Ϊ��x�����ϵģ���������һ������Ϊ�ߣ�y�����ϵģ�
	public static boolean isPointInRect//һ�����Ƿ��ھ����ڣ������߽磩
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
