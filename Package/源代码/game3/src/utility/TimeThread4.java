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

import com.userView.GameViewFour;
import com.userView.GameViewOne;
import com.userView.GameViewThree;
import com.userView.GameViewTwo;
/**
 * 
 * @author ɽ����ѧandroidʵ�������ѳ�
 *
 */
public class TimeThread4 extends Thread {
	int i=1;//�����ؿ�ʱ��
	int time=0;

	int bornWenzi = 0;//�ڶ������ӳ���ʱ��

	int xiangsteady1=0;//��������1����ʱ��
	int xiangsteady2=0;//��������2����ʱ��
	int xiangsteady3=0;//��������3����ʱ��
	int xiangsteady4=0;//��������4����ʱ��
	int xiangsteady5=0;//��������5����ʱ��
	int xiangsteady6=0;//��������6����ʱ��
	
	boolean ToDate=true;//�ؿ�ʱ��
	boolean flag=true;
	
	boolean xiangFlag1=false;//��ʶ��������1�Ƿ�ʼ��ʱ
	boolean xiangFlag2=false;//��ʶ��������2�Ƿ�ʼ��ʱ
	boolean xiangFlag3=false;//��ʶ��������3�Ƿ�ʼ��ʱ
	boolean xiangFlag4=false;//��ʶ��������4�Ƿ�ʼ��ʱ
	boolean xiangFlag5=false;//��ʶ��������5�Ƿ�ʼ��ʱ
	boolean xiangFlag6=false;//��ʶ��������6�Ƿ�ʼ��ʱ
	
	GameViewFour gameview;
	 public Handler mhandler;
	public TimeThread4(GameViewFour gameViewFour){
		this.gameview=gameViewFour;
		mhandler=new Handler(){
			public void handleMessage(Message msg){
				switch(msg.what){
				//case1,case2��������
				case 1:
					xiangFlag1=true;
					break;
				case 2:
					xiangFlag2=true;
					break;
				case 3:
					xiangFlag3=true;
					break;		
				case 4:
					xiangFlag4=true;
					break;
				case 5:
					xiangFlag5=true;
					break;
				case 6:
					xiangFlag6=true;
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

		if(bornWenzi==800){
			gameview.Wlist.add(gameview.createWenzi4(1, 1));
			gameview.Wlist.add(gameview.createWenzi5(1, 1));
		}
		
		bornWenzi++;
		
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

		
		//��һ���������
		if(xiangFlag1){
			ExpelIncense temp = gameview.incenseList.get(0);

			if(temp.level==1)
				xiangsteady1+=2;
				else 
				xiangsteady1+=1;

			//�ڶ���ͼƬ
			if(xiangsteady1==30){
				temp.state = 1;
				gameview.incenseList.set(0, temp);
				}
			//������ͼƬ
			if(xiangsteady1==60){
				temp.state = 2;
				gameview.incenseList.set(0, temp);
				}
			//������ͼƬ
			if(xiangsteady1==90){
				temp.state = 3;
				gameview.incenseList.set(0, temp);
				}
			//����ʱ��
			if(xiangsteady1==120){
				gameview.mhandler.sendEmptyMessage(1);
				xiangFlag1=false;
			}
		}
		//�ڶ����������
				if(xiangFlag2){
					ExpelIncense temp = gameview.incenseList.get(1);

					if(temp.level==1)
						xiangsteady2+=2;
						else 
						xiangsteady2+=1;

					//�ڶ���ͼƬ
					if(xiangsteady2==30){
						temp.state = 1;
						gameview.incenseList.set(1, temp);
						}
					//������ͼƬ
					if(xiangsteady2==60){
						temp.state = 2;
						gameview.incenseList.set(1, temp);
						}
					//������ͼƬ
					if(xiangsteady2==90){
						temp.state = 3;
						gameview.incenseList.set(1, temp);
						}
					//����ʱ��
					if(xiangsteady2==120){
						gameview.mhandler.sendEmptyMessage(2);
						xiangFlag2=false;
					}
				}
				//�������������
				if(xiangFlag3){
					ExpelIncense temp = gameview.incenseList.get(2);

					if(temp.level==1)
						xiangsteady3+=2;
						else 
						xiangsteady3+=1;

					//�ڶ���ͼƬ
					if(xiangsteady3==30){
						temp.state = 1;
						gameview.incenseList.set(2, temp);
						}
					//������ͼƬ
					if(xiangsteady3==60){
						temp.state = 2;
						gameview.incenseList.set(2, temp);
						}
					//������ͼƬ
					if(xiangsteady3==90){
						temp.state = 3;
						gameview.incenseList.set(2, temp);
						}
					//����ʱ��
					if(xiangsteady3==120){
						gameview.mhandler.sendEmptyMessage(3);
						xiangFlag3=false;
					}
				}
				//��4���������
				if(xiangFlag4){
					ExpelIncense temp = gameview.incenseList.get(3);

					if(temp.level==1)
						xiangsteady4+=2;
						else 
						xiangsteady4+=1;

					//�ڶ���ͼƬ
					if(xiangsteady4==30){
						temp.state = 1;
						gameview.incenseList.set(3, temp);
						}
					//������ͼƬ
					if(xiangsteady4==60){
						temp.state = 2;
						gameview.incenseList.set(3, temp);
						}
					//������ͼƬ
					if(xiangsteady4==90){
						temp.state = 3;
						gameview.incenseList.set(3, temp);
						}
					//����ʱ��
					if(xiangsteady4==120){
						gameview.mhandler.sendEmptyMessage(3);
						xiangFlag4=false;
					}
				}
				//��5���������
				if(xiangFlag5){
					ExpelIncense temp = gameview.incenseList.get(4);

					if(temp.level==1)
						xiangsteady5+=2;
						else 
						xiangsteady5+=1;

					//�ڶ���ͼƬ
					if(xiangsteady5==30){
						temp.state = 1;
						gameview.incenseList.set(4, temp);
						}
					//������ͼƬ
					if(xiangsteady5==60){
						temp.state = 2;
						gameview.incenseList.set(4, temp);
						}
					//������ͼƬ
					if(xiangsteady5==90){
						temp.state = 3;
						gameview.incenseList.set(4, temp);
						}
					//����ʱ��
					if(xiangsteady5==120){
						gameview.mhandler.sendEmptyMessage(5);
						xiangFlag5=false;
					}
				}
				//��6���������
				if(xiangFlag6){
					ExpelIncense temp = gameview.incenseList.get(5);

					if(temp.level==1)
						xiangsteady6+=2;
						else 
						xiangsteady6+=1;

					//�ڶ���ͼƬ
					if(xiangsteady6==30){
						temp.state = 1;
						gameview.incenseList.set(5, temp);
						}
					//������ͼƬ
					if(xiangsteady6==60){
						temp.state = 2;
						gameview.incenseList.set(5, temp);
						}
					//������ͼƬ
					if(xiangsteady6==90){
						temp.state = 3;
						gameview.incenseList.set(5, temp);
						}
					//����ʱ��
					if(xiangsteady6==120){
						gameview.mhandler.sendEmptyMessage(6);
						xiangFlag6=false;
					}
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
