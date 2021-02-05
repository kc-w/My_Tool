unit Commix;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, StdCtrls, ExtCtrls, SPComm;

type
  TCommixForm = class(TForm)
    lblPort: TLabel;
    cbbPort: TComboBox;
    lblBaudRate: TLabel;
    cbbBaudRate: TComboBox;
    btnPort: TButton;
    lblDataBits: TLabel;
    cbbDataBits: TComboBox;
    lblParity: TLabel;
    cbbParity: TComboBox;
    lblStopBits: TLabel;
    cbbStopBits: TComboBox;
    pnl1: TPanel;
    btnClearShow: TButton;
    rbInputHEX: TRadioButton;
    rbInputASC: TRadioButton;
    pnl2: TPanel;
    rbShowHEX: TRadioButton;
    rbShowASC: TRadioButton;
    pnl3: TPanel;
    mmoInput: TMemo;
    pnl4: TPanel;
    mmoShow: TMemo;
    btnClearInput: TButton;
    cmPort: TComm;
    btnSend: TButton;
    procedure btnClearShowClick(Sender: TObject);
    procedure btnPortClick(Sender: TObject);
    procedure btnClearInputClick(Sender: TObject);
    procedure btnSendClick(Sender: TObject);
    procedure cmPortReceiveData(Sender: TObject; Buffer: Pointer; BufferLength: Word);
  private
    procedure Commsize;
    { Private declarations }
  public
    { Public declarations }
  end;

var
  CommixForm: TCommixForm;

implementation

{$R *.dfm}

//检测串口
function ComPortAvailable(Port: PChar): boolean;
var
  DeviceName: array[0..80] of Char;
  ComFile: THandle;
begin
  StrPCopy(DeviceName, Port);
  ComFile := CreateFile(DeviceName, GENERIC_READ or GENERIC_WRITE, 0, Nil, OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, 0);
  Result := ComFile <> INVALID_HANDLE_VALUE;
  CloseHandle(ComFile);
end;

//字符串转十六进制
function StrToHex(const s: string): string;
var
  iCount: Integer;
  value, CH, CL: Byte;
begin
  SetLength(Result, 2 * Length(s));
  for iCount := 1 to Length(s) do
  begin
    value := Byte(s[iCount]);
    //字节右移四位进行与运算得到高位
    CH := (value shr 4) and $0F;
    CL := value and $0F;
    if CL < $0A then

      CL := CL + $30

    else
      CL := CL + $37;
    if CH < $0A then
      CH := CH + $30
    else
      CH := CH + $37;
    //ascii码转换为对应字符
    Result[iCount * 2 - 1] := Char(CH);
    Result[iCount * 2] := Char(CL);
  end;

end;




// 清除接收区响应事件
procedure TCommixForm.btnClearShowClick(Sender: TObject);
begin
  mmoShow.Text := '';
end;

// 清除发送区响应事件
procedure TCommixForm.btnClearInputClick(Sender: TObject);
begin
  mmoInput.Text := '';
end;

//打开、关闭串口
procedure TCommixForm.btnPortClick(Sender: TObject);
begin
  if btnPort.Caption = '开启串口' then
  begin
    if ComPortAvailable(pchar(cbbPort.Text)) then
    begin
      cmPort.StopComm;
      cmPort.CommName := cbbPort.Text;
      cmPort.BaudRate := StrToInt(cbbBaudRate.Text);
      //调用方法设置数据位、停止位、校验位
      Commsize;
      cmPort.StartComm;
      btnPort.Caption := '关闭串口';
      cbbPort.Enabled := False;
      btnSend.Enabled := True;
    end
    else
    begin
      ShowMessage(cbbPort.Text + ' 打开失败，请检查串口！');
      cbbPort.Enabled := True;
      btnPort.Caption := '开启串口';
      btnSend.Enabled := False;
    end;
  end
  else
  begin
    cmPort.StopComm;
    cbbPort.Enabled := True;
    btnPort.Caption := '开启串口';
    btnSend.Enabled := False;
  end;

end;

//数据位、停止位、校验位
procedure TCommixForm.Commsize;
begin
  case cbbDataBits.ItemIndex  of

    0:
      cmPort.ByteSize := TByteSize(_5);
    1:
      cmPort.ByteSize := TByteSize(_6);
    2:
      cmPort.ByteSize := TByteSize(_7);
    3:
      cmPort.ByteSize := TByteSize(_8);
  end;

  case cbbParity.ItemIndex of
    0:
      cmPort.Parity := TParity(None);
    1:
      cmPort.Parity := TParity(Odd);
    2:
      cmPort.Parity := TParity(Even);
    3:
      cmPort.Parity := TParity(Mark);
    4:
      cmPort.Parity := TParity(Space);
  end;

  case cbbStopBits.ItemIndex of
    0:
      cmPort.StopBits := TStopBits(_1);
    1:
      cmPort.StopBits := TStopBits(_1_5);
    2:
      cmPort.StopBits := TStopBits(_2);
  end;

end;


// 发送数据
procedure TCommixForm.btnSendClick(Sender: TObject);
var
  sendInput, result: string;
  i: Integer;
begin
  sendInput := mmoInput.Text;
  //如果16进制被选中
  if rbInputHEX.Checked then
  begin
    //获取要发送的数据替换掉所有空格
    sendInput := StringReplace(mmoInput.Text, ' ', '', [rfReplaceAll, rfIgnoreCase]);
    for i := 1 to Length(sendInput) do
    begin
      //判断是否为奇数
      if System.Odd(i) then
      begin
        //每次截取1个16进制字节字符转换为10进制
        result := result + Char(StrToIntDef('$' + Copy(sendInput, i, 2), 0));

      end;
    end;
    //整除,如果长度只为1的话就不发
    cmPort.WriteCommData(PChar(result), Length(sendInput) div 2);
  end
  else
  begin
    cmPort.WriteCommData(PChar(sendInput), Length(sendInput));
  end;
end;


//处理接收的数据
procedure TCommixForm.cmPortReceiveData(Sender: TObject; Buffer: Pointer; BufferLength: Word);
var
  str, rbuf: string;
  i: Integer;
begin
  SetLength(rbuf, BufferLength);
  Move(Buffer^, PChar(rbuf)^, BufferLength);
  if rbShowHEX.Checked then
  begin
    rbuf := StrToHex(rbuf);
    str := '';
    for i := 1 to Length(rbuf) do

    begin
      if System.Odd(i) then
      begin
        str := str + Copy(rbuf, i, 2)+' ';
      end
    end;
    rbuf := str;
  end;
  mmoShow.Font.Color := clBlue;
  mmoShow.Lines.Add(rbuf);
end;





end.


