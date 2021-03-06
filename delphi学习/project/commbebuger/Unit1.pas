unit Unit1;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, SPComm, ExtCtrls, ComCtrls, Buttons, TeEngine, Series,
  TeeProcs, Chart, IniFiles, Registry;

const OUT_CARD                        = $10;    {出卡}
const IN_CARD                         = $11;    {进卡}
const READ_IDCARD                     = $12;    {读ID卡内容}
const SCAN_BARCODE                    = $13;    {扫描条形码内容，下位机会自动移至扫描位置}
const OPEN_LIGHT                      = $14;    {开光源灯}
const CLOSE_LIGHT                     = $15;    {关光源灯}
const TEST                            = $16;    {测试试剂卡}
const READ_ADBUF                      = $17;    {读试AD值缓冲区，个数为gSysParam.TestStep}
const RUN_STEP                        = $18;    {带一个参数运行步数,小端模式有符号整型2byte 马达运动n步，正数出板，负数进板}
const READ_IDCARD_STATE               = $19;    {读ID卡状态  0没插ID卡， 1有卡}
const SEND_SYSTEMPARAM                = $1A;    {发送系统参数}
const PRINT_DATA                      = $1B;    {打印数据}
const COM_DATA                        = $1C;    {向COM口传数据}
const READ_HVER                       = $1D;    {取硬件版本号}
const READ_FVER                       = $1E;    {取软件版本号}
const START_AGEING_TEST               = $1F;    {开始老化}
const END_AGEING_TEST                 = $20;    {停止老化}
const GET_DATETIME                    = $21;    {取日期时间}
const SET_DATETIME                    = $22;    {设置日期时间}

const CMDSTR:array[0..$22] of string=('','','','','','','','','','','','','','','','',
'出卡',
'进卡',
'读ID卡',
'扫描条码',
'开光源灯',
'关光源灯',
'测试试剂',
'读试AD值',
'马达运行',
'读ID卡状态',
'发送系统参数',
'打印数据',
'向COM口传数据',
'取硬件版本号',
'取软件版本号',
'开始老化',
'停止老化',
'取日期时间',
'设置日期时间'
);

