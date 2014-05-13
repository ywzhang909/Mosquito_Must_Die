package utility;

import java.util.ArrayList;

import mosquito.Mosquito;

import tool.ExpelIncense;
import tool.ExpelPlant;
import tool.Frog;
import tool.killspray;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.userView.GameViewOne;
import com.userView.GameViewTwo;
/**
 * 
 * @author ɽ����ѧandroidʵ�������ѳ�
 *
 */
public class TimeThread2 extends Thread {
	int i=1;//�����ؿ�ʱ��
	int time=0;
	int steady1=0;//�������ò�1����ʱ��
	int steady2=0;//�������ò�2����ʱ��
	int steady3=0;//�������ò�3����ʱ��
	int steady4=0;//�������ò�4����ʱ��
	int steady5=0;//�������ò�5����ʱ��
	int steady6=0;//�������ò�6����ʱ��
	int bornWenzi = 0;//�ڶ������ӳ���ʱ��

	boolean ToDate=true;//�ؿ�ʱ��
	boolean flag=true;
	boolean daojuFlag1=false;//��ʶ���õ���һ�Ƿ�ʼ��ʱ
	boolean daojuFlag2=false;//��ʶ���õ��߶��Ƿ�ʼ��ʱ
	boolean daojuFlag3=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag4=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag5=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag6=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	
	int frogsteady=0;//�������õƳ���ʱ��Ŀ���
	boolean frogFlagCD=false;//���ܵ���CD
	boolean frogLastTimeFlag=false;//���ܵ��߳���ʱ��Ŀ���
	
	int spraysteady=0;//�������ü�����ʱ��Ŀ���
	boolean sprayFlagCD=false;//���ü�����CD
	boolean sprayLastTimeFlag=false;//���ü����߳���ʱ��Ŀ���
	
