package utility;

import java.util.ArrayList;

import mosquito.Mosquito;

import tool.ExpelIncense;
import tool.ExpelPlant;
import tool.Frog;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.userView.GameViewOne;
/**
 * 
 * @author ɽ����ѧandroidʵ�������ѳ�
 *
 */
public class TimeThread extends Thread {
	int i=1;//�����ؿ�ʱ��
	int time=0;
	int steady1=0;//�������ò�1����ʱ��
	int steady2=0;//�������ò�2����ʱ��
	int steady3=0;//�������ò�3����ʱ��
	int steady4=0;//�������ò�4����ʱ��
	int steady5=0;//�������ò�5����ʱ��
	int steady6=0;//�������ò�6����ʱ��
	int killersteady=0;//�������õƳ���ʱ��Ŀ���
	int bornWenzi = 0;//�ڶ������ӳ���ʱ��
	
	
	boolean ToDate=true;//�ؿ�ʱ��
	boolean flag=true;
	boolean daojuFlag1=false;//��ʶ���õ���һ�Ƿ�ʼ��ʱ
	boolean daojuFlag2=false;//��ʶ���õ��߶��Ƿ�ʼ��ʱ
	boolean daojuFlag3=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag4=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag5=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	boolean daojuFlag6=false;//��ʶ���õ������Ƿ�ʼ��ʱ
	
	boolean killerFlagCD=false;//ɱ���ӵ���CD
	boolean killerLastTimeFlag=false;//ɱ���ӵ��߳���ʱ��Ŀ���
	GameViewOne gameview;
	public Handler mhandler;
	public TimeThread(GameViewOne gameview){
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
				killerFlagCD=true;
			case 8:
				killerLastTimeFlag=true;
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
		
		if(bornWenzi==300){
			gameview.Wlist.add(gameview.createWenzi1(1, 1));
			gameview.Wlist.add(gameview.createWenzi2(1, 1));

		}
		
		bornWenzi++;
		//���õ��ߵ�cd����
		if(killerFlagCD)
		{
			gameview.angle+=2;
			gameview.cd=true;
			gameview.cdFlag=false;
			if(gameview.angle>=360)
			{   gameview.cdFlag=true;
				gameview.angle=0;
				this.killerFlagCD=false;
				gameview.cd=false;
			}
			
			
			gameview.changeShuijingXY(gameview.angle);
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
		
	
	//���ܳ���ʱ�����
	if(killerLastTimeFlag){
		Frog frog = gameview.frogList.get(0);
		//���ܲ�������
		if(killersteady==0){
			gameview.step=0;
			

		}
		if(killersteady==10){
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
		if(killersteady==20){
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
		if(killersteady==30){
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
		if(killersteady==40){
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
		if(killersteady==50){
			
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
		if(killersteady==60){
			
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
		if(killersteady==70){
			
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
		if(killersteady==80){
			
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
		if(killersteady==90){
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
		if(killersteady==100){
			
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
		if(killersteady==110){
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
		if(killersteady==120){
		
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
		if(killersteady==130){
			
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
		if(killersteady>=140){
			killerLastTimeFlag=false;
			gameview.FrogSteady=true;
			killersteady=0;
			gameview.mhandler.sendEmptyMessage(8);

		}
		killersteady++;
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