type
  TSysParam = packed record
     KeyWord   : DWORD;    //关键字                       u32
     EnIDcount : Word;     //使能ID卡检测计数             u16
     Start_pos : Word;     //测量起点位置,相对于光耦位置  u16
     WinWidth  : Word;     //检测窗口宽度                 u16
     TestStep  : Smallint; //检测步数，最大1000           s16
     MoveSpeed : Word;     //进出板马达速度               u16
     TestSpeed : Word;     //测量时马达速度               u16
     MaxStep   : Word;     //轨道长度                     u16
     bak       : Word;     //备份                         u16
 end;



  TForm1 = class(TForm)
    cm1: TComm;
    Panel1: TPanel;
    GroupBox2: TGroupBox;
    Button1: TButton;
    Button2: TButton;
    GroupBox3: TGroupBox;
    Label4: TLabel;
    Label5: TLabel;
    Label6: TLabel;
    Label7: TLabel;
    Label8: TLabel;
    Label9: TLabel;
    Label10: TLabel;
    Edit1: TEdit;
    Edit2: TEdit;
    Edit3: TEdit;
    Edit4: TEdit;
    Edit5: TEdit;
    Edit6: TEdit;
    Edit7: TEdit;
    Button3: TButton;
    Button4: TButton;
    Button5: TButton;
    GroupBox4: TGroupBox;
    SpeedButton1: TSpeedButton;
    DateTimePicker1: TDateTimePicker;
    DateTimePicker2: TDateTimePicker;
    Button6: TButton;
    Button7: TButton;
    GroupBox5: TGroupBox;
    Label12: TLabel;
    Edit8: TEdit;
    Button8: TButton;
    Button9: TButton;
    Button10: TButton;
    Button11: TButton;
    Button12: TButton;
    Button13: TButton;
    Button14: TButton;
    Button15: TButton;
    Button16: TButton;
    Button17: TButton;
    Button18: TButton;
    Button19: TButton;
    Timer1: TTimer;
    Splitter1: TSplitter;
    PageControl1: TPageControl;
    TabSheet1: TTabSheet;
    Memo1: TMemo;
    TabSheet2: TTabSheet;
    Chart1: TChart;
    grp1: TGroupBox;
    lbl1: TLabel;
    lbl2: TLabel;
    Label1: TLabel;
    Label2: TLabel;
    Label3: TLabel;
    CB_COMIDX: TComboBox;
    CB_BaudRate: TComboBox;
    Btn_OpenComm: TButton;
    CB_StopBit: TComboBox;
    CB_Parity: TComboBox;
    Btn_CloseComm: TButton;
    CB_ByteSize: TComboBox;
    procedure Btn_OpenCommClick(Sender: TObject);
    procedure Btn_CloseCommClick(Sender: TObject);
    procedure cm1ReceiveData(Sender: TObject; Buffer: Pointer;BufferLength: Word);
    procedure Button1Click(Sender: TObject);
    procedure Button2Click(Sender: TObject);
    procedure Button3Click(Sender: TObject);
    procedure Button4Click(Sender: TObject);
    procedure Button5Click(Sender: TObject);
    procedure Button6Click(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure Button7Click(Sender: TObject);
    procedure Button8Click(Sender: TObject);
    procedure Button9Click(Sender: TObject);
    procedure Button10Click(Sender: TObject);
    procedure Button11Click(Sender: TObject);
    procedure Button12Click(Sender: TObject);
    procedure Button13Click(Sender: TObject);
    procedure Button14Click(Sender: TObject);
    procedure Button15Click(Sender: TObject);
    procedure Button16Click(Sender: TObject);
    procedure Button17Click(Sender: TObject);
    procedure Button18Click(Sender: TObject);
    procedure Button19Click(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);
    procedure FormCreate(Sender: TObject);
  private
    { Private declarations }
    sysparam:TSysParam;
    RxBuf:array[0..102400-1] of Byte;
    RxCnt:Integer;
    TxBuf:array[0..63] of Byte; //开一个64字节数组存储发包数据

    ini: TiniFile;

    //监听u盘拔插
    procedure WMDeviceChange(var Msg: TMessage);
    message WM_DEVICECHANGE;

  public
    { Public declarations }
    gTimCnt:Integer;
    procedure SendCMD(CMDIdx:Byte; TimeOver:Integer);
    procedure PorcRx();
    procedure MCUProcBack();
  end;

var
  Form1: TForm1;

implementation

{$R *.dfm}

procedure TForm1.Btn_OpenCommClick(Sender: TObject);
begin
  RxCnt:=0;
  FillChar(RxBuf,Length(RxBuf),0);

  cm1.CommName:= CB_COMIDX.Text;
  cm1.BaudRate:= StrToInt(CB_BaudRate.Items[CB_BaudRate.ItemIndex]);
  case CB_ByteSize.ItemIndex of
    0://5
     cm1.ByteSize:=_5;
    1://6
     cm1.ByteSize:=_6;
    2://7
     cm1.ByteSize:=_7;
    3://8
     cm1.ByteSize:=_8;
  end;

  case CB_StopBit.ItemIndex of
    0://5
     cm1.StopBits:=_1;
    1://6
     cm1.StopBits:=_1_5;
    2://7
     cm1.StopBits:=_2;
  end;

  case CB_Parity.ItemIndex of
    0:
    begin
       cm1.Parity:=None;
       cm1.ParityCheck:=False;
    end;
    1:
    begin
       cm1.Parity:=Even;
       cm1.ParityCheck:=True;
    end;
    2:
    begin
       cm1.Parity:=Odd;
       cm1.ParityCheck:=True;
    end;
  end;

  cm1.StartComm;

  if cm1.Handle>0 then
  begin
    Btn_OpenComm.Enabled := False;
    Btn_CloseComm.Enabled := not Btn_OpenComm.Enabled;


    //保存INI
    ini.WriteString('SerialPort','comName',CB_COMIDX.Text);
    ini.WriteInteger('SerialPort','baudRate',CB_BaudRate.ItemIndex);
    ini.WriteInteger('SerialPort','byteSize',CB_ByteSize.ItemIndex);
    ini.WriteInteger('SerialPort','stopBits',CB_StopBit.ItemIndex);
    ini.WriteInteger('SerialPort','parity',CB_Parity.ItemIndex);



  end;
end;

procedure TForm1.Btn_CloseCommClick(Sender: TObject);
begin
  if cm1.Handle>0 then
  begin
    cm1.StopComm;
    Btn_OpenComm.Enabled := True;
    Btn_CloseComm.Enabled := not Btn_OpenComm.Enabled;
  end;
end;



procedure TForm1.cm1ReceiveData(Sender: TObject; Buffer: Pointer;BufferLength: Word);
var
  rbuf:array of Byte;
  i: Integer;
begin
  //设置数组长度rbuf
  SetLength(rbuf,BufferLength);
  //定义一个长度为rbuf的PChar数组存放Buffer
  Move(Buffer^, PChar(rbuf)^, BufferLength);

  Memo1.Lines.Add(FormatDateTime('Rx[yyyymmdd hh:nn:ss.zzz]:',Now));
  Memo1.Lines.BeginUpdate;
  for i:=0 to BufferLength-1 do
  begin
    Memo1.Lines[Memo1.Lines.Count-1]:=Memo1.Lines[Memo1.Lines.Count-1]+' '+IntToHex(rbuf[i],2);

    if RxBuf[i]=$02 then
      RxBuf[RxCnt]:=rbuf[i];
      Inc(RxCnt);

    if rbuf[i]=$03 then
      if RxCnt>=(RxBuf[1]+(RxBuf[2]*256)) then
        PorcRx();

  end;
  Memo1.Lines.EndUpdate;
  GroupBox2.Enabled := True;
end;


//数据包说明
//0x02...开始
//LLLL...包长度低字节
//HHHH...包长度高字节
//CMD ...命令号
//0x03...结尾
procedure TForm1.SendCMD(CMDIdx: Byte; TimeOver:Integer);
var
  //保存发包的长度
  TxCnt:Integer;
  i:Integer;
  s:string;

  Step: Smallint;
begin

  if Btn_OpenComm.Enabled = True then
  begin
    ShowMessage('串口未开启!');
    Exit;
  end;

  GroupBox2.Enabled := False;

  PageControl1.ActivePageIndex := 0;

  //排列命令号
  TxBuf[0]:=$02;    //0x02...开始
  TxBuf[2]:=$00;;   //HHHH...包长度高字节
  TxBuf[3]:=CMDIdx;;//CMD ...命令号
  TxCnt:=4;



  case CMDIdx of
    READ_ADBUF:
    begin
      PageControl1.ActivePageIndex := 1;
    end;
    RUN_STEP://带一个参数运行步数
    begin
      Step :=StrToInt(Edit8.Text);
      Move(Step,TxBuf[TxCnt],SizeOf(Step));
      TxCnt:=TxCnt+SizeOf(Step);
    end;
    COM_DATA://向com口传数据
    begin
      DateTimePicker1.Time:= DateTimePicker2.Time;
      s:= FormatDateTime('yyyymmddhhnnss',DateTimePicker1.DateTime);
      for i:=1 to Length(s) do
      begin
        TxBuf[TxCnt]:= ord(s[i]);
        inc(TxCnt);
      end;
    end;
    SEND_SYSTEMPARAM:
    begin
      sysparam.KeyWord:=100000; //关键字
      sysparam.EnIDcount :=StrToInt(Edit1.Text);//使能ID卡检测计数
      sysparam.Start_pos :=StrToInt(Edit2.Text);//测量起点位置,相对于光耦位置  u16
      sysparam.WinWidth  :=StrToInt(Edit3.Text);//检测窗口宽度                 u16
      sysparam.TestStep  :=StrToInt(Edit4.Text);//检测步数，最大1000           s16
      sysparam.MoveSpeed :=StrToInt(Edit5.Text);//进出板马达速度               u16
      sysparam.TestSpeed :=StrToInt(Edit6.Text);//测量时马达速度               u16
      sysparam.MaxStep   :=StrToInt(Edit7.Text);//轨道长度                     u16
      Move(sysparam,TxBuf[TxCnt],SizeOf(sysparam));
      TxCnt:=TxCnt+SizeOf(sysparam);
    end;
    SET_DATETIME:
    begin
      DateTimePicker1.Time:= DateTimePicker2.Time;
      s:= FormatDateTime('yyyymmddhhnnss',DateTimePicker1.DateTime);
      for i:=1 to Length(s) do
      begin
        TxBuf[TxCnt]:= ord(s[i]);
        inc(TxCnt);
      end;
    end;

  end;

  TxBuf[TxCnt]:=$03;//0x03...结尾
  inc(TxCnt);
  TxBuf[1]:=TxCnt;        //LLLL...包长度低字节

  s:='';
  for i:=0 to TxCnt-1 do
    s:=s+' '+IntToHex(TxBuf[i],2);
  Memo1.Lines.Add('Tx'+FormatDateTime('[yyyymmdd hh:nn:ss.zzz]:',Now)+s);
  cm1.WriteCommData(PChar(@TxBuf),TxCnt);

  //设置超时时间
  gTimCnt:= TimeOver;
  //开启定时器
  Timer1.Enabled:=True;
end;



procedure TForm1.MCUProcBack;
begin
  if RxBuf[4]=$0D then
     Memo1.Lines.Add('                           命令['+CMDSTR[RxBuf[3]]+']执行成功。');
end;

//数据包说明
//0x02...开始
//LLLL...包长度低字节
//HHHH...包长度高字节
//CMD ...命令号
//0x03...结尾
procedure TForm1.PorcRx;
var
  i:Integer;
  s:string;
  Series1: TFastLineSeries;
  LD:Word;
begin
  case RxBuf[3] of
    OUT_CARD://出卡
      MCUProcBack();
    IN_CARD://进卡
      MCUProcBack();

    READ_IDCARD_STATE:
    begin
      if RxBuf[4]=$01 then
        s:='                           命令['+CMDSTR[RxBuf[3]]+']执行成功,识别到ID卡插入。'
      else
      begin
        s:='                           命令['+CMDSTR[RxBuf[3]]+']执行成功,未识别到ID卡插入';
      end;
      Memo1.Lines.Add(s);
    end;
    READ_IDCARD://读ID卡内容
      MCUProcBack();
    SCAN_BARCODE://扫描条形码内容，下位机会自动移至扫描位置
    begin
      if RxBuf[4]=$0D then
        s:='                           命令['+CMDSTR[RxBuf[3]]+']执行成功,未扫到条码。'
      else
      begin
        s:='                           命令['+CMDSTR[RxBuf[3]]+']执行成功,扫到条码:';
        for i:=4 to (RxBuf[1]+(RxBuf[2]*256))-1 do
          s:=s+char(RxBuf[i]);
      end;
      Memo1.Lines.Add(s);
    end;
    OPEN_LIGHT://开光源灯
      MCUProcBack();
    CLOSE_LIGHT://关光源灯
      MCUProcBack();
    TEST://测试试剂卡
    begin
      s:='                           命令['+CMDSTR[RxBuf[3]]+']执行成功';
      Memo1.Lines.Add(s);
    end;

    READ_ADBUF://读试AD值缓冲区，个数为gSysParam.TestStep
    begin
      Series1:=TFastLineSeries.Create(Chart1);
      i:=4;
      while (i<(RxBuf[1]+(RxBuf[2]*256)-1)) do
      begin
        LD:=RxBuf[i]+RxBuf[i+1]*256;
        i:=i+2;
        Series1.Add(LD,'',Series1.SeriesColor);
      end;
      Chart1.AddSeries(Series1);
      MCUProcBack();
    end;
    RUN_STEP://带一个参数运行步数,小端模式有符号整型2byte 马达运动n步，正数出板，负数进板
      MCUProcBack();
    SEND_SYSTEMPARAM://发送系统参数
      MCUProcBack();
    PRINT_DATA://打印数据
      MCUProcBack();
    COM_DATA://向COM口传数据
      MCUProcBack();
    READ_HVER://取硬件版本号
    begin
      s:='                           命令['+CMDSTR[RxBuf[3]]+']执行成功,读到硬件版本:';
      for i:=4 to (RxBuf[1]+(RxBuf[2]*256))-1 do
        s:=s+char(RxBuf[i]);
      Memo1.Lines.Add(s);
    end;

    READ_FVER://取软件版本号
    begin
      s:='                           命令['+CMDSTR[RxBuf[3]]+']执行成功,读到软件版本:';
      for i:=4 to (RxBuf[1]+(RxBuf[2]*256))-1 do
        s:=s+char(RxBuf[i]);
      Memo1.Lines.Add(s);
    end;
    START_AGEING_TEST://开始老化
      MCUProcBack();
    END_AGEING_TEST://停止老化
      MCUProcBack();
    GET_DATETIME://日期时间
    begin
      s:='                           命令['+CMDSTR[RxBuf[3]]+']执行成功,读到日期:';
      for i:=4 to (RxBuf[1]+(RxBuf[2]*256))-1 do
        s:=s+char(RxBuf[i]);
      Memo1.Lines.Add(s);
    end;
    SET_DATETIME://设置日期时间
      MCUProcBack();
  end;
  FillChar(RxBuf,Length(RxBuf),0);
  RxCnt:=0;
  Timer1.Enabled:=False;
  GroupBox2.Enabled:=True;
end;

procedure TForm1.Button2Click(Sender: TObject);
begin
  SendCMD(OUT_CARD,10);
end;

procedure TForm1.Button1Click(Sender: TObject);
begin
  SendCMD(IN_CARD,10);
end;

procedure TForm1.Button3Click(Sender: TObject);
begin
  SendCMD(SEND_SYSTEMPARAM,3);
  //保存INI

  ini.WriteString('SystemData','EnIDcount',Edit1.Text);
  ini.WriteString('SystemData','Start_pos',Edit2.Text);
  ini.WriteString('SystemData','WinWidth',Edit3.Text);
  ini.WriteString('SystemData','TestStep',Edit4.Text);
  ini.WriteString('SystemData','MoveSpeed',Edit5.Text);
  ini.WriteString('SystemData','TestSpeed',Edit6.Text);
  ini.WriteString('SystemData','MaxStep',Edit7.Text);
  


end;

procedure TForm1.Button4Click(Sender: TObject);
begin
  SendCMD(READ_IDCARD,5);
end;

procedure TForm1.Button5Click(Sender: TObject);
begin
  SendCMD(SCAN_BARCODE,10);
end;


procedure TForm1.Button6Click(Sender: TObject);
begin
  SendCMD(SET_DATETIME,3);
end;

//刷新时间
procedure TForm1.SpeedButton1Click(Sender: TObject);
begin
  DateTimePicker1.DateTime:=Now;
  DateTimePicker2.DateTime:=Now;
end;

procedure TForm1.Button7Click(Sender: TObject);
begin
  SendCMD(GET_DATETIME,3);
end;

procedure TForm1.Button8Click(Sender: TObject);
begin
  SendCMD(RUN_STEP,10);
  //保存INI

  ini.WriteString('SystemData','Speed',Edit8.Text);
end;

procedure TForm1.Button9Click(Sender: TObject);
begin
  SendCMD(OPEN_LIGHT,3);
end;

procedure TForm1.Button10Click(Sender: TObject);
begin
  SendCMD(CLOSE_LIGHT,3);
end;

procedure TForm1.Button11Click(Sender: TObject);
begin
  SendCMD(TEST,15);
end;

procedure TForm1.Button12Click(Sender: TObject);
begin
  SendCMD(READ_ADBUF,5);
end;

procedure TForm1.Button13Click(Sender: TObject);
begin
  SendCMD(PRINT_DATA,5);
end;

procedure TForm1.Button14Click(Sender: TObject);
begin
  SendCMD(COM_DATA,5);
end;

procedure TForm1.Button15Click(Sender: TObject);
begin
  SendCMD(READ_HVER,3);
end;

procedure TForm1.Button16Click(Sender: TObject);
begin
  SendCMD(READ_FVER,3);
end;

procedure TForm1.Button17Click(Sender: TObject);
begin
  SendCMD(START_AGEING_TEST,3);
end;

procedure TForm1.Button18Click(Sender: TObject);
begin
  SendCMD(END_AGEING_TEST,15);
end;

procedure TForm1.Button19Click(Sender: TObject);
begin

  SendCMD(READ_IDCARD_STATE,3);
end;



procedure TForm1.Timer1Timer(Sender: TObject);
begin
  //计时器等待时间递减
  gTimCnt:=gTimCnt-1;
  if gTimCnt<=0 then
  begin
    Memo1.Lines.Add('    ['+CMDSTR[TxBuf[3]]+']命令执行超时！');
    GroupBox2.Enabled:=True;
    Timer1.Enabled:=False;
  END;
end;





procedure TForm1.FormCreate(Sender: TObject);

var

  path: string;         {ini 文件路径}
  Section,Key: string;  {分别表示 ini 文件的小节与关键字}
  List: TStrings;

  reg:TRegistry; // 注: 要引用Registry单元
  Namelst:TStrings;
  i:integer;

begin

  //从注册表读取串口
  Namelst:=TStringList.Create;
  reg:=TRegistry.Create;
  reg.RootKey :=HKEY_LOCAL_MACHINE;
  reg.OpenKeyReadOnly('\HARDWARE\DEVICEMAP\SERIALCOMM\');
  reg.GetValueNames(Namelst);

  for i := 0  to Namelst.Count -1 do
    CB_COMIDX.Items.Add(reg.ReadString(Namelst[i]));
  reg.CloseKey;
  reg.Free;
  Namelst.Free;



  //从ini文件中读取配置文件
  path := ChangeFileExt(ParamStr(0),'.ini');
  ini := TiniFile.Create(path);

  CB_COMIDX.Text := ini.ReadString('SerialPort','comName','COM1');

  CB_BaudRate.ItemIndex := ini.ReadInteger('SerialPort','baudRate',0);

  CB_ByteSize.ItemIndex := ini.ReadInteger('SerialPort','byteSize',0);

  CB_StopBit.ItemIndex := ini.ReadInteger('SerialPort','stopBits',0);

  CB_Parity.ItemIndex := ini.ReadInteger('SerialPort','parity',0);

  Edit1.Text := ini.ReadString('SystemData','EnIDcount','1');
  Edit2.Text := ini.ReadString('SystemData','Start_pos','550');
  Edit3.Text := ini.ReadString('SystemData','WinWidth','80');
  Edit4.Text := ini.ReadString('SystemData','TestStep','-1000');
  Edit5.Text := ini.ReadString('SystemData','MoveSpeed','3000');
  Edit6.Text := ini.ReadString('SystemData','TestSpeed','1000');
  Edit7.Text := ini.ReadString('SystemData','MaxStep','1550');
  Edit8.Text := ini.ReadString('SystemData','Speed','');

  //如果在串口列表中找不到之前使用的串口就默认显示第一项
  if CB_COMIDX.Items.IndexOf(trim(CB_COMIDX.Text)) = -1 then
  begin
    CB_COMIDX.ItemIndex := CB_COMIDX.Items.Count-1;
  end;

end;


//监听u盘拔插刷新串口
procedure TForm1.WMDeviceChange(var Msg: TMessage);
var
  reg:TRegistry; // 注: 要引用Registry单元
  Namelst:TStrings;
  i:integer;
begin
  //清除选项
  CB_COMIDX.Items.Clear;

  if cm1.Handle>0 then
  begin
    cm1.StopComm;
    Btn_OpenComm.Enabled := True;
    Btn_CloseComm.Enabled := not Btn_OpenComm.Enabled;
  end;


  //从注册表读取串口
  Namelst:=TStringList.Create;
  reg:=TRegistry.Create;
  reg.RootKey :=HKEY_LOCAL_MACHINE;
  reg.OpenKeyReadOnly('\HARDWARE\DEVICEMAP\SERIALCOMM\');
  reg.GetValueNames(Namelst);

  for i := 0  to Namelst.Count -1 do
    CB_COMIDX.Items.Add(reg.ReadString(Namelst[i]));
  reg.CloseKey;
  reg.Free;
  Namelst.Free;

  CB_COMIDX.ItemIndex := CB_COMIDX.Items.Count-1;

  Case Msg.WParam of
  32768:
  begin
    ShowMessage('U盘插入');
  end;
  32772:
  begin
    ShowMessage('U盘拔出');
  end;
  end;
end;

end.
