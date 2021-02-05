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
  hThread1,hThread2: THandle;{�߳̾��}

implementation

{$R *.dfm}


//ԭʼCreateThread����������쳣�����������ĬĬ���˳�
function TForm2.ThreadProc1(param: Pointer): Integer; stdcall;

begin
  showmessage('ThreadProc1ִ��');
  while True do
    begin
      i1 := i1+1;
      mmo1.Lines.Add(IntToStr(i1));
      Sleep(1000);
    end;
end;

//BeginThread�ڲ���Ȼ�ǵ��õ�CreateThread��������BeginThread�Ļص�����û��stcall���
//BeginThread�������߳��쳣�Ļ����ᱻĬ�ϵ��쳣������򲶻��˳�ʱ�е�����ʾ
function TForm2.ThreadProc2(param: Pointer): Integer;

begin
  showmessage('ThreadProc2ִ��');
  while True do
    begin
      i2 := i2+1;
      mmo2.Lines.Add(IntToStr(i2));
      Sleep(1000);
    end;
end;


//�߳�һ����
procedure TForm2.btn1Click(Sender: TObject);
begin
  hThread1 := CreateThread(nil, 0, @TForm2.ThreadProc1, nil, CREATE_SUSPENDED, threadId1);
  mmo1.Lines.Add('�߳�һid:'+IntToStr(threadId1)+'  �߳�һ���:'+IntToStr(hThread1));
  mmo1.Lines.Add('���߳�id:'+IntToStr(GetCurrentThreadId));
end;

//�̶߳�����
procedure TForm2.btn2Click(Sender: TObject);
begin
  hThread2 := BeginThread(nil, 0, @TForm2.ThreadProc2, nil, CREATE_SUSPENDED, threadId2);
  mmo2.Lines.Add('�̶߳�id:'+IntToStr(threadId2)+'  �̶߳����:'+IntToStr(hThread2));
  mmo2.Lines.Add('���߳�id:'+IntToStr(GetCurrentThreadId));
end;

//�߳�һ����
procedure TForm2.btn3Click(Sender: TObject);
begin
  SuspendThread(hThread1);
end;

//�̶߳�����
procedure TForm2.btn4Click(Sender: TObject);
begin
  SuspendThread(hThread2);
end;

//�߳�һ����
procedure TForm2.btn5Click(Sender: TObject);
begin
  ResumeThread(hThread1);
end;

//�̶߳�����
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
