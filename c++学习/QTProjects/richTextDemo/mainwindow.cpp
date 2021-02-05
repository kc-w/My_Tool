#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "mysyntaxhighlighter.h"

#include <QDebug>
#include <QLineEdit>
#include <QDialog>
#include <QPushButton>
#include <QVBoxLayout>
#include <QDragEnterEvent>
#include <QUrl>
#include <QFile>
#include <QTextStream>
#include <QMimeData>
#include <QFileDialog>
#include <QPrinter>
#include <QPrintDialog>
#include <QPrintPreviewDialog>
#include <QFileInfo>


//Qt对富文本的处理分为编辑操作和只读操作两种方式

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    QTextDocument * document = ui ->textEdit ->document();// 获取文档对象
    QTextFrame * rootFrame = document ->rootFrame(); //获取根框架
    QTextFrameFormat format; //创建框架格式
    format. setBorderBrush(Qt::red); //边界颜色
    format. setBorder(3); //边界宽度
    rootFrame->setFrameFormat(format); //框架使用格式


    QTextFrameFormat frameFormat;
    frameFormat.setBackground(Qt::lightGray);//设置背景颜色
    frameFormat.setMargin(10);//设置边距
    frameFormat.setPadding(5);//设置填衬
    frameFormat.setBorder(2);
    frameFormat. setBorderStyle(QTextFrameFormat::BorderStyle_Dotted);// 设置边框样式
    QTextCursor cursor = ui ->textEdit ->textCursor() ;// 获取光标
    cursor. insertFrame(frameFormat); // 在光标处插入框架



    QAction * action_textFrame = new QAction(tr("框架"),this);
    connect(action_textFrame,&QAction::triggered, this, &MainWindow::showTextFrame);
    ui ->toolBar ->addAction(action_textFrame);// 在工具栏添加动作


    QAction * action_textBlock = new QAction(tr("文本块"),this);
    connect(action_textBlock, &QAction::triggered,this, &MainWindow::showTextBlock);
    ui ->toolBar ->addAction(action_textBlock);


    QAction * action_font = new QAction( tr("字休"),this);
    action_font ->setCheckable(true); // 设置动作可以被选中
    connect(action_font, &QAction::toggled, this, &MainWindow::setTextFont);
    ui ->toolBar ->addAction( action_font);



    QAction * action_textTable = new QAction( tr("表格"),this);
    QAction * action_textList = new QAction( tr("列表"),this);
    QAction * action_textImage = new QAction(tr("图片"),this);
    connect(action_textTable, &QAction::triggered, this, &MainWindow::insertTable);
    connect(action_textList, &QAction::triggered, this, &MainWindow::insertList);
    connect(action_textImage, &QAction::triggered ,this, &MainWindow::insertImage);
    ui ->toolBar ->addAction(action_textTable);
    ui ->toolBar ->addAction(action_textList);
    ui ->toolBar ->addAction(action_textImage);



    QAction * action_textFind = new QAction(tr("査找"),this);
    connect(action_textFind, &QAction::triggered, this, &MainWindow::textFind);
    ui ->toolBar->addAction(action_textFind);
    findDialog = new QDialog( this); //创建对话框
    lineEdit = new QLineEdit(findDialog); //创建行编辑器
    QPushButton * btn1 = new QPushButton(findDialog); //创建按钮
    btn1 ->setText(tr("查找上一个"));
    connect(btn1, &QPushButton::clicked, this, &MainWindow::findLast);
    QPushButton * btn2 = new QPushButton(findDialog); //创建按钮
    btn2 ->setText(tr("查找下一个"));
    connect(btn2, &QPushButton::clicked, this, &MainWindow::findNext);
    QVBoxLayout * layout = new QVBoxLayout; //创建垂 直布局 管理器
    layout ->addWidget( lineEdit); // 添加部件
    layout ->addWidget( btn1);
    layout ->addWidget( btn2);
    findDialog->setLayout(layout);//在对话框中使用布局管理器



    QAction *action_print = new QAction(tr("打印"),this);
    QAction *action_printPreview = new QAction(tr("打印预览"),this);
    QAction *action_pdf = new QAction(tr("生成pdf"),this);
    connect(action_print,SIGNAL(triggered()),this,SLOT(doPrint()));
    connect(action_printPreview,SIGNAL(triggered()),this,SLOT(doPrintPreview()));
    connect(action_pdf,SIGNAL(triggered()),this,SLOT(createPdf()));
    ui->toolBar->addAction(action_print);
    ui->toolBar->addAction(action_printPreview);
    ui->toolBar->addAction(action_pdf);


    highlighter = new MySyntaxHighlighter(ui->textEdit->document());
    ui->textEdit->append(tr("<h1><font color=red>使用HTML</font></h1>"));

    
    //设置接收放下事件
    setAcceptDrops(true);





}

