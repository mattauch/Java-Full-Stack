package controller;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Aluno;

public class TelaController {

	private Alert alerta = new Alert(AlertType.WARNING);

	@FXML
	private TableView<Aluno> tabelaAluno;

	@FXML
	private TableColumn<Aluno, Long> colId;

	@FXML
	private TableColumn<Aluno, String> colNome;

	@FXML
	private TableColumn<Aluno, String> colCpf;

	@FXML
	private TableColumn<Aluno, String> colFone;

	@FXML // inje��o de depend�ncia
	private TextField txtId;

	@FXML
	private TextField txtNome;

	@FXML
	private TextField txtCpf;

	@FXML
	private TextField txtFone;

	@FXML
	private Label labelMsg;

	// m�todo carregado automaticamente assim que a tela inicializa!
	@FXML
	void initialize() {
		List<Aluno> alunos = PrincipalFX.daoAluno.listar();
		ObservableList<Aluno> listaAlunos = FXCollections.observableArrayList(alunos);

		colId.setCellValueFactory(new PropertyValueFactory<Aluno, Long>("id"));
		colNome.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
		colCpf.setCellValueFactory(new PropertyValueFactory<Aluno, String>("cpf"));
		colFone.setCellValueFactory(new PropertyValueFactory<Aluno, String>("fone"));

		tabelaAluno.setItems(listaAlunos);

		// tabela gr�fica -> dados -> eventos
		tabelaAluno.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Aluno>() {

			public void changed(ObservableValue<? extends Aluno> valor, Aluno oldAluno, Aluno newAluno) {
				txtId.setText(String.valueOf(newAluno.getId()));
				txtNome.setText(newAluno.getNome());
				txtCpf.setText(newAluno.getCpf());
				txtFone.setText(newAluno.getFone());
			}
		});
	}

	void limpar() {
		txtId.clear();
		txtNome.clear();
		txtCpf.clear();
		txtFone.clear();
	}

	@FXML
	void acaoLimpar(ActionEvent event) {
		limpar();
	}

	@FXML
	void acaoSalvar(ActionEvent event) {
		String strId = txtId.getText();
		String nome = txtNome.getText();
		String cpf = txtCpf.getText();
		String fone = txtFone.getText();

		// Salva o aluno
		if (strId.isEmpty()) {
			Aluno aluno = new Aluno(nome, cpf, fone);
			PrincipalFX.daoAluno.inserir(aluno);
			labelMsg.setText("Dados Salvos!");
			// Atualiza o aluno
			
		} else {
			long id = Long.parseLong(strId);
			Aluno retorno = PrincipalFX.daoAluno.buscar(id);

			if (retorno == null)
				labelMsg.setText("ID do aluno n�o encontrado para atualizar os dados.");
			else {
				Aluno aluno = new Aluno(nome, cpf, fone);
				aluno.setId(id);
				PrincipalFX.daoAluno.atualizar(aluno);
				labelMsg.setText("Dados atualizados com sucesso");
			}
		}
	}

	@FXML
	void acaoBuscar(ActionEvent event) {
		String strId = txtId.getText();
		if (strId.isEmpty()) {
			alerta.setTitle("Aviso");
			alerta.setHeaderText("Voc� esqueceu!");
			alerta.setContentText("Faltou digitar o ID do aluno.");
			alerta.showAndWait();
		} else {
			// converte String para Long
			long id = Long.parseLong(strId);
			Aluno aluno = PrincipalFX.daoAluno.buscar(id);
			if (aluno != null) {
				txtNome.setText(aluno.getNome());
				txtCpf.setText(aluno.getCpf());
				txtFone.setText(aluno.getFone());
			} else {
				alerta.setTitle("Aviso");
				alerta.setHeaderText("Aluno n�o encontrado!");
				alerta.setContentText("O aluno com ID " + strId + "n�o foi encontrado");
				alerta.showAndWait();
			}
		}
	}

	@FXML
	void acaoApagar() {
		String strId = txtId.getText();
		if (strId.isEmpty()) {
			alerta.setTitle("Aviso");
			alerta.setHeaderText("Voc� esqueceu!");
			alerta.setContentText("Faltou digitar o ID do aluno.");
			alerta.showAndWait();
		} else {
			// converte String para Long
			long id = Long.parseLong(strId);
			boolean apagou = PrincipalFX.daoAluno.apagar(id);
			if (apagou) {
				labelMsg.setText("Dados deletados com sucesso.");
				limpar();
			} else {
				alerta.setTitle("Aviso");
				alerta.setHeaderText("Dados n�o deletados!");
				alerta.setContentText("ID n�o encontrado ou falha ao apagar os dados.");
				alerta.showAndWait();
			}
		}
	}

	@FXML
	void acaoImprimir() {
		labelMsg.setText("Imprimindo a tabela");

		// Obt�m impressora padr�o do sistema
		Printer impressora = Printer.getDefaultPrinter();

		// Cria uma p�gina A4, retrato com margem padr�o
		PageLayout pagina = impressora.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);

		// cria uma fila de impress�o baseada na impressora padr�o
		PrinterJob fila = PrinterJob.createPrinterJob();

		// se a fila foi criada tenta enviar um documento
		if (fila != null) {
			System.out.println("Enviando documento para fila de impress�o.");

			// mostra a caixa de dialogo da impress�o
			if (fila.showPrintDialog(null)) {
				tabelaAluno.setLayoutX(0);
				tabelaAluno.setLayoutY(0);

				// envia o documento para o spooler de impress�o
				boolean enviado = fila.printPage(pagina, tabelaAluno);

				// Se o documento foi enviado para a fila com sucesso, ent�o finaliza a fila e
				// retorna true se obteve sucesso
				if (enviado && fila.endJob())
					// endJob() = true n�o significa que o documento saiu na impressora, mas que o
					// servi�o de envio da fila foi finalizado
					labelMsg.setText("Documento enviado com sucesso!");
				else
					labelMsg.setText("N�o foi poss�vel enviar o documento para a impressora.");
			} else
				labelMsg.setText("N�o foi poss�vel mostrar a caixa de impress�o.");

		} else
			labelMsg.setText("N�o foi poss�vel criar fila de impress�o.");
	}
}
