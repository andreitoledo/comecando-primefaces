package com.andreitoledo.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.andreitoledo.erp.model.Empresa;
import com.andreitoledo.erp.model.TipoEmpresa;
import com.andreitoledo.erp.repository.Empresas;
import com.andreitoledo.erp.service.CadastroEmpresaService;
import com.andreitoledo.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Empresas empresas;

	@Inject
	private CadastroEmpresaService cadastroEmpresa;

	@Inject
	private FacesMessages messages;

	private List<Empresa> todasEmpresas;
	private Empresa empresaEdicao = new Empresa();
	private Empresa empresaSelecionada;

	public void prepararNovoCadastro() {
		empresaEdicao = new Empresa();
	}

	public void salvar() {
		cadastroEmpresa.salvar(empresaEdicao);
		consultar();

		messages.info("Empresa salva com sucesso !");

		RequestContext.getCurrentInstance().update(
				Arrays.asList("frm:msgs", "frm:empresas-table"));

	}
	
	public void excluir(){
		cadastroEmpresa.excluir(empresaSelecionada);
		empresaSelecionada = null;
		
		consultar();
		
		messages.info("Empresa excluída com sucesso !");
		
	}

	public void consultar() {
		todasEmpresas = empresas.todas();
	}

	public List<Empresa> getTodasEmpresas() {
		return todasEmpresas;
	}

	public TipoEmpresa[] getTiposEmpresas() {
		return TipoEmpresa.values();

	}

	public Empresa getEmpresaEdicao() {
		return empresaEdicao;
	}

	public void setEmpresaEdicao(Empresa empresaEdicao) {
		this.empresaEdicao = empresaEdicao;
	}

	public Empresa getEmpresaSelecionada() {
		return empresaSelecionada;
	}

	public void setEmpresaSelecionada(Empresa empresaSelecionada) {
		this.empresaSelecionada = empresaSelecionada;
	}
	
	

}
