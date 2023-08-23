package br.ufc.poo.conta;

import java.io.Serializable;

import br.ufc.poo.conta.excecao.SIException;
import br.ufc.poo.conta.excecao.TNIException;
import br.ufc.poo.conta.excecao.VNException;
import java.io.*;

public class ContaImposto extends ContaAbstrata implements Serializable{
  private double taxa = 0.001;
  private static final long serialVersionUID = 1L;

  public ContaImposto(String numero) {
    super(numero);
  }

  public void debitar(double valor) throws SIException, VNException {
    if (valor < 0.0) {
      throw new VNException(this.numero, valor);
    }

    double valorAcrescidoTaxa = (valor + (valor * this.taxa));
    if (valorAcrescidoTaxa > this.saldo) {
      throw new SIException(this.numero, this.saldo);
    }

    this.saldo = this.saldo - valorAcrescidoTaxa;
  }

  public double getTaxa() {
    return this.taxa;
  }

  public void setTaxa(double taxa) throws TNIException {
    if (taxa < 0.0) {
      throw new TNIException(this.numero, taxa);
    }
    this.taxa = taxa;

  }
}