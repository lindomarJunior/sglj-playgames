package br.com.cliente_crud.bean;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "arquivoBean")
@ViewScoped
public class ArquivoBean {
	
	public void uploadArquivo(FileUploadEvent event){
		
		
		try {
			FileOutputStream fos = new FileOutputStream("c:\\Arquivos\\"+event.getFile().getFileName());
			fos.write(event.getFile().getContents());
			fos.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		FacesMessage msg = new FacesMessage("Succesful", "O arquivo "+event.getFile().getFileName()+" foi enviado com sucesso.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
}