// 遍历框架
void MainWindow::showTextFrame()
{
    QTextDocument * document = ui->textEdit ->document();
    QTextFrame * frame = document ->rootFrame();
    QTextFrame::iterator it; //建立 QTextFrame 类的迭代器

    qDebug()<<"=========================================";

    for (it = frame ->begin() ; ! (it.atEnd()) ; ++ it){
        QTextFrame * childFrame = it.currentFrame(); // 获取当前框架的指针
        QTextBlock childBlock = it.currentBlock(); // 获取当前文本块
        if (childFrame){
            qDebug()<<"子框架";
        }else if (childBlock. isValid()){
            qDebug()<<"文本块:"<<childBlock. text();
        }
    }

    qDebug()<<"=========================================";

}

// 遍历文本块
void MainWindow::showTextBlock( )
{
    QTextDocument * document = ui ->textEdit->document();
    QTextBlock block = document ->firstBlock(); // 获取文档的第一个文本块

    qDebug()<<"=========================================";
    for (int i = 0; i < document ->blockCount();i ++ ) {
        qDebug()<<tr("文本块%1文本块首行行号为：%2,长度为：%3内容为：")
        .arg(i).arg(block.firstLineNumber()).arg(block.length())
        <<block.text();
        block = block. next(); // 获取下一个文本块
    }

    qDebug()<<"=========================================";

}

//设置字体格式
void MainWindow::setTextFont(bool checked)
{

    if(checked){ //如果处于选中状态
        QTextCursor cursor = ui->textEdit ->textCursor();
        QTextBlockFormat blockFormat ; //文本块格式
        blockFormat. setAlignment(Qt::AlignCenter); //水平居中
        cursor.insertBlock(blockFormat); //使用文本块格式
        QTextCharFormat charFormat ;//字符格式
        charFormat.setBackground(Qt::lightGray); //背景色
        charFormat.setForeground(Qt::blue); //字体颜色
        charFormat.setFont(QFont(tr("宋体"),12,QFont::Bold,true));//使用宋体,12号,加粗,倾斜
        charFormat.setFontUnderline(true);//使用下划线
        cursor.setCharFormat(charFormat);//使用字符格式
        cursor.insertText(tr("测试字体"));//插入文本
    }else{
        //如果处于非选中状态，可以进行其他操作
    }
}

//插入表格
void MainWindow::insertTable()
{
    QTextCursor cursor = ui->textEdit ->textCursor();
    QTextTableFormat format;//表格格式
    format.setCellSpacing(2);//表格外边白
    format.setCellPadding(10);//表格内边白
    cursor.insertTable(2,2 , format);//插人2行2列表格
}

//插入列表
void MainWindow::insertList()
{
    QTextListFormat format;//列表格式
    format.setStyle(QTextListFormat::ListDecimal);//数字编号
    ui ->textEdit ->textCursor(). insertList(format);
}

//插入图片
void MainWindow::insertImage()
{
    QTextImageFormat format;//图片格式
    format.setWidth(100);
    format.setHeight(100);
    format.setName("../Demo4/ico.png");//图片路径
    ui->textEdit->textCursor().insertImage(format);
}

