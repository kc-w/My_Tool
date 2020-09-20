unit Unit2;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.StdCtrls;

type
  TForm2 = class(TForm)
    btn1: TButton;
    btn2: TButton;
    mmo1: TMemo;
    mmo2: TMemo;
    btn3: TButton;
    btn4: TButton;
    btn5: TButton;
    btn6: TButton;
    procedure btn1Click(Sender: TObject);
    procedure btn2Click(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure btn3Click(Sender: TObject);
    procedure btn4Click(Sender: TObject);
    procedure btn5Click(Sender: TObject);
    procedure btn6Click(Sender: TObject);
  private
    { Private declarations }
//    function ThreadProc1(param: Pointer): Integer; stdcall;
//    function ThreadProc2(param: Pointer): Integer;
  public
    function ThreadProc1(param: Pointer): Integer; stdcall;
    function ThreadProc2(param: Pointer): Integer;
    { Public declarations }
  end;

var
  Form2: TForm2;
  threadId1,threadId2: TThreadID;
  i1,i2: Integer;
  hThread1,hThread2: THandle;{线程句柄}

implementation

{$R *.dfm}


//原始CreateThread中如果发生异常，整个程序就默默地退出
function TForm2.ThreadProc1(param: Pointer): Integer; stdcall;

begin
  showmessage('ThreadProc1执行');
  while True do
    begin
      i1 := i1+1;
      mmo1.Lines.Add(IntToStr(i1));
      Sleep(1000);
    end;
end;

//BeginThread内部依然是调用的CreateThread。区别是BeginThread的回调函数没有stcall标记
//BeginThread创建的线程异常的话，会被默认的异常处理程序捕获，退出时有弹窗提示
function TForm2.ThreadProc2(param: Pointer): Integer;

begin
  showmessage('ThreadProc2执行');
  while True do
    begin
      i2 := i2+1;
      mmo2.Lines.Add(IntToStr(i2));
      Sleep(1000);
    end;
end;


//线程一创建
procedure TForm2.btn1Click(Sender: TObject);
begin
  hThread1 := CreateThread(nil, 0, @TForm2.ThreadProc1, nil, CREATE_SUSPENDED, threadId1);
  mmo1.Lines.Add('线程一id:'+IntToStr(threadId1)+'  线程一句柄:'+IntToStr(hThread1));
  mmo1.Lines.Add('主线程id:'+IntToStr(GetCurrentThreadId));
end;

//线程二创建
procedure TForm2.btn2Click(Sender: TObject);
begin
  hThread2 := BeginThread(nil, 0, @TForm2.ThreadProc2, nil, CREATE_SUSPENDED, threadId2);
  mmo2.Lines.Add('线程二id:'+IntToStr(threadId2)+'  线程二句柄:'+IntToStr(hThread2));
  mmo2.Lines.Add('主线程id:'+IntToStr(GetCurrentThreadId));
end;

//线程一挂起
procedure TForm2.btn3Click(Sender: TObject);
begin
  SuspendThread(hThread1);
end;

//线程二挂起
procedure TForm2.btn4Click(Sender: TObject);
begin
  SuspendThread(hThread2);
end;

//线程一唤醒
procedure TForm2.btn5Click(Sender: TObject);
begin
  ResumeThread(hThread1);
end;

//线程二唤醒
procedure TForm2.btn6Click(Sender: TObject);
begin
  ResumeThread(hThread2);
end;

procedure TForm2.FormCreate(Sender: TObject);
begin
mmo1.text :='';
mmo2.text :='';
end;

end.
