package com.softgraf.farmacia.bean;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import com.softgraf.farmacia.repositorio.RepositorioClientes;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/*
* Passos para gerar o relatório com JasperReport
* 
* 1. Criar o arquivo jasper report xml -> .jrxml
*  A melhor forma de fazer isso é com o jaspersoft Studio (plugin para eclipse)
*  https://marketplace.eclipse.org/content/jaspersoft-studio
*   
* 2. Compilar o arquivo .jrxml com JasperCompilerManager gerando um objeto JasperReport (relatório)
*
* 3. Preencher os dados do relatório com JasperFillManager gerando um objeto JasperPrint
*
* 4. exportar o JasperPrint para outro formato, como PDF, usando JasperExportManager
*  
* Resumindo: jrxml -> JasperCompilerManager -> JasperReport -> JasperFillManager -> JasperPrint -> JasperExportManager -> pdf
* 
*/

// Managed Bean CDI
@Named
@javax.faces.view.ViewScoped
public class RelatorioClienteMB implements Serializable {

	private static final long serialVersionUID = 1715868213909388355L;
	// softgraf.com/cursojava/farmaciaEE/relatorios/clientes.jrxml
	// jrxml é um arquivo externo ao projeto (baixar no pacote relatorios do projeto)
	String jrxml = "D:/JAVA - Amanda/FarmaciaEE/src/main/java/relatorios/clientes.jrxml";

	@Inject
	RepositorioClientes repositorioClientes;

	public void gerarPDF() {

		try {
			// obtém o contexto atual do JSF
			FacesContext contexto = FacesContext.getCurrentInstance();

			// compila o jrxml, criando o relatório
			JasperReport jasperReport = JasperCompileManager.compileReport(jrxml);

			// cria um data source contendo uma coleção de clientes
			JRBeanCollectionDataSource collectionDataSource = new JRBeanCollectionDataSource(repositorioClientes.todos());

			// preenche os dados do relatório gerando o arquivo de impressão
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, collectionDataSource);

			HttpServletResponse resp = (HttpServletResponse) contexto.getExternalContext().getResponse();
			resp.setHeader("Content-Disposition", "attachment; filename=clientes.pdf");
			resp.setContentType("application/pdf");

			// exporta o arquivo de impressão para o formato pdf e envia para o fluxo de saida
			JasperExportManager.exportReportToPdfStream(jasperPrint, resp.getOutputStream());
			contexto.responseComplete();

		} catch (JRException | IOException e) {
			e.printStackTrace();
		}
	}

}
