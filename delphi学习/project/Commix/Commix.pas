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

//��⴮��
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

//�ַ���תʮ������
function StrToHex(const s: string): string;
var
  iCount: Integer;
  value, CH, CL: Byte;
begin
  SetLength(Result, 2 * Length(s));
  for iCount := 1 to Length(s) do
  begin
    value := Byte(s[iCount]);
    //�ֽ�������λ����������õ���λ
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
    //ascii��ת��Ϊ��Ӧ�ַ�
    Result[iCount * 2 - 1] := Char(CH);
    Result[iCount * 2] := Char(CL);
  end;

end;




// �����������Ӧ�¼�
procedure TCommixForm.btnClearShowClick(Sender: TObject);
begin
  mmoShow.Text := '';
end;

// �����������Ӧ�¼�
procedure TCommixForm.btnClearInputClick(Sender: TObject);
begin
  mmoInput.Text := '';
end;

//�򿪡��رմ���
procedure TCommixForm.btnPortClick(Sender: TObject);
begin
  if btnPort.Caption = '��������' then
  begin
    if ComPortAvailable(pchar(cbbPort.Text)) then
    begin
      cmPort.StopComm;
      cmPort.CommName := cbbPort.Text;
      cmPort.BaudRate := StrToInt(cbbBaudRate.Text);
      //���÷�����������λ��ֹͣλ��У��λ
      Commsize;
      cmPort.StartComm;
      btnPort.Caption := '�رմ���';
      cbbPort.Enabled := False;
      btnSend.Enabled := True;
    end
    else
    begin
      ShowMessage(cbbPort.Text + ' ��ʧ�ܣ����鴮�ڣ�');
      cbbPort.Enabled := True;
      btnPort.Caption := '��������';
      btnSend.Enabled := False;
    end;
  end
  else
  begin
    cmPort.StopComm;
    cbbPort.Enabled := True;
    btnPort.Caption := '��������';
    btnSend.Enabled := False;
  end;

end;

//����λ��ֹͣλ��У��λ
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


// ��������
procedure TCommixForm.btnSendClick(Sender: TObject);
var
  sendInput, result: string;
  i: Integer;
begin
  sendInput := mmoInput.Text;
  //���16���Ʊ�ѡ��
  if rbInputHEX.Checked then
  begin
    //��ȡҪ���͵������滻�����пո�
    sendInput := StringReplace(mmoInput.Text, ' ', '', [rfReplaceAll, rfIgnoreCase]);
    for i := 1 to Length(sendInput) do
    begin
      //�ж��Ƿ�Ϊ����
      if System.Odd(i) then
      begin
        //ÿ�ν�ȡ1��16�����ֽ��ַ�ת��Ϊ10����
        result := result + Char(StrToIntDef('$' + Copy(sendInput, i, 2), 0));

      end;
    end;
    //����,�������ֻΪ1�Ļ��Ͳ���
    cmPort.WriteCommData(PChar(result), Length(sendInput) div 2);
  end
  else
  begin
    cmPort.WriteCommData(PChar(sendInput), Length(sendInput));
  end;
end;


//������յ�����
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