	GameViewTwo gameview;
	 public Handler mhandler;
	public TimeThread2(GameViewTwo gameview){
		this.gameview=gameview;

 mhandler=new Handler(){
		public void handleMessage(Message msg){
			switch(msg.what){
			//case1,case2��������
			case 1:
				daojuFlag1=true;
				break;
			case 2:
				daojuFlag2=true;
				break;
			case 3:
				daojuFlag3=true;
				break;
			case 4:
				daojuFlag4=true;
				break;
			case 5:
				daojuFlag5=true;
				break;
			case 6:
				daojuFlag6=true;
				break;			
			case 7:
				frogFlagCD=true;
			case 8:
				frogLastTimeFlag=true;
				break;
				
			case 9:
				sprayFlagCD=true;
			case 10:
				sprayLastTimeFlag=true;
				break;
				
			}
			
		}
	};
	}
	@Override
    public void run(){
	while(ToDate){
		
		//��ʱ����һ���ֱ仯һ��
		if(time==20){
			 gameview.timer.subtractTime(1);
			 time=0;
		}
		time++;
		
		if(bornWenzi==1000){
			gameview.Wlist.add(gameview.createWenzi2(1, 1));
			gameview.Wlist.add(gameview.createWenzi4(1, 1));
		}
		
		bornWenzi++;
		
		//���õ��ߵ�cd����
		if(frogFlagCD)
		{
			gameview.angle1+=1;
			gameview.cd1=true;
			gameview.cdFlag1=false;
			if(gameview.angle1>=360)
			{   gameview.cdFlag1=true;
				gameview.angle1=0;
				this.frogFlagCD=false;
				gameview.cd1=false;
			}
			
			
			gameview.changeShuijingXY1(gameview.angle1);
		}	
		
		if(sprayFlagCD)
		{
			gameview.angle2+=1;
			gameview.cd2=true;
			gameview.cdFlag2=false;
			if(gameview.angle2>=360)
			{   gameview.cdFlag2=true;
				gameview.angle2=0;
				this.sprayFlagCD=false;
				gameview.cd2=false;
			}
			
			
			gameview.changeShuijingXY2(gameview.angle2);
		}				
		
	try {
		Thread.sleep(50);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//ǰ��ֻ���ӳ���
	if(i==80){
		//gameview.isBornWenzi=true;
	}
	
	//��һ��ʱ�䵽�������ж��Ƿ���Ϸʤ���ķ���
	if(i>3600){
		ToDate=false;
	}
	i++;
	//��һ�����õ���
	if(daojuFlag1){
		
		ExpelPlant temp = gameview.expelPlantList.get(0);
		if(temp.level==1)
			steady1+=2;
			else 
			steady1++;

		//�ڶ���ͼƬ
		if(steady1==30){
			temp.state = 1;
			gameview.expelPlantList.set(0, temp);
			}
		//������ͼƬ
		if(steady1==60){
			temp.state = 2;
			gameview.expelPlantList.set(0, temp);	
			}
		//������ͼƬ
		if(steady1==90){
			temp.state = 3;
			gameview.expelPlantList.set(0, temp);
			}
		//����ʱ��
		if(steady1==120){
			gameview.mhandler.sendEmptyMessage(1);
			daojuFlag1=false;
		}
	}
	//�ڶ������õ���
	if(daojuFlag2){
		
		ExpelPlant temp = gameview.expelPlantList.get(1);
		if(temp.level==1)
			steady2+=2;
			else 
			steady2+=1;

		//�ڶ���ͼƬ
		if(steady2==30){
			temp.state = 1;
			gameview.expelPlantList.set(1, temp);
		}
		//������ͼƬ
		if(steady2==60){
			temp.state = 2;
			gameview.expelPlantList.set(1, temp);
		}
		//������ͼƬ
		if(steady2==90){
			temp.state = 3;
			gameview.expelPlantList.set(1, temp);
		}
		//����ʱ��
		if(steady2==120){
			gameview.mhandler.sendEmptyMessage(2);
			daojuFlag2=false;
		}
	}
	//���������õ���
		if(daojuFlag3){
			
			ExpelPlant temp = gameview.expelPlantList.get(2);

			if(temp!=null){
			if(temp.level==1)
				steady3+=2;
				else if(temp.level==2)
				steady3+=1;
			}

			//�ڶ���ͼƬ
			if(steady3==30){
				temp.state = 1;
				gameview.expelPlantList.set(2, temp);
				}
			//������ͼƬ
			if(steady3==60){
				temp.state = 2;
				gameview.expelPlantList.set(2, temp);
				}
			//������ͼƬ
			if(steady3==90){
				temp.state = 3;
				gameview.expelPlantList.set(2, temp);
				}
			//����ʱ��
			if(steady3==120){
				gameview.mhandler.sendEmptyMessage(3);
				daojuFlag3=false;
			}
		}
		//���ĸ����õ���
		if(daojuFlag4){
			ExpelPlant temp = gameview.expelPlantList.get(3);

			if(temp.level==1)
				steady4+=2;
				else 
				steady4+=1;

			//�ڶ���ͼƬ
			if(steady4==30){
				temp.state = 1;
				gameview.expelPlantList.set(3, temp);
				}
			//������ͼƬ
			if(steady4==60){
				temp.state = 2;
				gameview.expelPlantList.set(3, temp);
				}
			//������ͼƬ
			if(steady4==90){
				temp.state = 3;
				gameview.expelPlantList.set(3, temp);
				}
			//����ʱ��
			if(steady4==120){
				gameview.mhandler.sendEmptyMessage(4);
				daojuFlag4=false;
			}
		}
		//��������õ���
		if(daojuFlag5){
			ExpelPlant temp = gameview.expelPlantList.get(4);

			if(temp.level==1)
				steady5+=2;
				else 
				steady5+=1;

			//�ڶ���ͼƬ
			if(steady5==30){
				temp.state = 1;
				gameview.expelPlantList.set(4, temp);
				}
			//������ͼƬ
			if(steady5==60){
				temp.state = 2;
				gameview.expelPlantList.set(4, temp);
				}
			//������ͼƬ
			if(steady5==90){
				temp.state = 3;
				gameview.expelPlantList.set(4, temp);
				}
			//����ʱ��
			if(steady5==120){
				gameview.mhandler.sendEmptyMessage(5);
				daojuFlag5=false;
			}
		}
		//���������õ���
		if(daojuFlag6){
			ExpelPlant temp = gameview.expelPlantList.get(5);

			if(temp.level==1)
				steady6+=2;
				else 
				steady6+=1;

			//�ڶ���ͼƬ
			if(steady6==30){
				temp.state = 1;
				gameview.expelPlantList.set(5, temp);
				}
			//������ͼƬ
			if(steady6==60){
				temp.state = 2;
				gameview.expelPlantList.set(5, temp);
				}
			//������ͼƬ
			if(steady6==90){
				temp.state = 3;
				gameview.expelPlantList.set(5, temp);
				}
			//����ʱ��
			if(steady6==120){
				gameview.mhandler.sendEmptyMessage(6);
				daojuFlag6=false;
			}
		}
		
	
	//���ü�����ʱ�����
	if(sprayLastTimeFlag){
		killspray spray = gameview.sprayList.get(0);
		if(spray.level==1)
		spraysteady++;
		if(spray.level==2)
			spraysteady+=2;
		
		//�ڶ���ͼƬ
		if(spraysteady==30){
			spray.state = 1;
			gameview.sprayList.set(0, spray);
			}
		//������ͼƬ
		if(spraysteady==60){
			spray.state = 2;
			gameview.sprayList.set(0, spray);
			}
		//������ͼƬ
		if(spraysteady==90){
			sprayLastTimeFlag=false;
			spraysteady=0;
			gameview.mhandler.sendEmptyMessage(7);
			}
		
		
	}
	
	//���ܳ���ʱ�����
		if(frogLastTimeFlag){
			Frog frog = gameview.frogList.get(0);
			//���ܲ�������
			if(frogsteady==0){
				gameview.step=0;
				

			}
			if(frogsteady==15){
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=1;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}

			}
			if(frogsteady==30){
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=2;			
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==45){
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=3;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==60){
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=4;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==75){
				
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=5;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==90){
				
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=6;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==105){
				
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=7;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==120){
				
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=8;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==135){
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=9;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==150){
				
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=10;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==165){
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=11;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==180){
			
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=12;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady==195){
				
				gameview.map = frog.reMap(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
				gameview.step=13;
				gameview.map = frog.effect(gameview.map);
				for (Mosquito m : gameview.Wlist) {
					if(m!=null)
					m.setmap(gameview.map);
				}
			}
			if(frogsteady>=210){
				frogLastTimeFlag=false;
				gameview.FrogSteady=true;
				frogsteady=0;
				gameview.mhandler.sendEmptyMessage(8);

			}
			frogsteady++;
		}
	
}
	}
	
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public void setToDate(boolean flag){
		this.ToDate=flag;
	}
	
}
