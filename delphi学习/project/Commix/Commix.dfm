object CommixForm: TCommixForm
  Left = 1345
  Top = 182
  Anchors = []
  BorderIcons = [biSystemMenu, biMinimize]
  BorderStyle = bsSingle
  ClientHeight = 346
  ClientWidth = 597
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'MS Sans Serif'
  Font.Style = []
  OldCreateOrder = False
  Position = poScreenCenter
  DesignSize = (
    597
    346)
  PixelsPerInch = 96
  TextHeight = 13
  object lblPort: TLabel
    Left = 16
    Top = 16
    Width = 39
    Height = 13
    Anchors = []
    Caption = #20018#21475#21495':'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = []
    ParentFont = False
    Layout = tlCenter
  end
  object lblBaudRate: TLabel
    Left = 168
    Top = 16
    Width = 39
    Height = 13
    Anchors = []
    Caption = #27874#29305#29575':'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = []
    ParentFont = False
    Layout = tlCenter
  end
  object lblDataBits: TLabel
    Left = 16
    Top = 48
    Width = 39
    Height = 13
    Anchors = []
    Caption = #25968#25454#20301':'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = []
    ParentFont = False
    Layout = tlCenter
  end
  object lblParity: TLabel
    Left = 168
    Top = 48
    Width = 39
    Height = 13
    Anchors = []
    Caption = #26657#39564#20301':'
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = []
    ParentFont = False
    Layout = tlCenter
  end
  object lblStopBits: TLabel
    Left = 323
    Top = 48
    Width = 39
    Height = 13
    Anchors = []
    Caption = #20572#27490#20301':'
    Layout = tlCenter
  end
  object cbbPort: TComboBox
    Left = 70
    Top = 8
    Width = 73
    Height = 21
    Anchors = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = []
    ItemHeight = 13
    ItemIndex = 0
    ParentFont = False
    TabOrder = 2
    Text = 'COM1'
    Items.Strings = (
      'COM1'
      'COM2'
      'COM3'
      'COM4'
      'COM5'
      'COM6'
      'COM7'
      'COM8'
      'COM9'
      'COM10'
      'COM11'
      'COM12'
      'COM13'
      'COM14'
      'COM15'
      'COM16'
      'COM17'
      'COM18'
      'COM19'
      'COM20'
      'COM21'
      'COM22'
      'COM23'
      'COM24'
      'COM25'
      'COM26'
      'COM27'
      'COM28'
      'COM29'
      'COM30')
  end
  object cbbBaudRate: TComboBox
    Left = 234
    Top = 8
    Width = 71
    Height = 21
    Anchors = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = []
    ItemHeight = 13
    ItemIndex = 0
    ParentFont = False
    TabOrder = 1
    Text = '300'
    Items.Strings = (
      '300'
      '600'
      '1200'
      '2400'
      '4800'
      '9600'
      '19200'
      '38400'
      '57600'
      '115200')
  end
  object btnPort: TButton
    Left = 500
    Top = 6
    Width = 75
    Height = 21
    Caption = #24320#21551#20018#21475
    TabOrder = 0
    OnClick = btnPortClick
  end
  object cbbDataBits: TComboBox
    Left = 70
    Top = 41
    Width = 73
    Height = 21
    Anchors = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = []
    ItemHeight = 13
    ItemIndex = 0
    ParentFont = False
    TabOrder = 3
    Text = '5'
    Items.Strings = (
      '5'
      '6'
      '7'
      '8')
  end
  object cbbParity: TComboBox
    Left = 234
    Top = 41
    Width = 71
    Height = 21
    Anchors = []
    Font.Charset = DEFAULT_CHARSET
    Font.Color = clWindowText
    Font.Height = -11
    Font.Name = 'MS Sans Serif'
    Font.Style = []
    ItemHeight = 13
    ItemIndex = 0
    ParentFont = False
    TabOrder = 4
    Text = 'None'
    Items.Strings = (
      'None'
      'Odd'
      'Even'
      'Mark'
      'Space')
  end
  object cbbStopBits: TComboBox
    Left = 384
    Top = 41
    Width = 73
    Height = 21
    Anchors = []
    ItemHeight = 13
    ItemIndex = 0
    TabOrder = 5
    Text = '1'
    Items.Strings = (
      '1'
      '1.5'
      '2')
  end
  object pnl1: TPanel
    Left = 0
    Top = 72
    Width = 585
    Height = 49
    TabOrder = 6
    object btnClearShow: TButton
      Left = 500
      Top = 25
      Width = 85
      Height = 23
      Caption = #28165#31354#25509#25910#26694
      TabOrder = 3
      OnClick = btnClearShowClick
    end
    object rbInputHEX: TRadioButton
      Left = 8
      Top = 8
      Width = 105
      Height = 17
      Caption = #21457#36865'16'#36827#21046#25968
      Checked = True
      TabOrder = 2
      TabStop = True
    end
    object rbInputASC: TRadioButton
      Left = 8
      Top = 27
      Width = 97
      Height = 17
      Caption = #21457#36865'ASCII'#30721
      TabOrder = 4
    end
    object pnl2: TPanel
      Left = 120
      Top = 0
      Width = 121
      Height = 49
      TabOrder = 0
      object rbShowHEX: TRadioButton
        Left = 12
        Top = 8
        Width = 101
        Height = 17
        Caption = #25509#25910'16'#36827#21046#25968
        Checked = True
        TabOrder = 0
        TabStop = True
      end
      object rbShowASC: TRadioButton
        Left = 12
        Top = 27
        Width = 101
        Height = 17
        Caption = #25509#25910'ASCII'#30721
        TabOrder = 1
      end
    end
    object btnClearInput: TButton
      Left = 500
      Top = 1
      Width = 85
      Height = 23
      Caption = #28165#31354#36755#20837#26694
      TabOrder = 1
      OnClick = btnClearInputClick
    end
  end
  object pnl3: TPanel
    Left = 0
    Top = 122
    Width = 585
    Height = 71
    TabOrder = 7
    object btnSend: TButton
      Left = 512
      Top = 9
      Width = 66
      Height = 21
      Caption = #21457#36865
      Enabled = False
      TabOrder = 1
      OnClick = btnSendClick
    end
    object mmoInput: TMemo
      Left = 8
      Top = 6
      Width = 497
      Height = 59
      Font.Charset = GB2312_CHARSET
      Font.Color = clGreen
      Font.Height = -15
      Font.Name = #23435#20307
      Font.Style = [fsBold]
      ParentFont = False
      ScrollBars = ssVertical
      TabOrder = 0
    end
  end
  object pnl4: TPanel
    Left = 0
    Top = 196
    Width = 585
    Height = 109
    TabOrder = 8
    object mmoShow: TMemo
      Left = 8
      Top = 4
      Width = 569
      Height = 101
      Font.Charset = ANSI_CHARSET
      Font.Color = clBlack
      Font.Height = -15
      Font.Name = #23435#20307
      Font.Style = [fsBold]
      ParentFont = False
      ScrollBars = ssVertical
      TabOrder = 0
    end
  end
  object cmPort: TComm
    CommName = 'COM2'
    BaudRate = 9600
    ParityCheck = False
    Outx_CtsFlow = False
    Outx_DsrFlow = False
    DtrControl = DtrEnable
    DsrSensitivity = False
    TxContinueOnXoff = False
    Outx_XonXoffFlow = False
    Inx_XonXoffFlow = False
    ReplaceWhenParityError = False
    IgnoreNullChar = False
    RtsControl = RtsEnable
    XonLimit = 500
    XoffLimit = 500
    ByteSize = _8
    Parity = None
    StopBits = _1
    XonChar = #17
    XoffChar = #19
    ReplacedChar = #0
    ReadIntervalTimeout = 100
    ReadTotalTimeoutMultiplier = 0
    ReadTotalTimeoutConstant = 0
    WriteTotalTimeoutMultiplier = 0
    WriteTotalTimeoutConstant = 0
    OnReceiveData = cmPortReceiveData
    Left = 456
    Top = 8
  end
end
