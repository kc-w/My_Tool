#include "myitem.h"

#include <QApplication>
#include <QGraphicsScene>
#include <QGraphicsView>
#include <QGraphicsWidget>
#include <QTextEdit>
#include <QPushButton>
#include <QGraphicsProxyWidget>
#include <QGraphicsLinearLayout>
#include <QObject>

#include <QParallelAnimationGroup>
#include <QPropertyAnimation>
#include <QSequentialAnimationGroup>
#include <QStateMachine>
#include <QSignalTransition>
#include <QState>
#include <QFinalState>
#include <QHistoryState>
#include <QMessageBox>
#include <QLabel>


//从Qt4.4开始通过QGraphicsWidget类引入了支持几何和布局的图形项
//图形部件QGraphicsWidget与QWidget很相似,但是不完全相同,不是继承自QPaintDevice,而是QGraphicsItem
//通过它可以实现一个拥有事件、信号和槽、大小提示和策略的完整的部件,还可以使用QGraphicsLinearLayout和QGraphicsGridLayout来实现部件的布局
int main(int argc, char* argv[ ])
{
    QApplication app(argc, argv);
    QGraphicsScene scene;
    // 创建部件，并关联它们的信号和槽
    QTextEdit *edit = new QTextEdit;
    QPushButton *button = new QPushButton("清除内容");
    //(触发对象,触发函数,目标对象,目标函数)
    QObject::connect(button, SIGNAL(clicked()), edit, SLOT(clear()));
    // 将部件添加到场景中
    QGraphicsWidget *textEdit = scene.addWidget(edit);
    QGraphicsWidget *pushButton = scene.addWidget(button);
    // 将部件添加到布局管理器中
    QGraphicsLinearLayout *layout = new QGraphicsLinearLayout;
    layout->addItem(textEdit);
    layout->addItem(pushButton);
    //创建图形部件,设置其为一个顶层窗口,然后在其上应用布局
    QGraphicsWidget *form = new QGraphicsWidget;
    form->setWindowFlags(Qt::Window);
    form->setWindowTitle("窗口");
    form->setLayout(layout);
    //将图形部件进行扭曲,然后添加到场景中
    form->setTransform(QTransform().scale(1.5, 1.5), true);
    scene.addItem(form);
    QGraphicsView view(&scene);
    view.show();



    //========================动画==================================

    QPushButton button1("动画1");
    QPushButton button2("动画2");
    QPushButton button3("动画3");
    //显示按钮
    button1.show();
    button2.show();
    button3.show();
    //为按钮部件的geometry属性创建了动画
    QPropertyAnimation animation1(&button1, "geometry");
    QPropertyAnimation animation2(&button2, "geometry");
    QPropertyAnimation animation3(&button3, "geometry");
    //数指定了动画的持续时间为10秒
    animation1.setDuration(10000);
    animation2.setDuration(10000);
    animation3.setDuration(2000);


    //设置动画的开始坐标
    animation1.setStartValue(QRect(300, 0, 100, 50));
    //设置动画的结束坐标
    animation1.setEndValue(QRect(800, 500, 400, 200));


    //(设置时间分配值在0-1之间,坐标及图形大小)
    animation2.setKeyValueAt(0, QRect(20, 50, 120, 30));
    animation2.setKeyValueAt(0.8, QRect(500, 500, 200, 60));
    animation2.setKeyValueAt(1, QRect(20, 50, 120, 30));


    animation3.setStartValue(QRect(500, 0, 120, 30));
    animation3.setEndValue(QRect(900, 900, 120, 30));
    //使用缓和曲线
    animation3.setEasingCurve(QEasingCurve::OutBounce);


    //动画重复次数
    animation1.setLoopCount(10);
    animation3.setLoopCount(10);
    //开启动画
    animation1.start();
    animation2.start();
    animation3.start();
    //暂停动画
    animation1.pause();
    //恢复暂停状态
    animation1.resume();
//    //停止动画
//    animation.stop();




    //========================串行动画组===========================
    QPushButton button4("串行动画组1");
    button4.show();
    // 按钮部件的动画1先进行播放
    QPropertyAnimation *animation4 = new QPropertyAnimation(&button4, "geometry");
    animation4->setDuration(9000);
    animation4->setStartValue(QRect(900, 0, 120, 30));
    animation4->setEndValue(QRect(20, 300, 120, 30));
    animation4->setEasingCurve(QEasingCurve::OutBounce);
    // 按钮部件的动画2,在动画1完成播放后进行播放
    QPropertyAnimation *animation5 = new QPropertyAnimation(&button4, "geometry");
    animation5->setDuration(8000);
    animation5->setStartValue(QRect(800, 300, 120, 30));
    animation5->setEndValue(QRect(250, 300, 200, 60));
    // 串行动画组
    QSequentialAnimationGroup group;
    group.addAnimation(animation4);
    group.addAnimation(animation5);
    group.start();




    //===========================并行动画组========================
    QPushButton button6("并行动画组1");
    button6.show();
    QPushButton button7("并行动画组2");
    button7.show();
    // 按钮部件1的动画
    QPropertyAnimation *animation6 = new QPropertyAnimation(&button6, "geometry");
    animation6->setDuration(12000);
    animation6->setStartValue(QRect(250, 0, 120, 30));
    animation6->setEndValue(QRect(250, 300, 120, 30));
    animation6->setEasingCurve(QEasingCurve::OutBounce);
    // 按钮部件2的动画
    QPropertyAnimation *animation7 = new QPropertyAnimation(&button7, "geometry");
    animation7->setDuration(12000);
    animation7->setStartValue(QRect(400, 300, 120, 30));
    animation7->setEndValue(QRect(400, 300, 200, 60));
    // 并行动画组
    QParallelAnimationGroup group1;
    group1.addAnimation(animation6);
    group1.addAnimation(animation7);
    group1.start();


    //==============在图形视图框架中使用动画=================

    QGraphicsScene scene1;
    scene.setSceneRect(-200, -150, 400, 300);
    MyItem *item = new MyItem;
    scene1.addItem(item);
    QGraphicsView view1;
    view1.setScene(&scene1);
    view1.show();
    // 为图形项的rotation属性创建动画
    QPropertyAnimation *animation = new QPropertyAnimation(item, "rotation");
    animation->setDuration(5000);
    animation->setStartValue(0);
    animation->setEndValue(360);
    //当动画执行结束后便会自动销毁该动画指针对象
    animation->start(QAbstractAnimation::DeleteWhenStopped);



    //=================状态机框架================================
    QPushButton button8("状态机框架");
    // 创建状态机和三个状态，并将三个状态添加到状态机中
    QStateMachine machine;
    QState *s1 = new QState(&machine);
    QState *s2 = new QState(&machine);
    QState *s3 = new QState(&machine);
    // 为按钮部件的geometry属性分配一个值，当进入该状态时会设置该值
    s1->assignProperty(&button8, "geometry", QRect(100, 100, 120, 50));
    s2->assignProperty(&button8, "geometry", QRect(100, 200, 120, 50));
    s3->assignProperty(&button8, "geometry", QRect(100, 300, 120, 50));
    // 使用按钮部件的单击信号来完成三个状态的切换,第一次点击会由s1变成s2,第二次点击会由s2变成s3,第三次点击会由s3变成s1
    QSignalTransition *transition1 = s1->addTransition(&button8,&QPushButton::clicked, s2);
    QSignalTransition *transition2 = s2->addTransition(&button8,&QPushButton::clicked, s3);
    QSignalTransition *transition3 = s3->addTransition(&button8,&QPushButton::clicked, s1);
    QPropertyAnimation *animation8 = new QPropertyAnimation(&button8, "geometry");
    transition1->addAnimation(animation8);
    transition2->addAnimation(animation8);
    transition3->addAnimation(animation8);

    //当状态机进入s3状态时按钮全屏
    QObject::connect(s3, &QState::entered, &button8, &QPushButton::showFullScreen);
    //当状态机退出s3状态时按钮最大化
    QObject::connect(s3, &QState::exited, &button8, &QPushButton::showMaximized);

    // 设置状态机的初始状态并启动状态机
    machine.setInitialState(s1);
    //如果想对一个属性指定一个动画,从而使所有的切换都默认使用这个动画,那么可以在状态机中使用默认动画
    machine.addDefaultAnimation(animation8);
    machine.start();
    button8.show();


    //======================状态机分组===============================
    QPushButton button9("状态机分组");
    QPushButton quitButton("退出");
    //创建状态机
    QStateMachine machine1;
    //创建父状态
    QState *s = new QState(&machine1);
    //创建并加入3个子状态
    QState *s11 = new QState(s);
    QState *s12 = new QState(s);
    QState *s13 = new QState(s);
    //将第一个子状态设置为父状态的默认状态
    s->setInitialState(s11);

    // 为按钮部件的geometry属性分配一个值，当进入该状态时会设置该值
    s11->assignProperty(&button9, "geometry", QRect(100, 300, 120, 50));
    s12->assignProperty(&button9, "geometry", QRect(300, 400, 120, 50));
    s13->assignProperty(&button9, "geometry", QRect(200, 500, 120, 50));
    //指定信号切换的过程
    QSignalTransition *transition4 = s11->addTransition(&button9,&QPushButton::clicked, s12);
    QSignalTransition *transition5 = s12->addTransition(&button9,&QPushButton::clicked, s13);
    QSignalTransition *transition6 = s13->addTransition(&button9,&QPushButton::clicked, s11);

    //按下退出按钮时会切换到s12状态,为了可以退出应用程序,需要将状态机的finished()信号关联到quit()槽上
    //子状态也可以覆盖继承的切换，比如要使在s12状态时忽略退出按钮,在s12状态下点击退出按钮仍然是s12状态
    s12->addTransition(&quitButton, &QPushButton::clicked, s12);

    //为按钮创建动画
    QPropertyAnimation *animation9 = new QPropertyAnimation(&button9, "geometry");
    //将信号切换加入动画
    transition4->addAnimation(animation9);
    transition5->addAnimation(animation9);
    transition6->addAnimation(animation9);

    //设置最终状态,并加入到状态机与s并列
    QFinalState *ss = new QFinalState(&machine1);
    //在父状态下的任意子状态单击退出按钮切换到退出状态
    s->addTransition(&quitButton, &QPushButton::clicked, ss);
    QObject::connect(&machine1, &QStateMachine::finished, qApp, &QApplication::quit);


    QPushButton interruptButton("中断");
    interruptButton.move(1000, 50);
    interruptButton.show();
    //创建历史状态
    QHistoryState *s1h = new QHistoryState(s);
    //将ss3加入状态机
    QState *sss = new QState(&machine1);
    QMessageBox mbox;
    mbox.addButton(QMessageBox::Ok);
    mbox.setText("中断!");
    mbox.setIcon(QMessageBox::Information);
    //切换到sss时弹出弹窗(原状态切换)
    QObject::connect(sss, &QState::entered, &mbox, &QMessageBox::exec);
    //记录ss3状态
    sss->addTransition(s1h);
    //在ss1下点击中断按钮回到ss3状态
    s1->addTransition(&interruptButton, &QPushButton::clicked, sss);


    //将s状态设置为默认状态
    machine1.setInitialState(s);
    machine1.start();
    button9.show();
    quitButton.move(500, 500);
    quitButton.show();


    //====================用并行状态来避免组合爆炸===========================

    QPushButton buttonA("ss1下的子状态替换");
    QPushButton buttonB("ss2下的子状态替换");
    QLabel label;
    QLabel label1(&label);
    QLabel label2(&label);

    QStateMachine machine2;
    //创建QState对象时使用QState::ParallelStates作为参数来创建一个并行状态组
    QState *ss1 = new QState(QState::ParallelStates);
    //ss1状态下设置子状态ss11再在ss11下设置子状态ss111和ss112
    QState *ss11 = new QState(ss1);
    QState *ss111 = new QState(ss11);
    QState *ss112 = new QState(ss11);
    //设置ss11的默认子状态为ss111
    ss11->setInitialState(ss111);
    //设置该状态下显示的对应字符
    ss111->assignProperty(&label1, "text", "ss1-ss11-ss111");
    ss112->assignProperty(&label1, "text", "ss1-ss11-ss112");
    //设置点击按钮进行状态切换
    ss111->addTransition(&buttonA, &QPushButton::clicked, ss112);
    ss112->addTransition(&buttonA, &QPushButton::clicked, ss111);
    //ss1状态下设置子状态ss12再在ss12下设置子状态ss121和ss122
    QState *ss12 = new QState(ss1);
    QState *ss121 = new QState(ss12);
    QState *ss122 = new QState(ss12);
    ss12->setInitialState(ss122);
    ss121->assignProperty(&label2, "text", "ss1-ss12-ss121");
    ss122->assignProperty(&label2, "text", "ss1-ss12-ss122");
    ss121->addTransition(&buttonB, &QPushButton::clicked, ss122);
    ss122->addTransition(&buttonB, &QPushButton::clicked, ss121);


    ss1 ->addTransition(ss1, &QState::finished, ss1);

    //加入状态
    machine2.addState(ss1);
    machine2.setInitialState(ss1);
    machine2.start();
    buttonA.move(300, 300);
    buttonA.show();
    buttonB.move(500, 300);
    buttonB.show();
    label1.resize(100, 20);
    label2.resize(100, 20);
    label2.move(0, 20);
    label.move(180, 120);
    label.resize(100, 50);
    label.show();




    return app.exec();
}