// 弹出査找文本框
void MainWindow::textFind()
{
    findDialog ->show();
}

// 查找上一个
void MainWindow::findLast()
{
    QString string = lineEdit->text();
    //使用査找函数查找指定字符串，査找方式为向后査找
    bool isfind = ui ->textEdit ->find(string,QTextDocument::FindBackward);
    //如果査找成功,输出字符串所在行和列的编号
    if(isfind){
        qDebug() << tr("行号：%1   列号：%2")
                    .arg(ui ->textEdit ->textCursor(). blockNumber())
                    .arg(ui ->textEdit ->textCursor(). columnNumber());
    }

}

// 查找下一个
void MainWindow::findNext()
{
    QString string = lineEdit->text();
    //使用査找函数查找指定字符串，査找方式为向后査找
    bool isfind = ui ->textEdit ->find(string);
    //如果査找成功,输出字符串所在行和列的编号
    if(isfind){
        qDebug() << tr("行号：%1   列号：%2")
                    .arg(ui ->textEdit ->textCursor(). blockNumber())
                    .arg(ui ->textEdit ->textCursor(). columnNumber());
    }

}

// 监听拖动进入事件
void MainWindow::dragEnterEvent(QDragEnterEvent *event)
{
    // 数据中是否包含URL
    if(event->mimeData()->hasUrls()){
        event->acceptProposedAction();   // 如果是则接收动作
    }else{
        event->ignore();  // 否则忽略该事件
    }

}

//监听放下事件
void MainWindow::dropEvent(QDropEvent *event)
{
    const QMimeData *mimeData = event->mimeData();      // 获取MIME数据
    if(mimeData->hasUrls()){                            // 如果数据中包含URL
        QList<QUrl> urlList = mimeData->urls();         // 获取URL列表
        // 将其中第一个URL表示为本地文件路径
        QString fileName = urlList.at(0).toLocalFile();
        if(!fileName.isEmpty()){                        // 如果文件路径不为空
            QFile file(fileName);     // 建立QFile对象并且以只读方式打开该文件
            if(!file.open(QIODevice::ReadOnly)){
                return;
            }
            QTextStream in(&file);                      // 建立文本流对象
            ui->textEdit->setText(in.readAll());  // 将文件中所有内容读入编辑器
        }
    }
}

// 打印
void MainWindow::doPrint()
{
    QPrinter printer;                         // 创建打印机对象
    QPrintDialog dlg(&printer, this);         // 创建打印对话框
    // 如果编辑器中有选中区域，则打印选中区域
    if (ui->textEdit->textCursor().hasSelection()) {
        dlg.addEnabledOption(QAbstractPrintDialog::PrintSelection);
    }
    if (dlg.exec() == QDialog::Accepted) {    // 如果在对话框中按下了打印按钮
        ui->textEdit->print(&printer);        // 则执行打印操作
    }
}

// 打印预览
void MainWindow::doPrintPreview()
{
    QPrinter printer;
    QPrintPreviewDialog preview(&printer, this);     // 创建打印预览对话框
    // 当要生成预览页面时，发射paintRequested()信号
    connect(&preview, &QPrintPreviewDialog::paintRequested,
            this, &MainWindow::printPreview);
    preview.exec();
}

void MainWindow::printPreview(QPrinter *printer)
{
    ui->textEdit->print(printer);
}

// 生成PDF文件
void MainWindow::createPdf()
{
    QString fileName = QFileDialog::getSaveFileName(this, tr("导出PDF文件"),
                                                    QString(), "*.pdf");
    if (!fileName.isEmpty()) {
        if (QFileInfo(fileName).suffix().isEmpty())
            fileName.append(".pdf");        // 如果文件后缀为空，则默认使用.pdf
        QPrinter printer;
        printer.setOutputFormat(QPrinter::PdfFormat);    // 指定输出格式为pdf
        printer.setOutputFileName(fileName);
        ui->textEdit->print(&printer);
    }
}


MainWindow::~MainWindow()
{
    delete ui;
}

