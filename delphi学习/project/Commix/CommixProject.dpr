program CommixProject;

uses
  Forms,
  Commix in 'Commix.pas' {CommixForm};

{$R *.res}

begin
  Application.Initialize;
  Application.CreateForm(TCommixForm, CommixForm);
  Application.Run;
end.
