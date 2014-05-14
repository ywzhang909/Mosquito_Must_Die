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
import com.userView.GameViewThree;
import com.userView.GameViewTwo;
/**
 * 
 * @author ɽ����ѧ�Ա��������ء����������ں���
 */
public class TimeThread3 extends Thread {
	int i=1;//�����ؿ�ʱ��
	int time=0;
	int steady1=0;//�������ò�1����ʱ��
	int steady2=0;//�������ò�2����ʱ��
	int steady3=0;//�������ò�3����ʱ��
	int steady4=0;//�������ò�4����ʱ��
	int steady5=0;//�������ò�5����ʱ��
	int steady6=0;//�������ò�6����ʱ��
	
	int xiangsteady1=0;//��������1����ʱ��
	int xiangsteady2=0;//��������2����ʱ��
	int xiangsteady3=0;//��������3����ʱ��
	int xiangsteady4=0;//��������4����ʱ��
	
	boolean ToDate=true;//�ؿ�ʱ��
	boolean flag=true;
	boolean plantFlag1=false;//��ʶ�������ò�1�Ƿ�ʼ��ʱ
	boolean plantFlag2=false;//��ʶ�������ò�2�Ƿ�ʼ��ʱ
	boolean plantFlag3=false;//��ʶ�������ò�3�Ƿ�ʼ��ʱ
	boolean plantFlag4=false;//��ʶ�������ò�4�Ƿ�ʼ��ʱ
	boolean plantFlag5=false;//��ʶ�������ò�5�Ƿ�ʼ��ʱ
	boolean plantFlag6=false;//��ʶ�������ò�6�Ƿ�ʼ��ʱ
	
	boolean xiangFlag1=false;//��ʶ��������1�Ƿ�ʼ��ʱ
	boolean xiangFlag2=false;//��ʶ��������2�Ƿ�ʼ��ʱ
	boolean xiangFlag3=false;//��ʶ��������3�Ƿ�ʼ��ʱ
	boolean xiangFlag4=false;//��ʶ��������4�Ƿ�ʼ��ʱ
	
	
	int frogsteady=0;//�������ܳ���ʱ��Ŀ���
	boolean frogFlagCD=false;//���ܵ���CD
	boolean frogLastTimeFlag=false;//���ܵ��߳���ʱ��Ŀ���
	
	int spraysteady=0;//�������ü�����ʱ��Ŀ���
	boolean sprayFlagCD=false;//���ü�����CD
	boolean sprayLastTimeFlag=false;//���ü����߳���ʱ��Ŀ���
	int bornWenzi = 0;//�ڶ������ӳ���ʱ��

	GameViewThree gameview;
	 public Handler mhandler;
	public TimeThread3(GameViewThree gameview){
		this.gameview=gameview;
		mhandler=new Handler(){
			public void handleMessage(Message msg){
				switch(msg.what){
				//case1,case2��������
				case 1:
					plantFlag1=true;
					break;
				case 2:
					plantFlag2=true;
					break;
				case 3:
					plantFlag3=true;
					break;
				case 4:
					plantFlag4=true;
					break;
				case 5:
					plantFlag5=true;
					break;
				case 6:
					plantFlag6=true;
					break;			

					
				case 7:
					sprayFlagCD=true;
				case 8:
					sprayLastTimeFlag=true;
					break;
					
				case 9:
					xiangFlag1=true;
					break;
				case 10:
					xiangFlag2=true;
					break;
				case 11:
					xiangFlag3=true;
					break;
				case 12:
					xiangFlag4=true;
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
			gameview.Wlist.add(gameview.createWenzi1(1, 1));
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
	if(plantFlag1){
		
		ExpelPlant temp = gameview.expelPlantList.get(0);
		boolean a = false;
		if(temp!=null){
			a=true;
			Log.e("temp",""+a);
		}
			
		else 
			Log.e("temp",""+a);

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
			plantFlag1=false;
		}
	}
	//�ڶ������õ���
	if(plantFlag2){
		
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
			plantFlag2=false;
		}
	}
	//���������õ���
		if(plantFlag3){
			
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
				plantFlag3=false;
			}
		}
		//���ĸ����õ���
		if(plantFlag4){
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
				plantFlag4=false;
			}
		}
		//��������õ���
		if(plantFlag5){
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
				plantFlag5=false;
			}
		}
		//���������õ���
		if(plantFlag6){
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
				plantFlag6=false;
			}
		}
		
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
				gameview.mhandler.sendEmptyMessage(8);
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
						gameview.mhandler.sendEmptyMessage(9);
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
						gameview.mhandler.sendEmptyMessage(10);
						xiangFlag3=false;
					}
				}
				//���ĸ��������
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
						gameview.mhandler.sendEmptyMessage(11);
						xiangFlag4=false;
					}
				}
	//���ü�����ʱ�����
	if(sprayLastTimeFlag){
		killspray spray = gameview.sprayList.get(0);
		spraysteady++;
		
		//�ڶ���ͼƬ
		if(spraysteady==10){
			spray.state = 1;
			gameview.sprayList.set(0, spray);
			}
		//������ͼƬ
		if(spraysteady==20){
			spray.state = 2;
			gameview.sprayList.set(0, spray);
			}
		//������ͼƬ
		if(spraysteady==30){
			sprayLastTimeFlag=false;
			spraysteady=0;
			gameview.mhandler.sendEmptyMessage(7);
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
